-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- regMEM.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an N bit MEM register which uses dff
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity regMEM is
  generic(N : Integer := 32);
  port(i_Clk               : in std_logic;     -- Clock input
       i_Rst               : in std_logic;     -- Reset input
       i_WE                : in std_logic;     -- Write enable input
       Halt                : in std_logic;
       v0                  : in std_logic_vector(N-1 downto 0);
       instruction         : in std_logic_vector(N-1 downto 0);
       i_ALU               : in std_logic_vector(N-1 downto 0);     -- Data value input
       i_dataMem           : in std_logic_vector(N-1 downto 0);
       o_Halt              : out std_logic;
       o_v0                : out std_logic_vector(N-1 downto 0);
       instructionO        : out std_logic_vector(N-1 downto 0);
       o_ALU               : out std_logic_vector(N-1 downto 0);
       o_dataMEm           : out std_logic_vector(N-1 downto 0));   -- Data value output

end regMEM;

architecture structure of regMEM is
  
component MEMdff  
  port(i_CLK              : in std_logic;     -- Clock input
       i_RST              : in std_logic;     -- Reset input
       i_WE               : in std_logic;     -- Write enable input
       Halt               : in std_logic;
       v0                 : in std_logic;
       instruction        : in std_logic;
       i_dataMem          : in std_logic;     -- Data value input
       i_ALU              : in std_logic;
       o_Halt             : out std_logic;
       o_v0               : out std_logic;
       instructionO       : out std_logic;
       o_dataMem          : out std_logic;
       o_ALU              : out std_logic);   -- Data value output
end component;
begin

  
  ---------------------------------------------------------------------------
  -- Level 1: and the two inputs with the given select line
  ---------------------------------------------------------------------------
G1: for i in 0 to N-1 generate
dff_design: MEMdff
port MAP(i_CLK              => i_Clk,     -- Clock input
         i_RST              => i_Rst,     -- Reset input
         i_WE               => i_WE,     -- Write enable input
         Halt               => Halt,
         v0                 => v0(i),
         instruction        => instruction(i),
         i_dataMem          => i_dataMem(i),     -- Data value input
         i_ALU              => i_ALU(i),
         o_Halt             => o_Halt,
         o_v0               => o_v0(i),
         instructionO       => instructionO(i),
         o_dataMem          => o_dataMem(i),
         o_ALU              => o_ALU(i));   -- Data value output
end generate;

end structure;