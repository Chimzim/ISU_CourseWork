library IEEE;
use IEEE.std_logic_1164.all;
use ieee.numeric_std.all;

entity ALU_32bit is
	port(
		i_A : in std_logic_vector(31 downto 0);
		i_B : in std_logic_vector(31 downto 0);
		i_S : in std_logic_vector(2 downto 0);
		o_F : out std_logic_vector(31 downto 0);
		o_Co : out std_logic;
		overflow : out std_logic;
		zero : out std_logic);
end ALU_32bit;

architecture mixed of ALU_32bit is

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

component ALU_1bit is
	port(
		i_A : in std_logic;
		i_B : in std_logic;
		i_Ci: in std_logic;
		i_S : in std_logic_vector(2 downto 0);
		less: in std_logic;
		set : out std_logic;
		o_Co: out std_logic;
		o_F : out std_logic);
end component;

signal b_in , add_sub_output, carryIn, output_signal: std_logic_vector(31 downto 0);
signal zero_val, slt: std_logic;


begin 

b_in <= not i_B when i_S = "001" else
not i_B when i_S = "010" else
i_B;

carryIn(0) <= '0' when i_S = "000" else
'1' when i_S = "001" else
'1' when i_S = "010";

G0: ALU_1bit
	port map(
		i_A => i_A(0),
  		i_B => b_in(0),
		i_Ci=> carryIn(0),
		i_S => i_S,
		less => slt,
		o_Co=> carryIn(1),
		o_F => output_signal(0));
		
		

G1: for i in 1 to 30 generate 
	ALU_1bit_i: ALU_1bit
	port map(
		i_A => i_A(i),
		i_B => b_in(i),
		i_Ci=> carryIn(i),
		i_S => i_S,
		less=> '0',
		o_Co=> carryIn(i+1),
		o_F => output_signal(i));


end generate;


G2: ALU_1bit
	port map(
		i_A => i_A(31),
		i_B => b_in(31),
		i_Ci=> carryIn(31),
		i_S => i_S,
		less=> '0',
		set => slt,
		o_Co=> o_Co,
		o_F => output_signal(31));

overflow <= carryIn(31) xor output_signal(31);




zero_val  <= not (output_signal(0) or output_signal(1) or output_signal(2) or output_signal(3) or output_signal(4) or output_signal(5) or output_signal(6) or output_signal(7) or 
output_signal(8) or output_signal(9) or output_signal(10) or output_signal(11) or output_signal(12) or output_signal(13) or output_signal(14) or output_signal(15) or output_signal(16) or 
output_signal(17) or output_signal(18) or output_signal(19) or output_signal(20) or output_signal(21) or output_signal(22) or output_signal(23) or output_signal(24) or output_signal(25) or 
output_signal(26) or output_signal(27) or output_signal(28) or output_signal(29) or output_signal(30) or output_signal(31));

zero <= zero_val;
o_F  <= output_signal;

end mixed;




















