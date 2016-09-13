package testing;

import java.io.IOException;
import java.net.Socket;

import Game.GameState;
import util.Connection;
import util.Receiver;

public class CRec extends Receiver {
	public CRec(Connection c, Socket connection) throws IOException {
		super(c, connection, false);
	}

	@Override
	protected void handleEObject(GameState obj) {
		System.out.println("Client: Recieved object: " + obj);
	}

	@Override
	protected void onClientDisconnect() {
	}
}
