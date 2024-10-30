;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit CPUTop : 
  module ProgramCounter : 
    input clock : Clock
    input reset : Reset
    output io : {flip stop : UInt<1>, flip jump : UInt<1>, flip run : UInt<1>, flip programCounterJump : UInt<16>, programCounter : UInt<16>}
    
    
  module DataMemory : 
    input clock : Clock
    input reset : Reset
    output io : {flip address : UInt<16>, dataRead : UInt<32>, flip writeEnable : UInt<1>, flip dataWrite : UInt<32>, flip testerEnable : UInt<1>, flip testerAddress : UInt<16>, testerDataRead : UInt<32>, flip testerWriteEnable : UInt<1>, flip testerDataWrite : UInt<32>}
    
    cmem memory : UInt<32>[65536] @[DataMemory.scala 18:20]
    when io.testerEnable : @[DataMemory.scala 20:24]
      read mport _T = memory[io.testerAddress], clock @[DataMemory.scala 22:37]
      io.testerDataRead <= _T @[DataMemory.scala 22:23]
      io.dataRead <= UInt<32>("h00") @[DataMemory.scala 23:17]
      when io.testerWriteEnable : @[DataMemory.scala 24:32]
        write mport _T_1 = memory[io.testerAddress], clock
        _T_1 <= io.testerDataWrite
        io.testerDataRead <= io.testerDataWrite @[DataMemory.scala 26:25]
        skip @[DataMemory.scala 24:32]
      skip @[DataMemory.scala 20:24]
    else : @[DataMemory.scala 28:16]
      read mport _T_2 = memory[io.address], clock @[DataMemory.scala 30:31]
      io.dataRead <= _T_2 @[DataMemory.scala 30:17]
      io.testerDataRead <= UInt<32>("h00") @[DataMemory.scala 31:23]
      when io.writeEnable : @[DataMemory.scala 32:26]
        write mport _T_3 = memory[io.address], clock
        _T_3 <= io.dataWrite
        io.dataRead <= io.dataWrite @[DataMemory.scala 34:19]
        skip @[DataMemory.scala 32:26]
      skip @[DataMemory.scala 28:16]
    
  module ProgramMemory : 
    input clock : Clock
    input reset : Reset
    output io : {flip address : UInt<16>, instructionRead : UInt<32>, flip testerEnable : UInt<1>, flip testerAddress : UInt<16>, testerDataRead : UInt<32>, flip testerWriteEnable : UInt<1>, flip testerDataWrite : UInt<32>}
    
    cmem memory : UInt<32>[65536] @[ProgramMemory.scala 16:20]
    when io.testerEnable : @[ProgramMemory.scala 18:24]
      read mport _T = memory[io.testerAddress], clock @[ProgramMemory.scala 20:37]
      io.testerDataRead <= _T @[ProgramMemory.scala 20:23]
      io.instructionRead <= UInt<32>("h00") @[ProgramMemory.scala 21:24]
      when io.testerWriteEnable : @[ProgramMemory.scala 22:32]
        write mport _T_1 = memory[io.testerAddress], clock
        _T_1 <= io.testerDataWrite
        io.testerDataRead <= io.testerDataWrite @[ProgramMemory.scala 24:25]
        skip @[ProgramMemory.scala 22:32]
      skip @[ProgramMemory.scala 18:24]
    else : @[ProgramMemory.scala 26:16]
      read mport _T_2 = memory[io.address], clock @[ProgramMemory.scala 28:38]
      io.instructionRead <= _T_2 @[ProgramMemory.scala 28:24]
      io.testerDataRead <= UInt<32>("h00") @[ProgramMemory.scala 29:23]
      skip @[ProgramMemory.scala 26:16]
    
  module RegisterFile : 
    input clock : Clock
    input reset : Reset
    output io : {}
    
    
  module ControlUnit : 
    input clock : Clock
    input reset : Reset
    output io : {}
    
    
  module ALU : 
    input clock : Clock
    input reset : Reset
    output io : {}
    
    
  module CPUTop : 
    input clock : Clock
    input reset : UInt<1>
    output io : {done : UInt<1>, flip run : UInt<1>, flip testerDataMemEnable : UInt<1>, flip testerDataMemAddress : UInt<16>, testerDataMemDataRead : UInt<32>, flip testerDataMemWriteEnable : UInt<1>, flip testerDataMemDataWrite : UInt<32>, flip testerProgMemEnable : UInt<1>, flip testerProgMemAddress : UInt<16>, testerProgMemDataRead : UInt<32>, flip testerProgMemWriteEnable : UInt<1>, flip testerProgMemDataWrite : UInt<32>}
    
    inst programCounter of ProgramCounter @[CPUTop.scala 23:30]
    programCounter.clock <= clock
    programCounter.reset <= reset
    inst dataMemory of DataMemory @[CPUTop.scala 24:26]
    dataMemory.clock <= clock
    dataMemory.reset <= reset
    inst programMemory of ProgramMemory @[CPUTop.scala 25:29]
    programMemory.clock <= clock
    programMemory.reset <= reset
    inst registerFile of RegisterFile @[CPUTop.scala 26:28]
    registerFile.clock <= clock
    registerFile.reset <= reset
    inst controlUnit of ControlUnit @[CPUTop.scala 27:27]
    controlUnit.clock <= clock
    controlUnit.reset <= reset
    inst alu of ALU @[CPUTop.scala 28:19]
    alu.clock <= clock
    alu.reset <= reset
    programMemory.io.testerAddress <= io.testerProgMemAddress @[CPUTop.scala 39:34]
    io.testerProgMemDataRead <= programMemory.io.testerDataRead @[CPUTop.scala 40:28]
    programMemory.io.testerDataWrite <= io.testerProgMemDataWrite @[CPUTop.scala 41:36]
    programMemory.io.testerEnable <= io.testerProgMemEnable @[CPUTop.scala 42:33]
    programMemory.io.testerWriteEnable <= io.testerProgMemWriteEnable @[CPUTop.scala 43:38]
    dataMemory.io.testerAddress <= io.testerDataMemAddress @[CPUTop.scala 45:31]
    io.testerDataMemDataRead <= dataMemory.io.testerDataRead @[CPUTop.scala 46:28]
    dataMemory.io.testerDataWrite <= io.testerDataMemDataWrite @[CPUTop.scala 47:33]
    dataMemory.io.testerEnable <= io.testerDataMemEnable @[CPUTop.scala 48:30]
    dataMemory.io.testerWriteEnable <= io.testerDataMemWriteEnable @[CPUTop.scala 49:35]
    
