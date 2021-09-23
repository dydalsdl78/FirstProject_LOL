package com.myapp.model;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.myapp.DBUtil.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

public class ChampionDAO {
	
	// DB 연결
	private static Connection conn = DBConnection.getConnection();
	
	// statement, resultset 초기화
	private static Statement stmt;
	private static ResultSet rs;
	
	// Insert
	public static void ChampionInsert(ChampionVO newChampion) {
        String runSP = "{ call champion_pack.champion_insert(?, ?, ?, ?) }";
        
        try {
            CallableStatement callableStatement = conn.prepareCall(runSP);
            callableStatement.setString(1, newChampion.getChampionName());
            callableStatement.setInt(2, newChampion.getChampionAttack());
            callableStatement.setInt(3, newChampion.getChampionDefense());
            callableStatement.setInt(4, newChampion.getChampionPosition());
            callableStatement.executeUpdate();
            System.out.println("챔피언 생성 완료");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
	
	// Select all
	public static ObservableList<ChampionVO> ChampionSelectAll() {
		
		String runSP = "{ call champion_pack.champion_select_all(?) }";
		
		ObservableList<ChampionVO> champList = FXCollections.observableArrayList();
		
		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			System.out.println("챔피언 모두 불러오기 완료");
			
			ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
			
			// champList에 데이터 저장
			while (resultSet.next()) {
				ChampionVO champion = new ChampionVO();
				champion.setChampionId(resultSet.getInt(1));
				champion.setChampionName(resultSet.getString(2));
				champion.setChampionAttack(resultSet.getInt(3));
				champion.setChampionDefense(resultSet.getInt(4));
				champion.setChampionPositionName(resultSet.getString(5));
				champion.setChampionAttackType(resultSet.getString(6));
				champion.setChampionDefenseType(resultSet.getString(7));
				champList.add(champion);
	        }	 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return champList;
	}
	
	// Select Random
	public static ObservableList<ChampionVO> ChampionSelectRandom() {
		
		String runSP = "{ call champion_pack.champion_random(?) }";
		
		ObservableList<ChampionVO> champList = FXCollections.observableArrayList();
		
		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			
			ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
			
			// champList에 데이터 저장
			while (resultSet.next()) {
				ChampionVO champion = new ChampionVO();
				champion.setChampionId(resultSet.getInt(1));
				champion.setChampionName(resultSet.getString(2));
				champion.setChampionAttack(resultSet.getInt(3));
				champion.setChampionDefense(resultSet.getInt(4));
				champion.setChampionPosition(resultSet.getInt(5));
				champList.add(champion);
	        }			 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return champList;
	}
	
	// Update
	public static void Championupdate(int championId, int championAttackPoint, int championDefensePoint) {
	    String runSP = "{ call champion_pack.champion_update(?, ?, ?) }";
	    
	    try {
	        CallableStatement callableStatement = conn.prepareCall(runSP);
	        callableStatement.setInt(1, championId);
	        callableStatement.setInt(2, championAttackPoint);
	        callableStatement.setInt(3, championDefensePoint);
	        callableStatement.executeUpdate();
	        System.out.println("챔피언 업데이트 완료");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {

	    }
		
	}
	
	// Delete
	public static void ChampionDelete(int ChampionId) {
		String runSP = "{ call champion_pack.champion_delete(?) }";
        
        try {
            CallableStatement callableStatement = conn.prepareCall(runSP);
            callableStatement.setInt(1, ChampionId);
            callableStatement.executeUpdate();
            System.out.println("챔피언 삭제 완료");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
	}
	
	// Search Champion
	public static ObservableList<ChampionVO> ChampionSearch(int ChampionId) {
		
		ObservableList<ChampionVO> champList = FXCollections.observableArrayList();
				
		String runSP = "{ call champion_pack.champion_search(?, ?) }";
        
        try {
            CallableStatement callableStatement = conn.prepareCall(runSP);
            callableStatement.setInt(1, ChampionId);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            System.out.println("챔피언 서치 완료");
            
            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
            
            // champList에 데이터 저장
            while (resultSet.next()) {
            	ChampionVO champInfo = new ChampionVO();
            	champInfo.setChampionId(resultSet.getInt(1));
            	champInfo.setChampionName(resultSet.getString(2));
            	champInfo.setChampionAttack(resultSet.getInt(3));
            	champInfo.setChampionDefense(resultSet.getInt(4));
            	champInfo.setChampionPositionName(resultSet.getString(5));
            	champInfo.setChampionAttackType(resultSet.getString(6));
            	champInfo.setChampionDefenseType(resultSet.getString(7));
            	champList.add(champInfo);
	        }
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {

        }
        return champList;
	}
	
	// Detail Champion
//	public static ChampionVO ChampionDetail(int ChampionId) {
//		
//		ChampionVO champInfo = new ChampionVO();			
//		String runSP = "{ call champion_pack.champion_detail(?, ?) }";
//        
//        try {
//            CallableStatement callableStatement = conn.prepareCall(runSP);
//            callableStatement.setInt(1, ChampionId);
//            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//            callableStatement.execute();
//            System.out.println("챔피언 상세데이터 불러오기 완료");
//            
//            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
//
//            while (resultSet.next()) {
//            	
//            	champInfo.setChampionImage(resultSet.getString(1));
//            	champInfo.setChampionName(resultSet.getString(2));
//            	champInfo.setChampionAttack(resultSet.getInt(3));
//            	champInfo.setChampionDefense(resultSet.getInt(4));
//            	champInfo.setChampionPositionName(resultSet.getString(5));
//            	champInfo.setChampionAttackType(resultSet.getString(6));
//            	champInfo.setChampionDefenseType(resultSet.getString(7));
//            	champInfo.setChampionURL(resultSet.getString(8));
//	        }
//        } catch (SQLException e) {
//        	System.out.println("SQL문 오류.");
//        } catch (Exception e) {
//        	System.out.println("예외 발생");
//        } finally {
//
//        }
//        return champInfo;
//	}
	
	
	// Search Image
	public static String searchChampionImage(String championName) {
		String runSP = "{ call champion_pack.champion_select_image(?, ?) }";
		String imageUrl = "";
		
		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.setString(1, championName);
			callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			callableStatement.execute();
			imageUrl = callableStatement.getString(2);
			System.out.println("이미지 url 찾기 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return imageUrl;
	}

}
