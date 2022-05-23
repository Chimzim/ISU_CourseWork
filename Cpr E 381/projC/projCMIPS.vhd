
-------------------------------------------------------------------------
-- Chimzim and Parth
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- projAMips.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an example of project pipeline processor
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-- 1/25/19 by H3::Fixed name conflict with and2 and Quartus.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity MIPS_Processor is
  generic(N : integer:=32);
  port(iClk        : in std_logic;
       iRST            : in std_logic;
       iInstLd         : in std_logic;
       iInstAddr       : in std_logic_vector(N-1 downto 0);
       iInstExt        : in std_logic_vector(N-1 downto 0);
       oALUOut         : out std_logic_vector(N-1 downto 0));

end MIPS_Processor;

architecture structure of MIPS_Processor is
component mux
  port(s0             : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       oY 	      : out std_logic);
end component;

component PC
port(PCin     : in std_logic_vector(N-1 downto 0);
       PCout    : out std_logic_vector(N-1 downto 0));
end component;

component PClogic
  port(PCin         : in std_logic_vector(N-1 downto 0);
       Branch       : in std_logic;
       Jump         : in std_logic;
       JumpReg      : in std_logic;
       jRegister    : in std_logic_vector(N-1 downto 0);
       instruction  : in std_logic_vector(25 downto 0);
       extendedVal  : in std_logic_vector(N-1 downto 0);
       PCout        : out std_logic_vector(N-1 downto 0));
end component;

component fullControlUnit
 port(opcode         : in std_logic_vector(5 downto 0);
      funct          : in std_logic_vector(5 downto 0);
      jrInstr        : out std_logic;
      ALUsrc         : out std_logic;
      MemToReg       : out std_logic;
      MemWr          : out std_logic;
      RegWr          : out std_logic;
      MemRead        : out std_logic;
      RegDst         : out std_logic;
      Branch         : out std_logic;
      Jump           : out std_logic;
      datapath       : out std_logic;
      shiftDir       : out std_logic;
      logArth        : out std_logic;
      ALUctrl        : out std_logic_vector(2 downto 0));
end component;

component registerfile
generic(N : integer:=32);
  port(i_CLK           : in std_logic;     -- Clock input
       in_reset        : in std_logic; 
       in_enable       : in std_logic;        -- Reset input of the write address
       readA1          : in std_logic_vector(4 downto 0);      -- read address 1
       readA2	       : in std_logic_vector(4 downto 0);       -- Read address 2
       writeA          : in std_logic_vector(4 downto 0);     -- Write enable input based on the write address
       data            : in std_logic_vector(N-1 downto 0);     -- Data value input
       output1         : out std_logic_vector(N-1 downto 0);    -- Data value ouput1
       output2         : out std_logic_vector(N-1 downto 0));   -- Data value output2
end component;

