package com.myapp.model;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.DBUtil.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

public class ChampionDAO {
	
	private static Connection conn = DBConnection.getConnection();
	private static Statement stmt;
	private static ResultSet rs;
	
	public static void ChampionInsert(ChampionVO newChampion) {
        String runSP = "{ call champion_pack.champion_insert(?, ?, ?, ?, ?, ?, ?) }";
        
        try {
            CallableStatement callableStatement = conn.prepareCall(runSP);
//            callableStatement.setInt(1, newChampion.getChampionId());
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
	
	public static ObservableList<ChampionVO> ChampionSelectAll() {
		
		String runSP = "{ call champion_pack.champion_select_all(?) }";
		
		ObservableList<ChampionVO> champList = FXCollections.observableArrayList();
		
		try {
			CallableStatement callableStatement = conn.prepareCall(runSP);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			
			ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
			
//			ChampionVO champion = null;
			
			while (resultSet.next()) {
				ChampionVO champion = new ChampionVO();
				champion.setChampionId(resultSet.getInt(1));
//				System.out.print(champion.getChampionId());
				champion.setChampionName(resultSet.getString(2));
				champion.setChampionAttack(resultSet.getInt(3));
				champion.setChampionDefense(resultSet.getInt(4));
//				champion.setChampionPosition(resultSet.getInt(5));
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
	
//	public static void ChampionInsert(String text, int parseInt, int parseInt2, String text2) {
//		String runSP = "{ call champion_pack.champions_insert(?, ?, ?, ?) }";
//
//        try {
//            CallableStatement callableStatement = conn.prepareCall(runSP);
////            callableStatement.setInt(1, newChampion.getChampionId());
//            callableStatement.setString(1, text);
//            callableStatement.setInt(2, parseInt);
//            callableStatement.setInt(3, parseInt2);
//            callableStatement.setString(4, text2);
//            callableStatement.executeUpdate();
//            System.out.println("챔피언 생성 완료");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//		
//	}
}
