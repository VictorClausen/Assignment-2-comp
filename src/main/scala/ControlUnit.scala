import chisel3._           // Import the Chisel library for hardware design
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {  // Define the input-output bundle for the module
    /*

     */
    val opcode = Input(UInt(4.W))
    val writeRegister = Output(Bool())
    val stop = Output(Bool())
    val immediateJump = Output(Bool())
    val jump = Output(Bool())
    val aluFunc = Output(UInt(3.W))
    val immediateOperand = Output(Bool())
    val immediateLoad = Output(Bool())
    val loadFromMemory = Output(Bool())
    val writeToMemory = Output(Bool())
  })

  // Default values for the outputs when the opcode doesn't match any specific case
  io.writeRegister := false.B
  io.stop := false.B
  io.immediateJump := false.B
  io.jump := false.B
  io.aluFunc := 0.U(3.W)
  io.immediateOperand := false.B
  io.immediateLoad := false.B
  io.loadFromMemory := false.B
  io.writeToMemory := false.B

  // Switch statement for handling the opcode and setting the control signals accordingly
  switch(io.opcode) {
    // Case for the ADD instruction (opcode 1)
    is(1.U(4.W)) {
      io.writeRegister := true.B  // Write to a register
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 0.U(3.W)  // ALU function for addition
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the SUB instruction (opcode 2)
    is(2.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 1.U(3.W)  // ALU function for subtraction
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the ADDI instruction (opcode 3)
    is(3.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 0.U(3.W)  // ALU function for addition
      io.immediateOperand := true.B  // Immediate operand is used
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the SUBI instruction (opcode 4)
    is(4.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 1.U(3.W)  // ALU function for subtraction
      io.immediateOperand := true.B  // Immediate operand is used
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the MULT instruction (opcode 5)
    is(5.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 5.U(3.W)  // ALU function for multiplication
      io.immediateOperand := true.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the OR instruction (opcode 6)
    is(6.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 2.U(3.W)  // ALU function for bitwise OR
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the AND instruction (opcode 7)
    is(7.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 4.U(3.W)  // ALU function for bitwise AND
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the LOADI instruction (opcode 8)
    is(8.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 0.U(3.W)  // ALU function for loading immediate value
      io.immediateOperand := false.B
      io.immediateLoad := true.B  // Immediate load
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the LOAD instruction (opcode 9)
    is(9.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 0.U(3.W)  // ALU function for load
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := true.B  // Load from memory
      io.writeToMemory := false.B
    }
    // Case for the STORE instruction (opcode 10)
    is(10.U(4.W)) {
      io.writeRegister := false.B  // No register write
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 0.U(3.W)
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := true.B  // Store to memory
    }
    // Case for the INC instruction (opcode 11)
    is(11.U(4.W)) {
      io.writeRegister := true.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 6.U(3.W)  // ALU function for increment
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the JMP instruction (opcode 12)
    is(12.U(4.W)) {
      io.writeRegister := false.B
      io.stop := false.B
      io.immediateJump := true.B  // Immediate jump
      io.jump := true.B  // Jump
      io.aluFunc := 0.U(3.W)
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the JEQ instruction (opcode 13)
    is(13.U(4.W)) {
      io.writeRegister := false.B
      io.stop := false.B
      io.immediateJump := false.B
      io.jump := true.B  // Conditional jump
      io.aluFunc := 7.U(3.W)  // ALU function for equality check
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
    // Case for the END instruction (opcode 15)
    is(15.U(4.W)) {
      io.writeRegister := false.B
      io.stop := true.B  // Stop execution
      io.immediateJump := false.B
      io.jump := false.B
      io.aluFunc := 0.U(3.W)
      io.immediateOperand := false.B
      io.immediateLoad := false.B
      io.loadFromMemory := false.B
      io.writeToMemory := false.B
    }
  }
}
