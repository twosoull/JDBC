package com.javaex.author02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	// 필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";
	// 생성자
	// 디폴트 생략 가능

	// 메소드 g/s

	// 메소드 일반

	// 접속메소드
	private void getConnetion() {
		// 오라클 접속
		// 1. JDBC 드라이버 (Oracle) 로딩
		try {
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//자원관리
	private void close() {
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

	// 수정
	public int authorUpdate(AuthorVo authorVo) {
		int count = 0;
		// DB접속
		getConnetion();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author ";
			query += " set author_name = ?, ";
			query += "     author_desc = ? ";
			query += " where author_id = ? "; // 앞뒤로 한칸씩 띄는 버릇을 둬라
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] : " + count + " 건이 수정되었습니다");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 

			close();
		
		return count;
	}

	// 작가 리스트 가져오기
	public int authorDelete(int authorId) {
		int count = 0;
		// DB접속
		getConnetion();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DELETE FROM author ";
			query += " where author_id = ? ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] :" + count + "건이 삭제되었습니다");
	
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();

		return count;
	}// authorDelete

	public List<AuthorVo> getAuthorList() {
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		// DB접속
		getConnetion();

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  author_id as id, ";
			query += "  	   author_desc, ";
			query += " 		   author_name as name ";
			query += " from author";

			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			// rs에 있는 데이터를 List<AuthorVo> 로 구성해야 한다.
			while (rs.next()) {
				int authorId = rs.getInt("id"); // author_id 컬럼의 첫번째값을 가져온다
				String authorName = rs.getString("name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();
		return authorList;
	}

	// 작가 저장 기능
	public int authorInsert(AuthorVo authorVo) {
		// 0. import java.sql.*;
		int count = 0;
		// DB접속
		getConnetion();		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " INSERT INTO author ";
			query += " values(seq_author_id.nextval,?,?) ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println("[DAO] :" + count + "건 저장");

		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		close();

		return count;
	}// authorInsert()

}
