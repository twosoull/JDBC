package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorUpdate {
	
	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");


		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			System.out.println(DriverManager.getConnection(url, "webdb", "1234"));
			conn = DriverManager.getConnection(url, "webdb", "1234");

		    
			
			/*// 3. SQL문 준비 / 바인딩 / 실행
			update author
			set author_desc = '제주맨'
			where author_id = 7;
			*/
			
			String query = "";
			query += " update author ";
			query += " set author_desc = ? ";
			query += " where author_id = ? ";  //앞뒤로 한칸씩 띄는 버릇을 둬라
			
			pstmt = conn.prepareStatement(query);//쿼리로 만들기
			pstmt.setString(1,"서울시 하이미디어 별관");
			pstmt.setInt(2,7);
			
			
			int count = pstmt.executeUpdate();
		    // 4.결과처리
			System.out.println(count +" 건이 수정되었습니다.");
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
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
}
