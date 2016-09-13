package testing;

import java.io.IOException;
import java.net.Socket;

import Game.GameState;
import util.Connection;
import util.Receiver;

public class SRec extends Receiver {
	public SRec(Connection c, Socket connection) throws IOException {
		super(c, connection, true);
	}

	@Override
	protected void handleEObject(GameState obj) {
		System.out.println("Server: Recieved object: " + obj);
	}

	@Override
	protected void onClientDisconnect() {
	}

}
