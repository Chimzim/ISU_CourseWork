-------------------------------------------------------------------------
-- Joseph Zambreno
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- generate_example.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an example of using generic ports to
-- drive a "generate / for" block. 
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-- 1/25/19 by H3::Fixed name conflict with and2 and Quartus.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity sign_extension is
  generic(N : integer:=32);
  port(immediate  : in std_logic_vector(16-1 downto 0);
	output  : out std_logic_vector(N-1 downto 0));

end sign_extension;

architecture dataflow of sign_extension is
begin

G1: for i in 0 to 16-1 generate
output(i) <= immediate(i);
end generate;

G2: for i in 16 to 32-1 generate
with immediate(15) select
output(i) <= '1' when '1',
 '0' when others;
end generate;

end dataflow;