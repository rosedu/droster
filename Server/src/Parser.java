import java.net.Socket;

public class Parser {
	
	public String processMessage(String message, Socket client) {
		String[] strings = message.split(":");
		if(strings[0].equals("L")) { //login
			String user = strings[1];
			String pass = strings[2];
			String town = strings[3];
			String mail = strings[4];
		}
		
		return message;
	}
}
