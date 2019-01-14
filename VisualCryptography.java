//VisualCryptography.java

import java.lang.*;
import java.io.*;
import javax.swing.*;

public class VisualCryptography
{
	public VisualCryptography(String action,String path1,String path2)
	{
		if(action.equals("encode")==true)
		{
			System.out.print("Encoding...");
			PGM pgmin=new PGM(path1);
			(new Encoder()).encode(pgmin);
			System.out.println("done.");
		}
		else if(action.equals("decode")==true)
		{
			System.out.print("Decoding...");
			String paths[]={path1,path2};
			PGM decoded=Decoder.decode(paths);
			decoded.writeImageAs("data\\decoded\\decoded.pgm");
			System.out.println("done.");
		}
	}
	
	public static void main(String args[])
	{
		if(args.length<2||args.length>3)
		{
			System.out.println("Incorrect Arguments");
			System.exit(-1);
		}
		
		String action=args[0];
		if(action.equals("encode")==true)
		{
			new VisualCryptography("encode",args[1],"");
		}
		else if(action.equals("decode")==true)
		{
			new VisualCryptography("decode",args[1],args[2]);
		}
	}
}
