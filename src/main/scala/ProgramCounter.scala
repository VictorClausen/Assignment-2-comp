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

  //val PCreg: UInt = RegInit(0.U(16.W))
  val PCreg = Reg(UInt(16.W))

  val stopRunLogicGate: Bool = WireDefault(false.B)
  stopRunLogicGate := io.stop | ~io.run //the OR gate at the bottom of the diagram
  //Denne burde virker, men fik et lille problem med CPUtop, så jeg har kommenteret det ud.
  //Vil lige tjekke igen.

  //All the basic logic for the diagram/table of figure 12
  when(io.stop| ~io.run) {
    PCreg:=PCreg
  }.elsewhen(io.jump){
    PCreg:= io.programCounterJump
  }.otherwise{
    PCreg :=PCreg+1.U(16.W)
  }
  io.programCounter := PCreg //end initiolization of PCreg
}