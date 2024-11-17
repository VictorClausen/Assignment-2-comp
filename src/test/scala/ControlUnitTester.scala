import chisel3._
import chisel3.iotesters.PeekPokeTester

class ControlUnitTester(dut: ControlUnit) extends PeekPokeTester(dut) {
  // Test ADD
  poke(dut.io.opcode, 1)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 0)
  expect(dut.io.ImOp, false)

  // Test SUB
  poke(dut.io.opcode, 2)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 1)
  expect(dut.io.ImOp, false)

  // Test ADDI
  poke(dut.io.opcode, 3)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 0)
  expect(dut.io.ImOp, true)

  // Test SUBI
  poke(dut.io.opcode, 4)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 1)
  expect(dut.io.ImOp, true)

  // Test MULT
  poke(dut.io.opcode, 5)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 5)
  expect(dut.io.ImOp, true)

  // Test OR
  poke(dut.io.opcode, 6)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 2)
  expect(dut.io.ImOp, false)

  // Test AND
  poke(dut.io.opcode, 7)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 4)
  expect(dut.io.ImOp, false)

  // Test LOADI
  poke(dut.io.opcode, 8)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.ImLoad, true)

  // Test LOAD
  poke(dut.io.opcode, 9)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.MemLoad, true)

  // Test STORE
  poke(dut.io.opcode, 10)
  step(1)
  expect(dut.io.MemWrite, true)

  // Test INC
  poke(dut.io.opcode, 11)
  step(1)
  expect(dut.io.RegWrite, true)
  expect(dut.io.AluOp, 6)

  // Test JMP
  poke(dut.io.opcode, 12)
  step(1)
  expect(dut.io.jumpTo, true)
  expect(dut.io.ImJump, true)

  // Test JEQ
  poke(dut.io.opcode, 13)
  step(1)
  expect(dut.io.jumpTo, true)
  expect(dut.io.AluOp, 7)

  // Test END
  poke(dut.io.opcode, 14)
  step(1)
  expect(dut.io.done, true)
}

object ControlUnitTester {
  def main(args: Array[String]): Unit = {
    println("Testing the Control Unit / Tester Control Unit'en")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ControlUnit"),
      () => new ControlUnit()) {
      c => new ControlUnitTester(c)
    }
  }
}