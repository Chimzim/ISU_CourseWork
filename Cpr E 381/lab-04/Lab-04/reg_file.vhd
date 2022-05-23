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

entity reg_file is
  generic(N : integer:=32);
  port(clock_in  : in std_logic;
	reset  : in std_logic;
	enable  : in std_logic;
	reg_selector : in std_logic_vector(5-1 downto 0); --the register to choose for the decoder
	reg_selector2 : in std_logic_vector(5-1 downto 0); --register to choose for the MUX...these are read outputs
	reg_selector3 : in std_logic_vector(5-1 downto 0); --register to choose for the second mux... these are read outputs
	reg_in  : in std_logic_vector(N-1 downto 0); --data_in
	reg_out  : out std_logic_vector(N-1 downto 0);
	reg_out2 : out std_logic_vector(N-1 downto 0));
end reg_file;

architecture structure of reg_file is

component reg_strucn
  generic(N : integer:=32);
  port(clock_in  : in std_logic;
	reset  : in std_logic;
	write_n  : in std_logic;
	reg_in  : in std_logic_vector(N-1 downto 0);
	reg_out  : buffer std_logic_vector(N-1 downto 0));
end component;

component my_decoder
port(dec_in  : in std_logic_vector(5-1 downto 0);
	dec_out  : buffer std_logic_vector(32-1 downto 0));
end component;
component my_32mux
 port(D0,D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12,D13,D14,D15,D16,D17,D18,D19,D20,D21,D22,D23,D24,D25,D26,D27,D28,D29,D30,D31  : in std_logic_vector(32-1 downto 0);
	SEL : in std_logic_vector(5-1 downto 0);
	mx_out  : buffer std_logic_vector(32-1 downto 0));
end component;
component andg2
port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;
signal decoder_out : std_logic_vector(N-1 downto 0);
signal enable_and : std_logic_vector(N-1 downto 0);
signal output_1 : std_logic_vector(N-1 downto 0);
signal output_2 : std_logic_vector(N-1 downto 0);
signal output_3 : std_logic_vector(N-1 downto 0);
signal output_4 : std_logic_vector(N-1 downto 0);
signal output_5 : std_logic_vector(N-1 downto 0);
signal output_6 : std_logic_vector(N-1 downto 0);
signal output_7 : std_logic_vector(N-1 downto 0);
signal output_8 : std_logic_vector(N-1 downto 0);
signal output_9 : std_logic_vector(N-1 downto 0);
signal output_10 : std_logic_vector(N-1 downto 0);
signal output_11 : std_logic_vector(N-1 downto 0);
signal output_12 : std_logic_vector(N-1 downto 0);
signal output_13 : std_logic_vector(N-1 downto 0);
signal output_14 : std_logic_vector(N-1 downto 0);
signal output_15 : std_logic_vector(N-1 downto 0);
signal output_16 : std_logic_vector(N-1 downto 0);
signal output_17 : std_logic_vector(N-1 downto 0);
signal output_18 : std_logic_vector(N-1 downto 0);
signal output_19 : std_logic_vector(N-1 downto 0);
signal output_20 : std_logic_vector(N-1 downto 0);
signal output_21 : std_logic_vector(N-1 downto 0);
signal output_22 : std_logic_vector(N-1 downto 0);
signal output_23 : std_logic_vector(N-1 downto 0);
signal output_24 : std_logic_vector(N-1 downto 0);
signal output_25 : std_logic_vector(N-1 downto 0);
signal output_26 : std_logic_vector(N-1 downto 0);
signal output_27 : std_logic_vector(N-1 downto 0);
signal output_28 : std_logic_vector(N-1 downto 0);
signal output_29 : std_logic_vector(N-1 downto 0);
signal output_30 : std_logic_vector(N-1 downto 0);
signal output_31 : std_logic_vector(N-1 downto 0);
signal output_32 : std_logic_vector(N-1 downto 0);
begin


u1: my_decoder
port map(
       dec_in => reg_selector,   
       dec_out => decoder_out);  
G1: for i in 0 to N-1 generate
u2: andg2 
port map(
	i_A => enable,
	i_B => decoder_out(i),
	o_F => enable_and(i));  

end generate;
u3: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>'1',
	write_n => enable_and(0),
	reg_in  => reg_in,
	reg_out  => output_1);
