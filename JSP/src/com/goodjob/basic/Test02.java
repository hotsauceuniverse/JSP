package com.goodjob.basic;

import java.sql.*;

//	JDBC를 사용하여 회원들의 정보를 저장할 Account 테이블을 생성하는 Test02 클래스를 만들고
//	이름, 이메일, 적립금, 번호, 가입날짜 항목이 포함되어야 하며
//	acc_seq라는 시퀀스도 생성하라

public class Test02 {

	public static void main(String[] args) {
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "CREATE TABLE account("
				+ "name VARCHAR2(20),"
				+ "no NUMBER,"
				+ "email VARCHAR2(20),"
				+ "point NUMBER,"
				+ "reg DATE"
				+ ")" ;
		
		// 초기화
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			ps = con.prepareStatement(sql);
			ps.execute();
			System.out.println("Account 테이블 생성 완료");
			
			ps = con.prepareStatement("CREATE SEQUENCE acc_seq NOCACHE");
			ps.execute();
			System.out.println("acc_seq 시퀀스 생성 완료");
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {	// null pointer 처리 
					ps.close();
				}
				if(con != null ) {	// null pointer 처리
					con.close();
				}		
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
