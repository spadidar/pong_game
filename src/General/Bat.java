package General;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.geom.*;

public class Bat extends JPanel
{
	private static final long serialVersionUID = 1;

	private String position;
	private int width;
	private int height;
	private Color color;
	private int x,y;
	private  int arcWidth;
	private  int arcHeight;
	Graphics graph = getGraphics();
	public Bat(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
		width = 15;
		height = 100;
		arcWidth = 20;
		arcHeight = 20;
		color = Color.RED;
	}

	public int getXPos(){ return x;}
	public int getYPos(){ return y;}
	
	public void setColor(Color c) { color =  c; }
	public void setX(int xPos)	{ x = xPos;	}
	public void setY(int yPos)	{ y= yPos;	}
	public void setWidth(int w)	{ width = w;	}
	public void setHeight(int h){ height = h; 	}
	public void setArcWidth(int aw){ arcWidth = aw;	}
	public void setArcHeight(int ah){ arcHeight	= ah; }

	public RoundRectangle2D getRec()
	{
		RoundRectangle2D r = new  RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight);
		return r;
	}
}
