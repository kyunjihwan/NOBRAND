package com.ljh.inquirycenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.nobrand.main.Main;

public class InquiryInput {
	
	private InquiryService is = new InquiryService();
	
	public InquiryData writeBoardInput() throws Exception {
		System.out.println();
		System.out.println("                   ───── 고객센터게시글 작성 ─────");
		System.out.println("                        제목(뒤로가기:9)");
		System.out.print("                       입력 >> ");
		
		String title = Main.SC.nextLine();
		if(title.equals("9")) {
			System.out.println("                        작성 취소");
			is.startService();
		}

		System.out.println("                        내용(뒤로가기:9)");
		System.out.print("                       입력 >> ");
		String content = Main.SC.nextLine();
		if(content.equals("9")) {
			System.out.println("                        작성 취소");
			is.startService();
		}
		InquiryData data = new InquiryData();
		data.setTitle(title);
		data.setContent(content);
		return data;
	}
	
	public InquiryData deleteBoardInput(Connection conn) throws Exception {
		InquiryData data = new InquiryData();
		while(true) {
			System.out.println("\n                      삭제할 글의 번호 ");
			System.out.print("                       입력 >> ");
			String no = Main.SC.nextLine();
			if(no.equals("9")) {
				break;
			}
			data.setNo(no);
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,no);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				break;
			}else {
				System.out.println("해당하는 글이 없습니다. 다시 입력해주세요.");
			}
			conn.close();
		}
		return data;
	}
}
