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
entity tb_fullAdder is
generic(N : Integer := 32);
end tb_fullAdder;

architecture behavior of tb_fullAdder is

-- Declare the component we are going to instantiate
component NbitfullAdder
  port(Cin             : in std_logic;
     iX             : in std_logic_vector(N-1 downto 0);
     iY             : in std_logic_vector(N-1 downto 0);
     Cout	    : out std_logic_vector(N-1 downto 0);
     oY             : out std_logic_vector(N-1 downto 0));
end component;

component datafullAdder
 port(Cin             : in std_logic;
     i_X             : in std_logic_vector(N-1 downto 0);
     i_Y             : in std_logic_vector(N-1 downto 0);
     Cout	    : out std_logic_vector(N-1 downto 0);
     o_F             : out std_logic_vector(N-1 downto 0));
end component;

-- Signals to connect to the andg2 module 
signal carry : std_logic;
signal  s_X, s_Y, s_F, s_G, test1, test2  : std_logic_vector(N-1 downto 0);

begin

DUT: NbitfullAdder
  port map(Cin  => carry,
  	        iX => s_X,
		iY  => s_Y,
  	        Cout  => s_F,
                oY   => test1);


DUT1: datafullAdder
  port Map(Cin  => carry,
  	        i_X  => s_X,
		i_Y  => s_Y,
  	        Cout => s_G,
	        o_F   => test2);

  -- Remember, a process executes sequentially
  -- and then if not told to 'wait' loops back
  -- around
  process
  begin


    s_X <= x"00000001";
    s_Y <= x"00000001";
    wait for 100 ns;


    s_X <= x"11111111";
    s_Y <= x"11111111";
    wait for 100 ns;

 
    s_X <= x"11111111";
    s_Y <= x"00000000";
    wait for 100 ns;


    s_X <= x"00000004";
    s_Y <= x"00000003";
    wait for 100 ns;


  end process;
  
end behavior;