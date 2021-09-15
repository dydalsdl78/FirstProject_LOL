package com.myapp.controller;

import java.sql.SQLException;

import com.myapp.model.ChampionDAO;
import com.myapp.model.ChampionVO;
import com.myapp.model.MIDChampion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ChampionController {
	
	@FXML
	private TextField championIdText;
	@FXML
	private TextField championNameText;
	@FXML
	private TextField championAttackText;
	@FXML
	private TextField championDefenseText;
	@FXML
	private TextField championPositionText;
    @FXML
    private TableView championTable;
    @FXML
    private TableColumn<ChampionVO, Integer> championIdColumn;
    @FXML
    private TableColumn<ChampionVO, String> championNameColumn;
    @FXML
    private TableColumn<ChampionVO, Integer> championAttackColumn;
    @FXML
    private TableColumn<ChampionVO, Integer> championDefenseColumn;
    @FXML
    private TableColumn<ChampionVO, String> championPositionColumn;
    
    @FXML
    private void initailize() {
    	championIdColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionIdProperty().asObject());
    	championNameColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionNameProperty());
    	championAttackColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionAttackProperty().asObject());
    	championDefenseColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionDefenseProperty().asObject());
    	championPositionColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionPositionProperty());
    }
    
    // Insert
    @FXML
    private void insertChampion (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	int attack = Integer.parseInt(championAttackText.getText());
        	int defense = Integer.parseInt(championDefenseText.getText());
//        	System.out.println(attack + defense);
            ChampionVO newChampion = new MIDChampion(championNameText.getText(), attack, defense, championPositionText.getText());
//            System.out.println(newChampion);
            ChampionDAO.ChampionInsert(newChampion);
        } catch (NullPointerException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
    }
}