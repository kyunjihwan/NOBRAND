package com.sys;

import com.kjh.manager.ManagerEvent;
import com.kjh.manager.ManagerService;
import com.kjh.product.ProductService;
import com.ksk.basketlist.BasketlistShow;
import com.ljh.inquirycenter.InquiryService;
import com.ljh.review.ReviewService;
import com.nobrand.main.Main;
import com.lhc.PayCancle;
import com.lhc.coupon.CouponLoad;

public class MainService {
	
	
	private MyPage mp = new MyPage();
	private MemberService ms = new MemberService();
	private InquiryService is = new InquiryService();
	private ProductService ps = new ProductService();
	private ManagerService as = new ManagerService();
	BasketlistShow bs = new BasketlistShow();
	ManagerEvent me = new ManagerEvent();
	ReviewService rs = new ReviewService();
	private CouponLoad cp = new CouponLoad();
	private PayCancle pc = new PayCancle();
	
	public boolean startService() throws Exception {
		//선택지 보여주기
		showMenu();
		
		//입력받기
		System.out.print("                       입력 >> ");
		String input = Main.SC.nextLine();
		
		if("9".equals(input)) {
			return true;
		}
		
		//선택지에 따라 로직 실행
		processService(input);
		
		return false;
	}
	
	private void processService(String input) throws Exception {
		switch(input) {
		case "99" : 
		if(ms.managerLogin()) {
			while(true) {
				boolean isFinish = as.startManagerMenu();
				if(isFinish) {break;}
			}
		}; break;
		case "1" : ms.join();  break;
		case "2" : 
		if(ms.login()) {
			while(true) {
				boolean isFinish = startMenuService();
				if(isFinish) {break;}
				
			}
		}
		break;
		case "3" : ms.showFindMemberId(); break;
		case "4" : ms.showFindMemberPwd(); break;		
		default : System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	private void showMenu() {
		
		System.out.println();
		System.out.println();
		System.out.println(" ██╗   ██╗ ██████╗ ███████╗ ███████╗  ██████╗ ██╗   ██╗██████╗  ");
		System.out.println(" ████╗ ██║██╔═══██╗██╔═══██╗██╔═══██╗██╔═══██╗████╗ ██║██╔══██╗");
		System.out.println(" ██║██╗██║██║   ██║███████═╝███████╔╝████████║██╔██╗██║██║  ██║ ");
		System.out.println(" ██║╚████║██║   ██║██╔═══██╗██╔══██╚╗██╔═══██║██║╚████║██║  ██║");
		System.out.println(" ██║  ╚██║╚██████╔╝███████╔╝██║  ╚██║██║   ██║██║  ╚██║██████╔╝" );
		System.out.println(" ╚═╝   ╚═╝ ╚═════╝ ╚══════╝ ╚═╝   ╚═╝╚═╝   ╚═╝╚═╝   ╚═╝╚═════╝  ");
		System.out.println();
		System.out.println();
		System.out.println("                        1. 회원가입");
		System.out.println("                        2. 로그인");
		System.out.println("                        3. 아이디 찾기");
		System.out.println("                        4. 비번 찾기");
		System.out.println("                        9. 종료");
		System.out.println();
		
	}
	
	private boolean startMenuService() throws Exception {
		
		showMenuService();
		
		System.out.print("                       입력 >> ");
		String input = Main.SC.nextLine();
		
		if("9".equals(input)) {
			return true;
		}
		
		switch(input) {
		case "1" : startMyPageService(); break;
		case "2" : ms.Great(); break;
		case "3" : bs.showBasketlist(); break;
		case "4" : ps.showProduct(); break;
		case "5" : is.inquiry(); break;
		case "6" : rs.review(); break;
		default : System.out.println("잘못 입력했습니다."); 
		}
		
		return false;
		
	}
	
	private void showMenuService() throws Exception {
		
		
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║                                  ║ ");
		System.out.println("           ║             NO BRAND             ║ ");
		System.out.println("           ║                                  ║ ");
		System.out.println("           ╚══════════════════════════════════╝");	
		System.out.println("                      ╔═══════════╗         ");
		System.out.println("                         *" + me.showEventBoard()+ "*");
		System.out.println("                      ╚═══════════╝         ");
		System.out.println("                        1. 마이페이지");
		System.out.println("                        2. 좋아요");
		System.out.println("                        3. 장바구니");
		System.out.println("                        4. 쇼핑페이지");
		System.out.println("                        5. 고객문의센터");
		System.out.println("                        6. 리뷰 게시판");
		System.out.println("                        9. 뒤로가기");
		System.out.println();
		
	}
	
	private boolean startMyPageService() throws Exception {
		
		showMyPageService();
		System.out.println();
		System.out.print("                       입력 >> ");
		String input = Main.SC.nextLine();
		
		if("9".equals(input)) {
			return true;
		}
		
		switch(input) {
		case "1" : mp.showMemberInfo(); break;
		case "2" : ms.updateMemberInfo(); break;
		case "3" : mp.showOrderProduct(); break;
		case "4" : cp.couponShow(); break;
		case "5" : ms.withDraw(); break;
		default : System.out.println("잘못 입력했습니다."); 
		}
		
		return false;
		
	}
	
	private void showMyPageService() {

		System.out.println();
		System.out.println("           ╔══════════════════════════════════╗");
		System.out.println("           ║              MY PAGE             ║ ");
		System.out.println("           ╚══════════════════════════════════╝");	
		System.out.println("                        1. 회원정보 조회");
		System.out.println("                        2. 회원정보 수정");
		System.out.println("                        3. 구매내역");
		System.out.println("                        4. 쿠폰함");
		System.out.println("                        5. 회원탈퇴");
		System.out.println("                        9. 뒤로가기");
		
	}

}//class



