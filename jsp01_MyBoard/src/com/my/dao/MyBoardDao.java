package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

public class MyBoardDao {
	
	Connection con = null;
	public MyBoardDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			System.out.println("01. driver 연결 실패");
			e.printStackTrace();
		}
		
	}
	
	//전체출력
	public List<MyBoardDto> selectAll(){
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02. 계정 연결 실패");
			e.printStackTrace();
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		List<MyBoardDto> res = new ArrayList<MyBoardDto>();
		
		String sql = "SELECT * FROM MYBOARD";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비: "+ sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			while(rs.next()) {
				MyBoardDto dto = new MyBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				
				res.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("05. db 종료\n");
			} catch (SQLException e) {
				System.out.println("05. db 종료 에러");
				e.printStackTrace();
			}

		}
		
		return res;
	}
	
	public int insert(MyBoardDto dto) {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KH","KH");
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			System.out.println("02 계정연결 실패");
			e.printStackTrace();
		}
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("03. query 준비: sql");
			
			res= pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();
				System.out.println("05. db종료");
			} catch (SQLException e) {
				System.out.println("05. db종료 에러");
				e.printStackTrace();
			}
			
		}
		
		return res;
	}
}
