-------------------------------------------------------------------------
-- Chimzim Ogbondah
-- Software Engineering
-- Iowa State University
-------------------------------------------------------------------------


-- barrelShifter.vhd
-------------------------------------------------------------------------
-- DESCRIPTION: This file contains and implementation of a 2-4 mux
--
--
-- NOTES:
-- 8/27/20 by Chimzim::Design created.
-------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;


entity shiftControl is
  port(s0             : in std_logic_vector(1 downto 0);
       iW             : in std_logic;
       iX             : in std_logic;
       iY             : in std_logic;
       iZ             : in std_logic;
       oY 	      : out std_logic);

end shiftControl;

architecture design of shiftControl is
  
begin
   with s0 select
		oY <= iW when "00",
		      iX when "01",
		      iY when "10",
		      iZ when "11",
		      '0' when others;

end design;