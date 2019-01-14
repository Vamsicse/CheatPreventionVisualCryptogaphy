//EncodingProposed.java

import java.lang.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class EncodingProposed
{
	private String SecretPath,VerifyPath="";
	
	//constructor
	public EncodingProposed()
	{
		openSecretPath();
		openVerifyPath();
		
		if(new File(SecretPath).exists()==true&&new File(VerifyPath).exists()==true)
		{
			PGM pgmin=new PGM(SecretPath);
			System.out.println("Step1");
			VisCryptProposed.encode(pgmin,Globals.EncodedPath+"\\share1.pgm",Globals.EncodedPath+"\\share2.pgm");
			System.out.println("Step2");
			PGM pgmkey=new PGM(VerifyPath);
			VisCryptProposed.encode(pgmkey,Globals.EncodedPath+"\\verify1.pgm",Globals.EncodedPath+"\\verify2.pgm");
			JOptionPane.showMessageDialog(null,"Finished.");
			System.exit(-1);
		}
	}
	
	//internal functions
	private void openSecretPath()
	{
		JOptionPane.showMessageDialog(null,"Open Secret Image.");
		JFileChooser tFileChooser=new JFileChooser(Globals.OriginalPath);
		tFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int tResult=tFileChooser.showOpenDialog(null);
		
		if(tResult==JFileChooser.APPROVE_OPTION)
		{
			SecretPath=tFileChooser.getSelectedFile().getPath();
                        System.out.println();
			System.out.println("This is secret image "+SecretPath);
		}
	}
	
	private void openVerifyPath()
	{
		JOptionPane.showMessageDialog(null,"Open Verification Image.");
		JFileChooser tFileChooser=new JFileChooser(Globals.OriginalPath);
		tFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int tResult=tFileChooser.showOpenDialog(null);
		
		if(tResult==JFileChooser.APPROVE_OPTION)
		{
			VerifyPath=tFileChooser.getSelectedFile().getPath();
			System.out.println("This is verfication path "+VerifyPath);
		}
	}
	
	//main
	public static void main(String args[])
	{
		new EncodingProposed();
	}
}
