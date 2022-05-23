-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- NbitInvert.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an N bit inverter implmentation
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity NbitInvert is
  generic(N : integer := 32);
  port(i_A  : in std_logic_vector(N-1 downto 0);
       o_F  : out std_logic_vector(N-1 downto 0));

end NbitInvert;

architecture structure of NbitInvert is

component invg
  port(i_A          : in std_logic;
       o_F          : out std_logic);
end component;

begin

-- We loop through and connect all the components and then from there invert their values
G1: for i in 0 to N-1 generate
  and_i: invg 
    port map(i_A  => i_A(i),
  	          o_F  => o_F(i));
end generate;

  
end structure;