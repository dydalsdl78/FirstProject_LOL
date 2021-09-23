package com.myapp.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.myapp.controller.MainController.MyButtonEventHandler;
import com.myapp.model.ChampionDAO;
import com.myapp.model.ChampionVO;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RandomGameController implements Initializable {
	
	@FXML
	private Button gameStartBtn;
	
	@FXML
	private Label player1_Label;
	
	@FXML
	private Label player2_Label;
	
	@FXML
	private Label player1_cardName;
	
	@FXML
	private Label player1_point;
	
	@FXML
	private ImageView player1_champion_image;
	
	@FXML
	private ImageView player1_champion_image_border;
	
	@FXML
	private ImageView player1_position_image;
	
	@FXML
	private Label player2_cardName;

	@FXML
	private Label player2_point;
	
	@FXML
	private ImageView player2_champion_image;
	
	@FXML
	private ImageView player2_champion_image_border;
	
	@FXML
	private ImageView player2_position_image;
	
	@FXML
	private Label gameResultText;
	
	@FXML
    private Button exitBtn;
	
	@FXML
	private Label label;
	
	@FXML
    private HBox top;
	
	@FXML
	private AnchorPane parent;
	
	@FXML
	private Pane content;
	
	private double xOffset = 0;
	private double yOffset = 0;
	private Stage stage = null;
	
	// ���� ȭ�� ��������
	private String BGMpath = "src/com/myapp/resource/Audio/start(3dB down).mp3";
	private Media BGMmedia = new Media(new File(BGMpath).toURI().toString());
	MediaPlayer BGMmediaPlayer = new MediaPlayer(BGMmedia);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// �������� ���
		BGMmediaPlayer.play();
	
		// stage �巡�� ����
		stageDragableMoveWindow();

		// ī�� �Ѻκ� �ʱ�ȭ
		String championBorderImageURL = "com/myapp/resource/Image/card_border3.png";
		Image championBorderimage = new Image(championBorderImageURL);
		player1_champion_image_border.setImage(championBorderimage);
		player2_champion_image_border.setImage(championBorderimage);
		
		// ī�� �޸� �ʱ�ȭ
		String championImageURL = "com/myapp/resource/Image/card_behind.png";
		Image championImage = new Image(championImageURL);
		player1_champion_image.setImage(championImage);
		player2_champion_image.setImage(championImage);
		
		// ���� è�Ǿ� select ����
		gameStartBtn.setOnAction(event->selectRandom(event));
		
		// ������ ����
		exitBtn.setOnAction(event->sceneChangeMain(event));
		
		// ���콺 ������ Ŀ�� ����
		gameStartBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
		exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
	}
	
	// �������� scene �̵�
	public void sceneChangeMain(ActionEvent event) {
    	try {
    		Parent changedScene = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
    		Scene scene = new Scene(changedScene);
    		Stage primaryStage =  (Stage)exitBtn.getScene().getWindow();
    		primaryStage.setScene(scene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	// Ŭ���� �Ҹ�
    	MainController.ClickBtnAudioPlay();
    	
    	// ȭ�� ��ȯ�� �������� ����
    	BGMmediaPlayer.pause();
    }
	
	// è�Ǿ� ���� select ����
	public void selectRandom(ActionEvent event) {
		
		// player ��ü �ʱ�ȭ
		ChampionVO player1 = null;
		ChampionVO player2 = null;
		
		// �� �ʱ�ȭ
		int index = 0;
		int strChampionAttack1 = 0;
		int strChampionDefense1 = 0;
		int strChampionAttack2 = 0;
		int strChampionDefense2 = 0;
		int player1_total_point = 0;
		int player2_total_point = 0;
		
		try {
			ObservableList<ChampionVO> champData = ChampionDAO.ChampionSelectRandom();
			
			for (ChampionVO champ: champData) {
				if (index == 0) {
					player1 = champ;
					// ȸ�� �ִϸ��̼� ����
					rotateAnimation(player1_champion_image);
					
				} else {
					player2 = champ;
					// ȸ�� �ִϸ��̼� ����
					rotateAnimation(player2_champion_image);
				}
				index++;
			}
				
			// player1 �� set
			// �� ����Ʈ ��
			strChampionAttack1 = player1.getChampionAttack();
			strChampionDefense1 = player1.getChampionDefense();
			player1_total_point = strChampionAttack1 + strChampionDefense1;
			player1_point.setText(Integer.toString(player1_total_point));
			
			// �̸�
			player1_cardName.setText(player1.getChampionName());
			
			// �̹���
			String player1_championImageUrl = ChampionDAO.searchChampionImage(player1.getChampionName());
			Image player1_imageUrl = new Image(player1_championImageUrl);
			player1_champion_image.setImage(player1_imageUrl);
			
			// ������ �̹���
			if (player1.getChampionPosition() == 1) {
				String player1_positionImageUrl = "com/myapp/resource/Image/top.png";
				Image player1_positionImage = new Image(player1_positionImageUrl);
				player1_position_image.setImage(player1_positionImage);	
			} else if (player1.getChampionPosition() == 2) {
				String player1_positionImageUrl = "com/myapp/resource/Image/mid.png";
				Image player1_positionImage = new Image(player1_positionImageUrl);
				player1_position_image.setImage(player1_positionImage);	
			} else if (player1.getChampionPosition() == 3) {
				String player1_positionImageUrl = "com/myapp/resource/Image/bottom.png";
				Image player1_positionImage = new Image(player1_positionImageUrl);
				player1_position_image.setImage(player1_positionImage);	
			} else {
				String player1_positionImageUrl = "com/myapp/resource/Image/jungle.png";
				Image player1_positionImage = new Image(player1_positionImageUrl);
				player1_position_image.setImage(player1_positionImage);	
			}
			
			
			// player2 �� set
			// �� ����Ʈ ��
			strChampionAttack2 = player2.getChampionAttack();
			strChampionDefense2 = player2.getChampionDefense();
			player2_total_point = strChampionAttack2 + strChampionDefense2;
			player2_point.setText(Integer.toString(player2_total_point));
			
			// �̸�
			player2_cardName.setText(player2.getChampionName());
			
			// �̹���
			String player2_championImageUrl = ChampionDAO.searchChampionImage(player2.getChampionName());
			Image player2_imageUrl = new Image(player2_championImageUrl);
			player2_champion_image.setImage(player2_imageUrl);

			// ������ �̹���
			if (player2.getChampionPosition() == 1) {
				String player2_positionImageUrl = "com/myapp/resource/Image/top.png";
				Image player2_positionImage = new Image(player2_positionImageUrl);
				player2_position_image.setImage(player2_positionImage);	
			} else if (player2.getChampionPosition() == 2) {
				String player2_positionImageUrl = "com/myapp/resource/Image/mid.png";
				Image player2_positionImage = new Image(player2_positionImageUrl);
				player2_position_image.setImage(player2_positionImage);	
			} else if (player2.getChampionPosition() == 3) {
				String player2_positionImageUrl = "com/myapp/resource/Image/bottom.png";
				Image player2_positionImage = new Image(player2_positionImageUrl);
				player2_position_image.setImage(player2_positionImage);	
			} else {
				String player2_positionImageUrl = "com/myapp/resource/Image/jungle.png";
				Image player2_positionImage = new Image(player2_positionImageUrl);
				player2_position_image.setImage(player2_positionImage);	
			}
			
				
			// ��� text
			if (player1_total_point > player2_total_point) {
				gameResultText.setText("Player 1 Win!");
			} else if (player1_total_point < player2_total_point) {
				gameResultText.setText("Player 2 Win!");
			} else {
				gameResultText.setText("Draw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Ŭ�� �Ҹ�
		MainController.ClickBtnAudioPlay();
	}
	
	// �巡�� ���� ����
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

	
	// ���콺 ������ Ŀ�� ���� ����
	public class MyButtonEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();
			
			ButtonBase button = (ButtonBase) obj;
			button.setCursor(Cursor.HAND);
		}
	}
		
	// ȸ�� �ִϸ��̼� ����	
	private void rotateAnimation(ImageView imageView) {
		Duration duration = new Duration(1000);
		RotateTransition rotateTransition = new RotateTransition(duration, imageView);
		rotateTransition.setAxis(new Point3D(0, 5, 0));
		rotateTransition.setByAngle(360);
		rotateTransition.play();
	}
}
