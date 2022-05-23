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

entity tb_reg is
generic(gCLK_HPER   : time := 50 ns;
   N: integer :=32);
end tb_reg;

architecture behavior of tb_reg is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component reg_strucn
    generic(N : integer:=32);
  port(clock_in  : in std_logic;
	reset  : in std_logic;
	write_n  : in std_logic;
	reg_in  : in std_logic_vector(N-1 downto 0);
	reg_out  : buffer std_logic_vector(N-1 downto 0));
  end component;


  -- Temporary signals to connect to the dff component.
  signal s_CLK, s_RST,s_WE: std_logic;  
  signal s_D, s_Q : std_logic_vector(N-1 downto 0);

begin

  DUT: reg_strucn 
  port map(clock_in => s_CLK, 
	   reset => s_RST,
           write_n => s_WE,
           reg_in  => s_D,
           reg_out   => s_Q);

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
    s_WE  <= '0';
    s_D   <= x"FFFFFFFF";
    wait for cCLK_PER;

    -- Store '1'
    s_RST <= '0';
    s_WE  <=  '1';
    s_D   <= x"00000001";
    wait for cCLK_PER;  

    -- Keep '1'
    s_RST <= '0';
    s_WE  <=  '0';
    s_D   <= x"00000055";
    wait for cCLK_PER;  

    -- Store '0'    
    s_RST <= '0';
    s_WE  <=  '1';
    s_D   <= x"EE220011";
    wait for cCLK_PER;  

    -- Keep '0'
    s_RST <= '0';
    s_WE  <=  '0';
    s_D   <= x"00001111";
    wait for cCLK_PER;  

    wait;
  end process;
  
end behavior;