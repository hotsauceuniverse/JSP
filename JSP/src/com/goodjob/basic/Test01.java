package com.goodjob.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//자바로 쿼리문 실행
//1. 연결(Connection)
//2. 쿼리문 준비(PreparedStatement)
//3. 실행(execute())
//4. 정리(close())

public class Test01 {
	
	public static void main(String[] args) {
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");	// Class.forName(클래스명) => 클래스를 찾겠다
		Connection connection = DriverManager.getConnection(url, id, password);		// Connection : 다리 역할의 객체
		
		PreparedStatement ps = connection.prepareStatement("CREATE SEQUENCE student_seq NOCACHE");	// 쿼리문 준비 작업
		ps.execute();	// 실행
		
		ps.close();	// ps 정리
		connection.close();	// 통로 정리
		
		System.out.println("시퀀스 생성 완료");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	// OracleDriver 클래스가 없을 때 발생
		} catch (SQLException e) {
			e.printStackTrace();	// 커넥션(DB와 연동)하는 과정에서 예외 발생
		}
	}
}

