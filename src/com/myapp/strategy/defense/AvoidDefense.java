package com.myapp.strategy.defense;

public class AvoidDefense implements DefenseBehavior {
	@Override
	public void select() {
		System.out.println("������ ȸ���մϴ�.");
	}
}
