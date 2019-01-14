//MaliciousParticipant.java

import java.lang.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MaliciousParticipant
{
	private String VerifyPath="";
	
	//constructor
	public MaliciousParticipant()
	{
		openVerifyPath();
		
		if(new File(VerifyPath).exists()==true)
		{
			PGM pgmkey=new PGM(VerifyPath);
			PGM pgmfake=new PGM(pgmkey.getColumns(),pgmkey.getRows());
			
			for(int r=0;r<pgmfake.getRows();r++)
			{
				for(int c=0;c<pgmfake.getColumns();c++)
				{
					int inval=pgmkey.getPixel(r,c);
					int trnd=Globals.findRandom(100);
					int outval=0;
					if(trnd%2==0) outval=255;
					pgmfake.setPixel(r,c,outval);
				}
			}
			
			pgmfake.writeImageAs(Globals.EncodedPath+"\\fake1.pgm");
			
			JOptionPane.showMessageDialog(null,"Finished.");
			System.exit(-1);
		}
	}
	
	//internal functions
	private void openVerifyPath()
	{
		JOptionPane.showMessageDialog(null,"Open Share Image for converting it into Fake Share.");
		JFileChooser tFileChooser=new JFileChooser(Globals.EncodedPath);
		tFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int tResult=tFileChooser.showOpenDialog(null);
		
		if(tResult==JFileChooser.APPROVE_OPTION)
		{
			VerifyPath=tFileChooser.getSelectedFile().getPath();
		}
	}
	
	//main
	public static void main(String args[])
	{
		new MaliciousParticipant();
	}
}
