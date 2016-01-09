package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation implements Parameters {
	static final List<Server> servers = new ArrayList<>(MAX_SERVERS);
	static final List<Client> clients = new ArrayList<>(MAX_CLIENTS);
		
	public static void start() {
		
		DNS.servers = servers;
		
		for(int i=0;i<MAX_SERVERS;i++) {
			servers.add(new Server(i));
		}
		
		for(int i=0;i<MAX_CLIENTS;i++) {
			clients.add(new Client(i));
		}
		
		for(int i=0;i<INQUIRIES;i++) {
			Random rand = new Random();
			clients.get(rand.nextInt(MAX_CLIENTS)).incrementInquiry();			
		}
		
		for(int i=0;i<Math.max(MAX_CLIENTS, MAX_SERVERS);i++) {
			if(i<MAX_CLIENTS)
				clients.get(i).start();
						
			if(i<MAX_SERVERS)
				servers.get(i).start();			
		}
		
		while(!allCompleted());
		for(int i=0;i<MAX_SERVERS;i++) {
			servers.get(i).setFlag(false);
		}
		
	}
	
	public static boolean allCompleted() {
		for(int i=0;i<MAX_CLIENTS;i++)
			if(clients.get(i).getInquiry() != 0)
				return false;
		return true;
	}

}