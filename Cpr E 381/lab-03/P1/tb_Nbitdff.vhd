-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_Nbitdff.vhd
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

entity tb_Nbitdff is
  generic(gCLK_HPER   : time := 50 ns);

end tb_Nbitdff;

architecture behavior of tb_Nbitdff is
  
  -- Calculate the clock period as twice the half-period
  constant cCLK_PER  : time := gCLK_HPER * 2;


  component Nbitdff
    port(i_CLK        : in std_logic;     -- Clock input
         in_reset        : in std_logic;     -- Reset input
         in_WEn         : in std_logic;     -- Write enable input
         in_Data          : in std_logic_vector(31 downto 0);     -- Data value input
         o_Data          : out std_logic_vector(31 downto 0));   -- Data value output
  end component;

  -- Temporary signals to connect to the dff component.
  signal s_CLK : std_logic;
signal  s_RST, s_WE  : std_logic;
  signal s_D, s_Q : std_logic_vector(31 downto 0);

begin

  DUT: Nbitdff 
  port map(i_CLK => s_CLK, 
           in_reset => s_RST,
           in_WEn  => s_WE,
           in_Data   => s_D,
           o_Data   => s_Q);


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
    s_D   <= x"00000000";
    wait for cCLK_PER;

    -- Store '1'
    s_RST <= '0';
    s_WE  <= '1';
    s_D   <= x"00000001";
    wait for cCLK_PER;  

    -- Keep '1'
    s_RST <= '0';
    s_WE  <= '0';
    s_D   <= x"00000000";
    wait for cCLK_PER;  

    -- Store '0'    
    s_RST <= '0';
    s_WE  <= '1';
    s_D   <= x"00000000";
    wait for cCLK_PER;  

    -- Keep '0'
    s_RST <= '0';
    s_WE  <= '0';
    s_D   <= x"11111111";
    wait for cCLK_PER;  

    wait;
  end process;
  
end behavior;