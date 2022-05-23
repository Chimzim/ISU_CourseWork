-------------------------------------------------------------------------
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
entity tb_dmem is
generic 
	(
		DATA_WIDTH : natural := 32;
		ADDR_WIDTH : natural := 10
	);

end tb_dmem;

architecture behavior of tb_dmem is

-- Declare the component we are going to instantiate
component mem
port 
	(
		clk		: in std_logic;
		addr	        : in std_logic_vector((ADDR_WIDTH-1) downto 0);
		data	        : in std_logic_vector((DATA_WIDTH-1) downto 0);
		we		: in std_logic;
		q		: out std_logic_vector((DATA_WIDTH -1) downto 0)
	);
end component;
-- Signals to connect to the andg2 module
signal i_CLK, enable : std_logic;
signal  Addr :std_logic_vector(ADDR_WIDTH-1 downto 0); 
signal data, output, temp, temp2 : std_logic_vector(DATA_WIDTH-1 downto 0);

begin

DUT: mem
port MAP
	(
		clk		=> i_CLK,
		addr	        => Addr,
		data	        => data,
		we		=> enable,
		q		=> output
	);



  process
  begin

    i_CLK <= '1';
    Addr <= b"0000000000";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000000";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0000000001";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100000001";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;


    i_CLK <= '1';
    Addr <= b"0000000010";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000010";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0000000011";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100000011";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;


    i_CLK <= '1';
    Addr <= b"0000000100";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000100";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0000000101";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100000101";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0000000110";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000110";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0000000111";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100000111";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;


    i_CLK <= '1';
    Addr <= b"0000001000";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100001000";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0000001001";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100001001";
    data <= temp;
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;


    i_CLK <= '1';
    Addr <= b"0100000000";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100000001";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000010";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000011";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000100";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100000101";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


    i_CLK <= '1';
    Addr <= b"0100000110";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100000111";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;

    i_CLK <= '1';
    Addr <= b"0100001000";
    data <= x"11111111";
    enable <= '0';
    temp <= output;
    wait for 100 ns;


     i_CLK <= '1';
    Addr <= b"0100001001";
    data <= x"11111111";
    enable <= '1';
    temp2 <= output;
    wait for 100 ns;

  end process;
  
end behavior;