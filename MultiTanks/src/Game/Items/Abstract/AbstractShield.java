package Game.Items.Abstract;

import javax.xml.bind.annotation.XmlRootElement;

public abstract class AbstractShield extends AbstractItem {
	private int shieldValue;
	
	protected AbstractShield(String name, int cost, int amount, int shieldValue) {
		super(name, cost, amount);
		this.shieldValue = shieldValue;
	}

}
