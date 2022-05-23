
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

entity A_Sunit1 is
  generic(N : integer:=32);
  port(x_input  : in std_logic_vector(N-1 downto 0);
	y_input  : in std_logic_vector(N-1 downto 0);
	add_imm : in std_logic_vector(N-1 downto 0);
	selector  : in std_logic;
	ALU_src : in std_logic;
	s_i  : out std_logic_vector(N-1 downto 0));


end A_Sunit1;

architecture structure of A_Sunit1 is
component MUX2_strucn
generic(N : integer:=32);
  port(i_A  : in std_logic_vector(N-1 downto 0);
	i_select  : in std_logic;
	i_C  : in std_logic_vector(N-1 downto 0);
	o_F  : out std_logic_vector(N-1 downto 0));
end component;

component Full_AdderN
  generic(N : integer:=32);
  port(x_i  : in std_logic_vector(N-1 downto 0);
	y_i  : in std_logic_vector(N-1 downto 0);
	carry_i  : in std_logic;
	c_i: out std_logic;
	s_i  : out std_logic_vector(N-1 downto 0));

end component;
component ones_complement
  generic(N : integer:= 32);
 port(i_A  : in std_logic_vector(N-1 downto 0);
       o_F  : out std_logic_vector(N-1 downto 0));

end component;

signal inverse : std_logic_vector(N-1 downto 0);
signal carry_out_1 : std_logic;
signal y_inverse2 : std_logic_vector(N-1 downto 0);
signal output_alu : std_logic_vector(N-1 downto 0);
signal add_sub : std_logic_vector(N-1 downto 0);

begin

u1: MUX2_strucn
port map(
i_A =>y_input,
i_select  =>ALU_src,
i_C  => add_imm,
o_F  =>output_alu) ;

u2: ones_complement
port map(
i_A => output_alu,
o_F => inverse);



u3: MUX2_strucn
port map(
i_A =>output_alu,
i_select  =>selector,
i_C  => inverse,
o_F  =>add_sub) ;

u4: Full_AdderN
port map(
x_i=> x_input,
y_i => add_sub,
carry_i =>selector ,
c_i => carry_out_1,
s_i => s_i);



end structure;