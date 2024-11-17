import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(3.W))
    val input1 = Input(UInt(32.W))
    val input2 = Input(UInt(32.W))
    val output = Output(UInt(32.W))
  })

  val output = Wire(UInt(32.W))
  output := 0.U

  switch(io.sel){
    is (0.U) {output := io.input1 + io.input2} //addi operation
    is (1.U) {output := io.input1 - io.input2} //minus operation
    is (2.U) {output := io.input1 | io.input2} // BITWISE OR operation
    is (3.U) {output := io.input1} //load immediate (måske også load data idk)
    is(4.U) {output := io.input1 & io.input2}
    is(5.U) {output := io.input1 * io.input2}
    is(6.U) {output := io.input1 + (1.U)}
    is(7.U) {output := io.input1 === io.input2}
  }
  io.output:=output
}
