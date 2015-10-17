package cs251lab10package;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

public class Sender extends Thread {
	String prompt;
	JChatComm chat;
	JPacket pkt;
	public Sender(JChatComm chat, String prompt) {
		this.chat = chat;
		this.prompt = prompt;
	}

	public void run() {
		pkt = new JPacket(new JMessage(chat.myPublicKey));
		try {
			chat.sendMessage(pkt);
		} catch (EOFException | SocketException e) {
			e.printStackTrace();
		}
		pkt = new JPacket(new JMessage(chat.myModulus));
		try {
			chat.sendMessage(pkt);
		} catch (EOFException | SocketException e) {
			e.printStackTrace();
		}

		while (true) {
			String input="";
			try {
				input = chat.stdIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (input.equalsIgnoreCase("End Chat")) {
				chat.endChat();
				return;
			}
			pkt = new JPacket(new JMessage(input));
			try {
				chat.sendMessage(pkt);
			} catch (EOFException | SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("<sent at: "+pkt.timestamp+">\n");
		}
	}

}
