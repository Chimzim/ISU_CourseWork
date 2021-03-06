-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering 
-- Iowa State University
-------------------------------------------------------------------------


-- Einstein.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of the famous E = mc^2 equation
--
--
-- NOTES:
-- 8/26/2020 by Chimzim Ogbondah
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity Einstien is

  port(iCLK             : in std_logic;
       m 		            : in integer;
       E 		            : out integer);

end Einstien;

architecture structure of Einstien is

  component Multiplier
    port(iCLK           : in std_logic;
         iA             : in integer;
         iB             : in integer;
         oC             : out integer);
  end component;

  -- Arbitrary constants for C, don't need to be changed 
  constant cC : integer := 9487;
  

  -- Signals to store c^2
  signal sVALUE_C2 : integer;

begin

  
  ---------------------------------------------------------------------------
  -- Level 1: Calculate c^2
  ---------------------------------------------------------------------------
  g_Mult1: Multiplier
    port MAP(iCLK             => iCLK,
             iA               => cC,
             iB               => cC,
             oC               => sVALUE_C2);


 ---------------------------------------------------------------------------
  -- Level 2: Calculate c^2 * m
  ---------------------------------------------------------------------------
  g_Mult3: Multiplier
    port MAP(iCLK             => iCLK,
             iA               => sVALUE_C2,
             iB               => m,
             oC               => E);
  
end structure;