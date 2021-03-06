
-------------------------------------------------------------------------
-- Chimzim and Parth
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- projAMips.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an example of using generic ports to
-- drive a "generate / for" block. 
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-- 1/25/19 by H3::Fixed name conflict with and2 and Quartus.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity projAMips is
  generic(N : integer:=32);
  port(reg_decod : in std_logic_vector(5-1 downto 0); --register for the decoder
	read1 : in std_logic_vector(5-1 downto 0);
	read2 : in std_logic_vector(5-1 downto 0);
	operation : in std_logic_vector(2 downto 0);
	logArt : in std_logic;
        toShift : in std_logic;
        master : in std_logic;
        zero : out std_logic;
        overflow : out std_logic;
	shiftAmount : in std_logic_vector(4 downto 0);
	data : buffer std_logic_vector(N-1 downto 0); --mux output between the alu and mem output
	clock : in std_logic;
	carry_out : out std_logic;
	ALUsrc : in std_logic;
	reset : in std_logic_vector(N-1 downto 0);
	enable: in std_logic;
	enable_mem: in std_logic;
	mem_to_register  : in std_logic;
	sum  :in std_logic_vector(N-1 downto 0); --the output from the mu
	immediate  : in std_logic_vector(16-1 downto 0) --initial immediate value
	

	);


end projAMips;

architecture structure of projAMips is
component registerfile
generic(N : integer:=32);
  port(i_CLK           : in std_logic;     -- Clock input
       in_reset        : in std_logic_vector(N-1 downto 0);     -- Reset input of the write address
       readA1          : in std_logic_vector(4 downto 0);      -- read address 1
       readA2	       : in std_logic_vector(4 downto 0);       -- Read address 2
       enable          : in std_logic_vector(N-1 downto 0);     -- Write enable input based on the write address
       data            : in std_logic_vector(N-1 downto 0);     -- Data value input
       output1         : out std_logic_vector(N-1 downto 0);    -- Data value ouput1
       output2         : out std_logic_vector(N-1 downto 0));   -- Data value output2
end component;

component signExt
   generic(N : integer:=32);
  port(i_X             : in std_logic_vector(15 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
end component;

component Nbitmux
  generic(N : Integer := 32);
  port(s             : in std_logic;
       i_X             : in std_logic_vector(N-1 downto 0);
       i_Y             : in std_logic_vector(N-1 downto 0);
       o_Y 	      : out std_logic_vector(N-1 downto 0));
end component;

component ALUprojA
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
end component;

component mem

	generic 
	(
		DATA_WIDTH : natural := 32;
		ADDR_WIDTH : natural := 10
	);

	port 
	(
		clk		: in std_logic;
		addr	        : in std_logic_vector((ADDR_WIDTH-1) downto 0);
		data	        : in std_logic_vector((DATA_WIDTH-1) downto 0);
		we		: in std_logic := '1';
		q		: out std_logic_vector((DATA_WIDTH -1) downto 0)
	);
end component;



signal Ext : std_logic_vector(N-1 downto 0);
signal resultValue : std_logic_vector(N-1 downto 0);
signal mem_output : std_logic_vector(N-1 downto 0);
signal registerO : std_logic_vector(N-1 downto 0);
signal registerO2: std_logic_vector(N-1 downto 0);
signal muxO : std_logic_vector(N-1 downto 0);

begin


com1: registerfile
  port(i_CLK           => clock,     -- Clock input
       in_reset        =>  reset,     -- Reset input of the write address
       readA1           => read1,     -- read address 1
       readA2	       => read2,     -- Read address 2
       enable          => enable,     -- Write enable input based on the write address
       data            => data,    -- Data value input
       output1         => registerO,    -- Data value ouput1
       output2         => registerO2);   -- Data value output2

com2: signExt
  port MAP(i_X       => immediate,
       o_Y 	     => Ext);

com3: Nbitmux
 port(s             => ALUsrc,
       i_X           =>  registerO,
       i_Y            => registerO2,
       o_Y 	      => muxO);

com4: ALUprojA
port MAP(data 	=> registerO,
     data1 	=> sum,
     shiftAmount => shiftAmount,
     ALUsrc      => operation,
     LogArth     => logArt,
     shiftR_L    => toShift,
     dataPath    => master,  --determines whether to shift or to take ALU data
     overflow    => overflow,
     o_CarryOut  => carry_out,
     zero        => zero,
     output      => resultValue);

com5: mem
port map(
clk  => clock,
addr => resultValue(9 downto 0),
data => registerO2,
we   => enable_mem,
q    => memO);

com6: Nbitmux
 port(s           => mem_to_register,
       i_X        =>  mem_output,
       i_Y        => resultValue,
       o_Y 	  => muxO);

end structure;