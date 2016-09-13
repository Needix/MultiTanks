package testing;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.CConnector;
import util.Receiver;

public class CCon extends CConnector {
	public CCon(String ip, int port) throws IllegalStateException, UnknownHostException, IOException {
		super(ip, port);
	}

	@Override
	protected Receiver CreateReceiver(Socket s) {
		try {
			return new CRec(getConnection(), s);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
