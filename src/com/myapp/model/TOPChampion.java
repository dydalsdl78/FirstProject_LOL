package com.myapp.model;

import com.myapp.strategy.attack.SwordAttack;
import com.myapp.strategy.defense.ArmorDefense;

public class TOPChampion extends ChampionVO {
	
	public TOPChampion(String name, int attack, int defense, int position) {
		super.setChampionName(name);
		super.setChampionAttack(attack);
		super.setChampionDefense(defense);
		super.setChampionPosition(position);
		attackBehavior = new SwordAttack();
		defenseBehavior = new ArmorDefense();
	}

	@Override
	public void display() {
		System.out.println("TOP 포지션 챔피언이 생성됩니다.");
	}

}
