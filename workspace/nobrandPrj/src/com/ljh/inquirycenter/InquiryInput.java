package com.ljh.inquirycenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.main.Main;

public class InquiryInput {
	
	private InquiryService is = new InquiryService();
	
	public InquiryData writeBoardInput() throws Exception {
		System.out.print("\n제목(뒤로가기:9) : ");
		String title = Main.SC.next();
		if(title.equals("9")) {
			System.out.println("작성 취소");
			is.startService();
		}
		Main.SC.nextLine();
		System.out.print("내용(뒤로가기:9) : ");
		String content = Main.SC.nextLine();
		if(content.equals("9")) {
			System.out.println("작성 취소");
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
			System.out.print("\n삭제할 글의 번호를 입력하세요 : ");
			int no = Main.SC.nextInt();
			data.setNo(no);
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT INQ_NO FROM INQUIRYCENTER WHERE INQ_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
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