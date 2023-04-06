package com.goodjob.basic;

import java.sql.*;

//	JDBC를 사용하여 Account 테이블에 3개의 레코드를 임의로 추가하는 Test03클래스를 만들으시오
//	단 회원번호는 acc_seq를 사용하시오

public class Test03 {

	public static void main(String[] args) {
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql_1 = "INSERT INTO account VALUES('세영' ,acc_seq.NEXTVAL ,'seyoung@naver.com' ,100 ,SYSDATE )";
		String sql_2 = "INSERT INTO account VALUES('세영1' ,acc_seq.NEXTVAL ,'seyoung1@naver.com' ,101 ,SYSDATE )";
		String sql_3 = "INSERT INTO account VALUES('세영2' ,acc_seq.NEXTVAL ,'seyoung2@naver.com' ,102 ,SYSDATE )";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			ps = con.prepareStatement(sql_1);
			ps.execute();
			
			ps = con.prepareStatement(sql_2);
			ps.execute();
			
			ps = con.prepareStatement(sql_3);
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
	}
}
