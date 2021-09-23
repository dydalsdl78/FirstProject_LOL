package com.myapp.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.IconView;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML
    private Button crudBtn;
	
	@FXML
	private Button gameBtn;
	
	@FXML
	private Label minLabel;
	
	@FXML
	private Label closeLabel;
	
	@FXML
	private Label BGMLabel;
	
	@FXML
	private FontAwesomeIconView BGMIcon;
	
	@FXML
    private HBox top;
	
	@FXML
	private AnchorPane parent;
	
	@FXML
	private Pane content;

	// BGM üũ ����
	private Boolean isBGM = true;
	private double xOffset = 0;
	private double yOffset = 0;
	private Stage stage = null;
	
	// ���� ȭ�� �������
	private String BGMpath = "src/com/myapp/resource/Audio/main(3�� down).mp3";
	private Media BGMmedia = new Media(new File(BGMpath).toURI().toString());
	MediaPlayer BGMmediaPlayer = new MediaPlayer(BGMmedia);
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// INDEFINITE(-1) ���� ���
		BGMmediaPlayer.setCycleCount(BGMmediaPlayer.INDEFINITE);
		BGMmediaPlayer.play();
		
		// ȭ�� �巡�� ����
		stageDragableMoveWindow();
		
		// ȭ�� �̵� ��ư
		crudBtn.setOnAction(event->ChangeCRUDView(event));
		gameBtn.setOnAction(event->ChangeGameView(event));
		
		// ���콺���� �� Ŀ�� ����
		crudBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		gameBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		minLabel.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyLabelEventHandler());
		closeLabel.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyLabelEventHandler());
		BGMLabel.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyLabelEventHandler());

	}
	
	// ȭ�� �巡�� ���� ����
	private void stageDragableMoveWindow() {
		parent.setOnMousePressed((event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		
		parent.setOnMouseDragged((event) -> {
		// Launcher.stage.setX(event.getScreenX() - xOffset);
		// Launcher.stage.setY(event.getScreenY() - yOffset);
		// Launcher.stage.setOpacity(0.8f); // â ����ȭ
			stage = (Stage) parent.getScene().getWindow();
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
			stage.setOpacity(0.8f); // â ����ȭ
		});
		
		parent.setOnDragDone((event) -> {
			// Launcher.stage.setOpacity(1.0f);
			stage = (Stage) parent.getScene().getWindow();
			stage.setOpacity(1.0f);
		});
		
		parent.setOnMouseReleased((event) -> {
			// Launcher.stage.setOpacity(1.0f);
			stage = (Stage) parent.getScene().getWindow();
			stage.setOpacity(1.0f);
		});
	}
	
	// â �ּ�ȭ
	@FXML
	private void actionMinWindow(MouseEvent event) {
		// Launcher.stage.setIconified(true);
		stage = (Stage) parent.getScene().getWindow();
		stage.setIconified(true);
	}

	// â �ݱ�
	@FXML
	private void actionCloseWindow(MouseEvent event) {
		System.exit(0);
	}

	// è�Ǿ� CRUD ȭ�� �̵�
	public void ChangeCRUDView(ActionEvent event) {
    	try {
    		Parent changeCRUDView = FXMLLoader.load(getClass().getResource("../view/ChampionView.fxml"));
    		Scene CRUDScene = new Scene(changeCRUDView);
    		Stage primaryStage = (Stage)crudBtn.getScene().getWindow();
    		primaryStage.setScene(CRUDScene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	// Ŭ���� �Ҹ�, BGM
    	ClickBtnAudioPlay();
    	BGMmediaPlayer.pause();
    }
	
	// ���� ���� ȭ�� �̵�
	public void ChangeGameView(ActionEvent event) {
    	try {
    		Parent changeGameView = FXMLLoader.load(getClass().getResource("../view/RandomGameView.fxml"));
    		Scene GameScene = new Scene(changeGameView);
    		Stage primaryStage = (Stage)gameBtn.getScene().getWindow();
    		primaryStage.setScene(GameScene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	// Ŭ���� �Ҹ�, BGM
    	ClickBtnAudioPlay();
    	BGMmediaPlayer.pause();
    }
	
	// Ŭ���� �Ҹ�
	public static void ClickBtnAudioPlay() {
		// ȭ�� �̵� ��ư Ŭ���� ȿ����
		final String BtnClickpath = "src/com/myapp/resource/Audio/button.wav";
		final Media BtnClickmedia = new Media(new File(BtnClickpath).toURI().toString());
		
		MediaPlayer BtnClickmediaPlayer = new MediaPlayer(BtnClickmedia);
		BtnClickmediaPlayer.play();
	}
	
	// BGM ���
	public void BGMpause() {
		if (isBGM == true) {
			isBGM = false;
			BGMmediaPlayer.pause();
			BGMIcon.setGlyphName("PLAY");
			
		} else {
			isBGM = true;
			BGMmediaPlayer.play();
			BGMIcon.setGlyphName("PAUSE");
		}
		
	}
	
	// Button�� ���콺 ������ Ŀ�� ����
	public class MyButtonEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();
			
			ButtonBase button = (ButtonBase) obj;
			button.setCursor(Cursor.HAND);
		}
	}
	
	// Label�� ���콺 ������ Ŀ�� ����
	public class MyLabelEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();
			
			Labeled label = (Labeled) obj;
			label.setCursor(Cursor.HAND);
		}
	}



}
