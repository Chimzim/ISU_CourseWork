-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- registerfile.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an 32 bit register file containing 32 registers
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity registerfile is
  generic(N : Integer := 32);
  port(i_CLK           : in std_logic;     -- Clock input
       in_reset        : in std_logic;     -- Reset input of the write address
       in_enable       : in std_logic;
       readA1          : in std_logic_vector(4 downto 0);      -- read address 1
       readA2	       : in std_logic_vector(4 downto 0);       -- Read address 2
       writeA          : in std_logic_vector(4 downto 0);     -- Write enable input based on the write address
       data            : in std_logic_vector(N-1 downto 0);     -- Data value input
       output1         : out std_logic_vector(N-1 downto 0);    -- Data value ouput1
       output2         : out std_logic_vector(N-1 downto 0));   -- Data value output2

end registerfile;

architecture structure of registerfile is
  
component Nbitdff
 port(i_CLK        : in std_logic;     -- Clock input
       in_reset        : in std_logic;     -- Reset input
       in_WEn         : in std_logic;     -- Write enable input
       in_Data          : in std_logic_vector(N-1 downto 0);     -- Data value input
       o_Data          : out std_logic_vector(N-1 downto 0));
end component;

component Mux32_1
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
end component;

component Nbitdecoder
 port(i_A          : in std_logic_vector(4 downto 0);
       i_out        : out std_logic_vector(N-1 downto 0));
end component;
component andg2
  port(i_A          : in std_logic;
       i_B          : in std_logic;
       o_F          : out std_logic);
end component;

signal temp0, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp15, temp16, temp17 : std_logic_vector(N-1 downto 0);
signal temp18, temp19, temp20, temp21, temp22, temp23, temp24, temp25, temp26, temp27, temp28, temp29, temp30, temp31 : std_logic_vector(N-1 downto 0);
signal enable, tempEnable : std_logic_vector(N-1 downto 0);
begin
  
decoder: Nbitdecoder
 port MAP(i_A          => writeA,
       i_out           => tempEnable);

L1: for i in 0 to N-1 generate
g_andg2: andg2 
port MAP(i_A         => tempEnable(i),
        i_B          => in_enable,
        o_F          => enable(i));
end generate;
  ---------------------------------------------------------------------------
  -- Register 0 
  ---------------------------------------------------------------------------
dff_design0: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(0),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp0);   -- Data value output

  ---------------------------------------------------------------------------
  -- Register 1
  ---------------------------------------------------------------------------
dff_design1: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(1),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp1);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 2
  ---------------------------------------------------------------------------
dff_design2: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(2),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp2);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 3
  ---------------------------------------------------------------------------
dff_design3: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(3),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp3);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 4
  ---------------------------------------------------------------------------
dff_design4: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(4),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp4);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register5
  ---------------------------------------------------------------------------
dff_design5: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(5),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp5);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 6
  ---------------------------------------------------------------------------
dff_design6: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(6),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp6);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 7
  ---------------------------------------------------------------------------
dff_design7: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(7),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp7);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 8 
  ---------------------------------------------------------------------------
dff_design8: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(8),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp8);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 9
  ---------------------------------------------------------------------------
dff_design9: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(9),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp9);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 10 
  ---------------------------------------------------------------------------
dff_design10: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(10),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp10);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 11 
  ---------------------------------------------------------------------------
dff_design11: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(11),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp11);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 12
  ---------------------------------------------------------------------------
dff_design12: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(12),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp12);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 13
  ---------------------------------------------------------------------------
dff_design13: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(13),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp13);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 14 
  ---------------------------------------------------------------------------
dff_design14: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(14),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp14);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 15
  ---------------------------------------------------------------------------
dff_design15: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(15),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp15);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 16
  ---------------------------------------------------------------------------
dff_design16: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(16),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp16);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 17 
  ---------------------------------------------------------------------------
dff_design17: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(17),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp17);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 18
  ---------------------------------------------------------------------------
dff_design18: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(18),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp18);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 19
  ---------------------------------------------------------------------------
dff_design19: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(19),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp19);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 20 
  ---------------------------------------------------------------------------
dff_design20: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(20),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp20);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 21 
  ---------------------------------------------------------------------------
dff_design21: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(21),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp21);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 22 
  ---------------------------------------------------------------------------
dff_design22: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(22),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp22);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 23 
  ---------------------------------------------------------------------------
dff_design23: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(23),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp23);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 24 
  ---------------------------------------------------------------------------
dff_design24: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(24),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp24);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 25 
  ---------------------------------------------------------------------------
dff_design25: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(25),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp25);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 26 
  ---------------------------------------------------------------------------
dff_design26: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(26),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp26);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 27
  ---------------------------------------------------------------------------
dff_design27: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(27),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp27);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register28 
  ---------------------------------------------------------------------------
dff_design28: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(28),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp28);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 29
  ---------------------------------------------------------------------------
dff_design29: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(29),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp29);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 30 
  ---------------------------------------------------------------------------
dff_design30: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(30),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp30);   -- Data value output
  ---------------------------------------------------------------------------
  -- Register 31 
  ---------------------------------------------------------------------------
dff_design31: Nbitdff
port MAP(i_CLK        =>     i_CLK,-- Clock input
       in_reset        => in_reset,   -- Reset input
       in_WEn         => enable(31),     -- Write enable input
       in_Data         => data,   -- Data value input
       o_Data          => temp31);   -- Data value output
-----------------------------------------------------------------------------
-- Grabs the first register based off readA1
-----------------------------------------------------------------------------
g_Mux321: Mux32_1
 port MAP(sel          => readA1,
      input_data0  => temp0,
      input_data1  => temp1,
      input_data2  => temp2,
      input_data3  => temp3,
      input_data4  => temp4,
      input_data5  => temp5,
      input_data6  => temp6,
      input_data7  => temp7,
      input_data8  => temp8,
      input_data9  => temp9,
      input_data10  => temp10,
      input_data11  => temp11,
      input_data12  => temp12,
      input_data13  => temp13,
      input_data14  => temp14,
      input_data15  => temp15,
      input_data16  => temp16,
      input_data17  => temp17,
      input_data18  => temp18,
      input_data19  => temp19,
      input_data20  => temp20,
      input_data21  => temp21,
      input_data22  => temp22,
      input_data23  => temp23,
      input_data24  => temp24,
      input_data25  => temp25,
      input_data26  => temp26,
      input_data27  => temp27,
      input_data28  => temp28,
      input_data29  => temp29,
      input_data30  => temp30,
      input_data31  => temp31,
      i_out        => output1);

g_Mux322: Mux32_1
 port MAP(sel          => readA2,
      input_data0  => temp0,
      input_data1  => temp1,
      input_data2  => temp2,
      input_data3  => temp3,
      input_data4  => temp4,
      input_data5  => temp5,
      input_data6  => temp6,
      input_data7  => temp7,
      input_data8  => temp8,
      input_data9  => temp9,
      input_data10  => temp10,
      input_data11  => temp11,
      input_data12  => temp12,
      input_data13  => temp13,
      input_data14  => temp14,
      input_data15  => temp15,
      input_data16  => temp16,
      input_data17  => temp17,
      input_data18  => temp18,
      input_data19  => temp19,
      input_data20  => temp20,
      input_data21  => temp21,
      input_data22  => temp22,
      input_data23  => temp23,
      input_data24  => temp24,
      input_data25  => temp25,
      input_data26  => temp26,
      input_data27  => temp27,
      input_data28  => temp28,
      input_data29  => temp29,
      input_data30  => temp30,
      input_data31  => temp31,
      i_out        => output2);

end structure;