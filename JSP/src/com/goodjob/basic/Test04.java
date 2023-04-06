package com.goodjob.basic;

import java.sql.*;
import java.util.Scanner;

//	JDBC를 사용하여 이름, 이메일을 입력받아 Account에 정보를 추가하는 Test04클래스를 만드시오
//	회원번호는 acc_seq를 사용하고, 적립금은 1000원, 등록일자는 SYSDATE로 지정

public class Test04 {

	public static void main(String[] args) {
		
		String name, email;
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		name = sc.next();
		System.out.print("이메일 : ");
		email = sc.next();
		
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "INSERT INTO account VALUES('"+name+"', acc_seq.NEXTVAL, '"+email+"', 1000, SYSDATE)";

				
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// 커넥션 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			// 쿼리문 준비
			ps = con.prepareStatement(sql);
			
			// 실행
			ps.execute();
			System.out.println("레코드 추가 완료");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
	}

}
