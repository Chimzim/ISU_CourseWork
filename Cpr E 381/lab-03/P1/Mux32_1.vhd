-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- Mux32_1.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of a 32 bit 32:1 mux
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity Mux32_1 is
 generic(N : Integer := 32);
 port(sel          : in std_logic_vector(4 downto 0);
      input_data0  : in std_logic_vector(N-1 downto 0);
      input_data1  : in std_logic_vector(N-1 downto 0);
      input_data2  : in std_logic_vector(N-1 downto 0);
      input_data3  : in std_logic_vector(N-1 downto 0);
      input_data4  : in std_logic_vector(N-1 downto 0);
      input_data5  : in std_logic_vector(N-1 downto 0);
      input_data6  : in std_logic_vector(N-1 downto 0);
      input_data7  : in std_logic_vector(N-1 downto 0);
      input_data8  : in std_logic_vector(N-1 downto 0);
      input_data9  : in std_logic_vector(N-1 downto 0);
      input_data10  : in std_logic_vector(N-1 downto 0);
      input_data11  : in std_logic_vector(N-1 downto 0);
      input_data12  : in std_logic_vector(N-1 downto 0);
      input_data13  : in std_logic_vector(N-1 downto 0);
      input_data14  : in std_logic_vector(N-1 downto 0);
      input_data15  : in std_logic_vector(N-1 downto 0);
      input_data16  : in std_logic_vector(N-1 downto 0);
      input_data17  : in std_logic_vector(N-1 downto 0);
      input_data18  : in std_logic_vector(N-1 downto 0);
      input_data19  : in std_logic_vector(N-1 downto 0);
      input_data20  : in std_logic_vector(N-1 downto 0);
      input_data21  : in std_logic_vector(N-1 downto 0);
      input_data22  : in std_logic_vector(N-1 downto 0);
      input_data23  : in std_logic_vector(N-1 downto 0);
      input_data24  : in std_logic_vector(N-1 downto 0);
      input_data25  : in std_logic_vector(N-1 downto 0);
      input_data26  : in std_logic_vector(N-1 downto 0);
      input_data27  : in std_logic_vector(N-1 downto 0);
      input_data28  : in std_logic_vector(N-1 downto 0);
      input_data29  : in std_logic_vector(N-1 downto 0);
      input_data30  : in std_logic_vector(N-1 downto 0);
      input_data31  : in std_logic_vector(N-1 downto 0);
      i_out        : out std_logic_vector(N-1 downto 0));



end Mux32_1;

architecture design of Mux32_1 is

begin
  with sel select
		i_out <= input_data0 when "00000",
			 input_data1 when "00001",
			 input_data2 when "00010",
			 input_data3 when "00011",
			 input_data4 when "00100",
			 input_data5 when "00101",
                         input_data6 when "00110",
                         input_data7 when "00111",
                         input_data8 when "01000",
                         input_data9 when "01001",
                         input_data10 when "01010",
                         input_data11 when "01011",
                         input_data12 when "01100",
                         input_data13 when "01101",
                         input_data14 when "01110",
                         input_data15 when "01111",
                         input_data16 when "10000",
                         input_data17 when "10001",
                         input_data18 when "10010",
                         input_data19 when "10011",
                         input_data20 when "10100",
                         input_data21 when "10101",
                         input_data22 when "10110",
                         input_data23 when "10111",
                         input_data24 when "11000",
                         input_data25 when "11001",
                         input_data26 when "11010",
                         input_data27 when "11011",
                         input_data28 when "11100",
                         input_data29 when "11101",
                         input_data30 when "11110",
                         input_data31 when "11111",
                         "00000000" when others;


end design;