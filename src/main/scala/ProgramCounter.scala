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

  val PCreg: UInt = RegInit(0.U(16.W))

  val stopRunLogicGate: Bool = WireDefault(false.B)
  stopRunLogicGate := io.stop | ~io.run //the OR gate at the bottom of the diagram

  //All the basic logic for the diagram/table of figure 12
  when (stopRunLogicGate){
    PCreg := PCreg
  }.elsewhen(io.jump){
    PCreg := io.programCounterJump
  }.otherwise{
    PCreg := PCreg+1.U
  }

  io.programCounter := PCreg
}