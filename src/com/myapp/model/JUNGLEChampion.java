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
		System.out.println("JUNGLE 포지션 챔피언이 생성됩니다.");
	}
}
