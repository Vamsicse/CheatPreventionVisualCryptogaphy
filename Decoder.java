//Decoder.java

import java.lang.*;
import java.io.*;

public class Decoder
{
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
				for(int t=0;t<imgin.length;t++) 
                                   tsum+=imgin[t].getPixel(r,c);
			
				int outval=255-tsum;
				imgout.setPixel(r,c,outval);
			}
		}
		
		return(imgout);
	}
}
