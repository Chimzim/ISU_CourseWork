edit: message.o lab1.o
		gcc -o edit message.o lab1.o
		
message.o : message.c message.h
		     gcc -c message.c
			 
lab1.o : lab1.c message.h
			gcc -c lab1.c
			
clean : 
		rm edit message.o lab1.o