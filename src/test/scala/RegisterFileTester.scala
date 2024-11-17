import chisel3._
import chisel3.iotesters.PeekPokeTester

class RegisterFileTester(dut: RegisterFile) extends PeekPokeTester(dut) {
  // Test skrive til en register
  poke(dut.io.writeEnable, true)
  poke(dut.io.writeSel, 1)
  poke(dut.io.writeData, 42)
  step(1)
  expect(dut.io.a, 0) // Before selecting a register
  expect(dut.io.b, 0) // Before selecting a register

  // Test reading from register
  poke(dut.io.aSel, 1)
  poke(dut.io.bSel, 1)
  step(1)
  expect(dut.io.a, 42)
  expect(dut.io.b, 42)

  // Test skrive til en anden register
  poke(dut.io.writeSel, 2)
  poke(dut.io.writeData, 84)
  step(1)
  poke(dut.io.aSel, 2)
  poke(dut.io.bSel, 2)
  step(1)
  expect(dut.io.a, 84)
  expect(dut.io.b, 84)

  // Test writeEnable = false
  poke(dut.io.writeEnable, false)
  poke(dut.io.writeSel, 3)
  poke(dut.io.writeData, 168)
  step(1)
  poke(dut.io.aSel, 3)
  poke(dut.io.bSel, 3)
  step(1)
  expect(dut.io.a, 0)
  expect(dut.io.b, 0)
}

object RegisterFileTester {
  def main(args: Array[String]): Unit = {
    println("Testing the Register File / Tester Register File'en")
    iotesters.Driver.execute(
      Array("--generate-vcd-output", "on",
        "--target-dir", "generated",
        "--top-name", "RegisterFile"),
      () => new RegisterFile()) {
      c => new RegisterFileTester(c)
    }
  }
}