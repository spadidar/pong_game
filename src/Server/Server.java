package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.awt.Point;

import General.*;
import static General.SocketConstants.*;

public class Server implements MessageListener
{
	private static final long serialVersionUID = 1;

	private ExecutorService serverExecutor; // executor for server
	private Socket clientSocket;
	private ServerSocket serverSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private static MessageManager messageManager;
	
	public void startServer()
	{
		// create executor for server runnables
		serverExecutor = Executors.newCachedThreadPool();
//		messageManager = new SocketMessageManager();
		try
		// create server and manage new clients
		{
			serverSocket = new ServerSocket(SERVER_PORT, 100);

			while (true)
			{
				try
				{
					waitForConnections();

				} catch (Exception e)
				{
					System.err.println("Error in inner loop in Server");
					e.printStackTrace();
				}
			}
		} 
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		} 
	}

	public void display(Point p)
	{
		try
		{
			System.out.println("Point is : " + p.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void waitForConnections() throws IOException
	{
		System.out.printf("%s%d%s", "Server listening on port :::: ",
				SERVER_PORT, " ...");

		clientSocket = serverSocket.accept();
		serverExecutor.execute(new MessageReceiver (this, clientSocket));
		System.out.println("Connection received from: "	+ clientSocket.getInetAddress());
	}

	public void messageReceived(String from, String message)
	{
		System.out.println("Got Message");
		System.out.println(from + " <> " +message);
	}
	
	public void messageReceived(HashMap<Integer, Integer> hashmap)
	{
		System.out.println("Received message in Server hash map");	

		System.out.println("Size: " + hashmap.size() + " ---- " + hashmap.toString());
		hashmap.clear();
	}
	public void messageReceived(int x, int y)
	{
		System.out.println("Got Message");
		System.out.println(x + " <> " +y);
	}

	public void messageReceived(Point p)
	{
		System.out.println("Got Message");

		System.out.println("Got Message:: "+ p.toString());
	}

	public void messageReceived(String mess)
	{
		System.out.println("Got Message ->  " + mess);
	}
	
	public void messageReceived()
	{
		System.out.println("Got Message NO DATA");
	}

}
