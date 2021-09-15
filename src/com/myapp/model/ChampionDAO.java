package com.myapp.model;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.DBUtil.DBConnection;

public class ChampionDAO {
	
	private static Connection conn = DBConnection.getConnection();
	private static Statement stmt;
	private static ResultSet rs;
	
	public static void ChampionInsert(ChampionVO newChampion) {
        String runSP = "{ call champion_pack.champions_insert(?, ?, ?, ?) }";
        
        try {
            CallableStatement callableStatement = conn.prepareCall(runSP);
//            callableStatement.setInt(1, newChampion.getChampionId());
            callableStatement.setString(1, newChampion.getChampionName());
            callableStatement.setInt(2, newChampion.getChampionAttack());
            callableStatement.setInt(3, newChampion.getChampionDefense());
            callableStatement.setString(4, newChampion.getChampionPosition());
            callableStatement.executeUpdate();
            System.out.println("챔피언 생성 완료");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
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
