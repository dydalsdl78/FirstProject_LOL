package com.myapp.strategy.defense;

public class AvoidDefense implements DefenseBehavior {
	@Override
	public void select() {
		System.out.println("공격을 회피합니다.");
	}
}
