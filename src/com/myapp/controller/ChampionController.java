package com.myapp.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

import com.myapp.model.ChampionDAO;
import com.myapp.model.ChampionVO;
import com.myapp.model.MIDChampion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChampionController implements Initializable {
	
	@FXML
	private TextField championIdText;
	
	@FXML
	private TextField championNameText;
	
	@FXML
	private TextField championAttackText;
	
	@FXML
	private TextField championDefenseText;
	
	@FXML
	private ComboBox<String> championPositionComboBox;
	
    @FXML
    private TableView<ChampionVO> championTable;
    
    @FXML
    private TableColumn<ChampionVO, Integer> championIdColumn;
    
    @FXML
    private TableColumn<ChampionVO, String> championNameColumn;
    
    @FXML
    private TableColumn<ChampionVO, Integer> championAttackColumn;
    
    @FXML
    private TableColumn<ChampionVO, Integer> championDefenseColumn;
    
    @FXML
    private TableColumn<ChampionVO, Integer> championPositionColumn;
    
    @FXML
    private TableColumn<ChampionVO, String> championPositionNameColumn;
    
    @FXML
    private TableColumn<ChampionVO, String> championAttackTypeColumn;
    
    @FXML
    private TableColumn<ChampionVO, String> championDefenseTypeColumn;
    
    @FXML
    private Button selectBtn;
    
    @FXML
    private Button exitBtn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	ObservableList<String> fxComboPositionList = FXCollections.observableArrayList("TOP", "MID", "BOTTOM", "JUNGLE");
    	championPositionComboBox.setItems(fxComboPositionList);
    	
    	exitBtn.setOnAction(event->ChangeMainView(event));
    	selectBtn.setOnAction(event->selectAll(event));
//    	selectBtn.setOnAction(event-> {
//			try {
//				insertChampion(event);
//			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
//			}
//		});
    	
    	// 테이블
    	championIdColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionIdProperty().asObject());
    	championNameColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionNameProperty());
    	championAttackColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionAttackProperty().asObject());
    	championDefenseColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionDefenseProperty().asObject());
//    	championPositionColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionPositionProperty().asObject());
    	championPositionNameColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionPositionNameProperty());
    	championAttackTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionAttackTypeProperty());
    	championDefenseTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionDefenseTypeProperty());
    }
    
    public void ChangeMainView(ActionEvent event) {
    	try {
    		Parent changedScene = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
    		Scene scene = new Scene(changedScene);
    		Stage primaryStage =  (Stage)exitBtn.getScene().getWindow();
    		primaryStage.setScene(scene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    // ChampionSelectAll 메서드 리턴값으로 챔프 데이터 받아와서 printChampions 메서드 실행
    public void selectAll(ActionEvent event) {	
    	try {
    		ObservableList<ChampionVO> champData = ChampionDAO.ChampionSelectAll();
    		populateChampions(champData);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    // 테이블에 데이터 저장(바인딩 되기 때문에 자동으로 출력)
    @FXML
    private void populateChampions (ObservableList<ChampionVO> champData) throws ClassNotFoundException {
//    	System.out.println(champData);
    	championTable.setItems(champData);
    }
    
    // Insert
    @FXML
    private void insertChampion (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	String name = championNameText.getText();
        	int attack = Integer.parseInt(championAttackText.getText());
        	int defense = Integer.parseInt(championDefenseText.getText());
        	String position_tmp = championPositionComboBox.getSelectionModel().getSelectedItem().toString();
        	int position;
        	if (position_tmp.equals("TOP")) {
        		position = 1;
        	} else if (position_tmp.equals("MID")) {
        		position = 2;
        	} else if (position_tmp.equals("BOTTOM")) {
        		position = 3;
        	} else {
        		position = 4;
        	}
            ChampionVO newChampion = new MIDChampion(name, attack, defense, position);
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