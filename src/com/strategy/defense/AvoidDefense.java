package com.strategy.defense;

public class AvoidDefense implements DefenseBehavior {
	@Override
	public void defense() {
		System.out.println("공격을 회피합니다.");
	}
}
