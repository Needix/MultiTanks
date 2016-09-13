package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import GUI.GameState;
import util.Connection;
import util.Receiver;
import util.Sender;

/**
 * This class represents a server-sided connector which listens for new clients and accepts them.
 * 
 * @author Jonas Oeldemann
 */
public abstract class SConnector extends Observable implements Runnable {
	public static boolean showDebugs = false;
	
	private ArrayList<Connection> connections = new ArrayList<>();
	private ServerSocket server;

	/**
	 * Whether this connector should stop listing for new clients (permanently)
	 */
	public boolean Stop = false;

	/**
	 * Whether this connector should listen and accept new clients.
	 */
	public boolean listen = false;

	/**
	 * The constructor of the ServerConnector
	 * 
	 * @param port
	 *            The port this server should listen too
	 * @param listenImmediately
	 *            Whether this ServerConnector should immediately listen for new clients
	 * @throws IOException
	 *             If the server socket could not be created.
	 */
	public SConnector(int port, boolean listenImmediately) throws IOException {
		server = new ServerSocket(port);
		listen = listenImmediately;
		Thread t = new Thread(this);
		t.start();
	}

	public void addConnection(Connection conn) {
		connections.add(conn);
	}

	public void removeConnection(Connection conn) {
		connections.remove(conn);
	}

	/**
	 * Main Method of the the thread. It waits for incoming connections Do not call it! It get automatically called in constructor
	 */
	@Override
	public void run() {
		try {
			server.setSoTimeout(1000);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while (!Stop) {
			if (listen)
				waitForConnections();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}

			// Check for connection timeout
			// TODO: Readd Ping
			/*
			 * List<Connection> clientsToDelete = new ArrayList<Connection>(); for (Connection con : clients) { if (con.timeout>5) { onClientDisconnect(con); clientsToDelete.add(con); } else { con.timeout++; con.Send(new PingImpl()); } }
			 * 
			 * for(Connection con : clientsToDelete) { clients.remove(con); }
			 */
		}
	}

	protected abstract void onClientDisconnect(Connection con);

	/**
	 * Sends the specified EObject to the connection.
	 * 
	 * @param connection
	 *            The connection the EObject should be send to
	 * @param obj
	 *            The EObject to send
	 */
	public void sendToClient(Connection connection, GameState obj) {
		for (Connection client : connections) {
			if (client == connection)
				client.Send(obj);
		}
	}

	/**
	 * Sends all connected clients this EObject
	 * 
	 * @param obj
	 *            The EObject to send
	 */
	public void sendToAll(GameState obj) {
		for (Connection client : connections) {
			client.Send(obj);
		}
	}

	/**
	 * Returns the connection object associated with this socket
	 * 
	 * @param s
	 *            The socket to search
	 * @return The connection object, or null if the socket was not found
	 */
	public Connection getConnection(Socket s) {
		for (Connection c : connections) {
			if (c.getSocket() == s) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Returns a read-only list of all connected clients.
	 * 
	 * @return A read-only list of all connected clients.
	 */
	public List<Connection> getConnectedClients() {
		return Collections.unmodifiableList(connections);
	}

	/**
	 * This method should create a receiver and return it.
	 * 
	 * @param s
	 *            The socket to which the receiver should be connected
	 * @return The created receiver
	 */
	protected abstract Receiver CreateReceiver(Socket s);

	protected abstract void newConnection(Connection c);

	private boolean waitForConnections() {
		try {
			Socket s = server.accept();
			if(showDebugs) System.out.println("Server: Client connected: " + s);
			Receiver r = CreateReceiver(s);
			if (r == null)
				throw new IllegalStateException("Could not create receiver!");
			Connection c = new Connection(s, r, new Sender(s, true));
			r.setConnection(c);
			connections.add(c);
			newConnection(c);
			return true;
		} catch (IOException e) {
			if (e instanceof SocketTimeoutException)
				return false;
			System.out.println("Could not accept connection: ");
			e.printStackTrace();
			return false;
		}
	}
}
