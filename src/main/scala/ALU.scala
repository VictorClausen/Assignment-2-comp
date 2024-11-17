import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(3.W)) //selection of operation
    val input1 = Input(UInt(32.W)) //a
    val input2 = Input(UInt(32.W)) //b
    val output = Output(UInt(32.W))
  })

  val output = Wire(UInt(32.W)) //wire to connect output. see at the end of this file
  //for the connection
  output := 0.U //setting output to defalt value

  switch(io.sel){
    is (0.U){output := io.input1+ io.input2} //addi operation
    is(1.U) {output:= io.input1- io.input2} //minus operation
    is (2.U){output:= io.input1| io.input2} // BITWISE OR operation
    is (3.U){output := io.input1} //load immediate (måske også load data idk)
    is(4.U) {output:= io.input1& io.input2} //bitwise AND (mangler selve implimentationen,
    //men nu er den i hverfald lavet)
    is (5.U){output := io.input1* io.input2} //multiplication
    is(6.U) {output:= io.input1+ (1.U)} //tilføjer 1 til input1
    is(7.U) {output:= io.input1===io.input2} //JEQ operation
  }
  io.output:=output
}
