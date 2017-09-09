package tuan3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class UDPServer {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		try
	     { 
			//tạo datagram socket tại cổng 9876
	      DatagramSocket serverSocket = new DatagramSocket(9876); 
	  
	      byte[] receiveData = new byte[1024]; 
	      byte[] sendData  = new byte[1024]; 
	  
	      while(true) 
	        { 
	  
	          receiveData = new byte[1024]; 
	          //tạo vùng đệm nhận datagram
	          DatagramPacket receivePacket = 
	             new DatagramPacket(receiveData, receiveData.length); 

	          System.out.println ("Waiting for datagram packet");
	          //nhận datagram
	          serverSocket.receive(receivePacket); 

	          String sentence = new String(receivePacket.getData()); 
	          //lấy IP port của người gửi
	          InetAddress IPAddress = receivePacket.getAddress(); 
	  
	          int port = receivePacket.getPort(); 
	  
	          System.out.println ("From: " + IPAddress + ":" + port);
	          System.out.println ("Message: " + sentence);

	          String capitalizedSentence = sentence.toUpperCase(); 

	          sendData = capitalizedSentence.getBytes(); 
	          // tạo datagram để gửi tới client
	          DatagramPacket sendPacket = 
	             new DatagramPacket(sendData, sendData.length, IPAddress, 
	                               port); 
	          
	          //ghi datagram ra socket
	          serverSocket.send(sendPacket); 

	        } 

	     }
	      catch (SocketException ex) {
	        System.out.println("UDP Port 9876 is occupied.");
	        
	        System.exit(1);
	      }

	}

}
