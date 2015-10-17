package cs251lab10package;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class JChatComm {
	String type;
	String hisPublic;
	String hisModulus;
	String myPublicKey;
	String myModulus;
	RSA myrsa;
	ObjectInputStream in;
    ObjectOutputStream out;
    BufferedReader stdIn;
    Socket socket;
    int count1=0;
    int count2=0;
    public JChatComm(Socket echoSocket,ObjectInputStream in,ObjectOutputStream out, String type) {
    	myrsa = new RSA();
    	myPublicKey = myrsa.getPublicKey();
    	myModulus = myrsa.getModulus();
        this.type = type;
    	this.socket = echoSocket;
    	this.out = out;
    	this.in = in;
        stdIn = new BufferedReader(
                new InputStreamReader(System.in));
    }
    
    public void sendMessage(JPacket inputString) throws EOFException, SocketException {
    
    	if (count1==0||count1==1){
    	}
    	else
    	inputString.core.text = myrsa.encrypt(inputString.core.text,hisModulus,hisPublic);
        try {
            out.writeObject(inputString);
            count1+=1;
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    
    public JPacket receiveMessage() throws IOException, ClassNotFoundException, SocketException, EOFException {
        JPacket temp = (JPacket) in.readObject();
        
        if(count2==0||count2==1){
        	
        }
        else{
        	if (type.equalsIgnoreCase("Client")){
        		System.out.println("Server: ");
        	}
        	else {
        		System.out.println("Client: ");
        	}
        	System.out.println("Encrypted Message: "+temp.core.text);
        	temp.core.text = myrsa.decrypt(temp.core.text);
	        System.out.println("Decrypted Message: "+temp.core.text);
	 
	        
        }
        count2+=1;
        return temp;
    }
    
    public void endChat() {
        //System.out.println("Inside endChat()!");
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
