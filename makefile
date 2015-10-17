SHELL = bash

all: compile

compile:
	@mkdir -p bin
	@javac -d bin/ src/cs251lab10package/*
	@javac -d bin/ -classpath bin/ src/lab10Main.java
	
clean:
	@rm -rf bin
	
startClient:
	@java -classpath bin/ lab10Main client
	
startServer:
	@java -classpath bin/ lab10Main server
	
