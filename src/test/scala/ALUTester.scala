import chisel3._
import chisel3.iotesters.PeekPokeTester

class ALUTester(dut: ALU) extends PeekPokeTester(dut) {


  //Addi operation
  poke(dut.io.sel, 0)
  poke(dut.io.input1, 5)
  poke(dut.io.input2, 5)
  expect(dut.io.output,10)
  step(5) //lidt i tvivl om der skal være step(5) efter hver test

  //Subi operation
  poke(dut.io.sel, 1)
  poke(dut.io.input1, 11)
  poke(dut.io.input2,9)
  expect(dut.io.output,2)
  step(5)

  //BITWISE OR
  poke(dut.io.sel, 2)
  poke(dut.io.input1, 5)
  poke(dut.io.input2, 3)
  expect(dut.io.output,7)
  step(5)

  //load immediate
  poke(dut.io.sel,3)
  poke(dut.io.input1, 5)
  expect(dut.io.output,5)
  step(4)

  //compare equal
  poke(dut.io.sel,4)
  poke(dut.io.input1, 5)
  poke(dut.io.input2, 5)
  expect(dut.io.compariResults,true)
  step(5)

  //compare equal again (but this time false:)
  poke(dut.io.sel,4)
  poke(dut.io.input1, 5)
  poke(dut.io.input2, 6)
  expect(dut.io.compariResults,false)
  step(5)

  //jump
  poke(dut.io.sel,5)
  poke(dut.io.input1, 5)
  expect(dut.io.compariResults,true)
  step(4)

}
object ALUTester {
  def main(args: Array[String]): Unit = {
    println("Testing ALUTester")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ALUTester"),
      () => new ALU()) {
      c => new ALUTester(c)
    }
  }
}
