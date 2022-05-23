library IEEE;
use IEEE.std_logic_1164.all;

entity full_adder_struct is 
	port(i_A: in std_logic;
		i_B: in std_logic;
		i_Ci: in std_logic;
		o_S: out std_logic;
		o_Co: out std_logic);
end full_adder_struct;

architecture structure of full_adder_struct is

component andg2
	port (i_A: in std_logic;
		i_B: in std_logic;
		o_F: out std_logic);
end component;

component org2
	port (i_A: in std_logic;
		i_B: in std_logic;
		o_F: out std_logic);
end component;

component xorg2
	port (i_A: in std_logic;
		i_B: in std_logic;
		o_F: out std_logic);
end component;

signal aAndB, aXorB, sig0 : std_logic;

begin

g1: andg2
	port MAP(i_A	=> i_A,
			i_B	=> i_B,
			o_F	=> aAndB);

g2: xorg2
	port MAP(i_A	=> i_A,
			i_B	=> i_B,
			o_F	=> aXorB);

g5: xorg2
	port MAP(i_A	=> i_Ci,
			i_B	=> aXorB,
			o_F	=> o_S);

g6: andg2
	port MAP(i_A	=> i_Ci,
			i_B	=> aXorB,
			o_F	=> sig0);

g7: org2
	port MAP(i_A	=> sig0,
			i_B	=> aAndB,
			o_F	=> o_Co);

end structure;
	


















