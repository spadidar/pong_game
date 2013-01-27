package Client;

import javax.swing.JFrame;
import java.awt.Point ;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.*;
import java.io.*;
import java.net.InetAddress;

import General.*;
import GUI.*;
import Server.Server;
import static General.SocketConstants.*;
import java.util.*;

public class Client implements Runnable

{
	private static final long serialVersionUID = 1;

	private String userName; // userName to add to outgoing messages
	private Socket clientSocket;
	private String serverAddress;
	private ClientGUIFrame gui;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String message;
	
	private MessageManager messageManager; // communicates with server
	private MessageListener messageListener; // receives incoming messages
	private HashMap<Integer, Integer> coords;
	
	public Client(MessageManager m, ClientGUIFrame g)
	{
		try
		{
			messageManager = m;
			gui = g;
			coords = new HashMap<Integer, Integer> ();
			
			messageListener = new ClientMessageListener();
			connectToServer(); // connect to server

		} 
		catch ( IOException ioException ) 
		{
			System.err.println("Error in Clinet's constructor");
			ioException.printStackTrace();
		}
		catch ( Exception e) 
		{
			System.err.println("Error in Clinet's constructor Exception not IO");
			e.printStackTrace();
		}
	}
	public void setGUI(ClientGUIFrame g)
	{
		gui = g;
	}
	private void connectToServer() throws IOException
	{
		System.out.println("Connecting...");
		
		messageManager.connect(messageListener);
		
		System.out.println("Connected to Server! ");		
	}

	public void updateBatPos(int xp, int yp)
	{
		System.out.println("Point Updated in Client Backend : " + xp + " -- " + yp );

		messageManager.sendMessage("Test From Clinet ", " Message Coming ");
	}
	
	public void updateBallPos(Point p)
	{

	}

	public void setMessageToSend(String mess)
	{
		messageManager.sendMessage(mess);
	}

	public void setHashmap(int xpos, int ypos)
	{
		Integer x = new Integer(xpos);
		Integer y = new Integer(ypos);
		
		coords.put(x, y);
		
		System.out.println("Size of Coords hash map: " + coords.size() + " Data: " + coords.toString());
	
		messageManager.sendMessage(coords);
		coords.clear();
	}

	public void run()
	{
		
	}
	
	private class ClientMessageListener implements MessageListener
	{
		@Override
		public void messageReceived(String from, String message)
		{
			System.out.println("Received message in clinet");
			
		}
		@Override
		public void messageReceived(Point p)
		{
			System.out.println("Received message in clinet");
			
		}
		
		public void messageReceived(HashMap<Integer, Integer> hashmap)
		{
			System.out.println("Received message in clinet");
			
		}
		
		public void messageReceived(int x, int y)
		{
			System.out.println("Received message in clinet: " + x + "  " + y);
		}
		
		public void messageReceived(String mess)
		{
			System.out.println(mess);
		}
	
		@Override
		public void messageReceived()
		{
			System.out.println("Received message in clinet");
		}
		
		
	}

}
