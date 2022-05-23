-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- dataNbitInvert.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of an N bit one's compliment
--
-- NOTES:
-- 9/2/20 by Chimzim Ogbondah:Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity dataNbitInvert is
  generic(N : Integer:= 32);
  port(i_A          : in std_logic_vector(N-1 downto 0);
       o_F          : out std_logic_vector(N-1 downto 0));

end dataNbitInvert;


architecture dataflow of dataNbitInvert is
begin
G1: for i in 0 to N-1 generate
  o_F(i) <= Not i_A(i);
end generate;
  
end dataflow;