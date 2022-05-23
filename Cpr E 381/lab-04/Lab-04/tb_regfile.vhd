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

entity tb_regfile is
generic(gCLK_HPER   : time := 50 ns;
   N: integer :=32);
end tb_regfile;

architecture behavior of tb_regfile is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component reg_file
    generic(N : integer:=32);
  port(clock_in  : in std_logic;
	reset  : in std_logic;
	enable  : in std_logic;
	reg_selector : in std_logic_vector(5-1 downto 0);
	reg_selector2 : in std_logic_vector(5-1 downto 0);
	reg_selector3 : in std_logic_vector(5-1 downto 0);
	reg_in  : in std_logic_vector(N-1 downto 0);
	reg_out  : out std_logic_vector(N-1 downto 0);
	reg_out2 : out std_logic_vector(N-1 downto 0));
end component;


  -- Temporary signals to connect to the dff component.
  signal s_CLK, s_RST,s_WE: std_logic;  
  signal s_input, s_output,s_output2 : std_logic_vector(N-1 downto 0);
  signal reg_selectors, read_selector, read_selector2 : std_logic_vector(5-1 downto 0);

begin

  DUT: reg_file 
  port map(clock_in => s_CLK, 
	   reset => s_RST,
           enable => s_WE,
           reg_in  => s_input,
           reg_out   => s_output,
	reg_selector => reg_selectors,
	reg_selector2 => read_selector,
	reg_selector3 => read_selector2,
	   reg_out2   => s_output2);

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
    s_WE  <= '1';
    reg_selectors <= "01111";
    s_input   <= x"FFFFFFFF";
    read_selector <= "01111";
    read_selector2 <= "01111";
    wait for cCLK_PER;

    -- Store '1'
     s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "01101";
    s_input   <= x"22222211";
    read_selector <= "01101";
    read_selector2 <= "01111";
    wait for cCLK_PER;

    -- Keep '1'
    s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "10001";
    s_input   <= x"10102233";
    read_selector <= "10001";
    read_selector2 <="01101";
    wait for cCLK_PER;

    -- Store '0'    
    s_RST <= '0';
    s_WE  <= '1';
    reg_selectors <= "11110";
    s_input   <= x"FFFFEEEE";
    read_selector <= "11110";
    read_selector2 <= "10001";
    wait for cCLK_PER;
    -- Keep '0'
    s_RST <= '0';
    s_WE  <= '0';
    reg_selectors <= "00010";
    s_input   <= x"22334410";
    read_selector <= "00010";
    read_selector2 <= "11110";
    wait for cCLK_PER;



    wait;
  end process;
  
end behavior;