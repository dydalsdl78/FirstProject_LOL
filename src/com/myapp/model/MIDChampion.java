package com.myapp.model;

import com.myapp.strategy.attack.MagicAttack;
import com.myapp.strategy.attack.SwordAttack;
import com.myapp.strategy.defense.ArmorDefense;
import com.myapp.strategy.defense.AvoidDefense;

public class MIDChampion extends ChampionVO {
	
	// »ý¼ºÀÚ
	public MIDChampion(String name, int attack, int defense, int position) {
		super.setChampionName(name);
		super.setChampionAttack(attack);
		super.setChampionDefense(defense);
		super.setChampionPosition(position);
		attackBehavior = new MagicAttack();
		defenseBehavior = new AvoidDefense();
	}

}
