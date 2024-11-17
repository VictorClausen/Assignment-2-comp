import chisel3._
import chisel3.iotesters.PeekPokeTester

class ALUTester(dut: ALU) extends PeekPokeTester(dut) {
  // Test ADD operation
  poke(dut.io.sel, 0)
  poke(dut.io.input1, 10)
  poke(dut.io.input2, 5)
  step(1)
  expect(dut.io.output, 15)

  // Test SUB operation
  poke(dut.io.sel, 1)
  poke(dut.io.input1, 10)
  poke(dut.io.input2, 5)
  step(1)
  expect(dut.io.output, 5)

  // Test OR operation
  poke(dut.io.sel, 2)
  poke(dut.io.input1, 10)
  poke(dut.io.input2, 5)
  step(1)
  expect(dut.io.output, 15)

  // Test LOAD IMMEDIATE operation
  poke(dut.io.sel, 3)
  poke(dut.io.input1, 10)
  step(1)
  expect(dut.io.output, 10)

  // Test AND operation
  poke(dut.io.sel, 4)
  poke(dut.io.input1, 10)
  poke(dut.io.input2, 5)
  step(1)
  expect(dut.io.output, 0)

  // Test MULT operation
  poke(dut.io.sel, 5)
  poke(dut.io.input1, 10)
  poke(dut.io.input2, 5)
  step(1)
  expect(dut.io.output, 50)

  // Test INC operation
  poke(dut.io.sel, 6)
  poke(dut.io.input1, 10)
  step(1)
  expect(dut.io.output, 11)

  // Test EQUAL operation
  poke(dut.io.sel, 7)
  poke(dut.io.input1, 10)
  poke(dut.io.input2, 10)
  step(1)
  expect(dut.io.output, 1)
}

object ALUTester {
  def main(args: Array[String]): Unit = {
    println("Testing the ALU")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ALU"),
      () => new ALU()) {
      c => new ALUTester(c)
    }
  }
}