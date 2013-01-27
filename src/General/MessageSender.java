package General;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.net.Socket;
import java.awt.Point ;

import static General.SocketConstants.*;
import java.util.*;

public class MessageSender implements Runnable 
{
	private Socket clientSocket; 
	private String messageToSend; 
	private Point point;
	private HashMap<Integer, Integer> coords;
	private HashMap<String, String> message;
	
	private  ObjectOutputStream output ;

	public MessageSender( Socket socket) 
	{
		clientSocket = socket; // store socket for client
		coords = new HashMap<Integer, Integer>();
		try
		{
			System.out.println("Creating MessageSender Output Stream...");
			output = new ObjectOutputStream(clientSocket.getOutputStream());
		}
		catch(IOException ioe)
		{
			System.err.println("IO Erorr in MessageSender Constructor!");
		}   
	} 

	public MessageSender( Socket socket, HashMap<Integer, Integer> hm ) 
	{
		clientSocket = socket; // store socket for client
		coords = hm;	
		System.out.println("Client Socket:" + socket.toString());
		System.out.println("Client hashmap:" + hm.toString());
	} 

	public void setMessage(String str1, String str2)
	{
		message.clear();
		message.put(str1, str2);
	}
	
	public synchronized void setMessage(HashMap<Integer, Integer> c)
	{
		coords = c;
		System.out.println("Size of hash in Sender: " + coords.size()+ " Data: " + coords.toString());
	}
	
	public void setMessage(int x, int y)
	{
		coords.clear();
		coords.put(x, y);
	}
	
	public void setMessageToSend(String s)
	{
		messageToSend = s;
	}
	
	public synchronized HashMap<Integer, Integer> getCoords()
	{
		return coords;
	}
	public synchronized void sendHashMap()
	{
		try // send message and flush
		{     
			System.out.println("Writing to Output -->" + coords.size()+ " Data: " + getCoords().toString());
			output.writeObject(getCoords());
			output.flush();
		}
		catch ( IOException ioException ) 
		{
			System.err.println("Error in MessageSender Run!");
			ioException.printStackTrace();
		} // end catch
	}
	// send message and end
	public void run() 
	{
		try // send message and flush
		{     
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			System.out.println("Writing to Output -->" + coords.size()+ " Data: " + getCoords().toString());
			out.writeObject(getCoords());
			out.flush();
		}
		catch ( IOException ioException ) 
		{
			System.err.println("Error in MessageSender Run!");
			ioException.printStackTrace();
		} // end catch
	} // end method run
} 
