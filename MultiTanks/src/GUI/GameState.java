package GUI;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import Player.Player;

@XmlRootElement
public class GameState {
	public List<Player> Player = new ArrayList<>();
	public States State = States.Starting;
	
	public enum States {
		Starting,
		Waiting,
		Running,
	}
	
	public void addPlayer(Player p) {
		Player.add(p);
	}
}
