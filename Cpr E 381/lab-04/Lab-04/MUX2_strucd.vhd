-------------------------------------------------------------------------
-- Joseph Zambreno
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- invg.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of a 1-input NOT 
-- gate.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-- 1/16/19 by H3::Changed name to avoid name conflict with Quartus 
--         primitives.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity MUX2_strucd is
  generic(N : integer:= 32);
  port(i_A  : in std_logic_vector(N-1 downto 0);
	i_select  : in std_logic;
	i_C  : in std_logic_vector(N-1 downto 0);
       o_F  : out std_logic_vector(N-1 downto 0));

end MUX2_strucd;

architecture dataflow of MUX2_strucd is
signal e_sel : std_logic_vector(N-1 downto 0);
signal d : std_logic_vector(N-1 downto 0);
signal g : std_logic_vector(N-1 downto 0);
begin
G1: for i in 0 to N-1 generate
  e_sel(i) <= not i_select;
  d(i) <= e_sel(i) and i_A(i);
  g(i) <= i_select and i_C(i);
  o_F(i) <=d(i) or g(i);
  end generate;
end dataflow;