import chisel3._
import chisel3.iotesters.PeekPokeTester

class ControlUnitTester(dut: ControlUnit) extends PeekPokeTester(dut) {
  // Test ADDI instruction
  poke(dut.io.opcode, "b000001".U)
  step(1)
  expect(dut.io.aluOp, 1.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, true.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test SUBI instruction
  poke(dut.io.opcode, "b000010".U)
  step(1)
  expect(dut.io.aluOp, 2.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, true.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test OR instruction
  poke(dut.io.opcode, "b000011".U)
  step(1)
  expect(dut.io.aluOp, 3.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test LI instruction
  poke(dut.io.opcode, "b000100".U)
  step(1)
  expect(dut.io.aluOp, 4.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, true.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test LD instruction
  poke(dut.io.opcode, "b000101".U)
  step(1)
  expect(dut.io.aluOp, 0.U) // ALU operation is not relevant for LD
  expect(dut.io.memRead, true.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test ST instruction
  poke(dut.io.opcode, "b000110".U)
  step(1)
  expect(dut.io.aluOp, 0.U) // ALU operation is not relevant for ST
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, true.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test JMP instruction
  poke(dut.io.opcode, "b000111".U)
  step(1)
  expect(dut.io.aluOp, 0.U) // ALU operation is not relevant for JMP
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, true.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)

  // Test JEQ instruction
  poke(dut.io.opcode, "b001000".U)
  step(1)
  expect(dut.io.aluOp, 0.U) // ALU operation is not relevant for JEQ
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, true.B)
  expect(dut.io.end, false.B)

  // Test END instruction
  poke(dut.io.opcode, "b001001".U)
  step(1)
  expect(dut.io.aluOp, 0.U) // ALU operation is not relevant for END
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, true.B)
}

object ControlUnitTester {
  def main(args: Array[String]): Unit = {
    println("Testing Control Unit")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ControlUnit"),
      () => new ControlUnit()) {
      dut => new ControlUnitTester(dut)
    }
  }
}
