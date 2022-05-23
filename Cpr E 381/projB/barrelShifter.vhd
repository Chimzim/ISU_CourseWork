-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- barrelShifter.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of an 32 bit barrel shifter that offers both right and left logical and arthmetic shifting
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity barrelShifter is
  generic(N : Integer := 32);
  port(i_data : in std_logic_vector(N-1 downto 0);
       logArth : in std_logic;
       shift   : in std_logic;
       shiftAmount  : in std_logic_vector(4 downto 0);
       output : out std_logic_vector(N-1 downto 0));

end barrelshifter;

architecture structure of barrelShifter is
  
component mux
 port(s0             : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       oY 	      : out std_logic);
end component;

signal previous, temp, tempR, tempR1, tempR2, tempR3, tempR4, tempL, tempL1, tempL2, tempL3, tempL4, tempData : std_logic_vector(N-1 downto 0);
signal logArt : std_logic;
begin
---------------------------------------------------------
-- First decide if the shift is arthmetic or logical
----------------------------------------------------------
g_muxl: mux 
port MAP(s0	=> logArth,
         iX	=> '0',
         iY	=> '1',
         oY	=> logArt);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Level 1: shifting by one bit
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
L1: for a in 0 to N-2 generate
g_mux0: mux
port MAP(s0	=> shiftAmount(0),
         iX	=> i_data(a),
         iY	=> i_data(a+1),
         oY	=> tempR(a));
end generate;
g_mux1: mux 
port MAP(s0	=> shiftAmount(0),
         iX	=> i_data(31),
         iY	=> logArt,
         oY	=> tempR(31));
--left
g_muxt1: mux 
port MAP(s0	=> shiftAmount(0),
         iX	=> i_data(0),
         iY	=> logArt,
         oY	=> tempL(0));

L2: for b in 1 to N-1 generate
g_mux2: mux
port MAP(s0	=> shiftAmount(0),
         iX	=> i_data(b),
         iY	=> i_data(b-1),
         oY	=> tempL(b));
end generate;
------------------------------------------------------------------------------------------
-- Level 2: shifting by 2 bits
------------------------------------------------------------------------------------------
L3: for c in 0 to 29 generate
g_mux3: mux
port MAP(s0	=> shiftAmount(1),
         iX	=> tempR(c),
         iY	=> tempR(c+2),
         oY	=> tempR1(c));
end generate;
L4: for d in 30 to 31 generate 
g_mux4: mux
port MAP(s0	=> shiftAmount(1),
         iX	=> tempR(d),
         iY	=> logArt,
         oY	=> tempR1(d));
end generate;
---left
L45: for e in 0 to 1 generate
g_mux5: mux
port MAP(s0	=> shiftAmount(1),
         iX	=> tempL(e),
         iY	=> logArt,
         oY	=> tempL1(e));
end generate;
L5: for f in 2 to N-1 generate
g_mux6: mux
port MAP(s0	=> shiftAmount(1),
         iX	=> tempL(f),
         iY	=> tempL(f-2),
         oY	=> tempL1(f));
end generate;
------------------------------------------------------------------------------------------
-- Level 3: shifting by 4 bits
------------------------------------------------------------------------------------------
L6: for g in 0 to 27 generate
g_mux7: mux
port MAP(s0	=> shiftAmount(2),
         iX	=> tempR1(g),
         iY	=> tempR1(g+4),
         oY	=> tempR2(g));
end generate;
L7: for h in 28 to 31 generate 
g_mux8: mux
port MAP(s0	=> shiftAmount(2),
         iX	=> tempR1(h),
         iY	=> logArt,
         oY	=> tempR2(h));
end generate;
---left
L8: for i in 0 to 3 generate
g_mux9: mux
port MAP(s0	=> shiftAmount(2),
         iX	=> tempL1(i),
         iY	=> logArt,
         oY	=> tempL2(i));
end generate;
L9: for j in 4 to N-1 generate
g_mux10: mux
port MAP(s0	=> shiftAmount(2),
         iX	=> tempL1(j),
         iY	=> tempL1(j-4),
         oY	=> tempL2(j));
end generate;
------------------------------------------------------------------------------------------
-- Level 4: shifting by 8 bits
------------------------------------------------------------------------------------------
L10: for k in 0 to 23 generate
g_mux11: mux
port MAP(s0	=> shiftAmount(3),
         iX	=> tempR2(k),
         iY	=> tempR2(k+8),
         oY	=> tempR3(k));
end generate;
L11: for l in 24 to 31 generate 
g_mux12: mux
port MAP(s0	=> shiftAmount(3),
         iX	=> tempR2(l),
         iY	=> logArt,
         oY	=> tempR3(l));
end generate;
---left
L12: for m in 0 to 7 generate
g_mux13: mux
port MAP(s0	=> shiftAmount(3),
         iX	=> tempL2(m),
         iY	=> logArt,
         oY	=> tempL3(m));
end generate;
L13: for n in 8 to N-1 generate
g_mux14: mux
port MAP(s0	=> shiftAmount(3),
         iX	=> tempL2(n),
         iY	=> tempL2(n-8),
         oY	=> tempL3(n));
end generate;
------------------------------------------------------------------------------------------
-- Level 5: Shifting by 16 bits
-------------------------------------------------------------------------------------------
L14: for o in 0 to 15 generate
g_mux15: mux
port MAP(s0	=> shiftAmount(4),
         iX	=> tempR3(o),
         iY	=> tempR3(o+16),
         oY	=> tempR4(o));
end generate;
L15: for p in 16 to 31 generate 
g_mux16: mux
port MAP(s0	=> shiftAmount(4),
         iX	=> tempR3(p),
         iY	=> logArt,
         oY	=> tempR4(p));
end generate;
---left
L16: for q in 0 to 15 generate
g_mux17: mux
port MAP(s0	=> shiftAmount(4),
         iX	=> tempL3(q),
         iY	=> logArt,
         oY	=> tempL4(q));
end generate;
L17: for r in 16 to N-1 generate
g_mux18: mux
port MAP(s0	=> shiftAmount(4),
         iX	=> tempL3(r),
         iY	=> tempL3(r-16),
         oY	=> tempL4(r));
end generate;
--------------------------------------------------------------------------------------------
-- Level 6: determine if the the data should be shifted or not and if it is a left or right shift
---------------------------------------------------------------------------------------------
L18: for s  in 0 to N-1 generate 
g_mux19: mux
port MAP(s0	=> shift,
         iX	=> tempR4(s),
         iY	=> tempL4(s),
         oY	=> output(s));
end generate;

end structure;
