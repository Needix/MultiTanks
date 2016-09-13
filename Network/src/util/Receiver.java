package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import Game.GameState;
import serializer.XMLHandler;

/**
 * This abstract class represents a receiver that waits for incoming messages and handles them. Override the handleEObject method to receive the objects.
 * 
 * @author Jonas Oeldemann
 */
public abstract class Receiver extends Thread {
	public static boolean showDebugs = false;
	
	/**
	 * Returns the socket of this receiver
	 * 
	 * @return The socket of this receiver
	 */
	public Socket getSocket() {
		return socket;
	}

	private Socket socket;

	private BufferedReader input;

	/**
	 * Returns whether this receiver is client- or serversided.
	 * 
	 * @return True if serversided, else false
	 */
	public boolean isServer() {
		return isServer;
	}

	private boolean isServer;

	public void setConnection(Connection c) {
		connection = c;
	}

	/**
	 * Returns a connection object which contains information on the receiver, the sender and the socket
	 * 
	 * @return A connection object
	 */
	public Connection getConnection() {
		return connection;
	}

	private Connection connection;

	/**
	 * The constructor of the receiver.
	 * 
	 * @param connection
	 *            The connection info
	 * @param socket
	 * 			  The socket to the server
	 * @param isServer
	 *            Whether this receiver is a server- or clientsided receiver.
	 * @throws IOException
	 *             If the input stream of the stream could not be read.
	 */
	public Receiver(Connection connection, Socket socket, boolean isServer) throws IOException {
		this.socket = socket;
		this.connection = connection;
		this.isServer = isServer;
		this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.start();
	}

	/**
	 * This method starts the threading. It waits for messages and passes incoming EObjects to handleEObject.
	 */
	public void run() {
		while (true) {
			try {
				String line = input.readLine();
				if (line == null)
					break;
				GameState gs = XMLHandler.deserialize(line);
				if (isServer)
					if(showDebugs) System.out.println("Server received: " + line);
				else
					if(showDebugs) System.out.println("Client received: " + line);
				handleEObject(gs);
			} catch (IOException e) {
				if (e instanceof SocketException)
					break;
				System.out.println("Could not read line from connection: ");
				e.printStackTrace();
				onClientDisconnect();
				return;
			}
		}
		try {
			socket.close();
		} catch(IOException ex) {}
		onClientDisconnect();
	}

	protected abstract void onClientDisconnect();
	
	/**
	 * Handles what to do with the EMF EObject transmitted through the network
	 * 
	 * @param obj
	 *            The EMF EObject that was send
	 */
	protected abstract void handleEObject(GameState obj);
}
