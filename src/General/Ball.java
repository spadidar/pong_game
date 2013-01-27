package General;

import java.awt.*;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.geom.*;


public class Ball extends JPanel
{
	private static final long serialVersionUID = 1;

	private int width;
	private int height;
	private Color color;
	private int x,y;
	
	public Ball(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
		width = 30;
		height = 30;	
		color = color.BLUE;		
	}
	
	public void setColor(Color c) { color =  c; }
	public void setX(int xPos)	{ x = xPos;	}
	public void setY(int yPos)	{ y= yPos;	}
	public void setWidth(int w)	{ width = w;	}
	public void setHeight(int h){ height = h; 	}
	
	public Ellipse2D getEllipse()
	{ 
		Ellipse2D ellipse = new Ellipse2D.Float(x, y, width, height);
		return ellipse;
	}
	
}