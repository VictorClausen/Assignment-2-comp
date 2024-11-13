import chisel3._
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {
    val opcode = Input(UInt(6.W))
    val aluOp = Output(UInt(4.W))
    val memRead = Output(Bool())
    val memWrite = Output(Bool())
    val regWrite = Output(Bool())
    val aluSrc = Output(Bool())
    val jump = Output(Bool())
    val jumpIfEqual = Output(Bool())
    val end = Output(Bool())
  })

  io.aluOp := 0.U
  io.memRead := false.B
  io.memWrite := false.B
  io.regWrite := false.B
  io.aluSrc := false.B
  io.jump := false.B
  io.jumpIfEqual := false.B
  io.end := false.B

  switch(io.opcode) {
    is("b000001".U) { // ADDI
      io.aluOp := 1.U
      io.regWrite := true.B
      io.aluSrc := true.B
    }
    is("b000010".U) { // SUBI
      io.aluOp := 2.U
      io.regWrite := true.B
      io.aluSrc := true.B
    }
    is("b000011".U) { // OR
      io.aluOp := 3.U
      io.regWrite := true.B
    }
    is("b000100".U) { // LI
      io.aluOp := 4.U
      io.regWrite := true.B
      io.aluSrc := true.B
    }
    is("b000101".U) { // LD
      io.memRead := true.B
      io.regWrite := true.B
    }
    is("b000110".U) { // ST
      io.memWrite := true.B
    }
    is("b000111".U) { // JMP
      io.jump := true.B
    }
    is("b001000".U) { // JEQ
      io.jumpIfEqual := true.B
    }
    is("b001001".U) { // END
      io.end := true.B
    }
  }
}
