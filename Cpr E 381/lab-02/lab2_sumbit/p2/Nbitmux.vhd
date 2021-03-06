-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- Nbitmux.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of the 2-1 mux
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity Nbitmux is
  generic(N : Integer := 32);
  port(s             : in std_logic;
       i_X             : in std_logic_vector(N-1 downto 0);
       i_Y             : in std_logic_vector(N-1 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));

end Nbitmux;

architecture structure of Nbitmux is
  
component mux
port(s0             : in std_logic;
     iX             : in std_logic;
     iY             : in std_logic;
     oY 	    : out std_logic);
end component;


begin

  
  ---------------------------------------------------------------------------
  -- Level 1: and the two inputs with the given select line
  ---------------------------------------------------------------------------
G1: for i in 0 to N-1 generate
m_design: mux
port MAP(s0             => s,
       iX             => i_X(i),
       iY             => i_Y(i),
       oY 	      => o_Y(i));
end generate;

end structure;