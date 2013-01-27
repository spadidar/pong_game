package General;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

import Server.Server;
import static General.SocketConstants.*;
import java.awt.Point ;
import java.util.*;

public class MessageReceiver implements Runnable
{
	private ObjectInputStream input;
	private MessageListener messageListener; // message listener
	private boolean keepListening = true; // when false, ends runnable

	// MessageReceiver constructor
	public MessageReceiver( evisions Socket clientSocket ) 
	{
		// set listener to which new messages should be sent
		messageListener = listener;

		try 
		{
			// set timeout for reading from client
			clientSocket.setSoTimeout( 2000 ); // five seconds
			System.out.println("Creating MessageReceiver input stream...");
			// create BufferedReader for reading incoming messages
			input = new ObjectInputStream(clientSocket.getInputStream());

		} 
		catch (SocketTimeoutException sio)
		{
			System.err.println("Socket Time Out in Receiver");
		}
		catch ( IOException ioException ) 
		{
			ioException.printStackTrace();
		} // end catch
	} // end MessageReceiver constructor

	// listen for new messages and deliver them to MessageListener
	public void run() 
	{          
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer> ();
		// listen for messages until stopped
		while ( keepListening ) 
		{   
			try 
			{       
				System.out.print("Receiving...||");

				m = (HashMap<Integer, Integer>) input.readObject();		
				System.out.println("Hashmap in RECEIVER: " + m.size() + m.toString());

			}
			catch ( SocketTimeoutException socketTimeoutException ) 
			{
				System.err.println("Socket Timed Out in Receiver. Continuing");
				continue; // continue to next iteration to keep listening
			} 
			catch (ClassNotFoundException cnfe)
			{
				System.err.println("ClassNotFound Error");

				cnfe.printStackTrace();
			}
//			catch(NullPointerException npe)
//			{
////				System.err.println("Null Pointer Exception in Receiver");
//				continue;
////				try
////				{
////					Thread.sleep(3000);
////				}
////				catch(Exception e)
////				{
////					e.printStackTrace();
////				}
//			}
			catch(java.io.StreamCorruptedException sce)
			{
//				System.err.println("StreamCorrupted Exception happened");
//				sce.printStackTrace();
				m.clear();
			}
			catch ( IOException ioException ) 
			{
				System.err.println("IOerror For some reason");
				ioException.printStackTrace();            
				break;
			}

			if(m != null && !m.isEmpty())
			{
				messageListener.messageReceived(m);
				System.out.println("MEssage Received! --> " + m.size());
			}
			else
				System.out.println("Empty HashMap.In RECEIVER!");
		}
//		try
//		{   
//			if(input != null)
//				input.close();	
//		}
//		catch ( IOException ioException ) 
//		{
//			ioException.printStackTrace();     
//		} 
	} 

	// stop listening for incoming messages
	public void stopListening() 
	{
		keepListening = false;
	} 
}