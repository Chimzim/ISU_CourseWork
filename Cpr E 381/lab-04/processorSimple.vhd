-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- SimpleProcessor.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an 32 bit register file containing 32 registers
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity processorSimple is
  generic(N          : Integer := 32
          DATA_WIDTH : natural := 32
          ADDR_WIDTH : natural := 10);

  port(i_CLK            : in std_logic;     			 -- Clock input
       addsub            : in std_logic;
       ALUsrc           : in std_logic;
       load             : in std_logic;
       immidateValue    : in std_logic_vector(15 downto 0);
       reset            : in std_logic;     			 -- Reset input
       enable           : in std_logic;
       readA1		: in std_logic_vector(4 downto 0); 
       readA2		: in std_logic_vector(4 downto 0); 
       readA3           : in std_logic_vector(ADDR_WIDTTH-1 downto 0);
       writeA		: in std_logic_vector(4 downto 0);       -- Write enable input     -- Data value input    
       result           : out std_logic_vector(N-1 downto 0));   -- Data value output



end processorSimple;

architecture structure of processorSimple is
  
component Processor
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

end component;

component zeroExt
  port(i_X             : in std_logic_vector(15 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
end component;

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


signal output1, tempStore, immidateUpdate, output2 : std_logic_vector(N-1 downto 0);

begin
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Level 1: Will take the write address and get the position for the register to write to , from there it uses and gates to only write to and reset the desired register
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
g_ext: zeroExt 
port MAP(i_X             => immidateValue,
       o_Y 	      => immidateUpdate);

g_processor: Processor
port MAP(i_CLK            => i_CLK,    			 -- Clock input
       carryIn          => '0',
       ALUsrc           => ALUsrc,
       addsub           => addsub,
       immidateValue    => immidateUpdate
       in_reset         => reset,     			 -- Reset input
       enable           => load,
       readA1		=> readA1, 
       readA2		=> readA2, 
       writeA		=> writeA,       -- Write enable input
       data             => output2,     -- Data value input
       carryOut         => Cout,    
       result           => output);

tempStore <= output;
g_mem: mem
port MAP
	(
		clk		=> i_CLK,
		addr	        => Addr3,
		data	        => tempStore;
		we		=> store,
		q		=> output1,
	);
output2 <= output1;
  ------------------------------------------------------------------------------------------
  -- Level 2: load values from register file and then plug them into the NbitAddSub unit
  ------------------------------------------------------------------------------------------


end structure;