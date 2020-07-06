package com.mvc.dao;

import java.util.List;

import com.mvc.dto.MyMVCDto;

public interface MyMVCDao {
	
	String selectAllSql = " SELECT * FROM MYMVCBOARD ORDER BY SEQ DESC ";
	String selectOneSql=" ";
	String insertSql="";
	String updateSql="";
	String deleeteSql="";
	
	public List<MyMVCDto> selectAll();
	public MyMVCDto selectOne(int seq);
	public boolean insert(MyMVCDto dto);
	public boolean update(MyMVCDto dto);
	public boolean delete(int seq);
}
