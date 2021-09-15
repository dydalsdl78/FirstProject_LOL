package com.strategy.defense;

public class ShieldDefense implements DefenseBehavior {
	@Override
	public void defense() {
		System.out.println("방패로 방어합니다.");
	}
}
