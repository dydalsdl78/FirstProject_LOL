package com.myapp.model;

import com.strategy.attack.SwordAttack;
import com.strategy.defense.ArmorDefense;

public class TOPChampion extends ChampionVO {
	
	public TOPChampion() {
		attackBehavior = new SwordAttack();
		defenseBehavior = new ArmorDefense();
	}

	@Override
	public void display() {
		System.out.println("TOP 포지션 챔피언이 생성됩니다.");
	}

}
