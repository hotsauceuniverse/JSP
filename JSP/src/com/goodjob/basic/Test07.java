package com.goodjob.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test07 {

	public static void main(String[] args) {
		
		String id = "myJsp";
		String password = "jsppassword";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
				
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 커넥션 준비
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, password);
			
			String sql = " SELECT st_name, st_avg FROM student";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			boolean result = rs.next();		// 레코드가 1개라도 있으면 true, 없으면 false
											// 레코드를 확인하기 위해서는 최소 1회 next()를 실행해야 함
											// 실행하지 않은 초기 커서는 아무것도 가리키지 않기 때문이다
			while(rs.next()) {		// 레코드가 남아있을 동안 반복
				String name;
				double avg;
				
				name = rs.getString("st_name");		// 현재 위치 레코드의 st_name항목의 값을 String형태로 보여줌
				avg = rs.getDouble("st_avg");		// 현재 위치 레코드의 st_avg항목의 값을 Double형태로 보여줌
				
				System.out.println(name + "/" + avg + "점");
			}
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
