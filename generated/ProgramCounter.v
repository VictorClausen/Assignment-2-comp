module ProgramCounter(
  input         clock,
  input         reset,
  input         io_stop,
  input         io_jump,
  input         io_run,
  input  [15:0] io_programCounterJump,
  output [15:0] io_programCounter
);
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_0;
`endif // RANDOMIZE_REG_INIT
  reg [15:0] pc; // @[ProgramCounter.scala 14:21]
  wire [15:0] pcNext = pc + 16'h1; // @[ProgramCounter.scala 17:21]
  wire  _T_1 = ~io_stop; // @[ProgramCounter.scala 23:34]
  wire  enableUpdate = io_run | _T_1; // @[ProgramCounter.scala 23:31]
  assign io_programCounter = pc; // @[ProgramCounter.scala 29:23]
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {1{`RANDOM}};
  pc = _RAND_0[15:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
  always @(posedge clock) begin
    if (reset) begin
      pc <= 16'h0;
    end else if (enableUpdate) begin
      if (io_jump) begin
        pc <= io_programCounterJump;
      end else begin
        pc <= pcNext;
      end
    end
  end
endmodule
