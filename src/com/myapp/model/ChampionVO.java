package com.myapp.model;

import com.myapp.strategy.attack.AttackBehavior;
import com.myapp.strategy.defense.DefenseBehavior;

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
	private IntegerProperty championPosition;
	private StringProperty championPositionName;
	private StringProperty championAttackType;
	private StringProperty championDefenseType;

	AttackBehavior attackBehavior;
	DefenseBehavior defenseBehavior;
	
	public ChampionVO() {
        this.championId = new SimpleIntegerProperty();
        this.championName = new SimpleStringProperty();
        this.championAttack = new SimpleIntegerProperty();
        this.championDefense = new SimpleIntegerProperty();
        this.championPosition = new SimpleIntegerProperty();
        this.championPositionName = new SimpleStringProperty();
        this.championAttackType = new SimpleStringProperty();
        this.championDefenseType = new SimpleStringProperty();
    }
	
	public void display() {
		
	};
	
	public void performAttackBehavior() {
		attackBehavior.select();
	}
	
	public void performDefenseBehavior() {
		defenseBehavior.select();
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
	public IntegerProperty getChampionPositionProperty() {
		return championPosition;
	}

	public void setChampionPosition(int championPosition) {
		this.championPosition.set(championPosition);
	}
	
	public int getChampionPosition() {
		return championPosition.get();
	}

	// ChampionPositionName
	public StringProperty getChampionPositionNameProperty() {
		return championPositionName;
	}
	public void setChampionPositionName(String championPositionName) {
		this.championPositionName.set(championPositionName);
	}
	
	public String getChampionPositionName() {
		return championPositionName.get();
	}
	
	// ChampionAttackType
	public StringProperty getChampionAttackTypeProperty() {
		return championAttackType;
	}
	public void setChampionAttackType(String championAttackType) {
		this.championAttackType.set(championAttackType);
	}
	
	public String getChampionAttackType() {
		return championAttackType.get();
	}
	
	// ChampionDefenseType
	public StringProperty getChampionDefenseTypeProperty() {
		return championDefenseType;
	}
	public void setChampionDefenseType(String championDefenseType) {
		this.championDefenseType.set(championDefenseType);
	}
	
	public String getChampionDefenseType() {
		return championDefenseType.get();
	}
}
