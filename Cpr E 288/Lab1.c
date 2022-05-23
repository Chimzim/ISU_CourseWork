 timer_init(); // Initialize Timer, needed before any LCD screen functions can be called 
             // and enables time functions (e.g. timer_waitMillis)

lcd_init();   // Initialize the LCD screen.  This also clears the screen. 

// Print "Hello, world" on the LCD
//lcd_puts("Microcontrollers are lots of fun!");
char str[20] = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
char str1[] = "Microcontrollers are lots of fun!";
int j = 0, i = 19, c = 0, loop = 1, screenLen = 0;
while(loop == 1) {
   for(i = 0; i < 20; i++) {
     if(i+1 < 20) {
         str[i] = str[i+1];
     }
  }
  str[19] = str1[j];
  lcd_printf("%s", str);
  j++;
  timer_waitMillis(300);
 
 while(j == strlen(str1) && screenLen < 20) {
	   if(screenLen == 19) {
	   j = 0;
	   screenLen = 0;
	}
           for(c = 0; c < 20; c++) {
                 str[c] = str[c+1];
             }
			 str[19] = ' ';
			 lcd_printf("%s", str);
                 timer_waitMillis(300);
				 screenLen += 1;
   }
}