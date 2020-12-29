package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AuthorDao {
	// 필드
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "webdb";
	String pw = "1234";
	// 생성자
	// 메소드 g/s
	// 메소드 일반

	public int authorInsert(AuthorVo authorVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// jdbc 오라클 드라이버
			Class.forName(driver);
			// connection 받아오기
			conn = DriverManager.getConnection(url, id, pw);
			//SQL문 / 바인딩/ 실행
			String query = "";
			query += " INSERT INTO author ";
			query += " VALUES(seq_author_id.nextval,?,?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setNString(1, authorVo.getAuthor_name());
			pstmt.setNString(2, authorVo.author_desc);
			
			count = pstmt.executeUpdate();
			//확인
			System.out.println("[DAO] :" +count+"건이 등록되었습니다.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
		} finally {
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

		return count;
	}//authorInsert()

	public int authorUpdate(AuthorVo authorVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// jdbc 오라클 드라이버
			Class.forName(driver);
			// connection 받아오기
			conn = DriverManager.getConnection(url, id, pw);
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
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
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

		return count;
	}//authorUpdate;

	public int authorDelete(int authorId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// jdbc 오라클 드라이버
			Class.forName(driver);
			// connection 받아오기
			conn = DriverManager.getConnection(url, id, pw);
			// 쿼리문 / 바인드 / 실행
			String query = "";
			query += " DELETE FROM author ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,authorId);
			
			count = pstmt.executeUpdate();
			//실행
			System.out.println("[DAO] :" + count + "건이 삭제되었습니다");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
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

		return count;
	}//authorDelete()
	
	public List<AuthorVo> authorSelect(){
		
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// jdbc 오라클 드라이버
			Class.forName(driver);
			// connection 받아오기
			conn = DriverManager.getConnection(url, id, pw);
			
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
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션에 실패했습니다");
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

		return list;
	}
}//authorSelect()
