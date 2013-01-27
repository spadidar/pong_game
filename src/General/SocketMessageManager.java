package General;

import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.awt.Point ;

import static General.SocketConstants.*;

public class SocketMessageManager implements MessageManager
{
   private Socket clientSocket; // Socket for outgoing messages
   private String serverAddress; // DeitelMessengerServer address
   private MessageReceiver receiver; // receives multicast messages
   private boolean connected = false; // connection status
   private ExecutorService serverExecutor; // executor for server
   private MessageSender messageSender;
   
   public SocketMessageManager( String address )
   {
      serverAddress = address; // store server address
      serverExecutor = Executors.newCachedThreadPool();
  
   } 
   // connect to server and send messages to given MessageListener
   public void connect( MessageListener listener ) 
   {
      if ( connected )
         return; // if already connected, return immediately

      try 
      {
    	 System.out.println("Creating clientSocket...");
         clientSocket = new Socket( InetAddress.getByName( serverAddress ), SERVER_PORT );
         
//         System.out.println("Creating MessageSender for clientSocket...");
//         messageSender = new MessageSender(clientSocket); // setup the sender
//         	
//         serverExecutor.execute(messageSender);
         
         // create runnable for receiving incoming messages

//         System.out.println("Creating MessageReceiver...");
//         receiver = new MessageReceiver( listener, clientSocket );
//         
//         System.out.println("Executing MessageReceiver...");
//         serverExecutor.execute( receiver ); // execute runnable
         
         connected = true; // update connected flag
         
      } // end try
      catch(java.net.ConnectException ce)
      {
    	  System.err.println("Conncetion Refused. Server Seems to be unavailable!");
      }
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
   } // end method connect
   
   // disconnect from server and unregister given MessageListener
   public void disconnect( MessageListener listener ) 
   {
      if ( !connected )
         return; // if not connected, return immediately
//      
//      try // stop listener and disconnect from server
//      {     
         // notify server that client is disconnecting
//         Runnable disconnecter = new MessageSender( clientSocket, "", DISCONNECT_STRING );         
//         Future disconnecting = serverExecutor.submit( disconnecter );         
//         disconnecting.get(); // wait for disconnect message to be sent
//         receiver.stopListening(); // stop receiver
//         clientSocket.close(); // close outgoing Socket
//      } // end try
//      catch ( ExecutionException exception ) 
//      {
//         exception.printStackTrace();
//      } // end catch
//      catch ( InterruptedException exception ) 
//      {
//         exception.printStackTrace();
//      } // end catch
//      catch ( IOException ioException ) 
//      {
//         ioException.printStackTrace();
//      } // end catch
     
      connected = false; // update connected flag
   } // end method disconnect
   
   // send message to server
   public void sendMessage( String from, String message ) 
   {
      if ( !connected )
         return; // if not connected, return immediately
      
      System.out.println("Setting Message: " + from + " " + message);
      messageSender.setMessage(from, message);
      
      // create and start new MessageSender to deliver message
//      serverExecutor.execute( messageSender );
      System.out.println("Executing MessageSender...");
   } 
   public synchronized void sendMessage(HashMap<Integer, Integer> coords)
   {
	   if ( !connected )
	         return; // if not connected, return immediately
	      
	      System.out.println("Setting HashMap Data: " + coords.toString());
//	      messageSender.setMessage(coords);
//	      System.out.println("Executing Single MessageSender for hashmap...");
//	      serverExecutor.execute(messageSender);  
//	      messageSender.sendHashMap();
	      serverExecutor.execute(new MessageSender (clientSocket, coords));
   }
   public void sendMessage( int x, int y) 
   {
      if ( !connected )
         return; // if not connected, return immediately
      
      messageSender.setMessage(x, y);
      
      // create and start new MessageSender to deliver message
      serverExecutor.execute( messageSender );
   } 
   public void sendMessage( Point p) 
   {
      if ( !connected )
         return; // if not connected, return immediately
      
      // create and start new MessageSender to deliver message
//      serverExecutor.execute( new MessageSender( clientSocket) );
   } 
   
   public void sendMessage( String mess) 
   {
	   if ( !connected )
	         return; // if not connected, return immediately
	
       System.out.println("Single Message : " + mess);
	   messageSender.setMessageToSend(mess);
	   
       System.out.println("Executing Single MessageSender...");
	   serverExecutor.execute(messageSender);
	   
   }
   public void sendMessage( ) 
   {
	   if ( !connected )
	         return; // if not connected, return immediately

       System.out.println("Sending Nothing...");
	   //	   serverExecutor.execute( new MessageSender( clientSocket) );
	   
   }
} 