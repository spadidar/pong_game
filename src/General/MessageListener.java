package General;

import java.awt.Point;
import java.util.*;

public interface MessageListener 
{
   // receive new chat message
   public void messageReceived( String from, String message );
   public void messageReceived(int x, int y);

   public void messageReceived(Point p);
   
   public void messageReceived(HashMap<Integer, Integer> message);
   
   public void messageReceived(String mess);
   
   public void messageReceived();


} // end interface MessageListener
