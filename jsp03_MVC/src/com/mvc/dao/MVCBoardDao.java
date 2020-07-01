package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			System.out.println("03. query 준비" +sql);
			rs = stmt.executeQuery(sql);
			System.out.println("04. query");
			
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
			System.out.println("05. db 종료\n");
		}
		
		return list;
	}
	
	public MVCBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCBoardDto res = new MVCBoardDto();
		String sql = "SELECT * FROM MVCBOARD WHERE SEQ=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비" +sql);
			pstm.setInt(1, seq);
			
			rs= pstm.executeQuery();
			System.out.println("04. query");
			
			while(rs.next()) {
				res.setSeq(rs.getInt("SEQ"));
				res.setWriter(rs.getString("WRITER"));
				res.setTitle(rs.getString("TITLE"));
				res.setContent(rs.getString(4));
				res.setRegdate(rs.getDate(5));
				
				
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실행 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	//글 등록
	public int insert(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		String sql = "INSERT INTO MVCBOARD VALUES(MVCBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03. query 준비: "+sql);
			
			res=pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	public int update(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		String sql = "UPDATE MVCBOARD SET TITLE=?, CONTENT=? WHERE SEQ=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실행오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
}
