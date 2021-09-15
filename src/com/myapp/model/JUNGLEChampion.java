package com.myapp.model;

import com.strategy.attack.SwordAttack;
import com.strategy.defense.ArmorDefense;
import com.strategy.defense.ShieldDefense;

public class JUNGLEChampion extends ChampionVO {
	
	public JUNGLEChampion() {
		attackBehavior = new SwordAttack();
		defenseBehavior = new ShieldDefense();
	}
	
	@Override
	public void display() {
		System.out.println("JUNGLE ������ è�Ǿ��� �����˴ϴ�.");
	}
}
