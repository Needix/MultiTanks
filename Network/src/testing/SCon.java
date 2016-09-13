package testing;

import java.io.IOException;
import java.net.Socket;

import server.SConnector;
import util.Connection;
import util.Receiver;

public class SCon extends SConnector {
	public SCon(int port) throws IOException {
		super(port, true);
	}

	@Override
	protected Receiver CreateReceiver(Socket s) {
		try {
			return new SRec(getConnection(s), s);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void newConnection(Connection c) {
		System.out.println("New connection: " + c);
	}

	@Override
	protected void onClientDisconnect(Connection con) {

	}

}
