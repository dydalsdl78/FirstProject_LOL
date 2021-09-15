package com.strategy.attack;

public class SwordAttack implements AttackBehavior {
	@Override
	public void attack() {
		System.out.println("검으로 공격합니다.");
	}
}
