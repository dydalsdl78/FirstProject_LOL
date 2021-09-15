package com.myapp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("LOL Champion Game");
        initRootLayout();
    }
    
    public void initRootLayout() {
        try {
            // fxml 파일 읽기
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ChampionView.fxml"));

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
