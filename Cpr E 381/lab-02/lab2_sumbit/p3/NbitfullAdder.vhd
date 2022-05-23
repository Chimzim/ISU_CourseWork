-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- fullAdder.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of the 2-1 mux
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity NbitfullAdder is
  generic(N : integer := 32);
  port(Cin            : in std_logic;
       iX             : in std_logic_vector(N-1 downto 0);
       iY             : in std_logic_vector(N-1 downto 0);
       Cout	      : out std_logic_vector(N-1 downto 0);
       oY 	      : out std_logic_vector(N-1 downto 0));

end NbitfullAdder;

architecture structure of NbitfullAdder is
  
  -- Describe the component entities as defined in andg2.vhd 
  -- and org2.vhd (not strictly necessary).
component fullAdder
  port(Cin            : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       Cout	      : out std_logic;
       oY 	      : out std_logic);
  end component;

Signal Carrybit : std_logic_vector(N-1 downto 0);
begin

  
  ---------------------------------------------------------------------------
  -- Level 1: Loop through the the fulladder connecting all the components
  ---------------------------------------------------------------------------
g_design1: fullAdder
 port MAP(Cin            => Cin,
       iX             => iX(0),
       iY             => iY(0),
       Cout	      => Carrybit(0),
       oY 	      => oY(0));

G1: for i in 1 to N-1 generate

  g_design: fullAdder
  port MAP(Cin        => Carrybit(i-1),
       iX             => iX(i),
       iY             => iY(i),
       Cout	      => Carrybit(i),
       oY 	      => oY(i));
end generate;
  Cout <= Carrybit(31);
end structure;