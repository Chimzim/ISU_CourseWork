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

entity MUX2_strucn is
  generic(N : integer:=32);
  port(i_A  : in std_logic_vector(N-1 downto 0);
	i_select  : in std_logic;
	i_C  : in std_logic_vector(N-1 downto 0);
	o_F  : out std_logic_vector(N-1 downto 0));

end MUX2_strucn;

architecture structure of MUX2_strucn is
component invg
  port(i_A  : in std_logic;
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

signal e_sel : std_logic;
signal d : std_logic_vector(N-1 downto 0);
signal g : std_logic_vector(N-1 downto 0);
begin
G1: for i in 0 to N-1 generate
u1: invg
port map(
	i_A => i_select,
	o_F => e_sel);
u2: andg2
port map(
	i_A => i_A(i),
	i_B => e_sel,
	o_F => d(i));
u3: andg2
port map(
	i_A => i_select,
	i_B => i_C(i),
	o_F => g(i));
u4: org2
port map(
	i_A => d(i),
	i_B => g(i),
	o_F => o_F(i));
end generate;
end structure;