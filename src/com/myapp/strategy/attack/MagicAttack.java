package com.myapp.strategy.attack;

public class MagicAttack implements AttackBehavior {
	@Override
	public void select() {
		System.out.println("마법으로 공격합니다.");
	}
}
