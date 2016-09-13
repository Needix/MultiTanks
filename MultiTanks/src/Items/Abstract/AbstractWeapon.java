package Items.Abstract;

import javax.xml.bind.annotation.XmlRootElement;

public abstract class AbstractWeapon extends AbstractItem {
	protected AbstractWeapon(String name, int cost, int amount) {
		super(name, cost, amount);
	}

}
