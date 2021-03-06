package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {
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
		    String query = "";
		    query += " select b.book_id as bId, ";
		    query += "        b.title as bTitle,";
		    query += "        b.pubs as bPubs,";
		    query += "        b.pub_date as bPubDate, ";
		    query += "        b.author_id as bAuthorId, ";
		    query += "        a.author_name as aAuthorName, ";
		    query += "        a.author_desc ";
		    query += " from book b , author a ";
		    query += " where  b.author_id = a.author_id ";
		    
		    System.out.println(query);
			
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()) {
		    	int bookId = rs.getInt("bId");
		    	String bookTitle = rs.getString("bTitle");
		    	String bookPubs = rs.getString("bPubs");
		    	String bookPubDate = rs.getString("bPubDate");
		    	int bookAuthorId = rs.getInt("bAuthorId");
		    	String authorName = rs.getString("aAuthorName");
		    	String authorDesc = rs.getString(7);
		    	
		    	System.out.println(
		    			bookId + ", " +
		    			bookTitle + ", "+
		    			bookPubs + ", "+
		    			bookPubDate + ", "+
		    			bookAuthorId + ", "+
		    			authorName + ", "+
		    			authorDesc );
		    }
		    
		    
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
