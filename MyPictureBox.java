//MyPictureBox.java

import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
 
public class MyPictureBox extends JPanel
{
	//members
	private BufferedImage Picture;
	private JLabel tLabel=new JLabel();
	private JScrollPane tScrollPane=new JScrollPane();
	
	//constructor
	public MyPictureBox()
	{
		Picture=null;
		this.setLayout(null);
	}
	
	//get functions
	public BufferedImage getImage()
	{
		return(Picture);
	}
	
	//set functions
	public void setImage(BufferedImage tPicture)
	{
		Picture=tPicture;
		repaint();
	}
	
	//default methods
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		try
		{
			//draw image
			if(Picture!=null)
			{
				tLabel.setIcon(new ImageIcon(Picture));
				tScrollPane.setBounds(1,1,getWidth()-2,getHeight()-2);
				tScrollPane.getViewport().add(tLabel);
				this.add(tScrollPane);
			}
			
			//draw border
			g.setColor(new Color(0,0,0));
			g.drawRect(0,0,getWidth()-1,getHeight()-1);
		}
		catch(Exception err)
		{
			System.out.println("Error: "+err);
			System.exit(-1);
		}
	}
}
