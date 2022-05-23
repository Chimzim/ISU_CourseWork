library IEEE;
use IEEE.std_logic_1164.all;

entity mux_8to1 is
	port(
		i_0 : in std_logic;
		i_1 : in std_logic;
		i_2 : in std_logic;
		i_3 : in std_logic;
		i_4 : in std_logic;
		i_5 : in std_logic;
		i_6 : in std_logic;
		i_7 : in std_logic;
		i_S : in std_logic_vector(2 downto 0);
		o_F : out std_logic);

end mux_8to1;

architecture conditional of mux_8to1 is

begin

o_F <= (i_0) when i_S = "000" else
(i_1) when i_S = "001" else
(i_2) when i_S = "010" else
(i_3) when i_S = "011" else
(i_4) when i_S = "100" else
(i_5) when i_S = "101" else
(i_6) when i_S = "110" else
(i_7) when i_S = "111";

end conditional;