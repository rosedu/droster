import java.net.Socket;

public class Parser {
	
	public Database db;
	
	public Parser() {
		db = new Database();
	}
	
	public String processMessage(String message, Socket client) {
		String[] strings = message.split(":");
		if(strings[0].equals("SU")) { //login
			db.addClient(strings);
		}
		
		return message;
	}
}
