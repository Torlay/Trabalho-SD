

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class SearchBroadcastAddress {

	/**
	 * @param args
	 * @throws IOException
	 */
	public void broadcast() throws IOException {
		// TODO Auto-generated method stub

		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		
		while (interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
			if (networkInterface.isLoopback())
				continue; // Don't want to broadcast to the loopback interface
			for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
				
				InetAddress broadcast = interfaceAddress.getBroadcast();
				
				if (broadcast == null)
					continue;
				
				System.out.println("Broadcast na interface de rede " + broadcast);

				DatagramSocket socket = new DatagramSocket();
				socket.setBroadcast(true);
				
				// send request
				byte[] buf = "Gather around folks, gather around.".getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length,broadcast, 9000);
				socket.send(packet);
				socket.close();
			}
		}

	}

}
