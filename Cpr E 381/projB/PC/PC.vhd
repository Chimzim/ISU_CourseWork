-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- fullAdder.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of PC = PC +4 logic
--
--
-- NOTES:
-- 11/2/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity PC is
  generic(N : integer := 32);
  port(PCin     : in std_logic_vector(N-1 downto 0);
       PCout    : out std_logic_vector(N-1 downto 0));

end PC;

architecture structure of PC is
  
component NbitfullAdder
  port(Cin            : in std_logic;
       iX             : in std_logic_vector(N-1 downto 0);
       iY             : in std_logic_vector(N-1 downto 0);
       Cout	      : out std_logic;
       output 	      : out std_logic_vector(N-1 downto 0));
  end component;

Signal four : std_logic_vector(N-1 downto 0);
Signal Cout, Cin : std_logic;
begin

  
  ---------------------------------------------------------------------------
  -- Level 1: add the previous PC value with 4 
  ---------------------------------------------------------------------------
Cin <= '0';
four <= x"00000004";

g_adder: NbitfullAdder
  port MAP(Cin            => Cin,
       iX             => PCin, 
       iY             => four,
       Cout	      => Cout,
       output 	      => PCout);

end structure;