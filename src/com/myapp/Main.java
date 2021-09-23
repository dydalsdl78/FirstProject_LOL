package com.myapp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	private Stage primaryStage;
    private AnchorPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        // ���� ����
        this.primaryStage.setTitle("LEAGUE OF LEGENDS RANDOM CARD GAME");
        // ��Ÿ�� ����
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        
        initRootLayout();
        
    }
    
    public void initRootLayout() {
        try {
            
        	// �ܺ� font �б�
        	Font.loadFont(getClass().getResourceAsStream("Friz Quadrata Regular.ttf"), 10);
        	
        	// fxml ���� �а� load
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainView.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // scene ����
            Scene scene = new Scene(rootLayout); 
            primaryStage.setScene(scene); 
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image("com/myapp/resource/Image/icon2.png"));
            
            // ������ �����ֱ�
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void main(String args) {
        // AppMain ��ü ���� �� ���� ������ ����
    	launch(args);
    }
}
