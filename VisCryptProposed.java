//VisCryptProposed.java

import java.lang.*;
import java.io.*;

public class VisCryptProposed
{
	public static void encode(PGM pgmin,String outpath1,String outpath2)
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
		pgmout1.writeImageAs(outpath1);
		pgmout2.writeImageAs(outpath2);
	}
	
	public static PGM decode(String[] paths)
	{
		PGM imgin[]=new PGM[paths.length];
		for(int t=0;t<paths.length;t++) imgin[t]=new PGM(paths[t]);
		
		PGM imgout=new PGM(imgin[0].getColumns(),imgin[0].getRows());
		for(int r=0;r<imgout.getRows();r++)
		{
			for(int c=0;c<imgout.getColumns();c++)
			{
				int tsum=0;
				for(int t=0;t<imgin.length;t++) tsum+=imgin[t].getPixel(r,c);
				
				int outval=255-tsum;
				imgout.setPixel(r,c,outval);
			}
		}
		
		return(imgout);
	}
}
