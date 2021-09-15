package com.strategy.attack;

public class RifleAttack implements AttackBehavior {
	@Override
	public void attack() {
		System.out.println("총으로 공격합니다.");
	}

}
