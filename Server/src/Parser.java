import java.net.Socket;

public class Parser {
	
	public Database db;
	
	public Parser() {
		db = new Database();
	}
	
	public String processMessage(String message, Socket client) {
		String response = "";
		String[] strings = message.split(":");
		if(strings[0].equals("SU")) { //authentification
			response += db.addClient(strings);
		}
		else if(strings[0].equals("L")) { //login
			response += db.loginClient(strings);
		}
		else if(strings[0].equals("ADD")) { //add event
			System.out.println(message);
			response += db.addEvent(strings);
		}
		
		return response;
	}
}
