package com.myapp;

import java.io.File;
import java.io.IOException;

import com.myapp.controller.MainController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
	
	private Stage primaryStage;
    private AnchorPane rootLayout;
    private Button changeBtn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("LOL Champion Game");
        
        // 이미지
//        File imageFile = new File("../../../resource/main.jpg");
//        MainController m = new MainController();
//        m.initImage();

        initRootLayout();
        
    }
    
    public void initRootLayout() {
        try {
            // fxml 파일 읽기
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));

            rootLayout = (AnchorPane) loader.load();
            // scene 적용
            Scene scene = new Scene(rootLayout); 
            primaryStage.setScene(scene); 
            
            // 윈도우 보여주기
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void main(String args) {
        // AppMain 객체 생성 및 메인 윈도우 생성
    	launch(args);
    }
}
