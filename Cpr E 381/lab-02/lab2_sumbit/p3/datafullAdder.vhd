-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- dataNfullAdder.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a dataflow implementation of the N bit 2-1 mux
--
-- NOTES:
-- 9/2/20 by Chimzim Ogbondah:Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity datafullAdder is
  generic(N : Integer:= 32);
  port(Cin		: in std_logic;
	i_X          : in std_logic_vector(N-1 downto 0);
	i_Y          : in std_logic_vector(N-1 downto 0);
	Cout	:  out std_logic_vector(N-1 downto 0);
       o_F          : out std_logic_vector(N-1 downto 0));

end datafullAdder;


architecture dataflow of datafullAdder is
signal Carrybit, CiniX, iXiY, iXorY, CiniXorY: std_logic_vector(N-1 downto 0);
begin

CiniX(0) <= (Cin xor i_X(0));
o_F <= CiniX(0) xor i_Y(0);
iXiY(0) <= i_X(0) AND i_Y(0);
iXorY(0) <= i_X(0) xor i_Y(0);
CiniXorY(0) <= Cin AND iXorY(0);
Cout(0) <=  iXiY(0) OR CiniXorY(0);
Carrybit(0) <= Cout(0);

G1: for i in 1 to N-1 generate
 CiniX(i) <= (Carrybit(i-1) xor i_X(i));
o_F <= CiniX(i) xor i_Y(i);
iXiY(i) <= i_X(i) AND i_Y(i);
iXorY(i) <= i_X(i) xor i_Y(i);
CiniXorY(i) <= Cin AND iXorY(i);
Cout(i) =>  iXiY(i) OR CiniXorY(i);
Carrybit(i) <= Cout(i);
end generate;
  
end dataflow;