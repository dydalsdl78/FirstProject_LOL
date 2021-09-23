package com.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.myapp.model.BOTTOMChampion;
import com.myapp.model.ChampionDAO;
import com.myapp.model.ChampionVO;
import com.myapp.model.JUNGLEChampion;
import com.myapp.model.MIDChampion;
import com.myapp.model.TOPChampion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	private TextField updateChampionIdText;
	
	@FXML
	private TextField updateChampionAttackText;
	
	@FXML
	private TextField updateChampionDefenseText;
	
    @FXML
    private TextField tmpText;
	
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
    private Button insertBtn;
    
    @FXML
    private Button updateBtn;
    
    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button selectBtn;
    
    @FXML
    private Button searchBtn;
    
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
	
	// 콤보 박스
	ObservableList<String> fxComboPositionList = FXCollections.observableArrayList("TOP", "MID", "BOTTOM", "JUNGLE");
	private double xOffset = 0;
	private double yOffset = 0;
	private Stage stage = null;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	// 화면 이동 가능
    	stageDragableMoveWindow();
    	
    	// 테이블 창 초기 텍스트
    	championTable.setPlaceholder(new Label("Loading..."));
    	
    	// 콤보박스 항목 초기화
    	championPositionComboBox.setItems(fxComboPositionList);
    	
    	// select, delete, search, exit 버튼 실행 이벤트 
    	exitBtn.setOnAction(event->ChangeMainView(event));
    	selectBtn.setOnAction(event->selectAll(event));
    	deleteBtn.setOnAction(event->deleteChampion(event));
    	searchBtn.setOnAction(event->searchChampion(event));
    	
    	// 마우스 오버시 커서 변경
    	selectBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
    	searchBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
    	deleteBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
    	insertBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
    	updateBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
    	exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonEventHandler());
    	
    	
    	// 테이블 더블클릭
