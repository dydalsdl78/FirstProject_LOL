package com.myapp.model;

import com.strategy.attack.MagicAttack;
import com.strategy.attack.SwordAttack;
import com.strategy.defense.ArmorDefense;
import com.strategy.defense.AvoidDefense;

public class MIDChampion extends ChampionVO {
	
	public MIDChampion(String name, int attack, int defense, String position) {
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
