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


entity Processor is
  generic(N : Integer := 32);
  port(i_CLK            : in std_logic;     			 -- Clock input
       carryIn          : in std_logic;
       masterSelect     : in std_logic;
       ALUsrc           : in std_logic_vector(2 downto 0);
       logArth          : in std_logic;
       shiftAmount      : in std_logic_vector(4 downto 0);
       shiftL_R         : in std_logic;
       in_reset         : in std_logic;     			 -- Reset input
       enable           : in std_logic;
       readA1		: in std_logic_vector(4 downto 0); 
       readA2		: in std_logic_vector(4 downto 0); 
       writeA		: in std_logic_vector(4 downto 0);       -- Write enable input
       data             : in std_logic_vector(N-1 downto 0);     -- Data value input
       carryOut         : out std_logic;
       overflow         : out std_logic; 
       zero             : out std_logic;   
       result           : out std_logic_vector(N-1 downto 0));   -- Data value output



end Processor;

architecture structure of Processor is
  
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

component ALUprojA
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
end component;

signal writeEnBit, resetEnBit, decodeOut, oData, output1, output2 : std_logic_vector(N-1 downto 0);

begin
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Level 1: Will take the write address and get the position for the register to write to , from there it uses and gates to only write to and reset the desired register
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
g_decode: Nbitdecoder
port MAP(i_A	 => writeA,
       i_out      =>  decodeOut);

G1: for a in 0 to N-1 generate 
g_and0: andg2
port MAP(i_A          => enable,
       i_B          => decodeOut(a),
       o_F          => writeEnBit(a));
end generate;

G2: for b in 0 to N-1 generate
g_and1: andg2
port MAP(i_A          => in_reset,
       i_B          => decodeOut(b),
       o_F          => resetEnBit(b));
end generate;

  ------------------------------------------------------------------------------------------
  -- Level 2: load values from register file and then plug them into the NbitAddSub unit
  ------------------------------------------------------------------------------------------

dff_design: registerfile
 port MAP(i_CLK        => i_CLK,     	      -- Clock input
       in_reset        => resetEnBit,           -- Reset input of the write address
       readA1          => readA1,		      -- read address 1
       readA2	       => readA2,	              -- Read address 2
       enable          => writeEnBit,    	      -- Write enable input based on the write address
       data            => data,			      -- Data value input
       output1         => output1,		      -- Data value ouput1
       output2         => output2);  		      -- Data value output2


adderDesign: ALUprojA
port MAP(data => output1,
     data1 => output2,
     shiftAmount => shiftAmount,
     ALUsrc      => ALUsrc,
     LogArth     logArth,
     shiftR_L    => shiftL_R,
     dataPath    => masterSelect, --determines whether to shift or to take ALU data
     overflow    => overflow,
     o_CarryOut  => carryOut,
     zero        => zero,
     output      => result);
end structure;