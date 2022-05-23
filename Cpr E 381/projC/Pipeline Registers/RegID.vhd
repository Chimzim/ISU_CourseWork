-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- regIF.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an N bit ID register which uses dff
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity regID is
  generic(N : Integer := 32);
  port(i_Clk            : in std_logic;     -- Clock input
       i_Rst            : in std_logic;     -- Reset input
       i_WE             : in std_logic;     -- Write enable input
       Halt             : in std_logic;
       v0               : in std_logic_vector(N-1 downto 0);
       i_Reg1           : in std_logic_vector(N-1 downto 0);     -- Data value input
       i_Reg2           : in std_logic_vector(N-1 downto 0);
       i_SignEx         : in std_logic_vector(N-1 downto 0);
       i_PC             : in std_logic_vector(N-1 downto 0);
       instruction      : in std_logic_vector(N-1 downto 0);
       o_Halt           : out std_logic;
       o_v0             : out std_logic_vector(N-1 downto 0);
       o_Reg1           : out std_logic_vector(N-1 downto 0);
       o_Reg2           : out std_logic_vector(N-1 downto 0);
       o_SignEx         : out std_logic_vector(N-1 downto 0);
       o_PC             : out std_logic_vector(N-1 downto 0);
       instructionO     : out std_logic_vector(N-1 downto 0));   -- Data value output

end regID;

architecture structure of regID is
  
component IDdff
  port(i_CLK           : in std_logic;     -- Clock input
       i_RST           : in std_logic;     -- Reset input
       i_WE            : in std_logic;     -- Write enable input
       Halt            : in std_logic;
       v0              : in std_logic;
       i_PC            : in std_logic;
       instruction     : in std_logic;
       i_Reg1          : in std_logic;     -- ReadA1
       i_Reg2          : in std_logic;     -- ReadA2
       i_SignEx        : in std_logic;     -- Sign Extended Value
       o_Halt          : out std_logic;
       o_v0            : out std_logic;
       o_Reg1          : out std_logic;    -- Read1 output value
       o_Reg2          : out std_logic;    -- Read2 output value
       o_PC            : out std_logic;
       instructionO    : out std_logic;
       o_SignEx        : out std_logic);   -- Sign Extended output value
end component;


begin

  
  ---------------------------------------------------------------------------
  -- Level 1: and the two inputs with the given select line
  ---------------------------------------------------------------------------
G1: for i in 0 to N-1 generate
dff_design: IDdff
port MAP(i_CLK           => i_Clk,     -- Clock input
         i_RST           => i_Rst,     -- Reset input
         i_WE            => i_WE,     -- Write enable input
         Halt            => Halt,
         v0              => v0(i),
         i_PC            => i_PC(i),
         instruction     => instruction(i),
         i_Reg1          => i_Reg1(i),     -- ReadA1
         i_Reg2          => i_Reg2(i),     -- ReadA2
         i_SignEx        => i_SignEx(i),     -- Sign Extended Value
         o_Halt          => o_Halt,
         o_v0            => o_v0(i),
         o_Reg1          => o_Reg1(i),    -- Read1 output value
         o_Reg2          => o_Reg2(i),    -- Read2 output value
         o_PC            => o_PC(i),
         instructionO    => instructiono(i),
         o_SignEx        => o_SignEx(i));   -- Sign Extended output value
end generate;

end structure;