package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDelete {
	public static void main(String[] args) {
		//0.import java.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1.JDBC 드라이버 (Oracle)로딩
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url ="jdbc:oracle:thin:@localhost:1521:xe";
			
			conn = DriverManager.getConnection(url,"webdb","1234");
				// TODO Auto-generated catch block
			
			//3. sql 문 준비 /바인딩 / 실행
			
			String query = "";
			query += " DELETE FROM author ";
			query += " WHERE author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,7);
			
			int count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error: 드라이버 로딩 실패 -"+e);
		} catch (SQLException e) {
			System.out.println("error: "+ e);
		}
			//5.자원정리
		
		 try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
	}
}
