package Client;

import java.awt.Dimension;

import javax.swing.JFrame;

import GUI.*;
import General.*;

public class ClientRunner
{
	
	private static ClientGUIFrame guif;
	private static Client client ;
	
	public static void main(String args[])
	{		
		MessageManager mm;

		if(args.length == 0)
		{
			mm = new SocketMessageManager("localhost");
		}
		else
		{
			mm = new SocketMessageManager(args[0]);
		}
		ClientGUIFrame guiFrame = new ClientGUIFrame(mm);
		guiFrame.setSize(800, 400);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setVisible(true);
		System.out.println("GUI must be up");
	}
}
