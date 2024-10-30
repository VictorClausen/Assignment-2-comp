import chisel3._
import chisel3.util._

class ProgramCounter extends Module {
  val io = IO(new Bundle {
    val stop = Input(Bool())
    val jump = Input(Bool())
    val run = Input(Bool())
    val programCounterJump = Input(UInt(16.W))
    val programCounter = Output(UInt(16.W))
  })

    // Initialize the program counter register
    val pc = RegInit(0.U(16.W))

    // Incremented program counter value
    val pcNext = pc + 1.U

    // First multiplexer: select between incremented pc or programCounterJump
    val nextPc = Mux(io.jump, io.programCounterJump, pcNext)

    // NOT gate for stop, then OR gate for control (whether to update the counter)
    val enableUpdate = io.run || !io.stop

    // Second multiplexer: update program counter if enableUpdate is true, otherwise hold the current value
    pc := Mux(enableUpdate, nextPc, pc)

    // Output the current program counter value
    io.programCounter := pc
}