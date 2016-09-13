package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.GameState;
import util.Receiver;
import util.Sender;

/**
 * This class represents a client connector which connect to server.
 * 
 * @author Jonas Oeldemann
 */
public abstract class CConnector {
	public static boolean showDebugs = false;
	
	/**
	 * Returns the connection this connector is connected to
	 * 
	 * @return The connection this connector is connected to
	 */
	public util.Connection getConnection() {
		return Connection;
	}

	private util.Connection Connection;

	/**
	 * The constructor of the client connector
	 * 
	 * @param ip
	 *            The ip this connector should connect to
	 * @param port
	 *            The port this connector should connect to
	 * @throws IllegalStateException
	 *             If the receiver could not be created.
	 * @throws UnknownHostException
	 *             If the specified host is invalid
	 * @throws IOException
	 *             If the socket or sender could not be created
	 */
	public CConnector(String ip, int port) throws IllegalStateException, UnknownHostException, IOException {
		Socket s = new Socket(ip, port);
		if(showDebugs) System.out.println("Client: Connected to server: " + s);
		Receiver r = CreateReceiver(s);
		Connection = new util.Connection(s, r, new Sender(s, false));
		r.setConnection(Connection);
	}

	/**
	 * Sends a EObject to the server
	 * 
	 * @param obj
	 *            The EObject to send
	 * @return true if the sending was successful, else false
	 */
	public boolean Send(GameState obj) {
		if (Connection == null)
			return false;
		return Connection.Send(obj);
	}

	/**
	 * This method should create a receiver for the new connection
	 * 
	 * @param s
	 *            The server this client is now connected to
	 * @return The created receiver
	 */
	protected abstract Receiver CreateReceiver(Socket s);
}