u4: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(1),
	reg_in  => reg_in,
	reg_out  => output_2);

u5: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(2),
	reg_in  => reg_in,
	reg_out  => output_3);

u6: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(3),
	reg_in  => reg_in,
	reg_out  => output_4);
u7: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(4),
	reg_in  => reg_in,
	reg_out  => output_5);
u8: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(5),
	reg_in  => reg_in,
	reg_out  => output_6);
u9: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(6),
	reg_in  => reg_in,
	reg_out  => output_7);
u10: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(7),
	reg_in  => reg_in,
	reg_out  => output_8);
u11: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(8),
	reg_in  => reg_in,
	reg_out  => output_9);
u12: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(9),
	reg_in  => reg_in,
	reg_out  => output_10);
u13: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(10),
	reg_in  => reg_in,
	reg_out  => output_11);
u14: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(11),
	reg_in  => reg_in,
	reg_out  => output_12);
u15: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(12),
	reg_in  => reg_in,
	reg_out  => output_13);
u16: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(13),
	reg_in  => reg_in,
	reg_out  => output_14);
u17: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(14),
	reg_in  => reg_in,
	reg_out  => output_15);
u18: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(15),
	reg_in  => reg_in,
	reg_out  => output_16);
u19: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(16),
	reg_in  => reg_in,
	reg_out  => output_17);
u20: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(17),
	reg_in  => reg_in,
	reg_out  => output_18);
u21: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(18),
	reg_in  => reg_in,
	reg_out  => output_19);
u22: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(19),
	reg_in  => reg_in,
	reg_out  => output_20);
u23: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(20),
	reg_in  => reg_in,
	reg_out  => output_21);
u24: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(21),
	reg_in  => reg_in,
	reg_out  => output_22);
u25: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(22),
	reg_in  => reg_in,
	reg_out  => output_23);
u26: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(23),
	reg_in  => reg_in,
	reg_out  => output_24);
u27: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(24),
	reg_in  => reg_in,
	reg_out  => output_25);
u28: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(25),
	reg_in  => reg_in,
	reg_out  => output_26);
u29: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(26),
	reg_in  => reg_in,
	reg_out  => output_27);
u30: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(27),
	reg_in  => reg_in,
	reg_out  => output_28);
u31: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(28),
	reg_in  => reg_in,
	reg_out  => output_29);
u32: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(29),
	reg_in  => reg_in,
	reg_out  => output_30);
u33: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(30),
	reg_in  => reg_in,
	reg_out  => output_31);
u34: reg_strucn
port map(
	clock_in =>clock_in,
	reset  =>reset,
	write_n => enable_and(31),
	reg_in  => reg_in,
	reg_out  => output_32);

u35: my_32mux
port map(
D0 => output_1,
D1=> output_2,
D2=> output_3,
D3=> output_4,
D4=> output_5,
D5=> output_6,
D6=> output_7,
D7=> output_8,
D8=> output_9,
D9=> output_10,
D10=> output_11,
D11=> output_12,
D12=> output_13,
D13=> output_14,
D14=> output_15,
D15=> output_16,
D16=> output_17,
D17=> output_18,
D18=> output_19,
D19=> output_20,
D20=> output_21,
D21=> output_22,
D22=> output_23,
D23=> output_24,
D24=> output_25,
D25=> output_26,
D26=> output_27,
D27=> output_28,
D28=> output_29,
D29=> output_30,
D30=> output_31,
D31=> output_32,
SEL => reg_selector2,
mx_out =>reg_out); 



u36: my_32mux
port map(
D0 => output_1,
D1=> output_2,
D2=> output_3,
D3=> output_4,
D4=> output_5,
D5=> output_6,
D6=> output_7,
D7=> output_8,
D8=> output_9,
D9=> output_10,
D10=> output_11,
D11=> output_12,
D12=> output_13,
D13=> output_14,
D14=> output_15,
D15=> output_16,
D16=> output_17,
D17=> output_18,
D18=> output_19,
D19=> output_20,
D20=> output_21,
D21=> output_22,
D22=> output_23,
D23=> output_24,
D24=> output_25,
D25=> output_26,
D26=> output_27,
D27=> output_28,
D28=> output_29,
D29=> output_30,
D30=> output_31,
D31=> output_32,
SEL => reg_selector3,
mx_out =>reg_out2); 

end structure;