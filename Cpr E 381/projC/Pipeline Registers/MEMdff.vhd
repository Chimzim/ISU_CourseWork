-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- MEMdff.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of a MEM/WB edge-triggered
-- flip-flop with parallel access and reset.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity MEMdff is

  port(i_CLK              : in std_logic;     -- Clock input
       i_RST              : in std_logic;     -- Reset input
       i_WE               : in std_logic;     -- Write enable input
       Halt               : in std_logic;
       v0                 : in std_logic;
       instruction        : in std_logic;
       i_dataMem          : in std_logic;     -- Data value input
       i_ALU              : in std_logic;
       o_Halt             : out std_logic;
       o_v0               : out std_logic;
       instructionO       : out std_logic;
       o_dataMem          : out std_logic;
       o_ALU              : out std_logic);   -- Data value output

end MEMdff;

architecture mixed of MEMdff is
  signal s_Halt       : std_logic;
  signal s_v0         : std_logic;
  signal s_dataMem    : std_logic;    -- Multiplexed input to the FF
  signal s_instr      : std_logic;
  signal s_ALU        : std_logic;
  signal s_Q1         : std_logic;    -- Output of the FF
  signal s_Q2         : std_logic;
  signal s_Q3         : std_logic;
  signal s_Q4         : std_logic;
  signal s_Q5         : std_logic;

begin

  -- The output of the FF is fixed to s_Q
  o_dataMem <= s_Q1;
  o_ALU     <= s_Q2;
  instructionO <= s_Q3;
  o_Halt <= s_Q4;
  o_v0 <= s_Q5;
  
  -- Create a multiplexed input to the FF based on i_WE
  with i_WE select
    s_dataMem <= i_dataMem when '1',
                 s_Q1 when others;

  with i_WE select
    s_ALU   <= i_ALU when '1',
               s_Q2 when others;

  with i_WE select 
    s_instr <= instruction when '1',
               s_Q3 when others;
    
  with i_WE select 
       s_Halt <= Halt when '1',
                 s_Q4 when others;

  with i_WE select
          s_v0 <= v0 when '1',
                   s_Q5 when others;
  
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
    elsif (rising_edge(i_CLK)) then
      s_Q1 <= s_dataMem;
      s_Q2 <= s_ALU;
      s_Q3 <= s_instr;
      s_Q4 <= s_Halt;
      s_Q5 <= s_v0;
    end if;

  end process;
  
end mixed;