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

entity ID_EX is
generic(N : Integer := 32);
  port(i_CLK             : in std_logic;
       stall             : in std_logic;     
       flush             : in std_logic;     
       i_WE              : in std_logic;
       Halt              : in std_logic;
       v0                : in std_logic_vector(N-1 downto 0);
       instruction       : in std_logic_vector(N-1 downto 0);     
       i_Reg1            : in std_logic_vector(31 downto 0);     -- Data value input
       i_Reg2            : in std_logic_vector(31 downto 0);
       i_SignEx          : in std_logic_vector(31 downto 0);
       i_PC              : in std_logic_vector(31 downto 0);
       o_Halt            : out std_logic;
       o_v0              : out std_logic_vector(n-1 downto 0);
       instructionO      : out std_logic_vector(31 downto 0);
       o_Reg1            : out std_logic_vector(31 downto 0);
       o_Reg2            : out std_logic_vector(31 downto 0);
       o_SignEx          : out std_logic_vector(31 downto 0);
       o_PC              : out std_logic_vector(31 downto 0));   -- Data value output

end ID_EX;

architecture structure of ID_EX is

component regID
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
end component;

component org2
port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;

signal toStall : std_logic;

begin

g_org2: org2
port MAP(i_A          => stall,
         i_B          => i_CLK,
         o_F          => toStall);


g_ID: regID
port MAP(i_Clk            => toStall,     -- Clock input
         i_Rst            => flush,    -- Reset input
         i_WE             => i_WE,     -- Write enable input
         Halt             => Halt,
         v0               => v0,
         i_Reg1           => i_Reg1,     -- Data value input
         i_Reg2           => i_Reg2,
         i_SignEx         => i_SignEx,
         i_PC             => i_PC,
         instruction      => instruction,
         o_Halt           => o_Halt,
         o_v0             => o_v0,
         o_Reg1           => o_Reg1,
         o_Reg2           => o_Reg2,
         o_SignEx         => o_SignEx,
         o_PC             => o_PC,
         instructionO     => instructionO);   -- Data value output

 end structure;