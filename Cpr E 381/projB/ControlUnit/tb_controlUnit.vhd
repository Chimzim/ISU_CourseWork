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
entity tb_controlUnit is

end tb_controlUnit;

architecture behavior of tb_controlUnit is

-- Declare the component we are going to instantiate
component controlUnit
 port(opcode         : in std_logic_vector(5 downto 0);
      funct          : in std_logic_vector(5 downto 0);
      cALUsrc        : out std_logic;
      cMemToReg      : out std_logic;
      cMemWr         : out std_logic;
      cRegWr         : out std_logic;
      cALUop         : out std_logic_vector(1 downto 0);
      cRegDst        : out std_logic;
      cBranch        : out std_logic;
      cJump          : out std_logic;
      cMemRead       : out std_logic);
end component;

-- Signals to connect to the andg2 module
signal testOpcode, testFunct  : std_logic_vector(5 downto 0 );
signal testALUsrc, testMemToReg, testMemWr, testRegWr, testRegDst, testBranch, testJump, testMemRead : std_logic;
signal testALUop : std_logic_vector(1 downto 0);

begin

DUT: controlUnit 
port MAP(opcode       => testOpcode,
       funct          => testFunct,
       cALUsrc        => testALUsrc,
       cMemToReg      => testMemToReg,
       cMemWr         => testMemWr,
       cRegWr         => testRegWr,
       cALUop         => testALUop,
       cRegDst        => testRegDst,
       cBranch        => testBranch,
       cJump          => testJump,
       cMemRead       => testMemRead);

  -- Remember, a process executes sequentially
  -- and then if not told to 'wait' loops back
  -- around
  process
  begin

    testOpcode <= "001000"; --addi
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000000"; --add
    testFunct  <= "100000";
    wait for 100 ns;

    testOpcode <= "000000"; --addu
    testFunct  <= "100001";
    wait for 100 ns;


    testOpcode <= "000000"; --and
    testFunct  <= "100100";
    wait for 100 ns;

    testOpcode <= "001100"; --andi
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "100011"; --lw
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000000"; --nor
    testFunct  <= "100111";
    wait for 100 ns;

    testOpcode <= "000000"; --xor
    testFunct  <= "100110";
    wait for 100 ns;

    testOpcode <= "001110"; --xori
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000000"; --or
    testFunct  <= "100101";
    wait for 100 ns;

    testOpcode <= "001101"; --ori
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000000"; --slt
    testFunct  <= "101010";
    wait for 100 ns;

    testOpcode <= "001010"; --slti
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000000"; --sltu
    testFunct  <= "001011";
    wait for 100 ns;

    testOpcode <= "000000"; --sll
    testFunct  <= "000000";
    wait for 100 ns;

    testOpcode <= "000000"; --srl
    testFunct  <= "000010";
    wait for 100 ns;

    testOpcode <= "000000"; --sra
    testFunct  <= "000011";
    wait for 100 ns;

    testOpcode <= "000000"; --sllv
    testFunct  <= "000100";
    wait for 100 ns;

    testOpcode <= "000000"; --srlv
    testFunct  <= "000110";
    wait for 100 ns;

    testOpcode <= "000000"; --srav
    testFunct  <= "000111";
    wait for 100 ns;

    testOpcode <= "101001"; --sw
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000000"; --sub
    testFunct  <= "100010";
    wait for 100 ns;

    testOpcode <= "000000"; --subu
    testFunct  <= "100011";
    wait for 100 ns;

    testOpcode <= "000100"; --beq
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000101"; --bne
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000010"; --j
    testFunct  <= "111111";
    wait for 100 ns;

    testOpcode <= "000011"; --jal
    testFunct  <= "111111";
    wait for 100 ns;    

    testOpcode <= "000000"; --jr
    testFunct  <= "001000";
    wait for 100 ns;    


  end process;
  
end behavior;