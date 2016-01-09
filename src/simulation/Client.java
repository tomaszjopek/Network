package simulation;

import java.util.Random;

public class Client extends Thread {

		private int id;
		private int inquiry;	
		
		public Client() {
			setIden(-1);
			setInquiry(0);
		}
		
		public Client(int id) {
			setIden(id);
			setInquiry(0);
		}

		public int getIden() {
			return id;
		}

		public void setIden(int id) {
			this.id = id;
		}
		
		public int getInquiry() {
			return inquiry;
		}
		
		public void setInquiry(int newValue) {
			this.inquiry = newValue;
		}		
		
		public void incrementInquiry() {
			this.inquiry++;
		}
		
		public void decrementInquiry() {
			this.inquiry--;
		}		
		
		@Override
		public void run() {
			
			Random rand = new Random();
			
			while(inquiry > 0)
			{
				int server = rand.nextInt(Simulation.MAX_SERVERS);
				try {
					if(!DNS.servers.isEmpty())
					{
						System.out.println(this + "send request to DNS server");
						Server tmpServer = DNS.getServerIp(server, this);
						if(tmpServer.availability()) {
							tmpServer.getClientsQueue().add(this);
							decrementInquiry();
						}
						else {
							System.out.println(tmpServer + "doesn't response");
						}
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}				
			}
			
		}	
		
		@Override
		public String toString() {
			return  "Client " +this.id + " ";
		}
			
}