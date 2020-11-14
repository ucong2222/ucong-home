package com.sbs.example.ucong.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.example.ucong.container.Container;
import com.sbs.example.ucong.dto.Article;
import com.sbs.example.ucong.dto.Member;
import com.sbs.example.ucong.service.ArticleService;
import com.sbs.example.ucong.service.MemberService;

public class ArticleController extends Controller {
	private Scanner sc;
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		sc = Container.scanner;
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.equals("article list")) {
			showList(cmd);
		} else if (cmd.startsWith("article detail")) {
			showDetail(cmd);
		} else if (cmd.startsWith("article delete")) {
			doDelete(cmd);
		} else if (cmd.startsWith("article modify")) {
			doModify(cmd);
		} else if (cmd.equals("article write")) {
			doWrite(cmd);
		}
	}

	private void doWrite(String cmd) {
		if(Container.session.logouted()) {
			System.out.println("로그인하고 이용해주세요.");
			return;
		}
		System.out.println("== 게시물 작성 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		int memberId= Container.session.loginedMemberId;
		int boardId=1;//임시
		
		int id = articleService.write(memberId,boardId,title, body);
		System.out.printf("%d번 게시물이 생성되었습니다.\n",id);
	}

	private void doModify(String cmd) {
		if(Container.session.logouted()) {
			System.out.println("로그인하고 이용해주세요.");
			return;
		}
		System.out.println("== 게시물 수정 ==");
		String[] cmdBits = cmd.split(" ");
		if (cmdBits.length <= 2) {
			System.out.println("게시물 번호를 입력해주세요.");
			return;
		}
		int inputedId = Integer.parseInt(cmdBits[2]);

		Article article = articleService.getArticle(inputedId);
		int memberId = Container.session.loginedMemberId;
		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		if(article.memberId!=memberId) {
			System.out.println("게시물 수정 권한이 없습니다.");
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		articleService.modify(inputedId, title, body);
		System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);
	}

	private void doDelete(String cmd) {
		if(Container.session.logouted()) {
			System.out.println("로그인하고 이용해주세요.");
			return;
		}
		String[] cmdBits = cmd.split(" ");
		if (cmdBits.length <= 2) {
			System.out.println("게시물 번호를 입력해주세요.");
			return;
		}
		int inputedId = Integer.parseInt(cmdBits[2]);
		Article article = articleService.getArticle(inputedId);
		int memberId = Container.session.loginedMemberId;
		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		if(article.memberId!=memberId) {
			System.out.println("게시물 삭제 권한이 없습니다.");
			return;
		}
		

		System.out.println("== 게시물 삭제 ==");

		articleService.delete(inputedId);

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);
	}

	private void showDetail(String cmd) {
		System.out.println("== 게시물 상세보기 ==");
		String[] cmdBits = cmd.split(" ");
		if (cmdBits.length <= 2) {
			System.out.println("게시물 번호를 입력해주세요.");
			return;
		}
		int inputedId = Integer.parseInt(cmdBits[2]);

		Article article = articleService.getArticle(inputedId);
		if (article == null) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}
		Member member = memberService.getMemberById(article.memberId);
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", member.name);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	}

	private void showList(String cmd) {
		System.out.println("== 게시물 리스트 ==");
		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목");

		List<Article> articles = articleService.getArticles();

		for (Article article : articles) {
			Member member = memberService.getMemberById(article.memberId);
			System.out.printf("%d / %s / %s / %s / %s\n", article.id, article.regDate, article.updateDate,
					member.name, article.title);
		}
	}
}
