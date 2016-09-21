OBJS = alarm.o main.o arduino.o mobile.o lib.o my.h
all : main.out
main.out : $(OBJS)
	$(CC) -o $@ $(OBJS) -L /usr/lib/ -lpthread -lmysqlclient -g
.c.o: my.h lib.o
	$(CC) -c $<  -L /usr/lib/ -lpthread -lmysqlclient -g
clean :
	rm -f main.out *.o
