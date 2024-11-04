;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit ProgramCounter : 
  module ProgramCounter : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip stop : UInt<1>, flip jump : UInt<1>, flip run : UInt<1>, flip programCounterJump : UInt<16>, programCounter : UInt<16>}
    
    reg PCreg : UInt<16>, clock with : (reset => (reset, UInt<16>("h00"))) @[ProgramCounter.scala 13:28]
    wire stopRunLogicGate : UInt<1>
    stopRunLogicGate <= UInt<1>("h00")
    node _T = not(io.run) @[ProgramCounter.scala 16:33]
    node _T_1 = or(io.stop, _T) @[ProgramCounter.scala 16:31]
    stopRunLogicGate <= _T_1 @[ProgramCounter.scala 16:20]
    when stopRunLogicGate : @[ProgramCounter.scala 19:26]
      PCreg <= PCreg @[ProgramCounter.scala 20:11]
      skip @[ProgramCounter.scala 19:26]
    else : @[ProgramCounter.scala 21:22]
      when io.jump : @[ProgramCounter.scala 21:22]
        PCreg <= io.programCounterJump @[ProgramCounter.scala 22:11]
        skip @[ProgramCounter.scala 21:22]
      else : @[ProgramCounter.scala 23:14]
        node _T_2 = add(PCreg, UInt<1>("h01")) @[ProgramCounter.scala 24:19]
        node _T_3 = tail(_T_2, 1) @[ProgramCounter.scala 24:19]
        PCreg <= _T_3 @[ProgramCounter.scala 24:11]
        skip @[ProgramCounter.scala 23:14]
    io.programCounter <= PCreg @[ProgramCounter.scala 27:21]
    
