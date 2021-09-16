package com.myapp.model;

import com.myapp.strategy.attack.MagicAttack;
import com.myapp.strategy.attack.SwordAttack;
import com.myapp.strategy.defense.ArmorDefense;
import com.myapp.strategy.defense.AvoidDefense;

public class MIDChampion extends ChampionVO {
	
	public MIDChampion(String name, int attack, int defense, int position) {
		super.setChampionName(name);
		super.setChampionAttack(attack);
		super.setChampionDefense(defense);
		super.setChampionPosition(position);
		attackBehavior = new MagicAttack();
		defenseBehavior = new AvoidDefense();
	}

	@Override
	public void display() {
		System.out.println("MID 포지션 챔피언이 생성됩니다.");
	}

}
