package com.myapp.model;

import com.myapp.strategy.attack.SwordAttack;
import com.myapp.strategy.defense.ArmorDefense;
import com.myapp.strategy.defense.ShieldDefense;

public class JUNGLEChampion extends ChampionVO {
	
	public JUNGLEChampion(String name, int attack, int defense, int position) {
		super.setChampionName(name);
		super.setChampionAttack(attack);
		super.setChampionDefense(defense);
		super.setChampionPosition(position);
		attackBehavior = new SwordAttack();
		defenseBehavior = new ShieldDefense();
	}
	
	@Override
	public void display() {
		System.out.println("JUNGLE 포지션 챔피언이 생성됩니다.");
	}
}
