import java.net.Socket;

public class Parser {
	
	public Database db;
	
	public Parser() {
		db = new Database();
	}
	
	public String processMessage(String message, Socket client) {
		String response = "";
		String[] strings = message.split(":");
		if(strings[0].equals("SU")) { //login
			response += db.addClient(strings);
		}
		else if(strings[0].equals("L")) {
			response += db.loginClient(strings);
		}
		
		return response;
	}
}
