server : bank.c queue.c server.o
	gcc -o server server.c -pthread

server.o : server.c bank.h
	gcc -c server.c -pthread

clean : 
	rm server server.o
