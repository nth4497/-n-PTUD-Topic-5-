package tuan3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class UDPClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	        String serverHostname = new String ("192.168.137.1");

	        if (args.length > 0)
	           serverHostname = args[0];
	        //tạo input stream
	      BufferedReader inFromUser = 
	        new BufferedReader(new InputStreamReader(System.in)); 
	      //tạo client socket
	      DatagramSocket clientSocket = new DatagramSocket(); 
	      //chuyển đổi hostname->IP sử dụng DNS
	      InetAddress IPAddress = InetAddress.getByName(serverHostname); 
	      System.out.println ("Attemping to connect to " + IPAddress + 
	                          ") via UDP port 9876");
	  
	      byte[] sendData = new byte[1024]; 
	      byte[] receiveData = new byte[1024]; 
	  
	      System.out.print("Enter Message: ");
	      String sentence = inFromUser.readLine(); 
	      sendData = sentence.getBytes();         

	      System.out.println ("Sending data to " + sendData.length + 
	                          " bytes to server.");
	      //tạo datagram với data-to-send, length, IP addr, port
	      DatagramPacket sendPacket = 
	         new DatagramPacket(sendData, sendData.length, IPAddress, 9876); 
	      //gửi datagram tới server
	      clientSocket.send(sendPacket); 
	  
	      DatagramPacket receivePacket = 
	         new DatagramPacket(receiveData, receiveData.length); 
	  
	      System.out.println ("Waiting for return packet");
	      clientSocket.setSoTimeout(10000);

	      try {
	    	  //đọc datagram từ server
	           clientSocket.receive(receivePacket); 
	           String modifiedSentence = 
	               new String(receivePacket.getData()); 
	  
	           InetAddress returnIPAddress = receivePacket.getAddress();
	     
	           int port = receivePacket.getPort();

	           System.out.println ("From server at: " + returnIPAddress + 
	                               ":" + port);
	           System.out.println("Message: " + modifiedSentence); 

	          }
	      catch (SocketTimeoutException ste)
	          {
	           System.out.println ("Timeout Occurred: Packet assumed lost");
	      }
	  
	      clientSocket.close(); 
	     }
	   catch (UnknownHostException ex) { 
	     System.err.println(ex);
	    }
	   catch (IOException ex) {
	     System.err.println(ex);
	    }

	}

}