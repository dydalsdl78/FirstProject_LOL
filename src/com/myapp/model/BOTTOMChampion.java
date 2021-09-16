package com.myapp.model;

import com.myapp.strategy.attack.RifleAttack;
import com.myapp.strategy.attack.SwordAttack;
import com.myapp.strategy.defense.ArmorDefense;
import com.myapp.strategy.defense.AvoidDefense;

public class BOTTOMChampion extends ChampionVO {
	
	public BOTTOMChampion(String name, int attack, int defense, int position) {
		super.setChampionName(name);
		super.setChampionAttack(attack);
		super.setChampionDefense(defense);
		super.setChampionPosition(position);
		attackBehavior = new RifleAttack();
		defenseBehavior = new AvoidDefense();
	}

	@Override
	public void display() {
		System.out.println("BOTTOM 포지션 챔피언이 생성됩니다.");
	}

}
