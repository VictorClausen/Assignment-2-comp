import chisel3._           // Import the Chisel library for hardware design
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {  // Define the input-output bundle for the module
    /*
      * input for all the ALU along:
     */

    val writeRegister = Output(Bool())
    val done = Output(Bool())
    val opcode = Input(UInt(4.W))
    val immediateJump = Output(Bool())
    val jumpTo = Output(Bool())
    val aluFunc = Output(UInt(3.W))
    val immediateOperand = Output(Bool())
    val loadIMMEDIATE = Output(Bool())
    val loadFromMemory = Output(Bool())
    val writeToMemory = Output(Bool())
  })

  //
  io.writeRegister := false.B
  io.done := false.B
  io.immediateJump := false.B
  io.jumpTo := false.B
  io.aluFunc := 0.U(3.W)
  io.immediateOperand := false.B
  io.loadIMMEDIATE := false.B
  io.loadFromMemory := false.B
  io.writeToMemory := false.B

  switch(io.opcode) {
    is(1.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 0.U(3.W) // plus (se ALU)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(2.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 1.U(3.W)  // subtraction (se ALU)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(3.U(4.W)) {

      io.jumpTo := false.B
      io.aluFunc := 0.U(3.W)  // plus (se ALU). Immediate operand
      io.immediateOperand := true.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
    }
    is(4.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 1.U(3.W)  // plus (se ALU). Immediate operand
      io.immediateOperand := true.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(5.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 5.U(3.W)  // multiplikation (se ALU)
      io.immediateOperand := true.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(6.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 2.U(3.W)  //bitwise OR (se Alu)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(7.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 4.U(3.W)  // bitwise AND (se ALU)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(8.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 0.U(3.W)  // loading immediate opperation
      io.immediateOperand := false.B
      io.loadIMMEDIATE := true.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(9.U(4.W)) {
      io.writeRegister := true.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 0.U(3.W)  //load from memory. ved ikke helt om dette virker
      //vi skal lige dobbelttjekke det
      io.immediateOperand := false.B
      io.writeToMemory := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := true.B
    }
    is(10.U(4.W)) {
      io.writeRegister := false.B
      io.done := false.B
      io.immediateJump := false.B
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := true.B
      io.jumpTo := false.B
      io.aluFunc := 0.U(3.W) //addition fra alu. skal lige tjekkes om det virker

    }
    is(11.U(4.W)) {
      io.writeRegister := true.B

      io.jumpTo := false.B
      io.aluFunc := 6.U(3.W)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
      io.done := false.B
      io.immediateJump := false.B
    }

    is(12.U(4.W)) {
      // Immediate jump
      io.writeRegister := false.B

      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
      io.done := false.B
      io.immediateJump := true.B
      io.jumpTo := true.B
      io.aluFunc := 0.U(3.W)
    }
    is(13.U(4.W)) {
      io.writeRegister := false.B
      io.done := false.B
      io.immediateJump := false.B
      io.jumpTo := true.B
      io.aluFunc := 7.U(3.W)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    is(14.U(4.W)) {
      //end programe
      io.writeRegister := false.B
      io.done := true.B
      io.immediateJump := false.B
      io.jumpTo := false.B
      io.aluFunc := 0.U(3.W)
      io.immediateOperand := false.B
      io.loadIMMEDIATE := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
  }
}
