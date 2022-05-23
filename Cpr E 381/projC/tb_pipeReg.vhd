-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- IF_ID.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of an edge-triggered
-- flip-flop with parallel access and reset.
--
--
-- NOTES:
-- 11/13/20 by Chimzim
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity tb_pipeReg is

  port(i_Clk           : in std_logic;
       Stall           : in std_logic_vector(3 downto 0);     
       Flush           : in std_logic_vector(3 downto 0);     
       i_WE            : in std_logic;     
       data            : in std_logic_vector(31 downto 0);     -- Data value input
       output          : out std_logic_vector(31 downto 0));   -- Data value output

end tb_pipeReg;

architecture structure of tb_pipeReg is

component IF_ID
  port(i_CLK           : in std_logic;
       stall           : in std_logic;     
       flush           : in std_logic;     
       i_WE            : in std_logic;     
       data            : in std_logic_vector(31 downto 0);     -- Data value input
       output          : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component ID_EX
  port(i_CLK           : in std_logic;
       stall           : in std_logic;     
       flush           : in std_logic;     
       i_WE            : in std_logic;     
       data            : in std_logic_vector(31 downto 0);     -- Data value input
       output          : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component EX_MEM
  port(i_CLK           : in std_logic;
       stall           : in std_logic;     
       flush           : in std_logic;     
       i_WE            : in std_logic;     
       data            : in std_logic_vector(31 downto 0);     -- Data value input
       output          : out std_logic_vector(31 downto 0));   -- Data value output
end component;

component MEM_WB
  port(i_CLK           : in std_logic;
       stall           : in std_logic;     
       flush           : in std_logic;     
       i_WE            : in std_logic;     
       data            : in std_logic_vector(31 downto 0);     -- Data value input
       output          : out std_logic_vector(31 downto 0));   -- Data value output
end component;


signal outputIF, outputID, outputEX : std_logic_vector(31 downto 0);

begin
-------------------------------------------------------------------------------------------
-- Level 1: IF_ID
-------------------------------------------------------------------------------------------
com1: IF_ID
 port MAP(i_CLK           => i_Clk,
          stall           => Stall(0),     
          flush           => Flush(0),     
          i_WE            => i_WE,     
          data            => data,     -- Data value input
          output          => outputIF);   -- Data value output


-------------------------------------------------------------------------------------------
-- Level 2: ID_EX
-------------------------------------------------------------------------------------------
com2: ID_EX
 port MAP(i_CLK           => i_Clk,
          stall           => Stall(1),     
          flush           => Flush(1),     
          i_WE            => i_WE,     
          data            => outputIF,     -- Data value input
          output          => outputID);   -- Data value output
-------------------------------------------------------------------------------------------
-- Level 3: EX_MEM
-------------------------------------------------------------------------------------------
com3: EX_MEM
 port MAP(i_CLK           => i_Clk,
          stall           => Stall(2),     
          flush           => Flush(2),     
          i_WE            => i_WE,     
          data            => outputID,     -- Data value input
          output          => outputEX);   -- Data value output
-------------------------------------------------------------------------------------------
-- Level 3: MEM_WB
-------------------------------------------------------------------------------------------
com4: MEM_WB
 port MAP(i_CLK           => i_Clk,
          stall           => Stall(3),     
          flush           => Flush(3),     
          i_WE            => i_WE,     
          data            => outputEX,     -- Data value input
          output          => output);   -- Data value output

 end structure;