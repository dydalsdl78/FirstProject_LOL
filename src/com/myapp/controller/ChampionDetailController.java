package com.myapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.myapp.model.ChampionDAO;
import com.myapp.model.ChampionVO;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChampionDetailController implements Initializable {

	@FXML
	private ImageView championDetailImage;
	
	@FXML
	private Label championDetailName;
	
	@FXML
	private Label championDetailAttackPoint;
	
	@FXML
	private Label championDetailDefensePoint;
	
	@FXML
	private Label championDetailPosition;
	
	@FXML
	private Label championDetailAttackType;
	
	@FXML
	private Label championDetailDefenseType;
	
	@FXML
	private Label championDetailURL;
		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	// Search
    public void initData(String name, String a, String b, String c, String d, String e, String f) {
//		Image image = new Image(getClass().getResource(champData.getChampionImage()).toExternalForm()); 
//		championDetailImage.setImage(null);
    	System.out.println(name);
		championDetailName.setText(name);
		championDetailAttackPoint.setText(a);
		championDetailDefensePoint.setText(b);
		championDetailPosition.setText(c);
		championDetailAttackType.setText(d);
		championDetailDefenseType.setText(e);
		championDetailURL.setText(f);
		

    	MainController.ClickBtnAudioPlay();
    }


}
