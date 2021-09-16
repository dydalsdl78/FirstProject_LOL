package com.myapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameController implements Initializable {
	
	@FXML
    private Button exitBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exitBtn.setOnAction(event->SceneChangeMain(event));
	}
	
	public void SceneChangeMain(ActionEvent event) {
    	try {
    		Parent changedScene = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
    		Scene scene = new Scene(changedScene);
    		Stage primaryStage =  (Stage)exitBtn.getScene().getWindow();
    		primaryStage.setScene(scene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
}
