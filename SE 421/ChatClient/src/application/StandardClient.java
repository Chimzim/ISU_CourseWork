package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import application.chatboard.ChatBoard;

public class StandardClient extends Thread {
	
	private String host;
	private int port;
    public ChatBoard chatController;
    
    private static String userName;
	private Socket client;
	
	private OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	
	public StandardClient(String host, int port, String userName, ChatBoard controller) throws UnsupportedEncodingException {
		this.port = port;
		this.host = getServerAddress(host);
		StandardClient.userName = getUserName(userName);
		this.chatController = controller;		
		outputStream = null;
		objectOutputStream = null;
	}

	private String getUserName(String userName) throws UnsupportedEncodingException {
		return decode(userName);
	}

	@Override
	public void run() {
		try {
			client = new Socket(host, port);		
			
			System.out.println("Client successfully connected to server!");

			outputStream = client.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			
			// create a new thread for server messages handling
			new Thread(new ReceivedMessagesHandler(client, userName, host, String.valueOf(port), chatController)).start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getServerAddress(String host) throws UnsupportedEncodingException {
		return decode(host);
	}

	public static void send(String msg) throws IOException {
		Message messageObject = new Message();
		messageObject.setMessage(msg);
		messageObject.setUserName(userName);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
		Calendar now = Calendar.getInstance();
		messageObject.setMsgProcessTime(formatter.format(now.getTime()));
		
		objectOutputStream.writeObject(messageObject);
		objectOutputStream.flush();
	}
	
	
	private String decode(String string) throws UnsupportedEncodingException {
		byte[] decoded = Base64.getDecoder().decode(string);
		String s = new String(decoded, "UTF-8");
		return s;
	}
}

class ReceivedMessagesHandler implements Runnable {

	private Socket clientSocket;
	private ChatBoard chatController;
	
	private String userName;
    private String connectionText;
    private String host;
    private String port;
	
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    
	public ReceivedMessagesHandler(Socket clientSocket, String userName, String host, String port, ChatBoard chatController) {
		this.clientSocket = clientSocket;
		
		this.userName = userName;
		this.host = host;
		this.port = port;
		
		this.chatController = chatController;
		
		inputStream = null;
		objectInputStream = null;
	}

	@Override
	public void run() {
		try {
			connectionText = "Connected To Server";

			chatController.initConnectionStatus(connectionText, userName, host, port);
			
			// receive server messages and print out to screen			
			inputStream = clientSocket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);

			while(clientSocket.isConnected()) {
				Message messageObj = (Message) objectInputStream.readObject();
				System.out.println(messageObj.getMessage());
				chatController.addToChat(messageObj);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			
			connectionText = "FAILED";
			chatController.initConnectionStatus(connectionText, userName, host, port);
			
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			
			connectionText = "FAILED";
			chatController.initConnectionStatus(connectionText, userName, host, port);
						
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
			connectionText = "FAILED";
			chatController.initConnectionStatus(connectionText, userName, host, port);
			
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	private synchronized  void closeConnection() {
		
        if (inputStream != null){
            try {
            	inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (objectInputStream != null){
            try {
            	objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
	}
	

	
	
	
}
