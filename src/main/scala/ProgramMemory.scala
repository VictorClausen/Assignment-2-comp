import chisel3._

class ProgramMemory extends Module {
  val io = IO(new Bundle {
    val address = Input(UInt (16.W))
    val instructionRead = Output(UInt (32.W))

    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerEnable = Input(Bool ())
    val testerAddress = Input(UInt (16.W))
    val testerDataRead = Output(UInt (32.W))
    val testerWriteEnable = Input(Bool ())
    val testerDataWrite = Input(UInt (32.W))
  })

  val memory = Mem (65536 , UInt (32.W))

  when(io.testerEnable){
    //Tester mode
    io.testerDataRead := memory.read(io.testerAddress)
    io.instructionRead := 0.U(32.W)
    when(io.testerWriteEnable) {
      memory.write(io.testerAddress, io.testerDataWrite)
      io.testerDataRead := io.testerDataWrite
    }
  } .otherwise {
    //Normal mode
    io.instructionRead := memory.read(io.address)
    io.testerDataRead := 0.U(32.W)
  }

}