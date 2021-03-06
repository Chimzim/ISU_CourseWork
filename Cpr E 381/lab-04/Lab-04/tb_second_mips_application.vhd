-------------------------------------------------------------------------
-- Joseph Zambreno
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_dff.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a simple VHDL testbench for the
-- edge-triggered flip-flop with parallel access and reset.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity tb_second_mips_application is
generic(gCLK_HPER   : time := 50 ns;
   N: integer :=32);
end tb_second_mips_application;

architecture behavior of tb_second_mips_application is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component second_mips_app
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
	init_immediate  : in std_logic_vector(16-1 downto 0)); --initial immediate value
end component;


  -- Temporary signals to connect to the dff component.
 
  signal s_CLK, s_carryout,alu_src,s_g_reset,s_global_enable,s_enable_mem, s_mem_to_register: std_logic;  
  signal s_mux_output: std_logic_vector(N-1 downto 0);
  signal s_immediate: std_logic_vector(16-1 downto 0);
--  signal temp,temp1,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9: std_logic_vector(31 downto 0);
  signal s_reg_decod, s_read_port1, s_read_port2 : std_logic_vector(5-1 downto 0);

begin

  dmem: second_mips_app 
  port map(reg_decod =>s_reg_decod,
	read_port1 =>s_read_port1,
	read_port2 =>s_read_port2,
	mux_output =>s_mux_output,
	clock =>s_CLK,
	carry_out =>s_carryout,
	alu_src =>alu_src,
	g_reset=>s_g_reset,
	global_enable=>s_global_enable,
	enable_mem=>s_enable_mem,
	mem_to_register=>s_mem_to_register,
	--sum =>s_sum,
	init_immediate=>s_immediate);

  -- This process sets the clock value (low for gCLK_HPER, then high
  -- for gCLK_HPER). Absent a "wait" command, processes restart 
  -- at the beginning once they have reached the final statement.
  P_CLK: process
  begin
    s_CLK <= '0';
    wait for gCLK_HPER;
    s_CLK <= '1';
    wait for gCLK_HPER;
  end process;
  
  -- Testbench process  
  P_TB: process
  begin
    -- Reset the FF
    s_g_reset <= '1';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "00000";
    s_read_port2 <= "11001";
    s_reg_decod <= "11001";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';

    wait for cCLK_PER;

    -- Store '1'
     s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0100";
    s_read_port1 <= "00000";
    s_read_port2 <= "11010";
    s_reg_decod <= "11010";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';

    wait for cCLK_PER;

    -- Keep '1'
     s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "11001";
    s_read_port2 <= "00001";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;

    -- Store '0'    
     s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0004";
    s_read_port1 <= "11001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00010";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;


   s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "00001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '0';
    wait for cCLK_PER;


   s_g_reset <= '0';
    s_global_enable <= '0';
    s_immediate<= x"0000";
    s_read_port1 <= "11010";
    s_read_port2 <= "00001";
    s_reg_decod <= "11010";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0008";
    s_read_port1 <= "11001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00010";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "00001";
    s_read_port2 <= "00010";
s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '0';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '0';
    s_immediate<= x"0004";
    s_read_port1 <= "11010";
    s_read_port2 <= "00010";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';
    wait for cCLK_PER;



 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0004";
    s_read_port1 <= "11010";
    s_read_port2 <= "00001";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"000C";
    s_read_port1 <= "11001";
    s_read_port2 <= "00010";
s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '0';
    s_immediate<= x"0008";
    s_read_port1 <= "11010";
    s_read_port2 <= "00010";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "00001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '0';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '0';
    s_immediate<= x"000C";
    s_read_port1 <= "11010";
    s_read_port2 <= "00001";
s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';

    wait for cCLK_PER; s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0014";
    s_read_port1 <= "11001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00010";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "00001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '0';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0010";
    s_read_port1 <= "11010";
    s_read_port2 <= "00010";
    s_reg_decod <= "00010";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0018";
    s_read_port1 <= "11001";
    s_read_port2 <= "00010";
    s_reg_decod <= "00010";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0000";
    s_read_port1 <= "00001";
    s_read_port2 <= "00010";
s_reg_decod <= "00001";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '0';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"0200";
    s_read_port1 <= "11010";
    s_read_port2 <= "11011";
    s_reg_decod <= "11011";
    s_enable_mem <= '1';
    s_mem_to_register <='1';
    alu_src <= '1';
    wait for cCLK_PER;

 s_g_reset <= '0';
    s_global_enable <= '1';
    s_immediate<= x"FFFC";
    s_read_port1 <= "11011";
    s_read_port2 <= "00001";
    s_reg_decod <= "11011";
    s_enable_mem <= '1';
    s_mem_to_register <='0';
    alu_src <= '1';
    wait for cCLK_PER;

    wait;
  end process;
  
end behavior;