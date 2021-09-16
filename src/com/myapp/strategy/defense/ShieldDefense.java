package com.myapp.strategy.defense;

public class ShieldDefense implements DefenseBehavior {
	@Override
	public void select() {
		System.out.println("방패로 방어합니다.");
	}
}
