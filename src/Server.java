import java.io.IOException;
import java.net.*;
import java.util.ArrayList;


public class Server {
	
	private static final int serverPort = 9090;
	private ServerSocket serverSocket;
	private Socket client;
	private Boolean ide = true;
	private ArrayList<ClientThread> threadList= new ArrayList<ClientThread>();

	public static void main(String[] args) {
		new Server();		
	}
	
	public void stopListeningForNewConnections(){
		ide = false;
	}
	
	public void startSync(){
		Sync syn = new Sync("synchronizationThread",threadList);
		new Thread(syn).start();
	}
	
	Server(){
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("Server uspjesno pokrenut na potru: " + serverPort);
			
			//kolko konekcija prihvatiti
			while((threadList.size() < 2) && ide){
				client = serverSocket.accept();
				System.out.println("klijent " + Integer.toString(threadList.size()+1) + " uspjesno spojen");
				ClientThread ct = new ClientThread("konekcija" + Integer.toString(threadList.size()+1) , client);
				new Thread(ct).start();
				threadList.add(ct);
			}
			Sync sy = new Sync("sinkronizacija", threadList);
			new Thread(sy).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
//cd zavrsni\Projekt\bin