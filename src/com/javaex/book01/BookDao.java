package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	// 필드
	String id = "webdb";
	String pw = "1234";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	// 생성자
	// 메소드 g/s
	// 메소드 일반
	public int bookInsert(BookVo bv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// jdbc 받기
			Class.forName(driver);
			// connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// 쿼리문/ 바인드/ 실행
			String query = "";
			query += " INSERT INTO book ";
			query += " VALUES(seq_book_id.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,bv.getTitle());
			pstmt.setString(2,bv.getPubs());
			pstmt.setString(3,bv.getPub_date());
			pstmt.setInt(4,bv.getAuthor_id());
			
			count = pstmt.executeUpdate();
			
			
			//실행
			System.out.println("[DAO] :" + count + "건이 생성되었습니다.");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 받는데 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (conn != null)
					conn.close();

				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return count;
	}//bookInsert()
	public int bookUpdate(BookVo bv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// jdbc 받기
			Class.forName(driver);
			// connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			//쿼리문 /바인드/실행
			String query = "";
			query += " UPDATE book ";
			query += " set title= ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date  = ?, ";
			query += "     author_id = ? ";
			query += " where book_id = ? ";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,bv.getTitle());
			pstmt.setString(2, bv.getPubs() );
			pstmt.setString(3,bv.getPub_date());
			pstmt.setInt(4,bv.getAuthor_id());
			pstmt.setInt(5,bv.getBook_id());
			
			System.out.println(query);
			count = pstmt.executeUpdate();
			
			//실행
			System.out.println("[DAO] :" + count + "건이 수정되었습니다");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 받는데 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		} finally {
			
			try {
				if (rs != null)
					rs.close();
				
				if (conn != null)
					conn.close();
				
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return count;
	}//bookUpdate()
	public int bookDelete(BookVo bv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// jdbc 받기
			Class.forName(driver);
			// connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			//쿼리문 /바인드/ 실행
			String query = "DELETE FROM book where book_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bv.getBook_id());
			int count = pstmt.executeUpdate();
			System.out.println(query);
			
			//실행
			System.out.println("[DAO]:"+count + "건이 삭제되었습니다");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 받는데 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		} finally {
			
			try {
				if (rs != null)
					rs.close();
				
				if (conn != null)
					conn.close();
				
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}//bookDelete()
	public List<BookVo> bookSelect() {
		List<BookVo> bList = new ArrayList<BookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// jdbc 받기
			Class.forName(driver);
			// connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			//쿼리문 / 바인드 /실행
			String query = "";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        to_char(pub_date,'yy/mm/dd') as pubDate, ";
			query += "        author_id ";
			query += " from book ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pubDate");
				int authorId = rs.getInt("author_id");
				
				BookVo bv = new BookVo(bookId,title,pubs,pubDate,authorId);
				
				bList.add(bv);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 받는데 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		} finally {
			
			try {
				if (rs != null)
					rs.close();
				
				if (conn != null)
					conn.close();
				
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return bList;
	}//bookSelect()
	
	public List bookJoinSelect() {
		List<BookVo> bList = new ArrayList<BookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// jdbc 받기
			Class.forName(driver);
			// connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			//쿼리문 / 바인드 /실행
			String query = "";
			query += " select b.book_id as bookId, ";
			query += "        b.title as bTitle, ";
			query += "        b.pubs as bPubs, ";
			query += "        to_char(b.pub_date,'yy/mm/dd') as bPubDate, ";
			query += "        b.author_id as bAuthorId, ";
			query += " 		  a.author_name as aName,  ";
			query += " 		  a.author_desc as aDesc ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bookId = rs.getInt("bookId");
				String title = rs.getString("bTitle");
				String pubs = rs.getString("bPubs");
				String pubDate = rs.getString("bPubDate");
				int authorId = rs.getInt("bAuthorId");
				String aName = rs.getString("aName");
				String aDesc = rs.getString("aDesc");
				
				BookVo bv = new BookVo(bookId,title,pubs,pubDate,authorId,aName,aDesc);
				
				bList.add(bv);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 받는데 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		} finally {
			
			try {
				if (rs != null)
					rs.close();
				
				if (conn != null)
					conn.close();
				
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return bList;
	}//bookSelect()
}
