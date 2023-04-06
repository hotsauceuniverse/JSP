package com.goodjob.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//	1. 평균점수가 80점 이상인 학생들의 이름, 국어, 번호 평균점수 조회
//	2. 김씨 성을 가진 학생들의 이름, 번호, 연락처를 이름 가나다순으로 조회
//	3. 가장 나중에 등록한 학생을 삭제
//	4. 평균 점수가 가장 높은 학생의 이름과 평균 점수 조회

public class Test08 {

	public static void main(String[] args) {
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
				
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			// 커넥션 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			sql = "SELECT st_name, st_kr, st_no, st_avg FROM student WHERE st_avg>=80";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			String name;
			int kr;
			int no;
			double avg;
			
			
			System.out.println("======1번문제======");
			while(rs.next()) {
				name = rs.getString("st_name");
				kr = rs.getInt("st_kr");
				no = rs.getInt("st_no");
				avg = rs.getDouble("st_avg");
					
				
				System.out.println("이믈 : " + name);
				System.out.println("국어 : " + kr);
				System.out.println("번호 : " + no);
				System.out.println("평균점수 : " + avg);
				System.out.println();
			}
			rs.close();
			ps.close();
			
			
			System.out.println("======2번문제======");
			sql = "SELECT st_name, st_no, st_tel FROM student WHERE st_name LIKE '김%' ORDER BY st_name ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("이름 : " + rs.getString("st_name"));
				System.out.println("학번 : " + rs.getString("st_no"));
				System.out.println("연락처 : " + rs.getString("st_tel"));
				System.out.println();
			}
			rs.close();
			ps.close();
			
			
			System.out.println("======3번문제======");
			sql = "DELETE FROM student WHERE st_redgate = (SELECT MAX(st_redgate) FROM student)";
			ps = con.prepareStatement(sql);
			// System.out.println(ps.executeUpdate() + " 개 레코드가 삭제 됨");
			ps.close();
			
			
			System.out.println("======4번문제======");
			sql = "SELECT st_name, st_avg FROM student WHERE st_avg = (SELECT MAX(st_avg) FROM student)";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
	
			rs.next();
			System.out.println("1등의 이름 : " + rs.getString("st_name"));
			System.out.println("1등의 평균점수 : " + rs.getDouble("st_avg"));
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
