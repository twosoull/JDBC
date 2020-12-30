package com.javaex.author02;

import java.util.List;

public class AuthorApp {
	public static void main(String[] args) {
		List<AuthorVo> authorList = null;
		AuthorDao authorDao = new AuthorDao();
		//등록
		AuthorVo authorVo01 = new AuthorVo("이문열","경북영양");
		authorDao.authorInsert(authorVo01); // 작가테이블에 데이터 저장
		
		AuthorVo authorVo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(authorVo02); // 작가테이블에 데이터 저장
		
		AuthorVo authorVo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo03); // 작가테이블에 데이터 저장
		

		// 리스트
		authorList = authorDao.getAuthorList();// 출력을 한다.

		// 리스트 전체출력
		System.out.println("작가리스트 =========");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		// 작가삭제
		authorDao.authorDelete(3);

		authorList = authorDao.getAuthorList();
		// 값이 변경된 리스트의 객체를 다시 넣어준다.

		// 리스트 전체출력
		System.out.println("작가리스트 =========");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		//작가수정
		AuthorVo authorVo04 =new AuthorVo(2,"김경리","제주도");
		authorDao.authorUpdate(authorVo04);

		authorList = authorDao.getAuthorList();
		// 값이 변경된 리스트의 객체를 다시 넣어준다.

		// 리스트 전체출력
		System.out.println("작가리스트 =========");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}

	}
}
