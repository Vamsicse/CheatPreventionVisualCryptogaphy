//Encoder.java

import java.lang.*;
import java.io.*;

public class Encoder
{
	public static void encode(PGM pgmin)
	{
		PGM pgmout1=new PGM(pgmin.getColumns(),pgmin.getRows());
		PGM pgmout2=new PGM(pgmin.getColumns(),pgmin.getRows());
		
		//shares algorithm
		int outval_s1[]=new int[2];
		int outval_s2[]=new int[2];
		for(int r=0;r<pgmin.getRows();r++)
		{
			for(int c=0;c<pgmin.getColumns();c+=2)
			{
				int inval=pgmin.getPixel(r,c);
				int trnd=Globals.findRandom(100);
				
				if(inval==255)
				{
					if(trnd%2==0)
					{
						outval_s1[0]=0;
						outval_s1[1]=255;
						outval_s2[0]=0;
						outval_s2[1]=255;
					}
					else
					{
						outval_s1[0]=255;
						outval_s1[1]=0;
						outval_s2[0]=255;
						outval_s2[1]=0;
					}
				}
				else if(inval==0)
				{
					if(trnd%2==0)
					{
						outval_s1[0]=0;
						outval_s1[1]=255;
						outval_s2[0]=255;
						outval_s2[1]=0;
					}
					else
					{
						outval_s1[0]=255;
						outval_s1[1]=0;
						outval_s2[0]=0;
						outval_s2[1]=255;
					}
				}
				
				pgmout1.setPixel(r,c,outval_s1[0]);
				pgmout1.setPixel(r,c+1,outval_s1[1]);
				pgmout2.setPixel(r,c,outval_s2[0]);
				pgmout2.setPixel(r,c+1,outval_s2[1]);
			}
		}
		
		//write shares
		pgmout1.writeImageAs("data\\encoded\\share1.pgm");
		pgmout2.writeImageAs("data\\encoded\\share2.pgm");
	}
}
