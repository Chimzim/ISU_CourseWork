-------------------------------------------------------------------------
-- Chimzim Ogbondah & Parth 
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- ALUprojA.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an 32 bit barrel shifter that offers both right and left logical and arthmetic shifting
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity ALUprojA is
  generic(N : Integer := 32);
port(data : in std_logic_vector(N-1 downto 0);
     data1 : in std_logic_vector(N-1 downto 0);
     shiftAmount : in std_logic_vector(4 downto 0);
     ALUsrc      : in std_logic_vector(2 downto 0);
     LogArth     : in std_logic;
     shiftR_L    : in std_logic;
     dataPath    : in std_logic; --determines whether to shift or to take ALU data
     overflow    : out std_logic;
     o_CarryOut  : out std_logic;
     zero        : out std_logic;
     output      : out std_logic_vector(N-1 downto 0));
     
 
end ALUprojA;

architecture structure of ALUprojA is
  
component barrelShifter
port(i_data : in std_logic_vector(N-1 downto 0);
       logArth : in std_logic;
       shift   : in std_logic;
       shiftAmount  : in std_logic_vector(4 downto 0);
       output : out std_logic_vector(N-1 downto 0));

end component;

component ALU_32bit
port(i_A : in std_logic_vector(N-1 downto 0);
	i_B : in std_logic_vector(N-1 downto 0);
	i_S : in std_logic_vector(2 downto 0);
	o_F : out std_logic_vector(N-1 downto 0);
	o_Co : out std_logic;
	overflow : out std_logic;
	zero : out std_logic);
end component;

component mux
 port(s0             : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       oY 	      : out std_logic);
end component;

signal shiftData, ALUdata : std_logic_vector(N-1 downto 0);
signal logArt : std_logic;
begin
--------------------------------------------------------------------------
-- Level 1: shifting the data
---------------------------------------------------------------------------
g_barrelShifter: barrelShifter
port MAP(i_data => data,
       logArth => LogArth,
       shift   => shiftR_L,
       shiftAmount  => shiftAmount,
       output => shiftData);

----------------------------------------------------------------------------
-- Level 2: ALU data path
-----------------------------------------------------------------------------
g_32ALU: ALU_32bit
port MAP(i_A => data,
	i_B => data1,
	i_S => ALUsrc,
	o_F => ALUdata,
	o_Co => o_CarryOut,
	overflow => overflow,
	zero => zero);
-------------------------------------------------------------------------------
-- Level 3: selecting shift data or ALU data
-------------------------------------------------------------------------------
L1: for i in 0 to N-1 generate
g_mux: mux
port MAP(s0          => dataPath,
       iX             => ALUdata(i),
       iY             => shiftData(i),
       oY 	      => output(i));
end generate;

end structure;