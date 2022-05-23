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

entity Full_AdderN is
  generic(N : integer:=32);
  port(x_i  : in std_logic_vector(N-1 downto 0);
	y_i  : in std_logic_vector(N-1 downto 0);
	carry_i  : in std_logic;
	c_i: out std_logic;
	s_i  : out std_logic_vector(N-1 downto 0));


end Full_AdderN;

architecture structure of Full_AdderN is
component Full_Adder
port(x_i  : in std_logic;
	y_i  : in std_logic;
	carry_i  : in std_logic;
	c_i: out std_logic;
	s_i  : out std_logic);
end component;

signal e : std_logic;
signal d : std_logic;
signal g : std_logic;
signal h : std_logic_vector(N-1 downto 0);
signal car_bit : std_logic_vector(N downto 0);
signal j: std_logic;

begin
G1: for i in 0 to N-1 generate
u1: Full_Adder
port map(
x_i => x_i(i),
y_i => y_i(i),
carry_i => car_bit(i),
c_i => car_bit(i+1),
s_i => s_i(i));

end generate;
c_i <= car_bit(N);
car_bit(0) <= carry_i;
end structure;