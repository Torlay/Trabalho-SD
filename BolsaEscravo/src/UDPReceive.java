

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class UDPReceive {
	
  public InetAddress listen() {
    try {
      int port = 9000;

      // Create a socket to listen on the port.
      
      DatagramSocket dsocket = new DatagramSocket(port);

      // Create a buffer to read datagrams into. If a
      // packet is larger than this buffer, the
      // excess will simply be discarded!
      byte[] buffer = new byte[2048];

      // Create a packet to receive data into the buffer
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      // Now loop forever, waiting to receive packets and printing them.
      while (true) {
        // Wait to receive a datagram
        dsocket.receive(packet);

        
        // Convert the contents to a string, and display them
        String msg = new String(buffer, 0, packet.getLength());
        
        System.out.println(packet.getAddress().getHostName() + ": "
            + msg);
        // Reset the length of the packet before reusing it.
        packet.setLength(buffer.length);
        
        RespostaTCP resp = new RespostaTCP();
		resp.responder("Eu!", packet.getAddress(), 9001);
        
        return packet.getAddress();
        
      }
    } catch (Exception e) {
      System.err.println(e);
    }
	return null;
  }


}
