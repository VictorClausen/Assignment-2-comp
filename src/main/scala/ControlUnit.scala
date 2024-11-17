import chisel3._           // Import the Chisel library for hardware design
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {  // Define the input-output bundle for the module

    val RegWrite = Output(Bool())
    val done = Output(Bool())
    val opcode = Input(UInt(4.W))
    val ImJump = Output(Bool())
    val jumpTo = Output(Bool())
    val AluOp = Output(UInt(3.W))
    val ImOp = Output(Bool())
    val ImLoad = Output(Bool())
    val MemLoad = Output(Bool())
    val MemWrite = Output(Bool())
  })

  //
  io.RegWrite := false.B
  io.done := false.B
  io.ImJump := false.B
  io.jumpTo := false.B
  io.AluOp := 0.U(3.W)
  io.ImOp := false.B
  io.ImLoad := false.B
  io.MemLoad := false.B
  io.MemWrite := false.B

  switch(io.opcode) {
    is(1.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 0.U(3.W) // plus (se ALU)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(2.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 1.U(3.W)  // subtraction (se ALU)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(3.U(4.W)) {

      io.jumpTo := false.B
      io.AluOp := 0.U(3.W)  // plus (se ALU). Immediate operand
      io.ImOp := true.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
    }
    is(4.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 1.U(3.W)  // plus (se ALU). Immediate operand
      io.ImOp := true.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(5.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 5.U(3.W)  // multiplikation (se ALU)
      io.ImOp := true.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(6.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 2.U(3.W)  //bitwise OR (se Alu)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(7.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 4.U(3.W)  // bitwise AND (se ALU)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(8.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 0.U(3.W)  // loading immediate opperation
      io.ImOp := false.B
      io.ImLoad := true.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(9.U(4.W)) {
      io.RegWrite := true.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 0.U(3.W)  //load from memory. ved ikke helt om dette virker
      //vi skal lige dobbelttjekke det
      io.ImOp := false.B
      io.MemWrite := false.B
      io.ImLoad := false.B
      io.MemLoad := true.B
    }
    is(10.U(4.W)) {
      io.RegWrite := false.B
      io.done := false.B
      io.ImJump := false.B
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := true.B
      io.jumpTo := false.B
      io.AluOp := 0.U(3.W) //addition fra alu. skal lige tjekkes om det virker

    }
    is(11.U(4.W)) {
      io.RegWrite := true.B

      io.jumpTo := false.B
      io.AluOp := 6.U(3.W)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
      io.done := false.B
      io.ImJump := false.B
    }

    is(12.U(4.W)) {
      // Immediate jump
      io.RegWrite := false.B

      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
      io.done := false.B
      io.ImJump := true.B
      io.jumpTo := true.B
      io.AluOp := 0.U(3.W)
    }
    is(13.U(4.W)) {
      io.RegWrite := false.B
      io.done := false.B
      io.ImJump := false.B
      io.jumpTo := true.B
      io.AluOp := 7.U(3.W)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
    is(14.U(4.W)) {
      //end programe
      io.RegWrite := false.B
      io.done := true.B
      io.ImJump := false.B
      io.jumpTo := false.B
      io.AluOp := 0.U(3.W)
      io.ImOp := false.B
      io.ImLoad := false.B
      io.MemLoad := false.B
      io.MemWrite := false.B
    }
  }
}
