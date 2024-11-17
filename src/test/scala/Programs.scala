import chisel3._

object Programs {
  val instructions = Array(
    "b10000000000000000000000000000000".U,
    "b10000001000000000000000000000000".U,
    "b10000010000000000000000000010011".U,
    "b10000011000000000000000000000000".U,
    "b10000100000000000000000011111111".U,
    "b11010000000000100000000000101111".U,
    "b11010000000100100000000000101100".U,
    "b01010101000100000000000000010100".U,
    "b00010110000001010000000000000000".U,
    "b00110101011000000000000110010000".U,
    "b11010000000000110000000000101000".U,
    "b11010000000100110000000000101000".U,
    "b11010000000001000000000000101000".U,
    "b11010000000101000000000000101000".U,
    "b01010110000100000000000000010100".U,
    "b00010111000001100000000000000000".U,
    "b10011000000001110000000000000000".U,
    "b11010000100000110000000000101000".U,
    "b01001100000000000000000000000001".U,
    "b00111101000000000000000000000001".U,
    "b01001110000100000000000000000001".U,
    "b00111111000100000000000000000001".U,
    "b01010110000100000000000000010100".U,
    "b00010111011011000000000000000000".U,
    "b10011000000001110000000000000000".U,
    "b01010110000100000000000000010100".U,
    "b00010111011011010000000000000000".U,
    "b10011001000001110000000000000000".U,
    "b01111010100010010000000000000000".U,
    "b01010110111000000000000000010100".U,
    "b00010111011000000000000000000000".U,
    "b10011000000001110000000000000000".U,
    "b01111001100010100000000000000000".U,
    "b01010110111100000000000000010100".U,
    "b00010111011000000000000000000000".U,
    "b10011010000001110000000000000000".U,
    "b01111000100110100000000000000000".U,
    "b11010000100000110000000000101000".U,
    "b10100000010001010000000000000000".U,
    "b11000000000000000000000000101010".U,
    "b10100000001101010000000000000000".U,
    "b11000000000000000000000000101010".U,
    "b10110001000100000000000000000000".U,
    "b11000000000000000000000000000110".U,
    "b10110000000000000000000000000000".U,
    "b10000001000000000000000000000000".U,
    "b11000000000000000000000000000101".U,
    "b11110000000000000000000000000000".U
  )
  val simpleTest = Array(
    "b00010000001000000000000000000000".U(32.W),
    "b00010000011000000000000011111111".U(32.W),
    "b00000100101000010000001100100000".U(32.W),
    "b00011000011001100000000000000000".U(32.W),
    "b00000100001000010000000000000001".U(32.W),
    "b00000100101000010000001100100000".U(32.W),
    "b00011000011001100000000000000000".U(32.W),
    "b00100100000000000000000000000000".U(32.W)
  )

  val allWhite = Array(
    "b10000000000000000000000011111111".U,
    "b10000001000000000000000000000000".U,
    "b10000010000000000000000110010000".U,
    "b10100000000000010000000000000000".U,
    "b10110001000100000000000000000000".U,
    "b11010000000100100000000000000100".U,
    "b11000000000000000000000000000011".U,
    "b11110000000000000000000000000000".U
  )
}
