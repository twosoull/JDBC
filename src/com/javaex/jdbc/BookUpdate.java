package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookUpdate {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //오라클의 주소
			conn = DriverManager.getConnection(url, "webdb", "1234");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " UPDATE BOOK ";
			query += " SET title = ? ";
			query += " where book_id = ? ";
			pstmt = conn.prepareStatement(query);
		    pstmt.setString(1,"순정만화2권");
		    pstmt.setInt(2, 14);
		    
		    int count = pstmt.executeUpdate();
			
		    System.out.println(count + "건이 수정되었습니다.");
		    // 4.결과처리

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