//    	championTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
//    		@Override
//    		public void handle(MouseEvent event) {
//    			if (event.getClickCount() > 1) {
//    				
//    				popupDialog();
//    			}
//    		}
//    	});

    	// 테이블
    	championIdColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionIdProperty().asObject());
    	championNameColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionNameProperty());
    	championAttackColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionAttackProperty().asObject());
    	championDefenseColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionDefenseProperty().asObject());
    	championPositionNameColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionPositionNameProperty());
    	championAttackTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionAttackTypeProperty());
    	championDefenseTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getChampionDefenseTypeProperty());
    }
    
    // 메인으로 화면 이동
    public void ChangeMainView(ActionEvent event) {
    	try {
    		Parent changedScene = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
    		Scene scene = new Scene(changedScene);
    		Stage primaryStage =  (Stage)exitBtn.getScene().getWindow();
    		primaryStage.setScene(scene);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	MainController.ClickBtnAudioPlay();
    }
    
    // select all
    public void selectAll(ActionEvent event) {	
    	try {
    		// ChampionSelectAll 메서드 리턴값으로 챔프 데이터 받아와서 printChampions 메서드 실행
    		ObservableList<ChampionVO> champData = ChampionDAO.ChampionSelectAll();
    		
    		// 데이터 채우기
    		populateChampions(champData);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	MainController.ClickBtnAudioPlay();
    }
    
    // 테이블에 데이터 채우기
    @FXML
    private void populateChampions (ObservableList<ChampionVO> champData) throws ClassNotFoundException {
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
        	ChampionVO newChampion;
        	
        	// position 숫자에 따라 String으로 포지션 insert
        	if (position_tmp.equals("TOP")) {
        		position = 1;
        		newChampion = new TOPChampion(name, attack, defense, position);
        	} else if (position_tmp.equals("MID")) {
        		position = 2;
        		newChampion = new MIDChampion(name, attack, defense, position);
        	} else if (position_tmp.equals("BOTTOM")) {
        		position = 3;
        		newChampion = new BOTTOMChampion(name, attack, defense, position);
        	} else {
        		position = 4;
        		newChampion = new JUNGLEChampion(name, attack, defense, position);
        	}
            
        	// 쿼리 실행
            ChampionDAO.ChampionInsert(newChampion);
            
            // clear
            championNameText.clear();
			championAttackText.clear();
			championDefenseText.clear();
			championPositionComboBox.getSelectionModel().clearSelection();
			
        } catch (NullPointerException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
        MainController.ClickBtnAudioPlay();
    }
    
    // Update
    @FXML
    private void updateChampion (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	int updatedChampionId = Integer.parseInt(updateChampionIdText.getText());
        	int updatedChampionAttackPoint = Integer.parseInt(updateChampionAttackText.getText());
        	int updatedChampionDefensePoint = Integer.parseInt(updateChampionDefenseText.getText());
        	
        	// 쿼리 실행
            ChampionDAO.Championupdate(updatedChampionId, updatedChampionAttackPoint, updatedChampionDefensePoint);
            
            // clear
            updateChampionIdText.clear();
            updateChampionAttackText.clear();
            updateChampionDefenseText.clear();
			
        } catch (NullPointerException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
    }
    
    // Delete
    public void deleteChampion(ActionEvent event) {
    	int ChampionIdInt = Integer.parseInt(tmpText.getText());
    	tmpText.clear();
    	
    	// 쿼리 실행
    	ChampionDAO.ChampionDelete(ChampionIdInt);
    }
    
    // Search
    public void searchChampion(ActionEvent event) {
    	try {
    		int ChampionIdInt = Integer.parseInt(tmpText.getText());
        	tmpText.clear();
        	
        	// 쿼리 실행
        	ObservableList<ChampionVO> champData = ChampionDAO.ChampionSearch(ChampionIdInt);
			
        	// 데이터 채우기
        	populateChampions(champData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("챔피언 ID를 입력해주세요.");
		}
    	
    	MainController.ClickBtnAudioPlay();
    }
 
    
    // 테이블에 searched 데이터 채우기
    @FXML
    private void populateSearchedChampions (ObservableList<ChampionVO> champData) throws ClassNotFoundException {
    	championTable.setItems(champData);
    }
    
    // 마우스 오버시 커서 변경
    public class MyButtonEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();
			
			ButtonBase button = (ButtonBase) obj;
			button.setCursor(Cursor.HAND);
		}
	}
    
    // 화면 드래그로 이동 가능
    private void stageDragableMoveWindow() {
		parent.setOnMousePressed((event) -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		
		parent.setOnMouseDragged((event) -> {
		// Launcher.stage.setX(event.getScreenX() - xOffset);
		// Launcher.stage.setY(event.getScreenY() - yOffset);
		// Launcher.stage.setOpacity(0.8f); // 창 투명화
			stage = (Stage) parent.getScene().getWindow();
			stage.setX(event.getScreenX() - xOffset);
			stage.setY(event.getScreenY() - yOffset);
			stage.setOpacity(0.8f); // 창 투명화
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
	
    // 화면 최소화
	@FXML
	private void actionMinWindow(MouseEvent event) {
		// Launcher.stage.setIconified(true);
		stage = (Stage) parent.getScene().getWindow();
		stage.setIconified(true);
	}
	

	// 화면 닫기
	@FXML
	private void actionCloseWindow(MouseEvent event) {
		System.exit(0);
	}
	
	// 다이얼로그
//	public void popupDialog() {
//
//		try {
//			
//			Stage dialog = new Stage(StageStyle.UTILITY);
//			dialog.initModality(Modality.WINDOW_MODAL);
//			stage = (Stage) parent.getScene().getWindow();
//			dialog.initOwner(stage);
//			Parent parent;
//			
//			parent = FXMLLoader.load(getClass().getResource("../view/ChampionDetailView.fxml"));
//			
//			int championId = championTable.getSelectionModel().getSelectedItem().getChampionId();
////			System.out.println(championId);
//			FXMLLoader loader = new FXMLLoader();
//			
//			
//			ChampionVO champData = ChampionDAO.ChampionDetail(championId);
//			
//			String name = champData.getChampionName();
//			String attackPoint = Integer.toString(champData.getChampionAttack());
//			String defensePoint = Integer.toString(champData.getChampionAttack());
//			String p_name = champData.getChampionPositionName();
//			String a_t = champData.getChampionAttackType();
//			String d_t = champData.getChampionDefenseType();
//			String url = champData.getChampionURL();
//			
//			ChampionDetailController ct = loader.<ChampionDetailController>getController();
//			ct.initData(name, attackPoint, defensePoint, p_name, a_t, d_t, url);
//				
//			
//			Scene scene = new Scene(parent);
//			
//			dialog.setScene(scene);
//			dialog.setResizable(false);
//			dialog.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
   

}