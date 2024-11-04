import chisel3._
import chisel3.util._

class RegisterFile extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)

    //Register definitions idk
    val aSel= Input(UInt(4.W))
    val bSel= Input(UInt(4.W))
    val writeSel =Input(UInt(4.W)) //register som skal skrives i
    val writeEnable = Input(Bool()) //true hvis der skal skrives i en register
    val writeData = Input(UInt(32.W)) //lortet som skal skrives i den valgte register

    val reg= RegInit(0.U(32.W)) //just set it to 32 bits, could make it smaller :/
    val enableReg= Reg(UInt(4.W))

    val cntReg= RegInit(0.U(4.W))

    val a = Output(UInt(32.W))
    val b =Output(UInt(32.W))
  })

  //Implement this module here

  val registerFile = RegInit(VecInit(Seq.fill(16)(0.U(32.W)))) //16 registers, 32 bits each.
  //all starting with value 0.

  when(io.writeEnable){
    registerFile(io.writeSel):=io.writeData
  }
  io.a := registerFile(io.aSel)
  io.b := registerFile(io.bSel)
}