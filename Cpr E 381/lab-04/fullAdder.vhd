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


entity fullAdder is

  port(Cin            : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       Cout	      : out std_logic;
       oY 	      : out std_logic);

end fullAdder;

architecture structure of fullAdder is
  
  -- Describe the component entities as defined in andg2.vhd 
  -- and org2.vhd (not strictly necessary).
component andg2
  port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;

  component xorg2
  port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;

  component org2
    port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;
--------------------------------------------------------------------
--See diagram for more knowledge on the connection of these signals
----------------------------------------------------------------------
 signal sValue_XorXY, sValue_XY, sValue_XorXYhelp, sValue_CinXorXY : std_logic;

begin

  
  ---------------------------------------------------------------------------
  -- Level 1: XOR iX with iY and then XOR the result with Cin
  ---------------------------------------------------------------------------
  
--This take the not value of the select line and ands it will iX
  g_xor1: xorg2
    port MAP(i_A    => iX,
       i_B          => iY,
       o_F          => sValue_XorXY);

g_xor2: xorg2
    port MAP(i_A    => Cin,
       i_B          => sValue_XorXY,
       o_F          => oY);
-----------------------------------------------------------------------------------
-- Level 2: AND X and Y and then XOR X and Y
------------------------------------------------------------------------------------
  g_and1: andg2
     port MAP(i_A    => iX,
       i_B          => iY,
       o_F          => sValue_XY);

g_xor3: xorg2
     port MAP(i_A    => iX,
       i_B          => iY,
       o_F          => sValue_XorXYhelp);
    
 ---------------------------------------------------------------------------
  -- Level 3: AND Cin to the resul of XOR X&Y and then OR sValue_XY with that result
  ---------------------------------------------------------------------------
  g_and2: andg2
     port MAP(i_A          => Cin,
       i_B        	   => sValue_XorXYhelp,
       o_F          	   => sValue_CinXorXY);
  g_or1: org2
     port MAP(i_A          => sValue_XY,
       i_B        	   => sValue_CinXorXY,
       o_F          	   => Cout);
  
end structure;