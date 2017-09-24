package gui_nhan_file;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	//JButton btnConnect;
    JButton btnNhan;
	JTextField txtfilelocation,txtPort,txtip;
	
	DefaultListModel <Integer> lstmodel;
	JList <Integer> lstds;
	JSplitPane split;
	public static Socket Socket=null;
//	 public final static int SOCKET_PORT =12345;  // you may change this
//	  public final static String FILE_TO_SEND = "D:/File/mau.txt";  // you may change this
	public Client() throws IOException{
		super ("Client");
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		setSize(700,400);
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle= new JLabel ("Transfer File Between Computers"));
		Font fp= new Font ("Time new Roman",Font.BOLD,30);
		lblTitle.setFont(fp);
		lblTitle.setForeground(Color.blue);
		add(pNorth,BorderLayout.NORTH );

		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createLineBorder(Color.red));
		pWest.setLayout(new BoxLayout(pWest,BoxLayout.Y_AXIS) );
		//pWest.add(btnConnect = new JButton ("CONNECT SERVER"));
		//pWest.add(Box.createVerticalStrut(5));
		pWest.add(btnNhan = new JButton ("Nhan file"));
		pWest.add(Box.createVerticalStrut(5));
		add(pWest,BorderLayout.WEST);
		//btnConnect.addActionListener(this);
		btnNhan.addActionListener(this);
		JPanel p1;
		 p1 = new JPanel();
		 JPanel pCen = new JPanel();
		 Box b = Box.createVerticalBox();
		 b.setBorder(BorderFactory.createLineBorder(Color.red));
		 JLabel ip= new JLabel();
		 p1.add(ip = new JLabel("filelocation:"));
		 p1.add(txtfilelocation = new JTextField(20));
		 JLabel port= new JLabel();
		 p1.add( port= new JLabel("Port:"));
		 p1.add(txtPort = new JTextField(20));
		
		 JLabel ipi= new JLabel();
		 p1.add( ipi= new JLabel("IpAddress:"));
		 p1.add(txtip = new JTextField(20));
		
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
//		String so3;//dia chi IP
		fileLocation = txtfilelocation.getText();
		so2=String.valueOf(portNo);
		diachiip = txtip.getText();
		if(obj.equals(btnNhan)){
			{
				
				if(fileLocation.equals("")|| diachiip.equals("")||so2.equals(""))
				JOptionPane.showMessageDialog(this, "Ban chua nhap lieu");
			else
				try{
					fileLocation = txtfilelocation.getText();
					diachiip=txtip.getText(); // so3:diachiip
					portNo=Integer.parseInt(txtPort.getText());
					int bytesRead=0;
					int current = 0;
					FileOutputStream fileOutputStream = null;
					BufferedOutputStream bufferedOutputStream = null;
					Socket socket = null;
					try {

						//creating connection.
						socket = new Socket(diachiip,portNo);
						System.out.println("connected.");
						
						// receive file
						byte [] byteArray  = new byte [6022386];					//I have hard coded size of byteArray, you can send file size from socket before creating this.
						System.out.println("Please wait downloading file");
						
						//reading file from socket
						InputStream inputStream = socket.getInputStream();
						fileOutputStream = new FileOutputStream(fileLocation);
						bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
						bytesRead = inputStream.read(byteArray,0,byteArray.length);					//copying file from socket to byteArray

						current = bytesRead;
						do {
							bytesRead =inputStream.read(byteArray, current, (byteArray.length-current));
							if(bytesRead >= 0) current += bytesRead;
						} while(bytesRead > -1);
						bufferedOutputStream.write(byteArray, 0 , current);							//writing byteArray to file
						bufferedOutputStream.flush();												//flushing buffers
						
						System.out.println("File " + fileLocation  + " downloaded ( size: " + current + " bytes read)");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally {
						if (fileOutputStream != null) fileOutputStream.close();
						if (bufferedOutputStream != null) bufferedOutputStream.close();
						if (socket != null) socket.close();
					}
					
				}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Nhap sai dia chi");
				txtfilelocation.selectAll();
				txtfilelocation.requestFocus();
				txtPort.selectAll();
				txtPort.requestFocus();
				txtip.selectAll();
				txtip.requestFocus();
				}
			}
				
		
			}		
			
		}
		
	
	public static void main(String[] args) {
		try {
			new Client().setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
