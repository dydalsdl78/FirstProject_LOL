package com.myapp.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	
	@FXML
    private Button crudBtn;
	
	@FXML
	private Button gameBtn;
	
	@FXML
	private ImageView imageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		crudBtn.setOnAction(event->ChangeCRUDView(event));
		gameBtn.setOnAction(event->ChangeGameView(event));
//		initImage();
	}
	
	public void ChangeCRUDView(ActionEvent event) {
    	try {
    		Parent changeCRUDView = FXMLLoader.load(getClass().getResource("../view/ChampionView.fxml"));
    		Scene CRUDScene = new Scene(changeCRUDView);
    		Stage primaryStage = (Stage)crudBtn.getScene().getWindow();
    		primaryStage.setScene(CRUDScene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
	
	public void ChangeGameView(ActionEvent event) {
    	try {
    		Parent changeGameView = FXMLLoader.load(getClass().getResource("../view/RandomGameView.fxml"));
    		Scene GameScene = new Scene(changeGameView);
    		Stage primaryStage = (Stage)gameBtn.getScene().getWindow();
    		primaryStage.setScene(GameScene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
	
//	public void initImage() { 
//		//resource 폴더에서 불러오는 방법 
//		Image image = new Image(getClass().getResource("../../../../resource/main.png").toExternalForm()); 
//		imageView.setImage(image); 
//		}

}
