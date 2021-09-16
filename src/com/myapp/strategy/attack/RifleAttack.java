package com.myapp.strategy.attack;

public class RifleAttack implements AttackBehavior {
	@Override
	public void select() {
		System.out.println("총으로 공격합니다.");
	}

}
