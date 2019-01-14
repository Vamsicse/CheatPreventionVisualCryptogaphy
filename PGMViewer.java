//PGMViewer.java

import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

public class PGMViewer implements ActionListener
{
	private JFrame frmMain=new JFrame("");
	private MyPictureBox pbInput=new MyPictureBox();
	private JButton btRefresh=new JButton("Refresh");
	
	//constructor
	public PGMViewer(int tWidth,int tHeight)
	{
		frmMain.setResizable(false);
		frmMain.setBounds(100,100,tWidth,tHeight);
		frmMain.getContentPane().setLayout(null);
		
		OnResize();
		frmMain.getContentPane().add(pbInput);
		
		btRefresh.addActionListener(this);
		frmMain.getContentPane().add(btRefresh);
	}
	
	//events
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==btRefresh)
		{
			pbInput.revalidate();
			pbInput.repaint();
		}
	}
	
	//private functions
	private void OnResize()
	{
		pbInput.setBounds(5,5,frmMain.getWidth()-20,frmMain.getHeight()-62);
		btRefresh.setBounds(5,frmMain.getHeight()-54,80,20);
		frmMain.setLocationRelativeTo(null);
	}
	
	
	//get functions
	public String getTitle()
	{
		return(frmMain.getTitle());
	}
	
	//set functions
	public void setTitle(String tTitle)
	{
		frmMain.setTitle(tTitle);
	}
	
	public void setImage(String tPath)
	{
		try
		{
			String tExtension=tPath.substring(tPath.lastIndexOf(".")+1);
			BufferedImage tImage=null;
			
			if(tExtension.compareToIgnoreCase("pgm")==0)
			{
				PGM tpgm=new PGM(tPath);
				tImage=tpgm.getBufferedImage();
			}
			
			pbInput.setImage(tImage);
			setSize(tImage.getWidth(),tImage.getHeight());
		}
		catch(Exception err)
		{
			System.out.println("Error: "+err);
			System.exit(-1);
		}
	}
	
	public void setSize(int tWidth,int tHeight)
	{
		frmMain.setSize(tWidth,tHeight);
		OnResize();
	}
	
	public void setLocation(int tX,int tY)
	{
		frmMain.setLocation(tX,tY);
	}
	
	//methods
	public void show()
	{
		frmMain.setVisible(true);
	}
}
