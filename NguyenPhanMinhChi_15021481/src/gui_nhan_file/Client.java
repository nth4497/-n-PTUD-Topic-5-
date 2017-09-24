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
		
		pWest.add(btnNhan = new JButton ("Nhan file"));
		pWest.add(Box.createVerticalStrut(5));
		add(pWest,BorderLayout.WEST);
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
