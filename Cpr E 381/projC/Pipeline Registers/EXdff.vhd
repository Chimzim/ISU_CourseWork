-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- EXdff.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of a EX/MEM edge-triggered
-- flip-flop with parallel access and reset.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity EXdff is

  port(i_CLK          : in std_logic;     -- Clock input
       i_RST          : in std_logic;     -- Reset input
       i_WE           : in std_logic;     -- Write enable input
       Halt           : in std_logic;
       v0             : in std_logic;
       instruction    : in std_logic;
       i_ALU          : in std_logic;     -- Data value input
       i_Reg2         : in std_logic;
       i_PC           : in std_logic;
       o_Halt         : out std_logic;
       o_v0           : out std_logic;
       instructionO   : out std_logic;
       o_ALU          : out std_logic;
       o_Reg2         : out std_logic;
       o_PC           : out std_logic);   -- Data value output

end EXdff;

architecture mixed of EXdff is
  signal s_ALU    : std_logic;    -- Multiplexed input to the FF
  signal s_instr  : std_logic;
  signal s_Reg2   : std_logic;
  signal s_PC     : std_logic;
  signal s_Halt   : std_logic;
  signal s_v0     : std_logic;
  signal s_Q1      : std_logic;    -- Output of the FF
  signal s_Q2     : std_logic;
  signal s_Q3     : std_logic;
  signal s_Q4     : std_logic;
  signal s_Q5     : std_logic;
  signal s_Q6     : std_logic;

begin

  -- The output of the FF is fixed to s_Q
  o_ALU  <= s_Q1;
  o_Reg2 <= s_Q2;
  o_PC   <= s_Q3;
  instructionO <= s_Q4;
  o_Halt <= s_Q5;
  o_v0 <= s_Q6;
  
  -- Create a multiplexed input to the FF based on i_WE
  with i_WE select
    s_ALU <= i_ALU when '1',
           s_Q1 when others;

  with i_WE select 
    s_Reg2 <= i_Reg2 when '1',
              s_Q2 when others;

  with i_WE select 
    s_PC  <= i_PC when '1',
             s_Q3 when others;

  with i_WE select 
    s_instr <= instruction when '1',
               s_Q4 when others;

  with i_WE select 
     s_Halt <= Halt when '1',
               s_Q5 when others;

  with i_WE select
     s_v0 <= v0 when '1',
             s_Q6 when others;
  
  -- This process handles the asyncrhonous reset and
  -- synchronous write. We want to be able to reset 
  -- our processor's registers so that we minimize
  -- glitchy behavior on startup.
  process (i_CLK, i_RST)
  begin
    if (i_RST = '1') then
      s_Q1 <= '0'; -- Use "(others => '0')" for N-bit values
      s_Q2 <= '0';
      s_Q3 <= '0';
      s_Q4 <= '0';
      s_Q5 <= '0';
      s_Q6 <= '0';
    elsif (rising_edge(i_CLK)) then
      s_Q1 <= s_ALU;
      s_Q2 <= s_Reg2;
      s_Q3 <= s_PC;
      s_Q4 <= s_instr;
      s_Q5 <= s_Halt;
      s_Q6 <= s_v0;
    end if;

  end process;
  
end mixed;