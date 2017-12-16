package topic5_gui_nhan_file;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
@SuppressWarnings("serial")
public class Client extends JFrame implements ActionListener{
	JLabel lblTitle;
    JButton btnNhan;
	JTextField txtfilelocation,txtPort,txtip, txtlink;
	JSplitPane split;
	public static Socket Socket=null;
	JFileChooser browser= new JFileChooser() ;
	
	public Client() throws IOException{
		super ("Client");
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		setSize(460,200);
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle= new JLabel ("Gửi File Giữa 2 Máy Tính"));
		Font fp= new Font ("Time new Roman",Font.BOLD,30);
		lblTitle.setFont(fp);
		lblTitle.setForeground(Color.blue);
		add(pNorth,BorderLayout.NORTH );

		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createLineBorder(Color.red));
		pWest.setLayout(new BoxLayout(pWest,BoxLayout.Y_AXIS) );
		pWest.add(btnNhan = new JButton ("Nhận file"));
		pWest.add(Box.createVerticalStrut(5));
		add(pWest,BorderLayout.WEST);
		btnNhan.addActionListener(this);
		JPanel p1;
		 p1 = new JPanel();
		 JPanel pCen = new JPanel();
		 Box b = Box.createVerticalBox();
		 b.setBorder(BorderFactory.createLineBorder(Color.red));
		 JLabel ip= new JLabel();
		// p1.add(ip = new JLabel("Lưu file vào"));
		// p1.add(txtfilelocation = new JTextField(20));
		 
		 JLabel port= new JLabel();
		 p1.add( port= new JLabel("Port client "));
		 p1.add(txtPort = new JTextField(20));
		
		 JLabel ipi= new JLabel();
		 p1.add( ipi= new JLabel("Địa chỉ IP"));
		 p1.add(txtip = new JTextField(20));
		 
		 JLabel link= new JLabel();
		 p1.add(txtlink = new JTextField(20));
		 txtlink.setEditable(false);
		 txtlink.setBorder(null);
		 txtlink.setForeground(Color.red);
		 txtlink.setFont(new Font("Arial",Font.ITALIC,12));
		 
		
		 b.add(p1);
		 b.add(Box.createVerticalStrut(5));
		 split.setLeftComponent(pWest);
		 split.setRightComponent(b);
		 add(split,BorderLayout.CENTER);
		 
		 	
}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		String fileLocation;
		String diachiip;
		int portNo = 0;
//		String so1;//filelocation
		String so2;//port
		so2=String.valueOf(portNo);
		boolean ktrasocket;
		
		diachiip = txtip.getText();
		
		
		 
		if(obj.equals(btnNhan)){
			
				
				if( diachiip.equals("")||so2.equals(""))
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập liệu");
			else
				try
				{
					//fileLocation = txtfilelocation.getText();
					diachiip=txtip.getText(); 
					portNo=Integer.parseInt(txtPort.getText());
					int bytesRead=0;
					int current = 0;
					FileOutputStream fileOutputStream = null;
					BufferedOutputStream bufferedOutputStream = null;
					Socket socket = null;
					try 
					{
						try {
						//tạo kết nối
						socket = new Socket(diachiip,portNo);
						    //System.out.println("connected.");
						
						
						
						}     
						catch(Exception ex){
							// TODO: handle exception
						    JOptionPane.showMessageDialog(this, "Kết nối lại");
						   // System.exit(0);
						    
						
						}
						//nhận file
						 DataInputStream input=new DataInputStream(socket.getInputStream());
							String s=input.readUTF();
							txtlink.setText(s);
							JDialog.setDefaultLookAndFeelDecorated(true);
							int response = JOptionPane.showConfirmDialog(null,"Bạn có muốn nhận file\""+s+"\" không?", "Confirm",
					      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (response == JOptionPane.YES_OPTION) 
					{
								JDialog.setDefaultLookAndFeelDecorated(true);
								int response1 = JOptionPane.showConfirmDialog(null,txtfilelocation=new JTextField(), "Nhập đường dẫn lưu file",
						      JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (response1 == JOptionPane.OK_OPTION) {
								fileLocation = txtfilelocation.getText();
						    if(fileLocation.equals("")) {
						    	JOptionPane.showMessageDialog(this, "Bạn chưa nhập liệu");
						    }else {
									
								
						     byte [] byteArray  = new byte [6022386];		
						    // JOptionPane.showMessageDialog(this, "Đang tải tập tin");
						     //System.out.println("Please wait downloading file");
						     //doc file tu socket
						    
						    
						     fileOutputStream = new FileOutputStream(fileLocation);
						     bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
						     bytesRead = input.read(byteArray,0,byteArray.length);		//copy file tu socket den mang byte			
                             current = bytesRead;
						do 
						{
							bytesRead =input.read(byteArray, current, (byteArray.length-current));
							if(bytesRead >= 0) current += bytesRead;
						} 
						while(bytesRead > -1);
						bufferedOutputStream.write(byteArray, 0 , current);							
						bufferedOutputStream.flush();												
						JOptionPane.showMessageDialog(this, "File nhận được \n"+"File " + fileLocation  + " downloaded ( size: " + current + " bytes read)");
						//System.out.println("File " + fileLocation  + " downloaded ( size: " + current + " bytes read)");
						    }  
								}
					}
						
					}
						catch (IOException e1) 
						{
						   // TODO Auto-generated catch block	
						   e1.printStackTrace();
					    }
					
					finally 
					{
						if (fileOutputStream != null) fileOutputStream.close();
						if (bufferedOutputStream != null) bufferedOutputStream.close();
						if (socket != null) socket.close();
					}
					
				}
			    catch(Exception ex)
				{
				   JOptionPane.showMessageDialog(this, "Nhập sai địa chỉ");
			 	   txtfilelocation.selectAll();
				   txtfilelocation.requestFocus();
				   txtPort.selectAll();
				   txtPort.requestFocus();
				   txtip.selectAll();
				   txtip.requestFocus();
			    }
				
			}
			
		
			}		
		
		
		 
			
	public static void main(String[] args) 
	{
		try 
		{
			new Client().setVisible(true);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

