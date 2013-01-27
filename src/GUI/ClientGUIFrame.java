package GUI;

import java.awt.*;

import javax.swing.*;
import General.*;
import Client.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ClientGUIFrame extends JFrame implements KeyListener,
		ActionListener
{
	private static final long serialVersionUID = 1;
	private static ClientGUI panel = new ClientGUI();
	private int batSpeed = 10;
	private int width, height;
	private Client client;
	private Point batPos;
	private Point ballPos;
	private MessageManager messageManager ;
//	private MessageListener messageListener;
	
	ExecutorService es ;
	
	public ClientGUIFrame(MessageManager mm)
	{
		messageManager = mm ;

		client = new Client(messageManager, this);

		setWidth(800);
		setHeight(400);
		setSize(width, height);

		add(panel);

		addKeyListener(this);
		
		es = Executors.newFixedThreadPool(1);
		
	}

	public void setClient(Client c)
	{
		client = c;
	}
	public void setBatSpeed(int s)
	{
		batSpeed = s;
	}

	public void keyPressed(KeyEvent event)
	{
		int x = panel.getMyBat().getXPos();
		int y = panel.getMyBat().getYPos();

		if (event.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (y < height - 120)
			{
				panel.reDrawMyBat(x, y + batSpeed);
				setBatPos(x, y + batSpeed);
			}
		}
		if (event.getKeyCode() == KeyEvent.VK_UP)
		{
			if (y > 0)
				panel.reDrawMyBat(x, y - batSpeed);
		}

	}
	public void setWidth(int w)
	{
		width = w;
	}
	public void setHeight(int h)
	{
		height = h;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}

	public void keyReleased(KeyEvent event)
	{

	}

	public void keyTyped(KeyEvent event)
	{

	}

	public void actionPerformed(ActionEvent event)
	{

	}

	public void setBatPos(int xPos, int yPos)
	{
		System.out.println(xPos +" <---> "+ yPos);
		client.setHashmap(xPos, yPos); // set the coords in hashmap to get it sent
	}

	public void setBallPos(int xPos, int yPos)
	{

	}

	public Point getBatPos()
	{
		return batPos;
	}
//	public void run()
//	{
//		while (true)
//		{
//			try
//			{
////				manager.sendMessage(getBatPos());
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}


}
