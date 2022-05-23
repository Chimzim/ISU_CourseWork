-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- signExt.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of 16 bit zero extender
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity signExt is
  generic(N : Integer := 32);
  port(i_X             : in std_logic_vector(15 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
end signExt;

architecture dataflow of signExt is
  
signal signExtending : std_logic_vector(15 downto 0);

begin
signExtending <= x"1111";

G1: for i in 0 to 15 generate
o_Y(i) <= i_X(i) and i_X(i);
end generate;

G2: for j in 16 to N-1 generate
o_Y(j) <= signExtending(j-16);
end generate;


end dataflow;