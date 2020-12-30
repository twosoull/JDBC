package com.javaex.book02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookAuthorApp {
	public static void main(String[] args) {
		// AuthorDao,AuthorVo 먼저 등록 이유 : 작가없이 북테이블을 만들수없음
		// (수정,삭제 리스트)
		Scanner sc = new Scanner(System.in);

		List<BookVo> bList = new ArrayList<BookVo>();
		BookDao bookDao = new BookDao();
		List<AuthorVo> VoList = new ArrayList<AuthorVo>();
		AuthorDao authordao = new AuthorDao();
		boolean whileon = true;
		while (whileon) {
			System.out.println("**********************************");
			System.out.println("******북/작가 조회 프로그램*******");
			System.out.println("**********************************");
			System.out.println("1.작가등록 2.작가수정 3.작가삭제(foringkey로 인해 권장 x)");
			System.out.println("4.작가조회 5.책등록 6.책수정");
			System.out.println(" 7.책삭제 8.책조회 9.책,작가조회");
			System.out.println("10. 책/작가검색 11.종료");
			System.out.print(">>");
			int select = sc.nextInt();
			switch (select) {

			case 1:
				// 작가 6명 등록
				AuthorVo authorVo01 = new AuthorVo("이문열", "경북영양");
				authordao.authorInsert(authorVo01);
				AuthorVo authorVo02 = new AuthorVo("박경리", "경상남도 통영");
				authordao.authorInsert(authorVo02);
				AuthorVo authorVo03 = new AuthorVo("유시민", "17대국회의원");
				authordao.authorInsert(authorVo03);
				AuthorVo authorVo04 = new AuthorVo("기안84", "기안동에서 산 84년생");
				authordao.authorInsert(authorVo04);
				AuthorVo authorVo05 = new AuthorVo("강풀", "온라인만화가 1세대");
				authordao.authorInsert(authorVo05);
				AuthorVo authorVo06 = new AuthorVo("김영하", "알쓸신잡");
				authordao.authorInsert(authorVo06);
				break;
			case 2:
				// 수정
				AuthorVo authorVo07 = new AuthorVo(1, "영훈", "중랑구");
				authordao.authorUpdate(authorVo07);
				AuthorVo authorVo08 = new AuthorVo(3, "이지안", "나의아저씨");
				authordao.authorUpdate(authorVo08);
				break;
			case 3:
				// 삭제
				AuthorVo authorVo09 = new AuthorVo(1);
				authordao.authorDelete(6);
				break;
			case 4:
				// Select
				VoList = authordao.authorSelect();

				for (int i = 0; i < VoList.size(); i++) {
					AuthorVo vo = VoList.get(i);
					System.out.println(vo.getAuthor_id() + " , " + vo.getAuthor_name() + " , " + vo.getAuthor_desc());
				}
				break;
			// --------------Book----------------

			// List<BookVo> bList = new ArrayList<BookVo>();
			// BookDao bookDao = new BookDao();
			// 책 등록
			case 5:
				BookVo bv01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998/02/22", 1);
				BookVo bv02 = new BookVo("삼국지", "믿음", "2002/03/01", 1);
				BookVo bv03 = new BookVo("토지", "마로니에북스", "2012/08/15", 2);
				BookVo bv04 = new BookVo("유시민의 글쓰기 특강", "생각의 길", "2015/04/01", 3);
				BookVo bv05 = new BookVo("패션왕", "중앙북스(books)", "2012/02/22", 4);
				BookVo bv06 = new BookVo("순정만화", "재미주의", "2011/08/03", 5);
				BookVo bv07 = new BookVo("오직두사람", "문학동네", "2017/05/04", 6);
				BookVo bv08 = new BookVo("26년", "재미주의", "2012/02/04", 5);
				bookDao.bookInsert(bv01);
				bookDao.bookInsert(bv02);
				bookDao.bookInsert(bv03);
				bookDao.bookInsert(bv04);
				bookDao.bookInsert(bv05);
				bookDao.bookInsert(bv06);
				bookDao.bookInsert(bv07);
				bookDao.bookInsert(bv08);
				break;
			case 6:
				// 수정
				BookVo bv001 = new BookVo(1, "영훈포터", "cj", "1992/06/27", 5);
				bookDao.bookUpdate(bv001);
				break;
			case 7:
				// 삭제
				BookVo bv101 = new BookVo(1);
				bookDao.bookDelete(bv101);
				break;
			case 8:
				// 조회
				bList = bookDao.bookSelect();
				for (int i = 0; i < bList.size(); i++) {
					BookVo bv = bList.get(i);
					System.out.println(bv.getBook_id() + "," + bv.getTitle() + "," + bv.getPubs() + ","
							+ bv.getPub_date() + "," + bv.getAuthor_id());
				}
				break;
			case 9:
				// 조인조회
				bList = bookDao.bookJoinSelect();

				for (int i = 0; i < bList.size(); i++) {
					BookVo bv = bList.get(i);
					System.out.println(bv.getBook_id() + ", " + bv.getTitle() + ", " + bv.getPubs() + ", "
							+ bv.getPub_date() + ", " + bv.getAuthor_id() + ", " + bv.getAuthor_name() + ", "
							+ bv.getAuthor_desc());
				}
				break;
			case 10:
				System.out.print("검색>>");
				String str1 = sc.nextLine();
				String str = sc.nextLine();
				// 검색기능
				bList = bookDao.bookSearch(str);

				for (int i = 0; i < bList.size(); i++) {
					BookVo bv = bList.get(i);
					System.out.println(bv.getBook_id() + ", " + bv.getTitle() + ", " + bv.getPubs() + ", "
							+ bv.getPub_date() + ", " + bv.getAuthor_id() + ", " + bv.getAuthor_name() + ", "
							+ bv.getAuthor_desc());
				}

				break;
			case 11:
				whileon = false;
				System.out.println("종료되었습니다.");
				break;
			}// swhitch

		} // while
	}
}
