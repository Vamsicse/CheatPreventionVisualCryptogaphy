//Globals.java

import java.lang.*;
import java.io.*;

public class Globals
{
	public static String OriginalPath="data\\original";
	public static String EncodedPath="data\\encoded";
	public static String DecodedPath="data\\decoded";
	public static String DataPath="data";
	
	//global functions
	public static int findRandom(int max)
	{
		double trandom=Math.random();
		int ans=(int)(max*trandom);
		return(ans);
	}
	
	public static void displayPGM(String tpath,String ttitle)
	{
		PGMViewer tviewer=new PGMViewer(512,512);
		tviewer.setTitle(ttitle);
		tviewer.setImage(tpath);
		tviewer.show();
	}
	
	static String replaceString(String str,String pattern,String replace)
	{
		int s=0;
		int e=0;
		StringBuffer result=new StringBuffer();

		while((e=str.indexOf(pattern,s))>=0)
		{
			result.append(str.substring(s,e));
			result.append(replace);
			s=e+pattern.length();
		}
		result.append(str.substring(s));
		return(result.toString());
	}
}
