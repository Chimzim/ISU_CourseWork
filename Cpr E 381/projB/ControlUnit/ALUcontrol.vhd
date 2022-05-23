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


entity ALUcontrol is
 port(funct        : in std_logic_vector(5 downto 0);
      ALUop        : in std_logic_vector(1 downto 0);
      jrInstr      : out std_logic;
      LogArth      : out std_logic;
      shiftDir     : out std_logic;
      datapath     : out std_logic;
      ALUctrl      : out std_logic_vector(2 downto 0));
end ALUcontrol;

architecture design of ALUcontrol is

begin
  with funct select
	      LogArth <= '0' when "000000", --sll
                         '0' when "000100", --sllv
                         '1' when "000011", --sra
                         '1' when "000111", --srav
                         '0' when "000010", --srl
			 '0' when "000110", --srlv
                         '0' when others;


  with funct select
	     shiftDir <= '1' when "000000", --sll
                         '1' when "000100", --sllv
                         '0' when "000011", --sra
                         '0' when "000111", --srav
                         '0' when "000010", --srl
			 '0' when "000110", --srlv
                         '0' when others;

with funct select
	     datapath <= '0' when "100000", --add
			 '0' when "001000", --addi
			 '0' when "100001", --addu
			 '0' when "001001", --addiu
			 '0' when "100100", --and
			 '0' when "001100", --andi
			-- '1' when "001110", --lui
			 '0' when "100111", --nor
			 '0' when "100101", --or
			 '0' when "001101", --ori
			 '0' when "101010", --slt
			 '0' when "001010", --slti
                         '0' when "101011", --sltu
			 '0' when "001011", --sltiu
                         '0' when "100010", --sub
                         '0' when "100011", --subu
                         '0' when "100110", --XOR
			 '0' when "001110", --XORi
                         '1' when "000000", --sll
                         '1' when "000100", --sllv
                         '1' when "000011", --sra
                         '1' when "000111", --srav
                         '1' when "000010", --srl
			 '1' when "000110", --srlv
                         '0' when others;

	     ALUctrl  <= "000" when funct = "100000" and ALUop = "10" else  --add
			 "000" when funct = "001000" and ALUop = "11" else --addi
			 "000" when funct = "100001" and ALUop = "10" else --addu
			 "000" when funct = "001001" and ALUop = "11" else --addiu
			 "011" when funct = "100100" and ALUop = "10" else --and
			 "011" when funct = "001100" and ALUop = "11" else --andi
			 "000" when ALUop = "00" else --lw, sw
			 "001" when ALUop = "01" else -- beq, bne
			-- "000" when "001110", --lui
			 "111" when funct = "100111" and ALUop = "10" else --nor
			 "100" when funct = "100101" and ALUop = "10" else --or
 			 "100" when funct = "001101" and ALUop = "11" else --ori
			 "010" when funct = "101010" and ALUop = "10" else --slt
			 "010" when funct = "001010" and ALUop = "11" else --slti
                         "010" when funct = "101011" and ALUop = "10" else --sltu
			 "010" when funct = "001011" and ALUop = "11" else --sltiu
                         "001" when funct = "100010" and ALUop = "10" else --sub
                         "001" when funct = "100011" and ALUop = "10" else --subu
                         "101" when funct = "100110" and ALUop = "10" else --XOR
			 "101" when funct = "001110" and ALUop = "11" else --XORi
                         "000" when funct = "000000" and ALUop = "10" else --sll
                         "000" when funct = "000100" and ALUop = "10" else --sllv
                         "000" when funct = "000011" and ALUop = "10" else --sra
                         "000" when funct = "000111" and ALUop = "10" else --srav
                         "000" when funct = "000010" and ALUop = "10" else --srl
			 "000" when funct = "000110" and ALUop = "10" else --srlv
                         "000";

	jrInstr <= '1' when funct = "001000" else 
		   '0';


end design;