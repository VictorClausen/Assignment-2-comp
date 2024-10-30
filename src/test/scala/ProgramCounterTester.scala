import chisel3._
import chisel3.iotesters.PeekPokeTester

class ProgramCounterTester(dut: ProgramCounter) extends PeekPokeTester(dut) {

  //Program Counter running for 5 clock cycles
  poke(dut.io.jump, false)
  poke(dut.io.run, true)
  poke(dut.io.stop, false)
  poke(dut.io.programCounterJump, 0)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.jump, false)
  poke(dut.io.run, true)
  poke(dut.io.stop, true)
  poke(dut.io.programCounterJump, 0)
  step(5)

  //Hold for 5 clock cycles
  poke(dut.io.jump, false)
  poke(dut.io.run, false)
  poke(dut.io.stop, false)
  poke(dut.io.programCounterJump, 0)
  step(5)

  //Load the value 30
  poke(dut.io.jump, true)
  poke(dut.io.run, true)
  poke(dut.io.stop, false)
  poke(dut.io.programCounterJump, 30)
  step(1)

  //Program Counter running for another 5 clock cycles
  poke(dut.io.jump, false)
  poke(dut.io.run, true)
  poke(dut.io.stop, false)
  poke(dut.io.programCounterJump, 0)
  step(5)
}

object ProgramCounterTester {
  def main(args: Array[String]): Unit = {
    println("Testing Program Counter")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "ProgramCounter"),
      () => new ProgramCounter()) {
      c => new ProgramCounterTester(c)
    }
  }
}
