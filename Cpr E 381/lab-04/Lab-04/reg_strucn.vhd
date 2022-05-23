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

entity reg_strucn is
  generic(N : integer:=32);
  port(clock_in  : in std_logic;
	reset  : in std_logic;
	write_n  : in std_logic;
	reg_in  : in std_logic_vector(N-1 downto 0);
	reg_out  : buffer std_logic_vector(N-1 downto 0));
end reg_strucn;

architecture structure of reg_strucn is

component dff
  port(i_CLK        : in std_logic;     -- Clock input
       i_RST        : in std_logic;     -- Reset input
       i_WE         : in std_logic;     -- Write enable input
       i_D          : in std_logic;     -- Data value input
       o_Q          : out std_logic);   -- Data value output
end component;

begin

G1: for i in 0 to N-1 generate
u1: dff
port map(
       i_CLK => clock_in,   
       i_RST => reset,       
       i_WE  => write_n,       
       i_D =>reg_in(i),        
       o_Q => reg_out(i));        

end generate;

end structure;