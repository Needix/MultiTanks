package util;

import java.net.Socket;

import GUI.GameState;

/**
 * This class contains the receiver and sender of a connection. The list of these connections is saved inside the ServerConnector
 * 
 * @author Jonas Oeldemann
 */
public class Connection {
	/**
	 * Return the socket of this connection
	 * 
	 * @return the socket of this connection
	 */
	public Socket getSocket() {
		return socket;
	}

	private Socket socket;

	/**
	 * True if its likely that this connection has a timeout
	 */
	public int timeout = 0;

	/**
	 * Returns the receiver of this connection
	 * 
	 * @return The receiver of this connection
	 */
	public Receiver getReceiver() {
		return receiver;
	}

	private Receiver receiver;

	/**
	 * Returns the sender of this connection
	 * 
	 * @return the sender of this connection
	 */
	public Sender getSender() {
		return sender;
	}

	private Sender sender;

	/**
	 * The constructor of this connection
	 * 
	 * @param socket
	 *            The socket this connection is attached to
	 * @param r
	 *            The receiver of this connection
	 * @param sender
	 *            The Sender of this connection
	 */
	public Connection(Socket socket, Receiver r, Sender sender) {
		this.socket = socket;
		this.receiver = r;
		this.sender = sender;
	}

	/**
	 * Passes a EObject to the sender.
	 * 
	 * @param obj
	 *            The object to send
	 * @return true if sending the object was successful, else false
	 */
	public boolean Send(GameState obj) {
		if (sender == null)
			return false;
		return sender.Send(obj);
	}
}
