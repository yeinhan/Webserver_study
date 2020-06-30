package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCBoardDto;

import common.JDBCTemplate;

public class MVCBoardDao extends JDBCTemplate{
	public List<MVCBoardDto> selectAll(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		
		String sql = "SELECT * FROM MVCBOARD";
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MVCBoardDto tmp = new MVCBoardDto();
				tmp.setSeq(rs.getInt(1));
				tmp.setWriter(rs.getString(2));
				tmp.setTitle(rs.getString(3));
				tmp.setContent(rs.getString(4));
				tmp.setRegdate(rs.getDate(5));
				
				list.add(tmp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
		}
		
		return list;
	}
}
