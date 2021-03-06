-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- regIF.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an N bit IF register which uses dff
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity regIF is
  generic(N : Integer := 32);
  port(i_Clk            : in std_logic;     -- Clock input
       i_Rst            : in std_logic;     -- Reset input
       i_WE             : in std_logic;     -- Write enable input
       Halt             : in std_logic;
       v0               : in std_logic_vector(N-1 downto 0);
       i_instr          : in std_logic_vector(N-1 downto 0);     -- Data value input
       i_PC             : in std_logic_vector(N-1 downto 0);
       o_Halt           : out std_logic;
       o_v0             : out std_logic_vector(N-1 downto 0);
       o_instr          : out std_logic_vector(N-1 downto 0);
       o_PC             : out std_logic_vector(N-1 downto 0));   -- Data value output

end regIF;

architecture structure of regIF is
  
component IFdff
  port(i_CLK        : in std_logic;     -- Clock input
       i_RST        : in std_logic;     -- Reset input
       i_WE         : in std_logic;     -- Write enable input
       Halt         : in std_logic;
       v0           : in std_logic;
       i_instr      : in std_logic;     -- Data value input
       i_PC         : in std_logic;
       o_Halt       : out std_logic;
       o_v0         : out std_logic;
       o_instr      : out std_logic;
       o_PC         : out std_logic);   -- Data value output
end component;


begin

  
  ---------------------------------------------------------------------------
  -- Level 1: and the two inputs with the given select line
  ---------------------------------------------------------------------------
G1: for i in 0 to N-1 generate
dff_design: IFdff
port MAP(i_CLK        => i_Clk,     -- Clock input
         i_RST        => i_Rst,     -- Reset input
         i_WE         => i_WE,     -- Write enable input
         Halt         => Halt,
         v0           => v0(i),
         i_instr      => i_instr(i),     -- Data value input
         i_PC         => i_PC(i),
         o_Halt       => o_Halt,
         o_v0         => o_v0(i),
         o_instr      => o_instr(i),
         o_PC         => o_PC(i));   -- Data value output
end generate;

end structure;