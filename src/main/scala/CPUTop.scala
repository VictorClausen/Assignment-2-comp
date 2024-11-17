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
  io.done := controlUnit.io.done
  programCounter.io.stop := controlUnit.io.done

  val jumpAnd = (controlUnit.io.jumpTo & alu.io.output(0))

  val jumpResult =
    Mux(controlUnit.io.ImJump, controlUnit.io.jumpTo, jumpAnd)
  programCounter.io.jump := jumpResult

  alu.io.sel := controlUnit.io.AluOp
  alu.io.input1 := registerFile.io.a

  val ImOpMux = Mux(
    controlUnit.io.ImOp,
    programMemory.io.instructionRead(15, 0),
    registerFile.io.b
  )
  alu.io.input2 := ImOpMux

  val immediateLoadMux = Mux(
    controlUnit.io.ImLoad,
    programMemory.io.instructionRead(15, 0),
    alu.io.output
  )
  val MemLoadMux = Mux(
    controlUnit.io.MemLoad,
    dataMemory.io.dataRead,
    immediateLoadMux
  )
  registerFile.io.writeData := MemLoadMux

  controlUnit.io.opcode := programMemory.io.instructionRead(31, 28)

  registerFile.io.aSel := programMemory.io.instructionRead(23, 20)
  registerFile.io.bSel := programMemory.io.instructionRead(19, 16)
  registerFile.io.writeSel := programMemory.io.instructionRead(27, 24)
  programCounter.io.programCounterJump := programMemory.io.instructionRead(
    15,
    0
  )
  controlUnit.io.opcode := programMemory.io.instructionRead(31, 28)
  registerFile.io.writeEnable := controlUnit.io.RegWrite

  dataMemory.io.dataWrite := registerFile.io.a
  dataMemory.io.address := registerFile.io.b
  dataMemory.io.writeEnable := controlUnit.io.MemWrite

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