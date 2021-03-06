-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- fullAdder.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of PC logic
--
--
-- NOTES:
-- 11/2/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity PClogic is
  generic(N : integer := 32);
  port(PCin         : in std_logic_vector(N-1 downto 0);
       Branch       : in std_logic;
       Jump         : in std_logic;
       JumpReg      : in std_logic;
       jRegister    : in std_logic_vector(N-1 downto 0);
       instruction  : in std_logic_vector(25 downto 0);
       extendedVal  : in std_logic_vector(N-1 downto 0);
       PCout        : out std_logic_vector(N-1 downto 0));
end PClogic;

architecture structure of PClogic is
  
component PC
  port(PCin     : in std_logic_vector(N-1 downto 0);
       PCout    : out std_logic_vector(N-1 downto 0));
  end component;

component barrelShifter
  port(i_data : in std_logic_vector(N-1 downto 0);
       logArth : in std_logic;
       shift   : in std_logic;
       shiftAmount  : in std_logic_vector(4 downto 0);
       output : out std_logic_vector(N-1 downto 0));
end component;

component mux
  port(s0             : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       oY 	      : out std_logic);
end component;

component NbitfullAdder
  port(Cin            : in std_logic;
       iX             : in std_logic_vector(N-1 downto 0);
       iY             : in std_logic_vector(N-1 downto 0);
       Cout	      : out std_logic;
       output 	      : out std_logic_vector(N-1 downto 0));
end component;

signal instructionHold, tempPC, branchO, tempB, oB, tempJ : std_logic_vector(N-1 downto 0);
signal two : std_logic_vector(4 downto 0);
signal Cin, Cout : std_logic;

begin
  
---------------------------------------------------------------------------------------------------
-- Level 1: Insctruction input goes from 26 to 31 bits
---------------------------------------------------------------------------------------------------
Cin <= '0';
two <= b"00010";

g_PC : PC
port MAP(PCin  => PCin,
         PCout => tempPC);

L1: for i in 0 to 3 generate
instructionHold(i) <= tempPC(31-i);
end generate;

L2: for j in 4 to 5 generate
instructionHold(j) <= '0';
end generate;

L3: for z in 6 to N-1 generate
instructionHold(z) <= instruction(z-6);
end generate;
-----------------------------------------------------------------------------------------------------
-- Level 2: Getting the extended value and shift left logical 2 & feeding into the second full adder
-----------------------------------------------------------------------------------------------------
g_barrelShifter: barrelShifter
port MAP(i_data     => extendedVal,
       logArth      => '0',
       shift        => '1',
       shiftAmount  => two,
       output       => tempB);

g_fullAdder: NbitfullAdder
port MAP(Cin          => Cin,
       iX             => tempPC,
       iY             => tempB,
       Cout	      => Cout,
       output 	      => branchO);
------------------------------------------------------------------------------------------------------
-- Level 3: Muxing the outputs for Jumping and Branching on equal and Not equal
------------------------------------------------------------------------------------------------------
L4: for a in 0 to N-1 generate
g_mux: mux
port MAP(s0           => Branch,
       iX             => tempPC(a),
       iY             => branchO(a),
       oY 	      => oB(a));
end generate;

L5: for b in 0 to N-1 generate
g_mux1: mux
port MAP(s0           => Jump,
       iX             => oB(b),
       iY             => instructionHold(b),
       oY 	      => tempJ(b));
end generate;

-----------------------------------------------------------------------------------------------------------
-- Level 4: Muxing for Jump Register instruction
-----------------------------------------------------------------------------------------------------------
L6: for c in 0 to N-1 generate
g_mux2: mux 
port MAP(s0           => JumpReg,
       iX             => tempJ(c),
       iY             => jRegister(c),
       oY 	      => PCout(c));
end generate;

end structure;