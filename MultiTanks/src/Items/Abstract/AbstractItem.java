/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items.Abstract;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Need
 */
@XmlRootElement
public abstract class AbstractItem {
	//Constants
	private String name;
	private int cost;
	
	//Vars
	private int amount;
	
	protected AbstractItem(String name, int cost, int amount) {
		this.name = name;
		this.cost = cost;
		this.amount = amount;
	}
 	
	protected void AddItem(int amount) {
		this.amount += amount;
	}
	
	public void UseItem() {
		Use();
		amount--;
	}
	
    protected abstract void Use();
}
