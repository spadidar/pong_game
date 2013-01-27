package GUI;

import java.awt.*;

import javax.swing.*;
import General.*;
import Client.*;
import java.awt.event.*;

public class ClientGUI extends JPanel 
{
	private Bat myBat;
	private Bat enemyBat;
	private Ball ball;
	private static final long serialVersionUID = 1;
	
	public ClientGUI()
	{
		setBackground(Color.YELLOW);
		createMyBat(10, 10);
		createEnemyBat(760, 10);
		createBall(300, 200);
	}
	
	public Bat getMyBat() {return myBat;}
	public Bat getEnemyBat() {return enemyBat;}
	public Ball getBall() {return ball;}
	
	public void createMyBat(int x, int y)
	{
		myBat = new Bat(x, y);
	}
	
	public void createEnemyBat(int x, int y)
	{
		enemyBat = new Bat(x, y);
	}	
	
	public void createBall(int x, int y)
	{
		ball = new Ball(x, y);
	}

	public void reDrawBall(int x, int y)
	{
		
	}
	public void reDrawMyBat(int x, int y)
	{
		myBat.setX(x);
		myBat.setY(y);
		repaint();
	}
	public void reDrawEnemyBat(int x, int y)
	{
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.fill((Shape)myBat.getRec());
		g2d.fill((Shape)enemyBat.getRec());
		
		g2d.setColor(Color.RED);
		g2d.fill((Shape)ball.getEllipse());

	}

}
