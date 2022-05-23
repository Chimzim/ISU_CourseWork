-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- Nbitdff.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an N bit register which uses dff
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity Nbitdff is
  generic(N : Integer := 32);
  port(i_CLK        : in std_logic;     -- Clock input
       in_reset        : in std_logic;     -- Reset input
       in_WEn         : in std_logic;     -- Write enable input
       in_Data          : in std_logic_vector(N-1 downto 0);     -- Data value input
       o_Data          : out std_logic_vector(N-1 downto 0));   -- Data value output

end Nbitdff;

architecture structure of Nbitdff is
  
component dff
port(i_CLK        : in std_logic;     -- Clock input
       i_RST        : in std_logic;     -- Reset input
       i_WE         : in std_logic;     -- Write enable input
       i_D          : in std_logic;     -- Data value input
       o_Q          : out std_logic);   -- Data value output
end component;


begin

  
  ---------------------------------------------------------------------------
  -- Level 1: and the two inputs with the given select line
  ---------------------------------------------------------------------------
G1: for i in 0 to N-1 generate
dff_design: dff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       i_RST        => in_reset,   -- Reset input
       i_WE         => in_WEn,     -- Write enable input
       i_D          => in_Data(i),   -- Data value input
       o_Q          => o_Data(i));   -- Data value output
end generate;

end structure;