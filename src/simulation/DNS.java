package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SuppressWarnings("unused")
public class DNS {

	public static List<? extends Server> servers;
	private Random rand;
	
	public DNS() {
		servers = Simulation.servers;
		rand = new Random();
	}
	
	public DNS(List<? extends Server> servers) {
		DNS.servers = servers;
		rand = new Random();
	}
	
	public static synchronized Server getServerIp(int domain, Client client) throws InterruptedException {
		Random rand = new Random();
		Thread.sleep(rand.nextInt(Simulation.MAX_CLIENT_DNS_TIME));
		System.out.println("DNS answers to : " + client);
		Thread.sleep(rand.nextInt(Simulation.MAX_CLIENT_DNS_TIME));
		return servers.get(domain);
	}
}