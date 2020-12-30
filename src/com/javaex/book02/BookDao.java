package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String id = "webdb";
	private String pw = "1234";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";

	// 생성자
	// 메소드 g/s
	// 메소드 일반
	private void getConnection() {
		try {
			// jdbc 받기
			Class.forName(driver);
			// connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 받는데 실패했습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void close() {
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

	public int bookInsert(BookVo bv) {
		int count = 0;
		// DB접속
		getConnection();
		try {

			// 쿼리문/ 바인드/ 실행
			String query = "";
			query += " INSERT INTO book ";
			query += " VALUES(seq_book_id.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getPubs());
			pstmt.setString(3, bv.getPub_date());
			pstmt.setInt(4, bv.getAuthor_id());

			count = pstmt.executeUpdate();

			// 실행
			System.out.println("[DAO] :" + count + "건이 생성되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		}

		close();
		return count;
	}// bookInsert()

	public int bookUpdate(BookVo bv) {
		int count = 0;
		// DB접속
		getConnection();
		try {

			// 쿼리문 /바인드/실행
			String query = "";
			query += " UPDATE book ";
			query += " set title= ?, ";
			query += "     pubs = ?, ";
			query += "     pub_date  = ?, ";
			query += "     author_id = ? ";
			query += " where book_id = ? ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getPubs());
			pstmt.setString(3, bv.getPub_date());
			pstmt.setInt(4, bv.getAuthor_id());
			pstmt.setInt(5, bv.getBook_id());

			count = pstmt.executeUpdate();

			// 실행
			System.out.println("[DAO] :" + count + "건이 수정되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		}
		close();
		return count;
	}// bookUpdate()

	public int bookDelete(BookVo bv) {
		int count = 0;
		// DB접속
		getConnection();
		try {
			// 쿼리문 /바인드/ 실행
			String query = "DELETE FROM book where book_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bv.getBook_id());
			count = pstmt.executeUpdate();

			// 실행
			System.out.println("[DAO]:" + count + "건이 삭제되었습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		}
		close();
		return count;
	}// bookDelete()

	public List<BookVo> bookSelect() {
		List<BookVo> bList = new ArrayList<BookVo>();
		// DB접속
		getConnection();
		try {
			// 쿼리문 / 바인드 /실행
			String query = "";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        to_char(pub_date,'yy/mm/dd') as pubDate, ";
			query += "        author_id ";
			query += " from book ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pubDate");
				int authorId = rs.getInt("author_id");

				BookVo bv = new BookVo(bookId, title, pubs, pubDate, authorId);

				bList.add(bv);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		}
		close();
		return bList;
	}// bookSelect()

	public List bookJoinSelect() {
		List<BookVo> bList = new ArrayList<BookVo>();
		// DB접속
		getConnection();
		try {
			// 쿼리문 / 바인드 /실행
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
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String title = rs.getString("bTitle");
				String pubs = rs.getString("bPubs");
				String pubDate = rs.getString("bPubDate");
				int authorId = rs.getInt("bAuthorId");
				String aName = rs.getString("aName");
				String aDesc = rs.getString("aDesc");

				BookVo bv = new BookVo(bookId, title, pubs, pubDate, authorId, aName, aDesc);

				bList.add(bv);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("커넥션을 받는데 실패했습니다");
		}
		close();
		return bList;
	}// bookSelect()

	public List<BookVo> bookSearch(String str) {
		List<BookVo> bList = new ArrayList<BookVo>();
		getConnection();
		try {
			String query = "";
			query += " select b.book_id as bookId, ";
			query += "        b.title as bTitle, ";
			query += "        b.pubs as bPubs, ";
			query += "       to_char(b.pub_date,'yy/mm/dd') as bPubDate, ";
			query += "        b.author_id as bAuthorId, ";
			query += "        a.author_name as aName, ";
			query += "        a.author_desc as aDesc ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";
			query += " and (b.title like ? ";
			query += " or b.pubs like ? ";
			query += " or a.author_name like ?) ";

			String str1 = "%" + str + "%";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, str1);
			pstmt.setString(2, str1);
			pstmt.setString(3, str1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String bTile = rs.getString("bTitle");
				String bPubs = rs.getString("bPubs");
				String bPubDate = rs.getString("bPubDate");
				int bAuthorId = rs.getInt("bAuthorId");
				String aName = rs.getString("aName");
				String aDesc = rs.getString("aDesc");

				BookVo bv = new BookVo(bookId, bTile, bPubs, bPubDate, bAuthorId, aName, aDesc);

				bList.add(bv);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();

		return bList;

	}
}
