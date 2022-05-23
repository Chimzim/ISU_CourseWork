library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity ALU_1bit is
	port(
		i_A :	in std_logic;
		i_B :	in std_logic;
		i_Ci:	in std_logic;
		i_S :	in std_logic_vector(2 downto 0);
		less:	in std_logic;
		set :	out std_logic;
		o_Co:	out std_logic;
		o_F :	out std_logic
	);

end ALU_1bit;

architecture mixed of ALU_1bit is

component full_adder_struct is
	port(
		i_A: in std_logic;
		i_B: in std_logic;
		i_Ci: in std_logic;
		o_S: out std_logic;
		o_Co: out std_logic
	);
end component;

component mux_8to1 is
	port(
		i_0: in std_logic;
		i_1: in std_logic;
		i_2: in std_logic;
		i_3: in std_logic;
		i_4: in std_logic;
		i_5: in std_logic;
		i_6: in std_logic;
		i_7: in std_logic;
		i_S: in std_logic_vector(2 downto 0);
		o_F: out std_logic);
end component;

signal add, Carry, inB, slt, andR, orR, xorR, nandR, norR : std_logic;

begin

inB <= i_B when i_S = "000" else
not i_B when i_S = "001";

g1: full_adder_struct
	port map(
		i_A => i_A,
		i_B => i_B,
		i_Ci => i_Ci,
		o_S => add,
		o_Co => Carry);



andR <= i_A and i_B;
orR <= i_A or i_B;
xorR <= i_A xor i_B;
nandR <= i_A nand i_B;
norR <= i_A nor i_B;

slt<=less;

set<=add;

o_Co <= Carry;

g3: mux_8to1
	port map(
		i_0 => add,
		i_1 => add,
		i_2 => slt,
		i_3 => andR,
		i_4 => orR,
		i_5 => xorR,
		i_6 => nandR,
		i_7 => norR,
		i_S => i_S,
		o_F => o_F);

end mixed;