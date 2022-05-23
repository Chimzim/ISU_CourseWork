-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- IF_ID.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of an edge-triggered
-- flip-flop with parallel access and reset.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity IF_ID is

  port(i_CLK              : in std_logic;
       stall              : in std_logic;     
       flush              : in std_logic;     
       i_WE               : in std_logic;     
       i_instr            : in std_logic_vector(31 downto 0);     -- Data value input
       i_PC               : in std_logic_vector(31 downto 0);
       o_instr             : out std_logic_vector(31 downto 0);
       o_PC                : out std_logic_vector(31 downto 0));   -- Data value output

end IF_ID;

architecture structure of IF_ID is

component regIF
  port(i_Clk            : in std_logic;     -- Clock input
       i_Rst            : in std_logic;     -- Reset input
       i_WE           : in std_logic;     -- Write enable input
       i_instr          : in std_logic_vector(31 downto 0);     -- Data value input
       i_PC             : in std_logic_vector(31 downto 0);
       o_instr          : out std_logic_vector(31 downto 0);
       o_PC             : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component org2
port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;

signal toStall : std_logic;

begin

g_org2: org2
port MAP(i_A          => stall,
         i_B          => i_CLK,
         o_F          => toStall);


g_IF: regIF
port MAP(i_Clk            => toStall,
       i_Rst            => flush,     -- Reset input
       i_WE             => i_WE,     -- Write enable input
       i_instr          => i_instr,     -- Data value input
       i_PC             => i_PC,
       o_instr          => o_instr,
       o_PC             => o_PC);   -- Data value output

 end structure;