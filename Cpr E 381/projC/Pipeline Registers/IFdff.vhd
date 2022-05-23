-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- dff.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains an implementation of a IF/ID edge-triggered
-- flip-flop with parallel access and reset.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

entity IFdff is

  port(i_CLK        : in std_logic;     -- Clock input
       i_RST        : in std_logic;     -- Reset input
       i_WE         : in std_logic;     -- Write enable input
       Halt         : in std_logic;
       v0           : in std_logic;
       i_instr      : in std_logic;     -- Data value input
       i_PC         : in std_logic;
       o_Halt       : out std_logic;
       o_v0         : out std_logic;
       o_instr      : out std_logic;
       o_PC         : out std_logic);   -- Data value output

end IFdff;

architecture mixed of IFdff is
  signal s_D    : std_logic;    -- Multiplexed input to the FF
  signal s_iPC  : std_logic;    -- Muxed input of the FF
  signal s_Halt : std_logic;
  signal s_Q1   : std_logic;
  signal s_Q2   : std_logic;
  signal s_v0   : std_logic;
  signal s_Q    : std_logic;    -- Output of the FF
  signal s_PC   : std_logic;    -- output of the FF

begin

  -- The output of the FF is fixed to s_Q
  o_instr <= s_Q;
  o_PC <= s_PC;
  o_Halt <= s_Q1;
  o_v0 <= s_Q2;
  -- Create a multiplexed input to the FF based on i_WE
  with i_WE select
    s_D <= i_instr when '1',
           s_Q when others;


  with i_WE select
    s_iPC <= i_PC when '1',
             s_PC when others;

  with i_WE select
    s_Halt <= Halt when '1',
              s_Q1 when others;
 
  with i_WE select 
    s_v0 <= v0 when '1',
            s_Q2 when others;

             
  
  -- This process handles the asyncrhonous reset and
  -- synchronous write. We want to be able to reset 
  -- our processor's registers so that we minimize
  -- glitchy behavior on startup.
  process (i_CLK, i_RST)
  begin
    if (i_RST = '1') then
      s_Q <= '0'; -- Use "(others => '0')" for N-bit values
      s_PC <= '0';
      s_Q1 <= '0';
      s_Q2 <= '0';
    elsif (rising_edge(i_CLK)) then
      s_Q <= s_D;
      s_PC <= s_iPC;
      s_Q1 <= s_Halt;
      s_Q2 <= s_v0;
    end if;

  end process;
  
end mixed;