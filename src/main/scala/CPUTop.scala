import chisel3._
import chisel3.util._

class CPUTop extends Module {
  val io = IO(new Bundle {
    val done = Output(Bool())
    val run = Input(Bool())
    // This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerDataMemEnable = Input(Bool())
    val testerDataMemAddress = Input(UInt(16.W))
    val testerDataMemDataRead = Output(UInt(32.W))
    val testerDataMemWriteEnable = Input(Bool())
    val testerDataMemDataWrite = Input(UInt(32.W))
    // This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerProgMemEnable = Input(Bool())
    val testerProgMemAddress = Input(UInt(16.W))
    val testerProgMemDataRead = Output(UInt(32.W))
    val testerProgMemWriteEnable = Input(Bool())
    val testerProgMemDataWrite = Input(UInt(32.W))
  })

  // Creating components
  val programCounter = Module(new ProgramCounter())
  val dataMemory = Module(new DataMemory())
  val programMemory = Module(new ProgramMemory())
  val registerFile = Module(new RegisterFile())
  val controlUnit = Module(new ControlUnit())
  val alu = Module(new ALU())

  // Connecting the modules
  programCounter.io.run := io.run
  programMemory.io.address := programCounter.io.programCounter

  // Fetch instruction from program memory
  val instruction = programMemory.io.instructionRead

  // Decode instruction
  controlUnit.io.opcode := instruction(31, 26)

  // Register file connections
  registerFile.io.aSel := instruction(25, 21)
  registerFile.io.bSel := instruction(20, 16)
  registerFile.io.writeSel := instruction(15, 11)
  registerFile.io.writeEnable := controlUnit.io.regWrite

  // Introduce a register to break the combinational loop
  val dataReadReg = RegNext(dataMemory.io.dataRead)
  registerFile.io.writeData := Mux(controlUnit.io.memRead, dataReadReg, alu.io.output)

  // ALU connections
  alu.io.input1 := registerFile.io.a
  alu.io.input2 := Mux(controlUnit.io.aluSrc, instruction(15, 0).asUInt, registerFile.io.b)
  alu.io.sel := controlUnit.io.aluOp

  // Data memory connections
  dataMemory.io.address := alu.io.output
  dataMemory.io.dataWrite := registerFile.io.b
  dataMemory.io.writeEnable := controlUnit.io.memWrite

  // Program counter connections
  programCounter.io.stop := controlUnit.io.end
  programCounter.io.jump := controlUnit.io.jump
  programCounter.io.programCounterJump := instruction(15, 0).asUInt

  // Done signal
  io.done := controlUnit.io.end

  // This signals are used by the tester for loading the program to the program memory, do not touch
  programMemory.io.testerAddress := io.testerProgMemAddress
  io.testerProgMemDataRead := programMemory.io.testerDataRead
  programMemory.io.testerDataWrite := io.testerProgMemDataWrite
  programMemory.io.testerEnable := io.testerProgMemEnable
  programMemory.io.testerWriteEnable := io.testerProgMemWriteEnable

  // This signals are used by the tester for loading and dumping the data memory content, do not touch
  dataMemory.io.testerAddress := io.testerDataMemAddress
  io.testerDataMemDataRead := dataMemory.io.testerDataRead
  dataMemory.io.testerDataWrite := io.testerDataMemDataWrite
  dataMemory.io.testerEnable := io.testerDataMemEnable
  dataMemory.io.testerWriteEnable := io.testerDataMemWriteEnable
}
