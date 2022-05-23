-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- dataNbitmux.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a dataflow implementation of the N bit 2-1 mux
--
-- NOTES:
-- 9/2/20 by Chimzim Ogbondah:Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity dataNbitmux is
  generic(N : Integer:= 32);
  port(s		: in std_logic;
	i_X          : in std_logic_vector(N-1 downto 0);
	i_Y          : in std_logic_vector(N-1 downto 0);
       o_F          : out std_logic_vector(N-1 downto 0));

end dataNbitmux;


architecture dataflow of dataNbitmux is
begin
G1: for i in 0 to N-1 generate
  o_F(i) <= ( ((Not s) AND i_X(i)) AND (Not i_Y(i)) ) OR ( (s AND (Not i_X(i)) ) AND i_Y(i) );
end generate;
  
end dataflow;