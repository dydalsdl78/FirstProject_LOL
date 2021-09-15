package com.myapp.model;

import com.strategy.attack.AttackBehavior;
import com.strategy.defense.DefenseBehavior;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
//@NoArgsConstructor
public class ChampionVO {
	private IntegerProperty championId;
	private StringProperty championName;
	private IntegerProperty championAttack;
	private IntegerProperty championDefense;
	private StringProperty championPosition;
	private StringProperty attackType;
	private StringProperty defenseType;

	AttackBehavior attackBehavior;
	DefenseBehavior defenseBehavior;
	
	public ChampionVO() {
        this.championId = new SimpleIntegerProperty();
        this.championName = new SimpleStringProperty();
        this.championAttack = new SimpleIntegerProperty();
        this.championDefense = new SimpleIntegerProperty();
        this.championPosition = new SimpleStringProperty();
    }
	
	public void display() {
		
	};
	
	public void performAttackBehavior() {
		attackBehavior.attack();
	}
	
	public void performDefenseBehavior() {
		defenseBehavior.defense();
	}
	
	
	// getter - setter
	
	// ChampionId
	public IntegerProperty getChampionIdProperty() {
		return championId;
	}

	public void setChampionId(int championId) {
		this.championId.set(championId);
	}

	public int getChampionId() {
		return championId.get();
	}
	
	// ChampionName
	public StringProperty getChampionNameProperty() {
		return championName;
	}

	public void setChampionName(String championName) {
		this.championName.set(championName);
	}
	
	public String getChampionName() {
		return championName.get();
	}
	
	
	// ChampionAttack
	public IntegerProperty getChampionAttackProperty() {
		return championAttack;
	}

	public void setChampionAttack(int championAttack) {
		this.championAttack.set(championAttack);
	}
	
	public int getChampionAttack() {
		return championAttack.get();
	}
	
	
	// ChampionDefense
	public IntegerProperty getChampionDefenseProperty() {
		return championDefense;
	}

	public void setChampionDefense(int championDefense) {
		this.championDefense.set(championDefense);
	}
	
	public int getChampionDefense() {
		return championDefense.get();
	}

	
	// ChampionPosition
	public StringProperty getChampionPositionProperty() {
		return championPosition;
	}

	public void setChampionPosition(String championPosition) {
		this.championPosition.set(championPosition);
	}
	
	public String getChampionPosition() {
		return championPosition.get();
	}

	
	
		
}
