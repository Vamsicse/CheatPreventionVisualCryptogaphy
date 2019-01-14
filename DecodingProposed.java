//DecodingProposed.java

import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class DecodingProposed extends JFrame implements ActionListener
{
	//ui declarations
	JFrame      frmMain  =new JFrame("Decoding [with Prevention]");
	JList       lstPath1 =new JList();
	JScrollPane spPath1  =new JScrollPane(lstPath1);
	JButton     btBrowse1=new JButton("Browse");
	JButton     btVerify =new JButton("Verify");
	JButton     btDecode =new JButton("Decode");
	JList       lstPath2 =new JList();
	JScrollPane spPath2  =new JScrollPane(lstPath2);
	JButton     btBrowse2=new JButton("Browse");
	JButton     btFlag1  =new JButton("Flag1");
	JButton     btFlag2  =new JButton("Flag2");
	JTextArea   txtResult=new JTextArea("");
	JScrollPane spResult =new JScrollPane(txtResult);
	
  //System declarations
	Vector vPaths1 = new Vector();
	Vector vPaths2 = new Vector();
	boolean flag1=false, flag2=false;
	boolean flagdecoded=false;
	int verifycount=0;
	
	public DecodingProposed()
	{
   //UI definitions
		frmMain.setResizable(false);
		frmMain.setBounds(100,100,640,280);
		frmMain.getContentPane().setLayout(null);
		frmMain.setLocationRelativeTo(null);
		
		lstPath1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spPath1.setBounds(15,15,180,74);
		spPath1.setColumnHeaderView(new JLabel("Shares:"));
		frmMain.getContentPane().add(spPath1);
		
		btBrowse1.setBounds(208,15,105,20);
		btBrowse1.addActionListener(this);
		frmMain.getContentPane().add(btBrowse1);
		
		btVerify.setEnabled(false);
		btVerify.setBounds(208,40,105,20);
		btVerify.addActionListener(this);
		frmMain.getContentPane().add(btVerify);
		
		btDecode.setEnabled(false);
		btDecode.setBounds(208,65,105,20);
		btDecode.addActionListener(this);
		frmMain.getContentPane().add(btDecode);
		
		lstPath2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		spPath2.setBounds(327,15,180,74);
		spPath2.setColumnHeaderView(new JLabel("Verify:"));
		frmMain.getContentPane().add(spPath2);
		
		btBrowse2.setBounds(515,15,105,20);
		btBrowse2.addActionListener(this);
		frmMain.getContentPane().add(btBrowse2);
		
		btFlag1.setBounds(515,40,105,20);
		btFlag1.setEnabled(false);
		btFlag1.addActionListener(this);
		frmMain.getContentPane().add(btFlag1);
		
		btFlag2.setBounds(515,65,105,20);
		btFlag2.setEnabled(false);
		btFlag2.addActionListener(this);
		frmMain.getContentPane().add(btFlag2);
		
		spResult.setBounds(15,102,frmMain.getWidth()-34,frmMain.getHeight()-148);
		spResult.setColumnHeaderView(new JLabel("Result:"));
		txtResult.setFont(new Font("Courier New",Font.PLAIN,12));
		txtResult.setEditable(false);
		frmMain.getContentPane().add(spResult);
		
		frmMain.setVisible(true);
		displayResult();
	}
	
	//events
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==btBrowse1)
		{
			JFileChooser tFileChooser=new JFileChooser(Globals.EncodedPath);
			tFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			tFileChooser.setMultiSelectionEnabled(true);
			int tResult=tFileChooser.showOpenDialog(this);
			
			if(tResult==JFileChooser.APPROVE_OPTION)
			{
				File[] files=tFileChooser.getSelectedFiles();
				
				Vector tVector=new Vector();
				vPaths1.clear();
				for(int t=0;t<files.length;t++)
				{
					vPaths1.add(files[t].getPath());
					tVector.add(files[t].getName());
				}
				lstPath1.setListData(tVector);
				displayResult();
			}
		}
		else if(evt.getSource()==btBrowse2)
		{
			JFileChooser tFileChooser=new JFileChooser(Globals.EncodedPath);
			tFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			tFileChooser.setMultiSelectionEnabled(true);
			int tResult=tFileChooser.showOpenDialog(this);
			
			if(tResult==JFileChooser.APPROVE_OPTION)
			{
				File[] files=tFileChooser.getSelectedFiles();
				
				Vector tVector=new Vector();
				vPaths2.clear();
				for(int t=0;t<files.length;t++)
				{
					vPaths2.add(files[t].getPath());
					tVector.add(files[t].getName());
				}
				lstPath2.setListData(tVector);
				btVerify.setEnabled(true);
				displayResult();
			}
		}
		else if(evt.getSource()==btVerify)
		{
			String tpath1=(String)vPaths1.get(lstPath1.getSelectedIndex());
			String tpath2=(String)vPaths2.get(lstPath2.getSelectedIndex());
			String tfname1=new File(tpath1).getName().toLowerCase();
			String tfname2=new File(tpath2).getName().toLowerCase();
			String strNum1=Globals.replaceString(Globals.replaceString(tfname1,"share",""),".pgm","");
			String strNum2=Globals.replaceString(Globals.replaceString(tfname2,"verify",""),".pgm","");
			strNum2=Globals.replaceString(Globals.replaceString(strNum2,"fake",""),".pgm","");
			int tnum1=Integer.parseInt(strNum1);
			int tnum2=Integer.parseInt(strNum2);
			
			if((tfname1.indexOf("share"))>=0&&(tfname2.indexOf("verify")>=0||tfname2.indexOf("fake")>=0))
			{
				if(tnum1!=tnum2)
				{
					String tarr1[]=new String[vPaths2.size()];
					for(int t=0;t<tarr1.length;t++) 
                                             tarr1[t]=(String)vPaths2.get(t);
					PGM temp=Decoder.decode(tarr1);
					temp.writeImageAs(Globals.DataPath+"\\temp.pgm");
					if(verifycount==0) btFlag1.setEnabled(true);
					else if(verifycount==1) btFlag2.setEnabled(true);
					verifycount+=1;
					Globals.displayPGM(Globals.DataPath+"\\temp.pgm","Verification Image");
				}
			}
		}
		else if(evt.getSource()==btDecode)
		{
			String tarr1[]=new String[vPaths1.size()];
			for(int t=0;t<tarr1.length;t++) tarr1[t]=(String)vPaths1.get(t);
			PGM temp=Decoder.decode(tarr1);
			temp.writeImageAs(Globals.DecodedPath+"\\decoded.pgm");
			Globals.displayPGM(Globals.DecodedPath+"\\decoded.pgm","Decoded Image");
			flagdecoded=true;
			displayResult();
		}
		else if(evt.getSource()==btFlag1)
		{
			flag1=true;
			displayResult();
			JOptionPane.showMessageDialog(this,"Participant1 Verification Successful.");
		}
		else if(evt.getSource()==btFlag2)
		{
			flag2=true;
			btDecode.setEnabled(true);
			displayResult();
			JOptionPane.showMessageDialog(this,"Participant2 Verification Successful.");
		}
	}
	
//Internal functions
	private void displayResult()
	{
		String tstr="Shares: "+vPaths1.size()+", Verification: "+vPaths2.size()+"\r\n";
		tstr+="Participant1 Validated: "+flag1+"\r\n";
		tstr+="Participant2 Validated: "+flag2+"\r\n";
		if(flagdecoded==true) 
                  tstr+="Decoding Successfull.";
		txtResult.setText(tstr);
	}
	
	//main
	public static void main(String args[])
	{
		new DecodingProposed();
	}
}
