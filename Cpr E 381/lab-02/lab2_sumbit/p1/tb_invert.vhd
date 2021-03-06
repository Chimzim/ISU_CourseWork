-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_invert.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a simple VHDL testbench for the
-- 32 bit input one's compliment.
--
--
-- NOTES:
-- 9/2/20 by Chimzim Ogbondah::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

-- This is an empty entity so we don't need to declare ports
entity tb_invert is
generic(N: Integer := 32);
end tb_invert;

architecture behavior of tb_invert is

-- Declare the component we are going to instantiate
component Nbitinvert
  port(i_A  : in std_logic_vector(N-1 downto 0);
       o_F  : out std_logic_vector(N-1 downto 0));
end component;

component dataNbitInvert 
  port(i_A : in std_logic_vector(N-1 downto 0);
	o_F : out std_logic_vector(N-1 downto 0));
end component;

-- Signals to connect to the invert module
signal s_A, s_F, s_Y  : std_logic_vector(N-1 downto 0);

begin
G1: for i in 0 to N-1 generate
DUT_i: NbitInvert 
  port map(i_A  => s_A,
  	        o_F  => s_F);
end generate;
G2: for i in 0 to N-1 generate
DUTii: dataNbitInvert
  port map(i_A => s_A,
		o_F => s_Y);
end generate;

  -- Remember, a process executes sequentially
  -- and then if not told to 'wait' loops back
  -- around
  process
  begin

    s_A <= x"00000000";
    wait for 100 ns;

    s_A <= x"10101010";
    wait for 100 ns;

    s_A <= x"11110000";
    wait for 100 ns;

    s_A <= x"11001100";
    wait for 100 ns;


  end process;
  
end behavior;