# Java-Chat-Service
A chat service application for exchanging RSA encrypted messages b/w server and client

Instruction for running the app:-
Run "make compile" first to compile the source files. And then open two terminals, one for server and one for client
Run make startServer on the first terminal and then run make startClient on the second.
Also do not send a message "End Chat" from the server to the client.
The other case, i.e. a message fromm the client to the server saying "End Chat" ends the session for the client whereas the server waits for a new client.

Lab10Main has the main function which starts the client or the server by creating an object of the class JClient or JServer accordingly. The JServer class and the JClient class initiate two threads each, namely the sender and the receiver. The sender and the receiver then use the sendMessage, receiveMessage and endChat to send or receive messages or end the chat appropriately. Also the JClient and JServer create an object of class RSA for encryption, decryption and issuing the public key to the client or server.
RSA->
In our version of RSA Algorithm, we create 2 primes of length 2048 bits each(say P and Q) and multiply them to get N
We took E at 2^16+1
Then we calculate phi as (P-1)(Q-1).
Then D is calculated as modInverse(E, phi)
Whenever encrypting the message m, we took (m^E)mod N as the encrypted message
Whenever decrypting the message m(encrypted), we took (m^D)mod N to get the original message.


