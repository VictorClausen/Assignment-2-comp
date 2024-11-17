;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit ProgramCounter : 
  module ProgramCounter : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip stop : UInt<1>, flip jump : UInt<1>, flip run : UInt<1>, flip programCounterJump : UInt<16>, programCounter : UInt<16>}
    
    reg PCreg : UInt<16>, clock @[ProgramCounter.scala 14:18]
    wire stopRunLogicGate : UInt<1>
    stopRunLogicGate <= UInt<1>("h00")
    node _T = not(io.run) @[ProgramCounter.scala 17:33]
    node _T_1 = or(io.stop, _T) @[ProgramCounter.scala 17:31]
    stopRunLogicGate <= _T_1 @[ProgramCounter.scala 17:20]
    node _T_2 = not(io.run) @[ProgramCounter.scala 20:19]
    node _T_3 = or(io.stop, _T_2) @[ProgramCounter.scala 20:17]
    when _T_3 : @[ProgramCounter.scala 20:28]
      PCreg <= PCreg @[ProgramCounter.scala 21:11]
      skip @[ProgramCounter.scala 20:28]
    else : @[ProgramCounter.scala 22:22]
      when io.jump : @[ProgramCounter.scala 22:22]
        PCreg <= io.programCounterJump @[ProgramCounter.scala 23:11]
        skip @[ProgramCounter.scala 22:22]
      else : @[ProgramCounter.scala 24:14]
        node _T_4 = add(PCreg, UInt<16>("h01")) @[ProgramCounter.scala 25:19]
        node _T_5 = tail(_T_4, 1) @[ProgramCounter.scala 25:19]
        PCreg <= _T_5 @[ProgramCounter.scala 25:11]
        skip @[ProgramCounter.scala 24:14]
    io.programCounter <= PCreg @[ProgramCounter.scala 28:21]
    
