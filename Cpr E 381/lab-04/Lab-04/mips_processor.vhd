
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

entity mips_processor is
  generic(N : integer:=32);
  port(add_imm : in std_logic_vector(N-1 downto 0);
	reg_sel : in std_logic_vector(5-1 downto 0);
	read_port1 : in std_logic_vector(5-1 downto 0);
	read_port2 : in std_logic_vector(5-1 downto 0);
	clock : in std_logic;
	reset : in std_logic;
	global_enable: in std_logic;
	selector  : in std_logic;
	ALU_src : in std_logic;
	sum  : out std_logic_vector(N-1 downto 0));


end mips_processor;

architecture structure of mips_processor is
component reg_file
generic(N : integer:=32);
 port(clock_in  : in std_logic;
	reset  : in std_logic;
	enable  : in std_logic;
	reg_selector : in std_logic_vector(5-1 downto 0); --the register to choose for the decoder
	reg_selector2 : in std_logic_vector(5-1 downto 0); --register to choose for the MUX...these are read outputs
	reg_selector3 : in std_logic_vector(5-1 downto 0); --register to choose for the second mux... these are read outputs
	reg_in  : in std_logic_vector(N-1 downto 0);
	reg_out  : out std_logic_vector(N-1 downto 0);
	reg_out2 : out std_logic_vector(N-1 downto 0));
end component;

component A_Sunit1
   generic(N : integer:=32);
  port(x_input  : in std_logic_vector(N-1 downto 0);
	y_input  : in std_logic_vector(N-1 downto 0);
	add_imm : in std_logic_vector(N-1 downto 0);
	selector  : in std_logic;
	ALU_src : in std_logic;
	s_i  : out std_logic_vector(N-1 downto 0));
end component;

signal read_output : std_logic_vector(N-1 downto 0);
signal read_output2 : std_logic_vector(N-1 downto 0);
signal result : std_logic_vector(N-1 downto 0);

begin

u1: reg_file
port map(
clock_in  => clock,
reset  => reset,
enable => global_enable,
reg_selector => reg_sel, --the register to choose for the decoder
reg_selector2=> read_port1,  --register to choose for the MUX...these are read outputs
reg_selector3 => read_port2, --register to choose for the second mux... these are read outputs
reg_in =>result, 
reg_out =>read_output,   
reg_out2  =>read_output2);

u2: A_Sunit1
port map(
x_input => read_output,
y_input => read_output2,
add_imm => add_imm,
selector => selector,
ALU_src => ALU_src,
s_i =>result);


sum <= result;
end structure;