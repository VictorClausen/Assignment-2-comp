;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit CPUTop : 
  module ProgramCounter : 
    input clock : Clock
    input reset : Reset
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
    output io : {flip aSel : UInt<4>, flip bSel : UInt<4>, flip writeSel : UInt<4>, flip writeEnable : UInt<1>, flip writeData : UInt<32>, a : UInt<32>, b : UInt<32>}
    
    io.a <= UInt<1>("h00") @[RegisterFile.scala 17:7]
    io.b <= UInt<1>("h00") @[RegisterFile.scala 18:7]
    wire _T : UInt<32>[32] @[RegisterFile.scala 22:34]
    _T[0] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[1] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[2] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[3] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[4] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[5] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[6] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[7] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[8] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[9] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[10] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[11] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[12] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[13] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[14] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[15] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[16] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[17] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[18] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[19] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[20] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[21] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[22] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[23] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[24] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[25] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[26] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[27] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[28] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[29] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[30] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    _T[31] <= UInt<32>("h00") @[RegisterFile.scala 22:34]
    reg registers : UInt<32>[32], clock with : (reset => (reset, _T)) @[RegisterFile.scala 22:26]
    when io.writeEnable : @[RegisterFile.scala 24:23]
      registers[io.writeSel] <= io.writeData @[RegisterFile.scala 25:27]
      skip @[RegisterFile.scala 24:23]
    io.a <= registers[io.aSel] @[RegisterFile.scala 27:8]
    io.b <= registers[io.bSel] @[RegisterFile.scala 28:8]
    
  module ControlUnit : 
    input clock : Clock
    input reset : Reset
    output io : {flip opcode : UInt<6>, aluOp : UInt<4>, memRead : UInt<1>, memWrite : UInt<1>, regWrite : UInt<1>, aluSrc : UInt<1>, jump : UInt<1>, jumpIfEqual : UInt<1>, end : UInt<1>}
    
    io.aluOp <= UInt<1>("h00") @[ControlUnit.scala 17:12]
    io.memRead <= UInt<1>("h00") @[ControlUnit.scala 18:14]
    io.memWrite <= UInt<1>("h00") @[ControlUnit.scala 19:15]
    io.regWrite <= UInt<1>("h00") @[ControlUnit.scala 20:15]
    io.aluSrc <= UInt<1>("h00") @[ControlUnit.scala 21:13]
    io.jump <= UInt<1>("h00") @[ControlUnit.scala 22:11]
    io.jumpIfEqual <= UInt<1>("h00") @[ControlUnit.scala 23:18]
    io.end <= UInt<1>("h00") @[ControlUnit.scala 24:10]
    node _T = eq(UInt<1>("h01"), io.opcode) @[Conditional.scala 37:30]
    when _T : @[Conditional.scala 40:58]
      io.aluOp <= UInt<1>("h01") @[ControlUnit.scala 28:16]
      io.regWrite <= UInt<1>("h01") @[ControlUnit.scala 29:19]
      io.aluSrc <= UInt<1>("h01") @[ControlUnit.scala 30:17]
      skip @[Conditional.scala 40:58]
    else : @[Conditional.scala 39:67]
      node _T_1 = eq(UInt<2>("h02"), io.opcode) @[Conditional.scala 37:30]
      when _T_1 : @[Conditional.scala 39:67]
        io.aluOp <= UInt<2>("h02") @[ControlUnit.scala 33:16]
        io.regWrite <= UInt<1>("h01") @[ControlUnit.scala 34:19]
        io.aluSrc <= UInt<1>("h01") @[ControlUnit.scala 35:17]
        skip @[Conditional.scala 39:67]
      else : @[Conditional.scala 39:67]
        node _T_2 = eq(UInt<2>("h03"), io.opcode) @[Conditional.scala 37:30]
        when _T_2 : @[Conditional.scala 39:67]
          io.aluOp <= UInt<2>("h03") @[ControlUnit.scala 38:16]
          io.regWrite <= UInt<1>("h01") @[ControlUnit.scala 39:19]
          skip @[Conditional.scala 39:67]
        else : @[Conditional.scala 39:67]
          node _T_3 = eq(UInt<3>("h04"), io.opcode) @[Conditional.scala 37:30]
          when _T_3 : @[Conditional.scala 39:67]
            io.aluOp <= UInt<3>("h04") @[ControlUnit.scala 42:16]
            io.regWrite <= UInt<1>("h01") @[ControlUnit.scala 43:19]
            io.aluSrc <= UInt<1>("h01") @[ControlUnit.scala 44:17]
            skip @[Conditional.scala 39:67]
          else : @[Conditional.scala 39:67]
            node _T_4 = eq(UInt<3>("h05"), io.opcode) @[Conditional.scala 37:30]
            when _T_4 : @[Conditional.scala 39:67]
              io.memRead <= UInt<1>("h01") @[ControlUnit.scala 47:18]
              io.regWrite <= UInt<1>("h01") @[ControlUnit.scala 48:19]
              skip @[Conditional.scala 39:67]
            else : @[Conditional.scala 39:67]
              node _T_5 = eq(UInt<3>("h06"), io.opcode) @[Conditional.scala 37:30]
              when _T_5 : @[Conditional.scala 39:67]
                io.memWrite <= UInt<1>("h01") @[ControlUnit.scala 51:19]
                skip @[Conditional.scala 39:67]
              else : @[Conditional.scala 39:67]
                node _T_6 = eq(UInt<3>("h07"), io.opcode) @[Conditional.scala 37:30]
                when _T_6 : @[Conditional.scala 39:67]
                  io.jump <= UInt<1>("h01") @[ControlUnit.scala 54:15]
                  skip @[Conditional.scala 39:67]
                else : @[Conditional.scala 39:67]
                  node _T_7 = eq(UInt<4>("h08"), io.opcode) @[Conditional.scala 37:30]
                  when _T_7 : @[Conditional.scala 39:67]
                    io.jumpIfEqual <= UInt<1>("h01") @[ControlUnit.scala 57:22]
                    skip @[Conditional.scala 39:67]
                  else : @[Conditional.scala 39:67]
                    node _T_8 = eq(UInt<4>("h09"), io.opcode) @[Conditional.scala 37:30]
                    when _T_8 : @[Conditional.scala 39:67]
                      io.end <= UInt<1>("h01") @[ControlUnit.scala 60:14]
                      skip @[Conditional.scala 39:67]
    
  module ALU : 
    input clock : Clock
    input reset : Reset
    output io : {flip input1 : UInt<32>, flip input2 : UInt<32>, compariResults : UInt<1>, flip sel : UInt<4>, output : UInt<32>}
    
    io.output <= UInt<1>("h00") @[ALU.scala 14:13]
    io.compariResults <= UInt<1>("h00") @[ALU.scala 15:21]
    node _T = eq(UInt<1>("h00"), io.sel) @[Conditional.scala 37:30]
    when _T : @[Conditional.scala 40:58]
      node _T_1 = add(io.input1, io.input2) @[ALU.scala 18:38]
      node _T_2 = tail(_T_1, 1) @[ALU.scala 18:38]
      io.output <= _T_2 @[ALU.scala 18:25]
      skip @[Conditional.scala 40:58]
    else : @[Conditional.scala 39:67]
      node _T_3 = eq(UInt<1>("h01"), io.sel) @[Conditional.scala 37:30]
      when _T_3 : @[Conditional.scala 39:67]
        node _T_4 = sub(io.input1, io.input2) @[ALU.scala 19:38]
        node _T_5 = tail(_T_4, 1) @[ALU.scala 19:38]
        io.output <= _T_5 @[ALU.scala 19:25]
        skip @[Conditional.scala 39:67]
      else : @[Conditional.scala 39:67]
        node _T_6 = eq(UInt<2>("h02"), io.sel) @[Conditional.scala 37:30]
        when _T_6 : @[Conditional.scala 39:67]
          node _T_7 = or(io.input1, io.input2) @[ALU.scala 20:38]
          io.output <= _T_7 @[ALU.scala 20:25]
          skip @[Conditional.scala 39:67]
        else : @[Conditional.scala 39:67]
          node _T_8 = eq(UInt<2>("h03"), io.sel) @[Conditional.scala 37:30]
          when _T_8 : @[Conditional.scala 39:67]
            io.output <= io.input1 @[ALU.scala 21:25]
            skip @[Conditional.scala 39:67]
          else : @[Conditional.scala 39:67]
            node _T_9 = eq(UInt<3>("h04"), io.sel) @[Conditional.scala 37:30]
            when _T_9 : @[Conditional.scala 39:67]
              node _T_10 = eq(io.input1, io.input2) @[ALU.scala 23:46]
              io.compariResults <= _T_10 @[ALU.scala 23:33]
              skip @[Conditional.scala 39:67]
            else : @[Conditional.scala 39:67]
              node _T_11 = eq(UInt<3>("h05"), io.sel) @[Conditional.scala 37:30]
              when _T_11 : @[Conditional.scala 39:67]
                io.compariResults <= UInt<1>("h01") @[ALU.scala 24:33]
                skip @[Conditional.scala 39:67]
    
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
    programCounter.io.run <= io.run @[CPUTop.scala 31:25]
    programMemory.io.address <= programCounter.io.programCounter @[CPUTop.scala 32:28]
    node _T = bits(programMemory.io.instructionRead, 31, 26) @[CPUTop.scala 38:39]
    controlUnit.io.opcode <= _T @[CPUTop.scala 38:25]
    node _T_1 = bits(programMemory.io.instructionRead, 25, 21) @[CPUTop.scala 41:38]
    registerFile.io.aSel <= _T_1 @[CPUTop.scala 41:24]
    node _T_2 = bits(programMemory.io.instructionRead, 20, 16) @[CPUTop.scala 42:38]
    registerFile.io.bSel <= _T_2 @[CPUTop.scala 42:24]
    node _T_3 = bits(programMemory.io.instructionRead, 15, 11) @[CPUTop.scala 43:42]
    registerFile.io.writeSel <= _T_3 @[CPUTop.scala 43:28]
    registerFile.io.writeEnable <= controlUnit.io.regWrite @[CPUTop.scala 44:31]
    reg dataReadReg : UInt, clock @[CPUTop.scala 47:28]
    dataReadReg <= dataMemory.io.dataRead @[CPUTop.scala 47:28]
    node _T_4 = mux(controlUnit.io.memRead, dataReadReg, alu.io.output) @[CPUTop.scala 48:35]
    registerFile.io.writeData <= _T_4 @[CPUTop.scala 48:29]
    alu.io.input1 <= registerFile.io.a @[CPUTop.scala 51:17]
    node _T_5 = bits(programMemory.io.instructionRead, 15, 0) @[CPUTop.scala 52:58]
    node _T_6 = mux(controlUnit.io.aluSrc, _T_5, registerFile.io.b) @[CPUTop.scala 52:23]
    alu.io.input2 <= _T_6 @[CPUTop.scala 52:17]
    alu.io.sel <= controlUnit.io.aluOp @[CPUTop.scala 53:14]
    dataMemory.io.address <= alu.io.output @[CPUTop.scala 56:25]
    dataMemory.io.dataWrite <= registerFile.io.b @[CPUTop.scala 57:27]
    dataMemory.io.writeEnable <= controlUnit.io.memWrite @[CPUTop.scala 58:29]
    programCounter.io.stop <= controlUnit.io.end @[CPUTop.scala 61:26]
    programCounter.io.jump <= controlUnit.io.jump @[CPUTop.scala 62:26]
    node _T_7 = bits(programMemory.io.instructionRead, 15, 0) @[CPUTop.scala 63:54]
    programCounter.io.programCounterJump <= _T_7 @[CPUTop.scala 63:40]
    io.done <= controlUnit.io.end @[CPUTop.scala 66:11]
    programMemory.io.testerAddress <= io.testerProgMemAddress @[CPUTop.scala 69:34]
    io.testerProgMemDataRead <= programMemory.io.testerDataRead @[CPUTop.scala 70:28]
    programMemory.io.testerDataWrite <= io.testerProgMemDataWrite @[CPUTop.scala 71:36]
    programMemory.io.testerEnable <= io.testerProgMemEnable @[CPUTop.scala 72:33]
    programMemory.io.testerWriteEnable <= io.testerProgMemWriteEnable @[CPUTop.scala 73:38]
    dataMemory.io.testerAddress <= io.testerDataMemAddress @[CPUTop.scala 76:31]
    io.testerDataMemDataRead <= dataMemory.io.testerDataRead @[CPUTop.scala 77:28]
    dataMemory.io.testerDataWrite <= io.testerDataMemDataWrite @[CPUTop.scala 78:33]
    dataMemory.io.testerEnable <= io.testerDataMemEnable @[CPUTop.scala 79:30]
    dataMemory.io.testerWriteEnable <= io.testerDataMemWriteEnable @[CPUTop.scala 80:35]
    
