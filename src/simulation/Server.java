package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server extends Thread {
	private String ip;
	private int id;
	private boolean active;
	private List<Client> clientsQueue;
	private boolean flag = true;
	Random rand = new Random();

	public Server() {
		setIp("0.0.0.0");
		setIden(-1);
		setActive(false);
		setClientsQueue(new ArrayList<Client>());
	}

	public Server(int id) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int number = rand.nextInt(256);
			str.append(number);
			if (i != 3)
				str.append(".");
		}
		setIp(str.toString());
		this.setIden(id);
		setActive(true);
		setClientsQueue(new ArrayList<Client>());
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIden() {
		return id;
	}

	public void setIden(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<Client> getClientsQueue() {
		return clientsQueue;
	}

	public void setClientsQueue(List<Client> clientsQueue) {
		this.clientsQueue = clientsQueue;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/** 
	 * Converter number one, checking if server is accessible
	 * @return status of the server
	 * @throws InterruptedException 
	 */
	public synchronized boolean availability() throws InterruptedException {
		Thread.sleep(rand.nextInt(Simulation.MAX_CLIENT_SERVER_TIME));
		Thread.sleep(rand.nextInt(Simulation.MAX_CLIENT_SERVER_TIME));
		return isActive();
	}
	
	public synchronized String response() {
		return new Response("Server: " + this.id + ", " + this.ip + "  response  ").toString();
	}
		
	@Override
	public void run() {
		
		while(flag) {
			synchronized(clientsQueue) {
				if(!clientsQueue.isEmpty()) {
					Client tmpClient = clientsQueue.remove(0);
					try {
						Thread.sleep(rand.nextInt(Simulation.MAX_PROCESING_TIME));
						System.out.println(response() + "for Client " + (tmpClient.getIden()) + " request");
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		
	}

}