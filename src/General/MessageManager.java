package General;

import java.awt.Point;
import java.util.HashMap;

public interface MessageManager 
{      
   public void connect( MessageListener listener );
   
   // disconnect from message server and stop routing
   // incoming messages to given MessageListener
   public void disconnect( MessageListener listener );
   
   // send message to message server
   public void sendMessage( String from, String message );

   public void sendMessage(int x, int y) ;

   public void sendMessage(String mess);

   public void sendMessage(HashMap<Integer, Integer> coords);

   public void sendMessage( Point p) ;
   
   public void sendMessage() ;
} 
