-------------------------------------------------------------------------
-- Chimzim Ogbondah & Parth
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- controlUnit.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of the control unit design required to call the correct 
-- instruction based on the given opcode
--
-- NOTES:
-- 10/17/20 by Chimzim:Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity controlUnit is
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
end controlUnit;

architecture design of controlUnit is

begin
-- The output of the control logic signals goes as follows
-- ALUsrc, MemToReg, MemWr, RegWr,
  with opcode select
	      cALUsrc <= '0' when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl
			 '1' when "001000", --addi
			 '1' when "001001", --addiu
			 '1' when "001100", --andi
			 '1' when "001111", --lui
			 '1' when "001101", --ori
                         '1' when "001010", --slti
                         '1' when "001011", --sltiu
                         '1' when "001110", --xori
                         '0' when "100011", --lw
                         '0' when "111010", --sw
			 '0' when "000100", --beq
                         '0' when "000101", --bne
                         '0' when others;


with opcode select
	    cMemToReg <= '0' when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl
			 '0' when "001000", --addi
			 '0' when "001001", --addiu
			 '0' when "001100", --andi
			 '1' when "001111", --lui
			 '0' when "001101", --ori
                         '0' when "001010", --slti
                         '0' when "001011", --sltiu
                         '0' when "001110", --xori
                         '1' when "100011", --lw
                         '0' when "111010", --sw
                         '0' when others;

with opcode select
	       cMemWr <= '0' when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl, jr
			 '0' when "001000", --addi
			 '0' when "001001", --addiu
			 '0' when "001100", --andi
			 '0' when "001111", --lui
			 '0' when "001101", --ori
                         '0' when "001010", --slti
                         '0' when "001011", --sltiu
                         '0' when "001110", --xori
                         '0' when "100011", --lw
                         '1' when "111010", --sw
			 '0' when "000100", --beq
                         '0' when "000101", --bne
			 '0' when "000010", --j
                         '0' when "000011", --jal
                         '0' when others;

with opcode select
	       cRegWr <= '1' when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl
			 '1' when "001000", --addi
			 '1' when "001001", --addiu
			 '1' when "001100", --andi
			 '1' when "001111", --lui
			 '1' when "001101", --ori
                         '1' when "001010", --slti
                         '1' when "001011", --sltiu
                         '1' when "001110", --xori
                         '1' when "100011", --lw
                         '0' when "111010", --sw
			 '0' when "000100", --beq
                         '0' when "000101", --bne
			 '0' when "000010", --j
                         '0' when "000011", --jal

                         '0' when others;

--ALU control unit already handles immidate instructions based of their instruction code ??
with opcode select
	       cALUop <= "10" when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl, jr
			-- "00" when "001000", --addi
			-- "00" when "001001", --addiu
			-- "00" when "001100", --andi
			-- "00" when "001111", --lui
			-- "00" when "001101", --ori
                       --  "00" when "001010", --slti
                       --  "00" when "001011", --sltiu
                       --  "00" when "001110", --xori
                         "00" when "100011", --lw
                         "00" when "101001", --sw
                         "01" when "000100", --beq
                         "01" when "000101", --bne
                         "11" when others;

with opcode select
	      cRegDst <= '1' when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl
			 '0' when "001000", --addi
			 '0' when "001001", --addiu
			 '0' when "001100", --andi
			 '0' when "001111", --lui
			 '0' when "001101", --ori
                         '0' when "001010", --slti
                         '0' when "001011", --sltiu
                         '0' when "001110", --xori
                         '0' when "100011", --lw
                         '0' when "111010", --sw
                         '0' when others;

with opcode select
	      cBranch <= '0' when "000000", --add, addu, and, andu, nor, or, slt, sub, sub, xor, sll, sllv, sra, srav, srlv, srl
			 '0' when "001000", --addi
			 '0' when "001001", --addiu
			 '0' when "001100", --andi
			 '0' when "001111", --lui
			 '0' when "001101", --ori
                         '0' when "001010", --slti
                         '0' when "001011", --sltiu
                         '0' when "001110", --xori
                         '0' when "100011", --lw
                         '0' when "111010", --sw
			 '1' when "000100", --beq
                         '1' when "000101", --bne
                         '0' when others;



cJump <= '1' when opcode = "000011" else --jal
         '1' when opcode = "000010" else --j
         '1' when funct =  "001000" else --jr
         '0';

cMemRead <= '1' when opcode = "100011" else --lw
	    '0' when funct =  "001000" else --jr
	    '0'; 
			 


end design;