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
    val a = Output(UInt(32.W))
    val b =Output(UInt(32.W))
  })

  val registers = RegInit(VecInit(Seq.fill(32)(0.U(32.W))))
  //io.a:=0.U
  //io.b:=0.U

  val aReg =registers(io.aSel)
  val bReg =registers(io.bSel)

  //val registerFile = RegInit(VecInit(Seq.fill(32)(0.U(32.W)))) //32 registers, 32 bits each.
  //all starting with value 0.

  val writeReg  =registers(io.writeSel)

  when(io.writeEnable){
    writeReg:=io.writeData
  }
  io.a := registers(io.aSel)
  io.b := registers(io.bSel)
}