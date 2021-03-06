-------------------------------------------------------------------------
-- Joseph Zambreno
-- Department of Electrical and Computer Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- tb_andg2.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains a simple VHDL testbench for the
-- 2-input AND gate.
--
--
-- NOTES:
-- 8/19/16 by JAZ::Design created.
-- 1/16/19 by H3::Changed name to avoid name conflict with Quartus 
--         primitives.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;

-- This is an empty entity so we don't need to declare ports
entity tb_one_bit_Alu is
end tb_one_bit_Alu;

architecture behavior of tb_one_bit_Alu is

-- Declare the component we are going to instantiate
component one_bit_Alu
 port(x_input  : in std_logic;
	y_input  : in std_logic;
	s_0  : in std_logic;
	s_1  : in std_logic;
	s_2  : in std_logic;
	add_sub  : in std_logic;
	add_sub_carry: out std_logic;
	slt_carry : out std_logic;
	operation_output  : out std_logic);
end component;


-- Signals to connect to the andg2 module
signal x_1, y_1, s_0_tb, s_1_tb, s_2_tb, add_sub_tb,add_sub_carry_tb,slt_carry_tb,operation_output_tb :std_logic;


begin

DUT1: one_bit_Alu 
  port map(x_input  => x_1,
	y_input =>y_1,
	s_0 => s_0_tb,
	s_1 => s_1_tb,
	s_2 => s_2_tb,
	add_sub => add_sub_tb,
	add_sub_carry => add_sub_carry_tb,
	slt_carry => slt_carry_tb,
	operation_output => operation_output_tb);
		

  -- Remember, a process executes sequentially
  -- and then if not told to 'wait' loops back
  -- around
  process
  begin

   x_1 => '0';
   y_1=> '1';
   s_0_tb => '0';
   s_1_tb => '0';
   s_2_tb => '0';
   add_sub_tb => '0';

    wait for 100 ns;
    
    x_1 => '1';
   y_1=> '1';
   s_0_tb => '1';
   s_1_tb => '0';
   s_2_tb => '0';
   add_sub_tb => '0';


    wait for 100 ns;
   x_1 => '1';
   y_1=> '0';
   s_0_tb => '0';
   s_1_tb => '1';
   s_2_tb => '0';
   add_sub_tb => '1';


    wait for 100 ns;

  x_1 => '1';
   y_1=> '0';
   s_0_tb => '1';
   s_1_tb => '1';
   s_2_tb => '0';
   add_sub_tb => '1';


    wait for 100 ns;

  x_1 => '1';
   y_1=> '1';
   s_0_tb => '0';
   s_1_tb => '0';
   s_2_tb => '1';
   add_sub_tb => '0';


    wait for 100 ns;
  x_1 => '1';
   y_1=> '0';
   s_0_tb => '1';
   s_1_tb => '0';
   s_2_tb => '1';
   add_sub_tb => '1';


    wait for 100 ns;

  x_1 => '1';
   y_1=> '1';
   s_0_tb => '0';
   s_1_tb => '1';
   s_2_tb => '1';
   add_sub_tb => '0';


    wait for 100 ns;

  x_1 => '1';
   y_1=> '0';
   s_0_tb => '1';
   s_1_tb => '1';
   s_2_tb => '1';
   add_sub_tb => '1';


    wait for 100 ns;


  end process;
  
end behavior;