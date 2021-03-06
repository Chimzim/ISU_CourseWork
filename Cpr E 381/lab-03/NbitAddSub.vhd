-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- NbitAddSub.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of the N bit Adder/Subtractor with a control
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity NbitAddSub is
generic(N : Integer := 32);
  port(addSub         : in std_logic;
       ALUsrc         : in std_logic;
       carry          : in std_logic;
       iX             : in std_logic_vector(N-1 downto 0);
       iY             : in std_logic_vector(N-1 downto 0);
       immidateValue  : in std_logic_vector(N-1 downto 0);
       Cout	      : out std_logic;
       sum 	      : out std_logic_vector(N-1 downto 0));

end NbitAddSub;

architecture structure of NbitAddSub is
  
  -- Describe the component entities as defined in andg2.vhd 
  -- and org2.vhd (not strictly necessary).
component NbitInvert
  port(i_A          : in std_logic_vector(N-1 downto 0);
       o_F          : out std_logic_vector(N-1 downto 0));
  end component;

  component Nbitmux
   port(s             : in std_logic;
       i_X             : in std_logic_vector(N-1 downto 0);
       i_Y             : in std_logic_vector(N-1 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
  end component;

component fullAdder
  port(Cin            : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       Cout	      : out std_logic;
       oY 	      : out std_logic);
  end component;
--------------------------------------------------------------------
--See diagram for more knowledge on the connection of these signals
----------------------------------------------------------------------
Signal sValue_niY, sValue_iY, ALUsrcValue, Carrybit, nImmidateValue, dataImmidate : std_logic_vector(N-1 downto 0);

begin

  
  -------------------------------------------------------------------------------------------------------------------------------------------
  -- Level 1: Inverts iY so it can be used for subtraction, Then takes the add_sub and ALUsrc to determine the inputs to the fulladder
  -----------------------------------------------------------------------------------------------------------------------------------------------
   
g_invert: NbitInvert
port MAP(i_A  => iY,
       o_F  => sValue_niY);

g_invert1: NbitInvert
port MAP(i_A  => immidateValue,
     o_F  => nImmidateValue);

g_mux: Nbitmux
port MAP(s    =>  addSub,
       i_X          =>  iY,
       i_Y      => sValue_niY,	
       o_Y          => sValue_iY);

g_mux0: Nbitmux
port MAP(s    =>  addSub,
     i_X          =>  immidateValue,
      i_Y      => nImmidateValue,	
      o_Y          => dataImmidate);

g_mux1: Nbitmux
    port MAP(s    => ALUsrc,
       i_X         => sValue_iY,
       i_Y      => dataImmidate,	
       o_Y          => ALUsrcValue);

-----------------------------------------------------------------------------------
-- Level 2: Takes sValue_iY and iX and plugs them into the NbitfullAdder
------------------------------------------------------------------------------------
g_full: fullAdder
 port MAP(Cin            => carry,
       iX             => iX(0),
       iY             => ALUsrcValue(0),
       Cout	      => Carrybit(0),
       oY 	      => sum(0));

G1: for i in 1 to N-1 generate
g_fulli: fullAdder
 port MAP(Cin        => Carrybit(i-1),
       iX             => iX(i),
       iY             => ALUsrcValue(i),
       Cout	      => Carrybit(i),
       oY 	      => sum(i));
end generate;

Cout <= Carrybit(31);
end structure;