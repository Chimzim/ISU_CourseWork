-------------------------------------------------------------------------
-- Chimzim Ogbondah & Parth
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- controlUnit.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of the full control unit design
-- that will give the correct signal output based on funct and opcode as well as ALU control signals
-- NOTES:
-- 10/2/20 by Chimzim:Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity fullControlUnit is
 port(opcode         : in std_logic_vector(5 downto 0);
      funct          : in std_logic_vector(5 downto 0);
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

end fullControlUnit;

architecture structure of fullControlUnit is

component ALUcontrol
 port(funct        : in std_logic_vector(5 downto 0);
      ALUop        : in std_logic_vector(1 downto 0);
      LogArth      : out std_logic;
      shiftDir     : out std_logic;
      datapath     : out std_logic;
      ALUctrl      : out std_logic_vector(2 downto 0));
end component;

component controlUnit
 port(opcode         : in std_logic_vector(5 downto 0);
      funct          : in std_logic_vector(5 downto 0);
      cALUsrc        : out std_logic;
      cMemToReg      : out std_logic;
      cMemWr         : out std_logic;
      cRegWr         : out std_logic;
      cALUop         : out std_logic_vector(1 downto 0);
      cRegDst        : out std_logic;
      cBranch        : out std_logic;
      cJump          : out std_logic;
      cMemRead       : out std_logic);
end component;

signal tempALUop : std_logic_vector(1 downto 0);

begin
---------------------------------------------------------------------------
-- Level 1: feeding the inputs into the control unit
---------------------------------------------------------------------------
g_controlUnit: controlUnit
port MAP(opcode       => opcode,
      funct            => funct,
      cALUsrc          => ALUsrc,
      cMemToReg        => MemToReg,
      cMemWr           => MemWr,
      cRegWr           => RegWr,
      cALUop           => tempALUop,
      cRegDst          => RegDst,
      cBranch          => Branch,
      cJump            => Jump,
      cMemRead         => MemRead);

---------------------------------------------------------------------------
-- Level 2: feeding the inputs into the ALU control unit
---------------------------------------------------------------------------
g_ALUcontrol: ALUcontrol
 port MAP(funct        => funct,
      ALUop            => tempALUop,
      LogArth          => logArth,
      shiftDir         => shiftDir,
      datapath         => datapath,
      ALUctrl          => ALUctrl);

end structure;