package com.myapp.model;

import com.myapp.strategy.attack.SwordAttack;
import com.myapp.strategy.defense.ArmorDefense;
import com.myapp.strategy.defense.ShieldDefense;

public class JUNGLEChampion extends ChampionVO {
	
	// »ý¼ºÀÚ
	public JUNGLEChampion(String name, int attack, int defense, int position) {
		super.setChampionName(name);
		super.setChampionAttack(attack);
		super.setChampionDefense(defense);
		super.setChampionPosition(position);
		attackBehavior = new SwordAttack();
		defenseBehavior = new ShieldDefense();
	}

}
