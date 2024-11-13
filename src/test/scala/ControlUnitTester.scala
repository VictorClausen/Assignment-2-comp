import chisel3._
import chisel3.iotesters.PeekPokeTester

class ControlUnitTester(dut: ControlUnit) extends PeekPokeTester(dut) {
  // Test ADDI instruction
  poke(dut.io.opcode, "b000001".U)
  expect(dut.io.aluOp, 1.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, true.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test SUBI instruction
  poke(dut.io.opcode, "b000010".U)
  expect(dut.io.aluOp, 2.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, true.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test OR instruction (dont think we acutally need this one...)
  poke(dut.io.opcode, "b000011".U)
  expect(dut.io.aluOp, 3.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test Load Immedeate instruction
  poke(dut.io.opcode, "b000100".U)
  expect(dut.io.aluOp, 4.U)
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, true.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test Load Instructions instruction
  poke(dut.io.opcode, "b000101".U)
  expect(dut.io.aluOp, 0.U) // just setting alu=0 because its not neede for load/store
  expect(dut.io.memRead, true.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, true.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test Store Data instruction
  poke(dut.io.opcode, "b000110".U)
  expect(dut.io.aluOp, 0.U) // just setting alu=0 because its not neede for load/store
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, true.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test JMP instruction
  poke(dut.io.opcode, "b000111".U)
  expect(dut.io.aluOp, 0.U) // just setting alu=0 because its not neede for jump/jeq/end
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, true.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test JEQ instruction
  poke(dut.io.opcode, "b001000".U)
  expect(dut.io.aluOp, 0.U) // just setting alu=0 because its not neede for jump/jeq/end
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, true.B)
  expect(dut.io.end, false.B)
  step(5)

  // Test END instruction
  poke(dut.io.opcode, "b001001".U)
  expect(dut.io.aluOp, 0.U) // just setting alu=0 because its not neede for jump/jeq/end
  expect(dut.io.memRead, false.B)
  expect(dut.io.memWrite, false.B)
  expect(dut.io.regWrite, false.B)
  expect(dut.io.aluSrc, false.B)
  expect(dut.io.jump, false.B)
  expect(dut.io.jumpIfEqual, false.B)
  expect(dut.io.end, true.B)
  step(5)
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
