;buildInfoPackage: chisel3, version: 3.3.2, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit Hello : 
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
    
  module Hello : 
    input clock : Clock
    input reset : UInt<1>
    output io : {done : UInt<1>, flip run : UInt<1>, flip testerDataMemEnable : UInt<1>, flip testerDataMemAddress : UInt<16>, testerDataMemDataRead : UInt<32>, flip testerDataMemWriteEnable : UInt<1>, flip testerDataMemDataWrite : UInt<32>}
    
    inst dataMemory of DataMemory @[Hello.scala 33:26]
    dataMemory.clock <= clock
    dataMemory.reset <= reset
    reg addressCounterReg : UInt<999999>, clock with : (reset => (reset, UInt<999999>("h00"))) @[Hello.scala 35:34]
    reg dataReg : UInt<32>, clock with : (reset => (reset, UInt<32>("h00"))) @[Hello.scala 36:24]
    io.done <= UInt<1>("h00") @[Hello.scala 38:11]
    node _T = eq(addressCounterReg, UInt<16>("h0320")) @[Hello.scala 39:26]
    when _T : @[Hello.scala 39:42]
      io.done <= UInt<1>("h01") @[Hello.scala 40:13]
      addressCounterReg <= addressCounterReg @[Hello.scala 41:23]
      skip @[Hello.scala 39:42]
    else : @[Hello.scala 42:16]
      when io.run : @[Hello.scala 43:17]
        node _T_1 = add(addressCounterReg, UInt<16>("h01")) @[Hello.scala 44:46]
        node _T_2 = tail(_T_1, 1) @[Hello.scala 44:46]
        addressCounterReg <= _T_2 @[Hello.scala 44:25]
        skip @[Hello.scala 43:17]
      skip @[Hello.scala 42:16]
    node _T_3 = bits(addressCounterReg, 0, 0) @[Hello.scala 48:25]
    node _T_4 = eq(_T_3, UInt<1>("h00")) @[Hello.scala 48:29]
    when _T_4 : @[Hello.scala 48:42]
      dataReg <= dataMemory.io.dataRead @[Hello.scala 49:13]
      skip @[Hello.scala 48:42]
    wire invertedPixel : UInt<32> @[Hello.scala 52:27]
    node _T_5 = bits(dataReg, 7, 0) @[Hello.scala 53:43]
    node _T_6 = not(_T_5) @[Hello.scala 53:35]
    node _T_7 = cat(UInt<24>("h00"), _T_6) @[Cat.scala 29:58]
    invertedPixel <= _T_7 @[Hello.scala 53:17]
    node _T_8 = bits(addressCounterReg, 0, 0) @[Hello.scala 55:49]
    node _T_9 = bits(addressCounterReg, 16, 1) @[Hello.scala 55:71]
    node _T_10 = add(_T_9, UInt<16>("h0190")) @[Hello.scala 55:77]
    node _T_11 = tail(_T_10, 1) @[Hello.scala 55:77]
    node _T_12 = bits(addressCounterReg, 16, 1) @[Hello.scala 55:108]
    node _T_13 = mux(_T_8, _T_11, _T_12) @[Hello.scala 55:31]
    dataMemory.io.address <= _T_13 @[Hello.scala 55:25]
    dataMemory.io.dataWrite <= invertedPixel @[Hello.scala 56:27]
    node _T_14 = bits(addressCounterReg, 0, 0) @[Hello.scala 57:49]
    dataMemory.io.writeEnable <= _T_14 @[Hello.scala 57:29]
    dataMemory.io.testerAddress <= io.testerDataMemAddress @[Hello.scala 60:31]
    io.testerDataMemDataRead <= dataMemory.io.testerDataRead @[Hello.scala 61:28]
    dataMemory.io.testerDataWrite <= io.testerDataMemDataWrite @[Hello.scala 62:33]
    dataMemory.io.testerEnable <= io.testerDataMemEnable @[Hello.scala 63:30]
    dataMemory.io.testerWriteEnable <= io.testerDataMemWriteEnable @[Hello.scala 64:35]
    
