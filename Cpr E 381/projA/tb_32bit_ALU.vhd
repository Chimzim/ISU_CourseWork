library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity tb_32bit_ALU is 

end tb_32bit_ALU;

architecture behavior of tb_32bit_ALU is

component ALU_32bit is
	port(
		i_A : in std_logic_vector(31 downto 0);
		i_B : in std_logic_vector(31 downto 0);
		i_S : in std_logic_vector(2 downto 0);
		o_F : out std_logic_vector(31 downto 0);
		o_Co : out std_logic;
		overflow : out std_logic;
		zero : out std_logic);
end component;

signal s_A, s_B, s_F : std_logic_vector(31 downto 0);
signal s_S : std_logic_vector(2 downto 0);
signal s_Co, s_zero, s_overflow : std_logic;

begin

DUT : ALU_32bit

	port map(
		i_A => s_A,
		i_B => s_B,
		i_S => s_S,
		o_F => s_F,
		o_Co => s_Co,
		overflow => s_overflow,
		zero => s_zero);

P_TB: process
begin
	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "000";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "001";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "010";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "011";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "100";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "101";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "110";
	wait for 100ns;

	s_A <= x"12345678";
	s_B <= x"87654321";
	s_S <= "111";
	wait for 100ns;

wait;
end process;
end behavior;







