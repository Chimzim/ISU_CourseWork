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

entity Full_Adder is
  port(x_i  : in std_logic;
	y_i  : in std_logic;
	carry_i  : in std_logic;
	c_i: out std_logic;
	s_i  : out std_logic);


end Full_Adder;

architecture structure of Full_Adder is
component xorg2
  port(i_A  : in std_logic;
	i_B  : in std_logic;
       o_F  : out std_logic);
end component;

component andg2
   port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);

end component;

component org2
 port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);

end component;

signal e : std_logic;
signal d : std_logic;
signal g : std_logic;
signal h : std_logic;
signal j: std_logic;

begin
u1: xorg2
port map(i_A => x_i,
	i_B => y_i,
	o_F => h);
u2: xorg2
port map(
	i_A => h,
	i_B => carry_i,
	o_F => s_i);
u3: andg2
port map(
	i_A => x_i,
	i_B => y_i,
	o_F => e);
u4: andg2
port map(
	i_A => x_i,
	i_B => carry_i,
	o_F => d);
u5: andg2
port map(
	i_A => y_i,
	i_B => carry_i,
	o_F => g);

u6: org2
port map(
	i_A => e,
	i_B => d,
	o_F => j);
u7: org2
port map(
	i_A => j,
	i_B => g,
	o_F => c_i);
end structure;