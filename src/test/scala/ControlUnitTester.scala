import chisel3._
import chisel3.iotesters.PeekPokeTester

class ControlUnitTester(dut: ControlUnit) extends PeekPokeTester(dut) {
  // Test Add
  poke(dut.io.opcode, 1.U(4.W)) // ADD
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 0.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 2.U(4.W)) // SUB
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 1.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 3.U(4.W)) // ADDI
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 0.U(3.W))
  expect(dut.io.immediateOperand, true.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 4.U(4.W)) // SUBI
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 1.U(3.W))
  expect(dut.io.immediateOperand, true.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 5.U(4.W)) // MULT
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 5.U(3.W))
  expect(dut.io.immediateOperand, true.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 6.U(4.W)) // OR
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 2.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 7.U(4.W)) // AND
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 3.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 8.U(4.W)) // LOADI
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.loadIMMEDIATE, true.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 9.U(4.W)) // LOAD
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, true.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 10.U(4.W)) // STORE
  expect(dut.io.writeRegister, false.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, true.B)
  step(1)

  poke(dut.io.opcode, 11.U(4.W)) // INC
  expect(dut.io.writeRegister, true.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 6.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 12.U(4.W)) // JMP
  expect(dut.io.writeRegister, false.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, true.B)
  expect(dut.io.jumpTo, true.B)
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 13.U(4.W)) // JEQ
  expect(dut.io.writeRegister, false.B)
  expect(dut.io.done, false.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, true.B)
  expect(dut.io.aluFunc, 4.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

  poke(dut.io.opcode, 15.U(4.W)) // END
  expect(dut.io.writeRegister, false.B)
  expect(dut.io.done, true.B)
  expect(dut.io.immediateJump, false.B)
  expect(dut.io.jumpTo, false.B)
  expect(dut.io.aluFunc, 0.U(3.W))
  expect(dut.io.immediateOperand, false.B)
  expect(dut.io.loadIMMEDIATE, false.B)
  expect(dut.io.loadFromMemory, false.B)
  expect(dut.io.writeToMemory, false.B)
  step(1)

}

object ControlUnitTester {
  def main(args: Array[String]): Unit = {
    println("Testing Control Unit")
    iotesters.Driver.execute(
      Array(
        "--generate-vcd-output",
        "on",
        "--target-dir",
        "generated",
        "--top-name",
        "ControlUnit"
      ),
      () => new ControlUnit()
    ) { c =>
      new ControlUnitTester(c)
    }
  }
}