package cs251lab10package;
import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Date;


public class Receiver extends Thread {
    String prompt;
    JChatComm chat;
    String serverAdd;
    
    public Receiver(JChatComm chat, String prompt, String serverAdd) {
        this.chat = chat;
        this.serverAdd = serverAdd;
        this.prompt = prompt;
    }
    
    public void run() {       
        JPacket received;
        int count=0;
        String publicKey;
        String Modulus;
        try {
            while((received = chat.receiveMessage()) != null) {    
                if (count==0){
                	publicKey=received.core.text;
                	chat.hisPublic = publicKey;
                }
                else if (count==1){
                	Modulus=received.core.text;
                	chat.hisModulus = Modulus;
                	if (!(prompt.equalsIgnoreCase("Client : "))){
                		System.out.println("Connected to server: "+serverAdd+"\nStreams Established. Ready to chat ..");
                	}
                	else {
                		System.out.println("Keys Exchanged!");
                	}
                }
                else{
                	           	
                	System.out.println("<This message was sent at: "+received.timestamp+">");
        	        System.out.println("[This message was received at:"+ new Timestamp((new Date()).getTime())+"]");
	            }
            count+=1;
            }
        } catch (SocketException e) {
            if (prompt.equalsIgnoreCase("Client : ")){
            	System.out.println("Session Ended with client!\nWaiitingfor a client to connect...");
            }
                      
        } catch (EOFException e) {
        	if (prompt.equalsIgnoreCase("Client : ")){
            	System.out.println("Session Ended with client!\nWaiitingfor a client to connect...");
            }
        } catch (IOException e) {
            e.printStackTrace();
    
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
