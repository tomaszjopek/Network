package simulation;

public class Response {
	private String response;
	
	public Response(String ip)
	{
		response = ip;
	}
	
	@Override
	public String toString() {
		return response;
	}
	
	
}