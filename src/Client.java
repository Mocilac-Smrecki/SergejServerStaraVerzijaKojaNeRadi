import java.io.*;
import java.net.*;


public class Client {

    Socket socket;
    public static final String serverIP = "localhost"; 
    public static final int serverPort = 9090;
    private PrintWriter outputMessage = null;
    private BufferedReader inputMessage = null;
    private String serverMessage;
    private Boolean ide = true;

	public static void main(String[] args) {
		new Client();	
	}
	
    public void sendMessage(String message){
        if (outputMessage != null && !outputMessage.checkError()) {
        	outputMessage.println(message);
        	outputMessage.flush();
        }
    }
 
    public void stopClient(){
        ide = false;
    }
	
	Client(){
		try {
			/*InetAddress serverAddr = InetAddress.getByName(SERVERIP);
			socket = new Socket(serverAddr, SERVERPORT);*/
			socket = new Socket(serverIP, serverPort);
			outputMessage = new PrintWriter(socket.getOutputStream(), true);
			inputMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			serverMessage = inputMessage.readLine();
			System.out.println(serverMessage);
			
			while(ide){
				serverMessage = inputMessage.readLine();//napravi nesto s tim
				System.out.println(serverMessage);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
