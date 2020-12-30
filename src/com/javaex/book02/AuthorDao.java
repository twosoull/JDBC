package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AuthorDao {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";
	// 생성자
	// 메소드 g/s
	// 메소드 일반
	
	//DB접속 
	private void getConnection() {
		// jdbc 오라클 드라이버
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url, id, pw);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("드라이버 실패했습니다");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//SQL문 / 바인딩/ 실행
	}
	private void close() {
		try {
			if (rs != null)
				rs.close();

			if (pstmt != null)
				pstmt.close();

			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public int authorInsert(AuthorVo authorVo) {
		int count = 0;
		try {
			//DB접속
			getConnection();
			
			String query = "";
			query += " INSERT INTO author ";
			query += " VALUES(seq_author_id.nextval,?,?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setNString(1, authorVo.getAuthor_name());
			pstmt.setNString(2, authorVo.author_desc);
			
			count = pstmt.executeUpdate();
			//확인
			System.out.println("[DAO] :" +count+"건이 등록되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
		} 
		close();

		return count;
	}//authorInsert()

	public int authorUpdate(AuthorVo authorVo) {
		
		int count = 0;
		try {
			//DB접속
			getConnection();
			// 쿼리문/ 바인드 / 실행
			String query = "";
			query += " UPDATE author";
			query += " set author_name = ?,";
			query += "     author_desc = ? ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthor_name());
			pstmt.setString(2, authorVo.getAuthor_desc());
			pstmt.setInt(3, authorVo.author_id);
			count = pstmt.executeUpdate();
			//실행
			System.out.println("[DAO] :" + count + "건이 수정되었습니다");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
		} 
		close();

		return count;
	}//authorUpdate;

	public int authorDelete(int authorId) {
		
		int count = 0;
		try {
			//DB접속
			getConnection();
			// 쿼리문 / 바인드 / 실행
			String query = "";
			query += " DELETE FROM author ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,authorId);
			
			count = pstmt.executeUpdate();
			//실행
			System.out.println("[DAO] :" + count + "건이 삭제되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
		}
		close();

		return count;
	}//authorDelete()
	
	public List<AuthorVo> authorSelect(){
		
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		try {
			//DB접속
			getConnection();
			//select문 쿼리 작성/ 바인드 / 실행
			String query = "";
			query += " select author_id,";
			query += "        author_name,";
			query += "        author_desc";
			query += " from author";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("author_id");
				String name =rs.getString("author_name");
				String desc = rs.getString("author_desc");
			
				AuthorVo authorvo =new AuthorVo(id,name,desc);
				list.add(authorvo);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
		} 
		close();
		return list;
	}//authorSelect()
	}