component signExt
   generic(N : integer:=32);
  port(i_X             : in std_logic_vector(15 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
end component;

component Nbitmux
  generic(N : Integer := 32);
  port(s             : in std_logic;
       i_X             : in std_logic_vector(N-1 downto 0);
       i_Y             : in std_logic_vector(N-1 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
end component;

component andg2
 port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;
component ALUprojA
  generic(N : Integer := 32);
port(data : in std_logic_vector(N-1 downto 0);
     data1 : in std_logic_vector(N-1 downto 0);
     shiftAmount : in std_logic_vector(4 downto 0);
     ALUsrc      : in std_logic_vector(2 downto 0);
     LogArth     : in std_logic;
     shiftR_L    : in std_logic;
     dataPath    : in std_logic; --determines whether to shift or to take ALU data
     overflow    : out std_logic;
     o_CarryOut  : out std_logic;
     zero        : out std_logic;
     output      : out std_logic_vector(N-1 downto 0));
end component;

component mem

	generic 
	(
		DATA_WIDTH : natural := 32;
		ADDR_WIDTH : natural := 10
	);

	port 
	(
		clk		: in std_logic;
		addr	        : in std_logic_vector((ADDR_WIDTH-1) downto 0);
		data	        : in std_logic_vector((DATA_WIDTH-1) downto 0);
		we		: in std_logic := '1';
		q		: out std_logic_vector((DATA_WIDTH -1) downto 0)
	);
end component;

component IF_ID
  port(i_CLK              : in std_logic;
       stall              : in std_logic;     
       flush              : in std_logic;     
       i_WE               : in std_logic; 
       Halt               : in std_logic;
       v0                 : in std_logic_vector(31 downto 0);    
       i_instr            : in std_logic_vector(31 downto 0);     -- Data value input
       i_PC               : in std_logic_vector(31 downto 0);
       o_Halt             : out std_logic;
       o_v0               : out std_logic_vector(31 downto 0);
       o_instr             : out std_logic_vector(31 downto 0);
       o_PC                : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component ID_EX
  port(i_CLK             : in std_logic;
       stall             : in std_logic;     
       flush             : in std_logic;     
       i_WE              : in std_logic;
       Halt              : in std_logic;
       v0                : in std_logic_vector(N-1 downto 0);
       instruction       : in std_logic_vector(N-1 downto 0);     
       i_Reg1            : in std_logic_vector(31 downto 0);     -- Data value input
       i_Reg2            : in std_logic_vector(31 downto 0);
       i_SignEx          : in std_logic_vector(31 downto 0);
       i_PC              : in std_logic_vector(31 downto 0);
       o_Halt            : out std_logic;
       o_v0              : out std_logic_vector(n-1 downto 0);
       instructionO      : out std_logic_vector(31 downto 0);
       o_Reg1            : out std_logic_vector(31 downto 0);
       o_Reg2            : out std_logic_vector(31 downto 0);
       o_SignEx          : out std_logic_vector(31 downto 0);
       o_PC              : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component EX_MEM
  port(i_CLK            : in std_logic;
       stall            : in std_logic;     
       flush            : in std_logic;     
       i_WE             : in std_logic;
       Halt             : in std_logic;
       v0               : in std_logic_vector(N-1 downto 0);
       instruction      : in std_logic_vector(31 downto 0);     
       i_ALU            : in std_logic_vector(31 downto 0);     -- Data value input
       i_Reg2           : in std_logic_vector(31 downto 0);
       i_PC             : in std_logic_vector(31 downto 0);
       o_Halt           : out std_logic;
       o_v0             : out std_logic_vector(31 downto 0);
       instructionO     : out std_logic_vector(31 downto 0);
       o_ALU            : out std_logic_vector(31 downto 0);
       o_Reg2           : out std_logic_vector(31 downto 0);
       o_PC             : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component MEM_WB
    port(i_CLK                : in std_logic;
       stall                : in std_logic;     
       flush                : in std_logic;     
       i_WE                 : in std_logic;
       Halt                 : in std_logic;
       v0                   : in std_logic_vector(31 downto 0);  
       instruction          : in std_logic_vector(31 downto 0);   
       i_dataMem            : in std_logic_vector(31 downto 0);     -- Data value input
       i_ALU                : in std_logic_vector(31 downto 0);
       o_Halt               : out std_logic;
       o_v0                 : out std_logic_vector(31 downto 0);
       instructionO         : out std_logic_vector(31 downto 0);
       o_dataMem            : out std_logic_vector(31 downto 0);
       o_ALU                : out std_logic_vector(31 downto 0));   -- Data value output
end component;

signal doNothing, instruction, instructionO, data, nextPC, nextPCID, PCi, PCEX : std_logic_vector(N-1 downto 0);
signal Ext, ExtID : std_logic_vector(N-1 downto 0);
signal resultValue, ALUEX, ALUMem, registerEX2 : std_logic_vector(N-1 downto 0);
signal memO : std_logic_vector(N-1 downto 0);
signal registerO, registerID, registerMem : std_logic_vector(N-1 downto 0);
signal registerO2, registerID2: std_logic_vector(N-1 downto 0);
signal muxO : std_logic_vector(N-1 downto 0);
signal ALUsrc, MemToReg, MemWr, RegWr, MemRead, RegDst, Branch, Jump, datapath, shiftDir, logArth, overflow, Cout, zero, jrInstr, toBranch : std_logic;
signal ALUctrl : std_logic_vector(2 downto 0);
signal IorRtype : std_logic_vector(4 downto 0);
signal s_IMemAddr     : std_logic_vector(N-1 downto 0); -- Do not assign this signal, assign to s_NextInstAddr instead
signal s_NextInstAddr : std_logic_vector(N-1 downto 0); -- TODO: use this signal as your intended final instruction memory address input.
signal v0             : std_logic_vector(N-1 downto 0); -- TODO: should be assigned to the output of register 2, used to implement the halt SYSCALL
signal s_Halt         : std_logic;  -- TODO: this signal indicates to the simulation that intended program execution has completed. This case happens when the syscall instruction is observed and the V0 register is at 0x0000000A. This signal is active high and should only be asserted after the last register and memory writes before the syscall are guaranteed to be completed.
signal instructionIF, instructionID, instructionEX, instructionMEM : std_logic_vector(31 downto 0);


begin
--doNothing <= x"00000000";
with iInstLd select
    s_IMemAddr <= s_NextInstAddr when '0',
      iInstAddr when others;

com1: PC
port MAP(PCin     => s_IMemAddr,
       PCout      => s_NextInstAddr);

com2: mem
port map(
clk  => iClk,
addr => s_IMemAddr(11 downto 2), --PC
data => iInstExt,
we   => iInstLd,
q    => instruction);

s_Halt <='1' when (instruction(31 downto 26) = "000000") and (instruction(5 downto 0) = "001100") and (v0 = "00000000000000000000000000001010") else '0';

com3: IF_ID
port MAP(i_CLK              => iClk,
       stall                => '0',    
       flush                => '0',     
       i_WE                 => '1',     
       i_instr              => instruction,     -- Data value input
       i_PC                 => s_NextInstAddr,
       o_instr              => instructionIF,
       o_PC                 => nextPC);   -- Data value output

com4: fullControlUnit
port MAP(opcode         => instructionIF(31 downto 26),
      funct             => instructionIF(5 downto 0),
      jrInstr           => jrInstr,
      ALUsrc            => ALUsrc,
      MemToReg          => MemToReg,
      MemWr             => MemWr,
      RegWr             => RegWr,
      MemRead           => MemRead,
      RegDst            => RegDst,
      Branch            => Branch,
      Jump              => Jump,
      datapath          => datapath,
      shiftDir          => shiftDir,
      logArth           => logArth,
      ALUctrl           => ALUctrl);


L1: for i in 0 to 4 generate
com5: mux
port MAP(s0             => RegDst,
       iX             => instructionIF(i+16),
       iY             => instructionIF(i+11),
       oY 	       => IorRtype(i));
end generate;

com6: registerfile
 port MAP(i_CLK           => iClk,                         -- Clock input
       in_reset           => iRST,
       in_enable          => RegWr,                         -- Reset input of the write address
       readA1             => instructionIF(25 downto 21),     -- read address 1
       readA2	          => instructionIF(20 downto 16),     -- Read address 2
       writeA             => IorRtype,     -- Write enable input based on the write address
       data               => data,    -- Data value input
       output1            => registerO,    -- Data value ouput1
       output2            => registerO2);   -- Data value output2

com7: signExt
  port MAP(i_X       => instructionIF(15 downto 0),
       o_Y 	     => Ext);

com8: ID_EX
port MAP(i_CLK             => iClk,
       stall             => '0',     
       flush             => '0',     
       i_WE              => '1',     
       i_Reg1            => registerO,     -- Data value input
       i_Reg2            => registerO2,
       i_SignEx          => Ext,
       i_PC              => nextPC,
       o_Reg1            => registerID,
       o_Reg2            => registerID2,
       o_SignEx          => ExtID,
       o_PC              => nextPCID);   -- Data value output


com9: Nbitmux
 port MAP(s           => ALUsrc,
       i_X            => registerID2,
       i_Y            => ExtID,
       o_Y 	      => muxO);

com10: ALUprojA
port MAP(data 	 => registerID,
     data1 	 => muxO,
     shiftAmount => instructionIF(10 downto 6),
     ALUsrc      => ALUctrl,
     LogArth     => logArth,
     shiftR_L    => shiftDir,
     dataPath    => datapath,  --determines whether to shift or to take ALU data
     overflow    => overflow,
     o_CarryOut  => Cout,
     zero        => zero,
     output      => resultValue);

com11: andg2
 port MAP(i_A      => Branch,
       i_B         => zero,
       o_F         => toBranch);

com12: PClogic
port MAP(PCin         => nextPCID,
       Branch         => toBranch,
       Jump           => Jump,
       JumpReg        => jrInstr,
       jRegister      => registerID,
       instruction    => instructionIF(25 downto 0),
       extendedVal    => Ext,
       PCout          => PCi);

com13: EX_MEM
port MAP(i_CLK            => iClk,
       stall              => '0',     
       flush              => '0',    
       i_WE               => '1',     
       i_ALU              => resultValue,     -- Data value input
       i_Reg2             => muxO,
       i_PC               => PCi,
       o_ALU              => ALUEX,
       o_Reg2             => registerEX2,
       o_PC               => PCEX);   -- Data value output

com14: mem
port map(
clk  => iClk,
addr => ALUEX(9 downto 0),
data => registerEX2,
we   => MemWr,
q    => memO);

com15: MEM_WB
port MAP(i_CLK                => iClk,
       stall                  => '0',     
       flush                  => '0',    
       i_WE                   => '1',    
       i_dataMem              => memO,     -- Data value input
       i_ALU                  => ALUEX,
       o_dataMem              => registerMem,
       o_ALU                  => ALUMem);   -- Data value output

com16: Nbitmux
port MAP(s           => MemToReg,
       i_X        => ALUMem,
       i_Y        => registerMem,
       o_Y 	  => data);

end structure;