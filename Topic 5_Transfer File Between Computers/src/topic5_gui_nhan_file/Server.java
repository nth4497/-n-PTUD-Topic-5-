
package topic5_gui_nhan_file;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import java.util.Scanner;
@SuppressWarnings("serial")
public class Server extends JFrame implements ActionListener{
	JLabel lblTitle;
	JButton btnBrowser ;
	JButton btnGui;
	JTextField txtfilelocation,txtPort;
	JSplitPane split;
	JFileChooser browser= new JFileChooser() ;
	public static ServerSocket serverSocket=null;
	
	public Server() throws IOException{
		super ("SERVER");
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		setSize(800,300);
		JPanel pNorth = new JPanel();
		pNorth.add(lblTitle= new JLabel ("Transfer File Between Computers"));
		Font fp= new Font ("Time new Roman",Font.BOLD,30);
		lblTitle.setFont(fp);
		lblTitle.setForeground(Color.blue);
		add(pNorth,BorderLayout.NORTH );

		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createLineBorder(Color.red));
		pWest.setLayout(new BoxLayout(pWest,BoxLayout.Y_AXIS) );
		
		pWest.add(btnGui = new JButton ("Gửi File"));
		pWest.add(Box.createVerticalStrut(5));
		add(pWest,BorderLayout.WEST);
		btnGui.addActionListener(this);
		
		JPanel p1;
		 p1 = new JPanel();
		 
		 JPanel pCen = new JPanel();
		 Box b = Box.createVerticalBox();
		 b.setBorder(BorderFactory.createLineBorder(Color.red));
		 JLabel ip= new JLabel(); 
		 p1.add(btnBrowser = new JButton ("Chọn File"));
		 p1.add(txtfilelocation = new JTextField(20));
		 p1.add(Box.createVerticalStrut(5));	
		 
		 btnBrowser.addActionListener(this);
		 
		 JLabel port= new JLabel();
		 p1.add( port= new JLabel("Port Server:"));
		 p1.add(txtPort = new JTextField(20));
		
		
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
		int portNo = 0;
		String portNo2;
		
		Socket socket = null;
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;

		OutputStream outputStream = null;
		fileLocation = txtfilelocation.getText();
		portNo2=String.valueOf(portNo);
		if(obj.equals(btnGui)){
			
			if(fileLocation.equals("")&& portNo2.equals(""))
			JOptionPane.showMessageDialog(this, "Ban chua nhap lieu");
		else
			try{
					fileLocation = txtfilelocation.getText();
					portNo=Integer.parseInt(txtPort.getText());
					serverSocket=new ServerSocket(portNo);
					JOptionPane.showMessageDialog(this, "Đợi client kết nối để gửi file");
				//System.out.println("Waiting for receiver...");
							socket = serverSocket.accept();
							//System.out.println("Accepted connection : " + socket);
							JOptionPane.showMessageDialog(this, "Đã chấp nhận kết nối từ client" + socket);
							
							//connection established successfully
							//creating object to send file
							File file = new File (fileLocation);
							byte [] byteArray  = new byte [(int)file.length()];
							fileInputStream = new FileInputStream(file);
							bufferedInputStream = new BufferedInputStream(fileInputStream);
							bufferedInputStream.read(byteArray,0,byteArray.length); // copied file into byteArray
		
							//sending file through socket
							outputStream = socket.getOutputStream();
							//System.out.println("Sending " + fileLocation + "( size: " + byteArray.length + " bytes)");
							outputStream.write(byteArray,0,byteArray.length);			//copying byteArray to socket
							outputStream.flush();										//flushing socket
							//System.out.println("Done.");								//file has been sent
						}
			catch(Exception ex){
				JOptionPane.showMessageDialog(this, "Nhap sai dia chi");
				txtfilelocation.selectAll();
				txtfilelocation.requestFocus();
				txtPort.selectAll();
				txtPort.requestFocus();
				}
						finally {
							if (bufferedInputStream != null)
								try {
									bufferedInputStream.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							if (outputStream != null)
								try {
									bufferedInputStream.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							if (socket!=null)
								try {
									socket.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							if (serverSocket != null)
								try {
									serverSocket.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}		
					
		}


	 if (obj.equals(btnBrowser))
		{
			browser.showOpenDialog(this);
			File file1 =browser.getSelectedFile();;
			String text=file1.getAbsolutePath();
			txtfilelocation.setText(text);
		}
	 
	
	 }
	
	

public static void main(String[] args) throws IOException {
	new Server().setVisible(true);
}
}