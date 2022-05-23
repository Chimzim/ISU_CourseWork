-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- mux.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of the 2-1 mux
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity mux is

  port(s0             : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       oY 	      : out std_logic);

end mux;

architecture structure of mux is
  
  -- Describe the component entities as defined in andg2.vhd 
  -- and org2.vhd (not strictly necessary).
  component andg2
  port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;

  component org2
    port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
  end component;

 component invg
 port(i_A          : in std_logic;
       o_F          : out std_logic);
 end component;
 signal sValue_Nots0, sValue_iXNs0, sValue_iYs0 : std_logic;

begin

  
---------------------------------------------------------------------------
-- Level 1: and the two inputs with the given select line
---------------------------------------------------------------------------

  g_notS0: invg
  	port MAP(i_A          => s0,
             o_F          => sValue_Nots0);

--This take the not value of the select line and ands it will iX
  g_and1of2: andg2
    port MAP(i_A    => iX,
       i_B          => sValue_Nots0,
       o_F          => sValue_iXNs0);
--this will take the previous result and then and it again with y and store the result in the same place
g_and1: andg2
    port MAP(i_A    => iY,
       i_B          => s0,
       o_F          => sValue_iYs0);

    
---------------------------------------------------------------------------
-- Level 2: or the result
---------------------------------------------------------------------------
 g_Mult3: org2
     port MAP(i_A          => sValue_iYs0,
       i_B        	   => sValue_iXNs0,
       o_F          	   => oY);

 

  
end structure;