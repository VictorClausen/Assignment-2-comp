/*
 * Hello.scala
 *
 * This is just a simple circuit that reads a binary image stored in the data memory (address 0 to 399),
 * inverts the pixel color (black becomes white and viceversa) and write the image in the data memory
 * (address 400 to 799).
 *
 * The associated tester for this circuit is test/scala/HelloTester.scala. There you can select the image you want
 * to use as input. To speed-up simulation images are 20x20 pixels. Images are stored in test/scala/Images.scala.
 *
 * Run in the terminal>> sbt "test:runMain HelloTester" to see it at work and test your Chisel3/Scala/Java
 * installation. In the Hello
 *
 * Author: Luca Pezzarossa
 */

import chisel3._
import chisel3.util.Cat

class Hello extends Module {
  val io = IO(new Bundle {
    val done = Output(Bool ())
    val run = Input(Bool ())
    //The following signals are used by the tester to load and dump the memory contents. Do not touch.
    val testerDataMemEnable = Input(Bool ())
    val testerDataMemAddress = Input(UInt (16.W))
    val testerDataMemDataRead = Output(UInt (32.W))
    val testerDataMemWriteEnable = Input(Bool ())
    val testerDataMemDataWrite = Input(UInt (32.W))
  })

  //Creating components
  val dataMemory = Module(new DataMemory())

  val addressCounterReg = RegInit(0.U(17.W))
  val dataReg = RegInit(0.U(32.W))

  io.done := false.B
  when(addressCounterReg === 800.U(16.W)){
    io.done := true.B
    addressCounterReg := addressCounterReg
  } .otherwise {
    when(io.run){
      addressCounterReg := addressCounterReg + 1.U(16.W)
    }
  }

  when(addressCounterReg(0) === 0.U(1.W)){
    dataReg := dataMemory.io.dataRead
  }

  val invertedPixel = Wire(UInt(32.W))
  invertedPixel := Cat(0.U(24.W), ~dataReg(7,0))

  dataMemory.io.address := Mux(addressCounterReg(0), addressCounterReg(16,1)+400.U(16.W), addressCounterReg(16,1))
  dataMemory.io.dataWrite := invertedPixel
  dataMemory.io.writeEnable := addressCounterReg(0)

  //This signals are used by the tester for loading and dumping the data memory content, do not touch
  dataMemory.io.testerAddress := io.testerDataMemAddress
  io.testerDataMemDataRead := dataMemory.io.testerDataRead
  dataMemory.io.testerDataWrite := io.testerDataMemDataWrite
  dataMemory.io.testerEnable := io.testerDataMemEnable
  dataMemory.io.testerWriteEnable := io.testerDataMemWriteEnable

}

