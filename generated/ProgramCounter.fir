;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit ProgramCounter : 
  module ProgramCounter : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip stop : UInt<1>, flip jump : UInt<1>, flip run : UInt<1>, flip programCounterJump : UInt<16>, programCounter : UInt<16>}
    
    reg pc : UInt<16>, clock with : (reset => (reset, UInt<16>("h00"))) @[ProgramCounter.scala 14:21]
    node _T = add(pc, UInt<1>("h01")) @[ProgramCounter.scala 17:21]
    node pcNext = tail(_T, 1) @[ProgramCounter.scala 17:21]
    node nextPc = mux(io.jump, io.programCounterJump, pcNext) @[ProgramCounter.scala 20:21]
    node _T_1 = eq(io.stop, UInt<1>("h00")) @[ProgramCounter.scala 23:34]
    node enableUpdate = or(io.run, _T_1) @[ProgramCounter.scala 23:31]
    node _T_2 = mux(enableUpdate, nextPc, pc) @[ProgramCounter.scala 26:14]
    pc <= _T_2 @[ProgramCounter.scala 26:8]
    io.programCounter <= pc @[ProgramCounter.scala 29:23]
    
