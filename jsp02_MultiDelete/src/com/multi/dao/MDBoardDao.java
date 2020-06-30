package com.multi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.multi.dto.MDBoardDto;

import common.JDBCTemplate;

public class MDBoardDao extends JDBCTemplate {
	
	//게시판 목록
	public List<MDBoardDto> selectAll(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MDBoardDto> res = new ArrayList<MDBoardDto>();
		
		String sql = "SELECT * FROM MDBOARD ORDER BY SEQ DESC";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비" +sql);
			
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query");
			
			while(rs.next()) {
				MDBoardDto tmp = new MDBoardDto();
				tmp.setSeq(rs.getInt(1));
				tmp.setWriter(rs.getString(2));
				tmp.setTitle(rs.getString(3));
				tmp.setContent(rs.getString(4));
				tmp.setRegdate(rs.getDate(5));
				
				res.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	public int insert(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res=0;
		String sql = "INSERT INTO MDBOARD VALUES(MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03. query 준비: " +sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("3/4 실행 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
			
		}
		
		return res;
		
	}
	
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MDBoardDto res = new MDBoardDto();
		String sql = "SELECT * FROM MDBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3.quety 준비: "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				res.setSeq(rs.getInt(1));
				res.setWriter(rs.getString(2));
				res.setTitle(rs.getString(3));
				res.setContent(rs.getString(4));
				res.setRegdate(rs.getDate(5));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료");
		}
		return res;
	}
	
	public int update(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res= 0;
		String sql = " UPDATE MDBOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2,  dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03. query 실행 준비");
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("3/4 단계  오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		
		return res;
	}
	
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03. query 준비: "+sql);
			
			res=pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println(("05. db 종료\n"));
		}
		
		
		return res;
	}
	
	
	//다중삭제
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement  pstm = null;
		int res =0;
		int[] cnt = null;
		String sql = " DELETE FROM MDBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			for(int i=0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				
				pstm.addBatch();
				System.out.println("03. query 준비: " +sql +"(삭제할 번호: "+seq[i]+")");
			}
			cnt = pstm.executeBatch();
			System.out.println("04. query 실행 및 리턴");
			
			for(int i=0; i<cnt.length; i++) {
				if(cnt [i] == -2) {	//성공이면
					res++;
				}
			}
			if(seq.length == res) {	//우리가 삭제하고자 하는 게시글 번호 seq
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
}
