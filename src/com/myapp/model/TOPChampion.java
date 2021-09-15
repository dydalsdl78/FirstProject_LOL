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
		System.out.println("TOP ������ è�Ǿ��� �����˴ϴ�.");
	}

}
