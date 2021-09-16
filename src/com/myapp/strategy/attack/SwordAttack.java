package com.myapp.strategy.attack;

public class SwordAttack implements AttackBehavior {
	@Override
	public void select() {
		System.out.println("검으로 공격합니다.");
	}
}
