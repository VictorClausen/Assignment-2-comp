import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    val input1 = Input(UInt(32.W))
    val input2 = Input(UInt(32.W))
    val compariResults = Output(Bool())
    val sel = Input(UInt(4.W)) //altid sæt denne til antal instructions minus 1
    val output = Output(UInt(32.W))
  })
  //Implement this module here
  io.output := 0.U
  io.compariResults := false.B

  switch(io.sel){
    is (0.U) {io.output := io.input1 + io.input2} //addi operation
    is (1.U) {io.output := io.input1 - io.input2} //minus operation
    is (2.U) {io.output := io.input1 | io.input2} // BITWISE OR operation
    is (3.U) {io.output := io.input1} //load immediate (måske også load data idk)

    is (4.U) {io.compariResults := io.input1 === io.input2} //compare equal (1.U hvis true ellers 0.U)
    is (5.U) {io.compariResults := true.B} //jump (JMP)
  }
}