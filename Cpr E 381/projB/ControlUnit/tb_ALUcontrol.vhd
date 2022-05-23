-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering 
-- Iowa State University
-------------------------------------------------------------------------


-- tb_controlUnit.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a simple VHDL testbench for the
-- ControlUnit logic
--
--
-- NOTES:
-- 10/31/20 by Chimzim Ogbondah
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

-- This is an empty entity so we don't need to declare ports
entity tb_ALUcontrol is

end tb_ALUcontrol;

architecture behavior of tb_ALUcontrol is

-- Declare the component we are going to instantiate
component ALUcontrol
 port(funct        : in std_logic_vector(5 downto 0);
      LogArth      : out std_logic;
      shiftDir     : out std_logic;
      datapath     : out std_logic;
      ALUctrl      : out std_logic_vector(3 downto 0));
end component;

-- Signals to connect to the andg2 module
signal testFunct  : std_logic_vector(5 downto 0 );
signal testLogArth, testShiftDir, testDatapath : std_logic;
signal testALUctrl: std_logic_vector(3 downto 0);

begin

DUT: ALUcontrol 
 port MAP(funct     => testFunct,
      LogArth      => testLogArth,
      shiftDir     => testShiftDir,
      datapath     => testDatapath,
      ALUctrl      => testALUctrl);

  -- Remember, a process executes sequentially
  -- and then if not told to 'wait' loops back
  -- around
  process
  begin

   --addi
    testFunct  <= "001000";
    wait for 100 ns;

    --add
    testFunct  <= "100000";
    wait for 100 ns;

    --addu
    testFunct  <= "100001";
    wait for 100 ns;


    --and
    testFunct  <= "100100";
    wait for 100 ns;

     --andi
    testFunct  <= "001100";
    wait for 100 ns;

    --lw
    testFunct  <= "100011";
    wait for 100 ns;

    --nor
    testFunct  <= "100111";
    wait for 100 ns;

    --xor
    testFunct  <= "100110";
    wait for 100 ns;

    --xori
    testFunct  <= "001110";
    wait for 100 ns;

    --or
    testFunct  <= "100101";
    wait for 100 ns;

    --ori
    testFunct  <= "001101";
    wait for 100 ns;

    --slt
    testFunct  <= "101010";
    wait for 100 ns;

    --slti
    testFunct  <= "001010";
    wait for 100 ns;

    --sltu
    testFunct  <= "001011";
    wait for 100 ns;

    --sll
    testFunct  <= "000000";
    wait for 100 ns;

    --srl
    testFunct  <= "000010";
    wait for 100 ns;

    --sra
    testFunct  <= "000011";
    wait for 100 ns;

    --sllv
    testFunct  <= "000100";
    wait for 100 ns;

    --srlv
    testFunct  <= "000110";
    wait for 100 ns;

    --srav
    testFunct  <= "000111";
    wait for 100 ns;

    --sw
    testFunct  <= "101001";
    wait for 100 ns;

    --sub
    testFunct  <= "100010";
    wait for 100 ns;

    --subu
    testFunct  <= "100011";
    wait for 100 ns;

     --beq
    testFunct  <= "000100";
    wait for 100 ns;

    --bne
    testFunct  <= "000101";
    wait for 100 ns;

    --j
    testFunct  <= "000010";
    wait for 100 ns;

    --jal
    testFunct  <= "000011";
    wait for 100 ns;    

    --jr
    testFunct  <= "001000";
    wait for 100 ns;    


  end process;
  
end behavior;