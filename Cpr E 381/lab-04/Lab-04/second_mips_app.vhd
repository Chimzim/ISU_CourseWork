
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

entity second_mips_app is
  generic(N : integer:=32);
  port(reg_decod : in std_logic_vector(5-1 downto 0); --register for the decoder
	read_port1 : in std_logic_vector(5-1 downto 0);
	read_port2 : in std_logic_vector(5-1 downto 0);
	mux_output : buffer std_logic_vector(N-1 downto 0); --mux output between the alu and mem output
	clock : in std_logic;
	carry_out : out std_logic;
	alu_src : in std_logic;
	g_reset : in std_logic;
	global_enable: in std_logic;
	enable_mem: in std_logic;
	mem_to_register  : in std_logic;
	--sum  : buffer std_logic_vector(N-1 downto 0); --the output from the mu
	init_immediate  : in std_logic_vector(16-1 downto 0) --initial immediate value
	

	);


end second_mips_app;

architecture structure of second_mips_app is
component reg_file
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
end component;

component sign_extension
   generic(N : integer:=32);
  port(immediate  : in std_logic_vector(16-1 downto 0);
	output  : out std_logic_vector(N-1 downto 0));
end component;


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

component mem

	generic 
	(
		DATA_WIDTH : natural := 32;
		ADDR_WIDTH : natural := 10
	);

	port 
	(
		clk		: in std_logic;
		addr	        : in std_logic_vector((ADDR_WIDTH-1) downto 0);
		data	        : in std_logic_vector((DATA_WIDTH-1) downto 0);
		we		: in std_logic := '1';
		q		: out std_logic_vector((DATA_WIDTH -1) downto 0)
	);
end component;



signal extension : std_logic_vector(N-1 downto 0);
signal result : std_logic_vector(N-1 downto 0);
signal mem_output : std_logic_vector(N-1 downto 0);
signal reg_output : std_logic_vector(N-1 downto 0);
signal reg_output2 : std_logic_vector(N-1 downto 0);
signal sum_mux : std_logic_vector(N-1 downto 0);

begin


u1: reg_file
port map(
clock_in  => clock,
reset =>g_reset,
enable => global_enable,
reg_selector=>reg_decod,
reg_selector2 =>read_port1,
reg_selector3 =>read_port2,
reg_in =>mux_output,
reg_out =>reg_output, 
reg_out2 => reg_output2);



u2: sign_extension
port map(
immediate => init_immediate,
output  => extension);

u3: MUX2_strucn
port map(
i_A => reg_output2,
i_select => alu_src,
i_C  => extension,
o_F  => sum_mux);

u4: Full_AdderN
port map(
x_i => reg_output,
y_i => sum_mux,
carry_i => '0',
c_i => carry_out,
s_i => result);

u5: mem
port map(
clk => clock,
addr=> result(9 downto 0),
data=>reg_output2,
we=> enable_mem,
q=> mem_output);

u6: MUX2_strucn
port map(
i_A => mem_output,
i_select => mem_to_register,
i_C  => result,
o_F  => mux_output);


end structure;