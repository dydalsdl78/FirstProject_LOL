package com.myapp.model;

import com.strategy.attack.RifleAttack;
import com.strategy.attack.SwordAttack;
import com.strategy.defense.ArmorDefense;
import com.strategy.defense.AvoidDefense;

public class BOTTOMChampion extends ChampionVO {
	
	public BOTTOMChampion() {
		attackBehavior = new RifleAttack();
		defenseBehavior = new AvoidDefense();
	}

	@Override
	public void display() {
		System.out.println("BOTTOM ������ è�Ǿ��� �����˴ϴ�.");
	}

}
