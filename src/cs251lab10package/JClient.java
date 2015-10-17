package cs251lab10package;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;


public class JClient {
    JChatComm chat;
    public JClient() throws ClassNotFoundException, IOException {
    	BufferedReader ans = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter IP address of server : ");
        this.callServer(ans.readLine(), 5123);
    }

    public void callServer(String hostName,int portNumber) throws ClassNotFoundException, UnknownHostException, IOException {
     	System.out.println("Trying to connect to a server ... ");
        Socket echoSocket = new Socket(hostName, portNumber);
        System.out.println("Connected to server : "+hostName);
        ObjectOutputStream out =  new ObjectOutputStream(echoSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(echoSocket.getInputStream());
        System.out.println("Streams established. Ready for a chat ...\n");
        WaitingThread wait = new WaitingThread();
        chat = new JChatComm(echoSocket, in, out,"Client"); 
        JPacket pkt = new JPacket(new JMessage("Hey! Want to chat? My public keys are: " + chat.myPublicKey+" and "+chat.myModulus));
        System.out.println("Me : "+pkt.core.text);
        System.out.println("<sent at: "+pkt.timestamp+">\n");
        out.writeObject(pkt);
        wait.start();
        JPacket reply = (JPacket)(in.readObject());
        if ((reply.core.text).contains("Sure")) {
	        System.out.println(hostName+ " : " + reply.core.text);
	        System.out.println("<This maessage was sent at: "+pkt.timestamp+">");
	        System.out.println("[This message was received at:"+ new Timestamp((new Date()).getTime())+"]");
	        Sender sender = new Sender(chat, "Me : ");
	        Receiver receiver = new Receiver(chat, hostName+" : ", hostName);
	        sender.start();
	        receiver.start();
        }
        else {
            echoSocket.close();
            System.out.println("Time Out!");
        }
 
    }
}
