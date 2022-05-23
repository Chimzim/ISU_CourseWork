-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- MIPS.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an 32 bit register file containing 32 registers
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity simplerProcessor is
  generic(N : Integer := 32);
  port(i_CLK            : in std_logic;     			 -- Clock input
       carryIn          : in std_logic;
       ALUsrc           : in std_logic;
       addsub           : in std_logic;
       immidateValue    : in std_logic_vector(N-1 downto 0);
       in_reset         : in std_logic;     			 -- Reset input
       enable           : in std_logic;
       readA1		: in std_logic_vector(4 downto 0); 
       readA2		: in std_logic_vector(4 downto 0); 
       writeA		: in std_logic_vector(4 downto 0);       -- Write enable input
       data             : in std_logic_vector(N-1 downto 0);     -- Data value input
       carryOut         : out std_logic;    
       result           : out std_logic_vector(N-1 downto 0));   -- Data value output



end simplerProcessor;

architecture structure of simpleProcessor is
  
component registerfile
 port(i_CLK           : in std_logic;     -- Clock input
       in_reset        : in std_logic_vector(N-1 downto 0);     -- Reset input of the write address
       readA1          : in std_logic_vector(4 downto 0);      -- read address 1
       readA2	       : in std_logic_vector(4 downto 0);       -- Read address 2
       enable          : in std_logic_vector(N-1 downto 0);     -- Write enable input based on the write address
       data            : in std_logic_vector(N-1 downto 0);     -- Data value input
       output1         : out std_logic_vector(N-1 downto 0);    -- Data value ouput1
       output2         : out std_logic_vector(N-1 downto 0));   -- Data value output2
end component;

component Nbitdecoder
 port(i_A          : in std_logic_vector(4 downto 0);
       i_out        : out std_logic_vector(N-1 downto 0));
end component;

component andg2
port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;

component NbitAddSub
 port(addSub            : in std_logic;
	ALUsrc        : in std_logic;
	carry         : in std_logic;
       iX             : in std_logic_vector(N-1 downto 0);
       iY             : in std_logic_vector(N-1 downto 0);
       immidateValue  : in std_logic_vector(N-1 downto 0);
       Cout	      : out std_logic;
       sum 	      : out std_logic_vector(N-1 downto 0));
end component;

signal writeEnBit, resetEnBit, decodeOut, oData: std_logic_vector(N-1 downto 0);

begin
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Level 1: Will take the write address and get the position for the register to write to , from there it uses and gates to only write to and reset the desired register
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
g_decode: Nbitdecoder
port MAP(i_A	 => writeA,
       i_out      =>  decodeOut);

G1: for i in 0 to N-1 generate 
g_and0: andg2
port MAP(i_A          => enable,
       i_B          => decodeOut(i),
       o_F          => writeEnBit(i));
end generate;

G2: for i in 0 to N-1 generate
g_and1: andg2
port MAP(i_A          => in_reset,
       i_B          => decodeOut(i),
       o_F          => resetEnBit(i));
end generate;

  ------------------------------------------------------------------------------------------
  -- Level 2: load values from register file and then plug them into the NbitAddSub unit
  ------------------------------------------------------------------------------------------

dff_design: registerfile
 port MAP(i_CLK           =>	i_CLK,     	      -- Clock input
       in_reset        =>	resetEnBit,           -- Reset input of the write address
       readA1          => readA1,		      -- read address 1
       readA2	       => readA2,	              -- Read address 2
       enable          => writeEnBit,    	      -- Write enable input based on the write address
       data            => data,			      -- Data value input
       output1         => output1,		      -- Data value ouput1
       output2         => output2);  		      -- Data value output2

adderDesign: NbitAddSub
 port MAP(addSub      => addsub,
	ALUsrc        => ALUsrc,
	carry         => carryIn,
       iX             => output1,
       iY             => output2,
       immidateValue  => immidateValue,
       Cout	      => carryOut,
       sum 	      => result);
end structure;