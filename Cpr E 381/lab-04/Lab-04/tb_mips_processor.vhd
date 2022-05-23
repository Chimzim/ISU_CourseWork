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

entity tb_mips_processor is
generic(gCLK_HPER   : time := 50 ns;
   N: integer :=32);
end tb_mips_processor;

architecture behavior of tb_mips_processor is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component mips_processor
    port(
	add_imm : in std_logic_vector(N-1 downto 0);
	reg_sel : in std_logic_vector(5-1 downto 0);
	read_port1 : in std_logic_vector(5-1 downto 0);
	read_port2 : in std_logic_vector(5-1 downto 0);
	clock : in std_logic;
	reset : in std_logic;
	global_enable: in std_logic;
	selector  : in std_logic;
	ALU_src : in std_logic;
	sum  : out std_logic_vector(N-1 downto 0));
end component;


  -- Temporary signals to connect to the dff component.
  signal s_CLK, s_RST,s_WE,s_selector,alu_s: std_logic;  
  signal s_output,adds_imm: std_logic_vector(N-1 downto 0);
  signal reg_selectors, read_selector, read_selector2 : std_logic_vector(5-1 downto 0);

begin

  DUT: mips_processor 
  port map( 
	   add_imm => adds_imm,
           reg_sel => reg_selectors,
           read_port1   => read_selector,
	   read_port2 => read_selector2,
	   clock => s_CLK,
	   reset => s_RST,
	   selector => s_selector,
	   ALU_src => alu_s,
	   sum => s_output,
	   global_enable   => s_WE);

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
    s_RST <= '1';
    
    wait for cCLK_PER;
    s_RST <= '0';
     s_WE  <= '1';
    reg_selectors <= "00001";
    adds_imm <= x"00000001";
    read_selector <= "00001";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;

   s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "00010";
    adds_imm <= x"00000002";
    read_selector <= "00010";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;
s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "00011";
    
    adds_imm <= x"00000003";
    read_selector <= "00011";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;

s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "00100";
    
    adds_imm <= x"00000004";
    read_selector <= "00100";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;

s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "00101";
    
    adds_imm <= x"00000005";
    read_selector <= "00101";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;

s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "00110";
   
    adds_imm <= x"00000006";
    read_selector <= "00110";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;

s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "00111";
    
    adds_imm <= x"00000007";
    read_selector <= "00111";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;
s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01000";
   
    adds_imm <= x"00000008";
    read_selector <= "01000";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;
s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01001";
    
    adds_imm <= x"00000009";
    read_selector <= "01001";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;
s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01010";
    
    adds_imm <= x"0000000A";
    read_selector <= "01010";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;
    s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01011";
    adds_imm <= x"00000000";
    read_selector <= "00001";
    read_selector2 <= "00010";
    s_selector <= '0';
    alu_s <= '0';
    
    
    wait for cCLK_PER;

 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01100";
    adds_imm <= x"00000001";
    read_selector <= "01011";
    read_selector2 <= "00011";
    s_selector <= '1';
    alu_s <= '0';
  
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01101";
    
    adds_imm <= x"00000001";
    read_selector <= "01100";
    read_selector2 <= "00100";
    s_selector <= '0';
    alu_s <= '0';
    
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01110";
    
    adds_imm <= x"00000001";
    read_selector <= "01101";
    read_selector2 <= "00101";
    s_selector <= '1';
    alu_s <= '0';
   
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01111";
   
    adds_imm <= x"00000001";
    read_selector <= "01110";
    read_selector2 <= "00110";
    s_selector <= '0';
    alu_s <= '0';
    
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10000";
    
    adds_imm <= x"00000001";
    read_selector <= "01111";
    read_selector2 <= "00111";
    s_selector <= '1';
    alu_s <= '0';
    
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10001";
    
    adds_imm <= x"00000001";
    read_selector <= "10000";
    read_selector2 <= "01000";
    s_selector <= '0';
    alu_s <= '0';
   
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10010";
   
    adds_imm <= x"00000001";
    read_selector <= "10001";
    read_selector2 <= "01001";
    s_selector <= '1';
    alu_s <= '0';
    
    
    wait for cCLK_PER;
 s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10011";
    adds_imm <= x"00000001";
    read_selector <= "10010";
    read_selector2 <= "01010";
    s_selector <= '0';
    alu_s <= '0';
    
    
    wait for cCLK_PER;
    s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10100";
    adds_imm <= x"00000023";
    read_selector <= "10100";
    read_selector2 <= "00000";
    s_selector <= '0';
    alu_s <= '1';
    
    wait for cCLK_PER;
    s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10101";
    
    read_selector2 <= "10011";
    read_selector <= "10100";
    adds_imm <= x"00000000";
    s_selector <= '0';
    alu_s <= '0';
 
    wait for cCLK_PER;

    wait;
  end process;
  
end behavior;