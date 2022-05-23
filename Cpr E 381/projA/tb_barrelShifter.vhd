-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_mem.vhd
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
entity tb_barrelShifer is
	generic(N : Integer := 32);

end tb_barrelShifer;

architecture behavior of tb_barrelShifer is

-- Declare the component we are going to instantiate
component barrelShifter
port(i_data : in std_logic_vector(N-1 downto 0);
       shift  : in std_logic;
       output : out std_logic_vector(N-1 downto 0));
end component;

-- Signals to connect to the andg2 module
signal shift :  std_logic;
signal data, output : std_logic_vector(N-1 downto 0);

begin

DUT: barrelShifter
port MAP(i_data => data,
       shift => shift,
       output => output);



  process
  begin

    data <= x"10000000";
    shift <= '0';
    wait for 100 ns;

  data <= x"10000000";
    shift <= '1';
    wait for 100 ns;

    data <= x"00000001";
    shift <= '0';
    wait for 100 ns;

    data <= x"00000001";
    shift <= '1';
    wait for 100 ns;

end process;


end behavior;
