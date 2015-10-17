
import cs251lab10package.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lab10Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.print("Who are you? Server or Client? : ");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String ans = bf.readLine();
		if(ans.equalsIgnoreCase("server")){
			 JServer server = new JServer(5123);
		}
		else{
			JClient client = new JClient();
		}
	}

}
