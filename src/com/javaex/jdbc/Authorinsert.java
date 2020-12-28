package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authorinsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");	//드라이버를 넣고
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; //오라클의 주소
			conn = DriverManager.getConnection(url, "webdb", "1234"); //url, 아이디,비밀번호 //접속할 아이디와 비번  

			
		    // 3. SQL문 준비 / 바인딩 / 실행
			//	insert into author values(seq_author_id.nextval,'이문열','경북 영양'); //문자열로 만들기
			
			String query = "insert into author values(seq_author_id.nextval,?,?)"; //넣고싶은 형식을 스트링문자열로 준비
			pstmt = conn.prepareStatement(query); //작성한 쿼리를 넣고 
			
			pstmt.setString(1, "황일영"); //? 부분을 채워준다
			pstmt.setString(2, "서울시도봉구"); //?부분을 채워준다 3개까지 있다면 3번으로 준비해주면된다.
			
			int count = pstmt.executeUpdate(); //몇개의 쿼리문이 들어갔는지

			
		    // 4.결과처리
			
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		    	/*
		        if (rs != null) {
		            rs.close();
		        } 
		        */               
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
