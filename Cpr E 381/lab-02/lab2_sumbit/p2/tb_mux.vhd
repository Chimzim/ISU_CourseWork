-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_mux.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a simple VHDL testbench for the
-- 2-1 mux.
--
--
-- NOTES:
-- 9/2/20 by Chimzim Ogbondah::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

-- This is an empty entity so we don't need to declare ports
entity tb_mux is
generic(N : Integer := 32);
end tb_mux;

architecture behavior of tb_mux is

-- Declare the component we are going to instantiate
component Nbitmux
  port(s             : in std_logic;
     i_X             : in std_logic_vector(N-1 downto 0);
     i_Y             : in std_logic_vector(N-1 downto 0);
     o_Y 	    : out std_logic_vector(N-1 downto 0));
end component;

component dataNbitmux
port(s             : in std_logic;
     i_X             : in std_logic_vector(N-1 downto 0);
     i_Y             : in std_logic_vector(N-1 downto 0);
     o_F 	    : out std_logic_vector(N-1 downto 0));
end component;

-- Signals to connect to the andg2 module
signal s_A, s_B :std_logic; 
signal s_X, s_Y, s_F, s_G  : std_logic_vector(N-1 downto 0);

begin

DUT: Nbitmux
  port map(s  => s_A,
  	        i_X  => s_X,
		i_Y  => s_Y,
  	        o_Y  => s_F);


DUT1: dataNbitmux
  port Map(s  => s_B,
  	        i_X  => s_X,
		i_Y  => s_Y,
  	        o_F  => s_G);

  -- Remember, a process executes sequentially
  -- and then if not told to 'wait' loops back
  -- around
  process
  begin

    s_A <= '0';
    s_B <= '0';
    s_X <= x"11111111";
    s_Y <= x"00000000";
    wait for 100 ns;

    s_A <= '0';
    s_B <= '1';
    s_X <= x"11111111";
    s_Y <= x"00000000";
    wait for 100 ns;

    s_A <= '1';
    s_B <= '0';
    s_X <= x"11111111";
    s_Y <= x"00000000";
    wait for 100 ns;

    s_A <= '1';
    s_B <= '1';
    s_X <= x"11111111";
    s_Y <= x"00000000";
    wait for 100 ns;


  end process;
  
end behavior;