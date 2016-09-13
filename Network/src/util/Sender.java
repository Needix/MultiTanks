package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import GUI.GameState;
import serializer.XMLHandler;

/**
 * This class represents a sender which sends JSON EObjects over the network to a socket. Every sender has one specific socket allocated to them.
 * 
 * @author Need
 *
 */
public class Sender {
	public static boolean showDebugs = false;
	
	private Socket Connection;
	private PrintWriter Output;
	private boolean isServer;

	/**
	 * The constructor of this sender
	 * 
	 * @param connection
	 *            The connection this sender is attached to
	 * @param isServer Whether this sender is on the client oder server side
	 * @throws IOException
	 *             If the output stream of the socket could not be read.
	 */
	public Sender(Socket connection, boolean isServer) throws IOException {
		Connection = connection;
		Output = new PrintWriter(Connection.getOutputStream());
		this.isServer = isServer;
	}

	/**
	 * Sends the EMF EObject to the socket
	 * 
	 * @param obj
	 *            The EMF EObject to send
	 * @return true, if sending was successful, else false
	 */
	public boolean Send(GameState obj) {
		String xml = XMLHandler.serialize(obj);
		if (isServer)
			if(showDebugs) System.out.println("Server sending: " + xml);
		else
			if(showDebugs) System.out.println("Client sending: " + xml);
		Output.println(xml);
		Output.flush();
		return true;
	}
}
