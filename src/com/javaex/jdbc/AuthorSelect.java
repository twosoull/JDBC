package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {
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
			conn = DriverManager.getConnection(url, "webdb", "1234");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    /*
		     select  author_id,
        			 author_desc,
        			 author_name
			 from author;
		     */
			String query = "";
			query += " select  author_id as id, ";
			query += "  	   author_desc, ";
			query += " 		   author_name as name ";
			query += " from author";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			//select의 실행문은 insert,update,delete와는 다르다
			
			rs = pstmt.executeQuery();
			
			//여기까지오면 쿼리가 완성되어있다.
			//rs에 완성된 테이블의 주소를 준다
			
		    // 4.결과처리
			
			while(rs.next()) {
				int authorId= rs.getInt("id"); //author_id 컬럼의 첫번째값을 가져온다
				String authorName = rs.getString("name");
				String authorDesc = rs.getString("author_desc");
				
				System.out.println(authorId + ", "+authorName + ", "+authorDesc);
			}
			
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
