import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.PeekPokeTester
import java.util

object Programs{
  val program1 = Array(
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W)
  )

  val program2 = Array(
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W)
  )
}