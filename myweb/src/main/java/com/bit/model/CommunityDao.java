package com.bit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommunityDao {
	String url ="";
	String user ="";
	String password="";
	//DB 연결
	public void getConnection(){
		url="jdbc:mysql://localhost:3306/lecture";
		user = System.getenv("MYSQL_USER");
		password = System.getenv("MYSQL_PW");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int pageCount(int limit) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select CEILING(COUNT(*)/?) as pageCount from ajaxcommunity";
		getConnection();
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, limit);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("pageCount");
			}
		}
		return 0;
	}
	//목록
	public List<CommunityVo> listAll(int limit, int offset,int page, int pageCount) throws SQLException{
		String sql = "SELECT * FROM ajaxcommunity where state='T' order by communityNo desc limit ? offset ?";
		System.out.println(sql);
		List<CommunityVo> list = new ArrayList<CommunityVo>();
		getConnection();
		System.out.println(url+","+user+","+password);
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CommunityVo bean = new CommunityVo();
				bean.setCommunityNo(rs.getInt("communityNo"));
				bean.setId(rs.getString("id"));
				bean.setPw(rs.getString("pw"));
				bean.setTitle(rs.getString("title"));
				bean.setHiredate(rs.getDate("hiredate"));
				bean.setCounts(rs.getInt("counts"));
				bean.setState(rs.getString("state"));
				bean.setPageCount(pageCount);
				bean.setPage(page);
				list.add(bean);
			}
		}
		System.out.println("리스트 : "+list.toString());
		return list;
	}
	
	/*
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	public List<CommunityVo> listAll(int limit, int offset,int page) {
		// TODO Auto-generated method stub
		String sql = "select communityNo, id, title, hiredate, counts from ajaxcommunity where state='T' ORDER BY communityNo desc limit ? offset ?";
//		System.out.println(limit+", "+ offset);
		List<CommunityVo> list = null;
		list = new ArrayList<CommunityVo>();
		getConnection();
		try {
			//페이지 수 가져오기
			int pageCount = 0;
			pstmt = conn.prepareStatement("select CEILING(count(*)/?) as pageCount from ajaxcommunity");
			pstmt.setInt(1, limit);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				pageCount = rs.getInt("pageCount");
				rs.close();
				pstmt.close();
			}
			
			//10개씩 가져오기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommunityVo bean = new CommunityVo();
				bean.setCommunityNo(rs.getInt("communityNo"));
				bean.setId(rs.getString("id"));
				bean.setPw(rs.getString("pw"));
				bean.setTitle(rs.getString("title"));
				bean.setHiredate(rs.getDate("hiredate"));
				bean.setCounts(rs.getInt("counts"));
				bean.setState(rs.getString("state"));
				bean.setPageCount(pageCount);
				bean.setPage(page);
//				System.out.println(dto.toString());
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return list;
	}
	 * */
	//상세보기
	public CommunityVo detail(int communityNo) throws SQLException {
		String sql = "SELECT * FROM ajaxcommunity WHERE communityNo = ?";
		getConnection();
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, communityNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				CommunityVo bean = new CommunityVo();
				bean.setCommunityNo(rs.getInt("communityNo"));
				bean.setId(rs.getString("id"));
				bean.setPw(rs.getString("pw"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setHiredate(rs.getDate("hiredate"));
				bean.setCounts(rs.getInt("counts"));
				return bean;
			}
		}
		return null;
	}
	//글작성
	public int write(String title, String content) throws SQLException {
		String sql = "INSERT INTO ajaxcommunity (id,pw,title,content,hiredate) values (?,?,?,?,now())";
		getConnection();
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, "test"); //임시
			pstmt.setString(2, "1234"); //임시
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			return pstmt.executeUpdate();
			
		}
	}
	
	public int edit(int communityNo,String title, String content ) throws SQLException {
		String sql="update ajaxcommunity set title=?, content=? where communityNo=?";
		getConnection();
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(3, communityNo);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			return pstmt.executeUpdate();
		}
	}
	public int delete(int communityNo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update ajaxcommunity set state='F' where communityNo=?";
		getConnection();
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, communityNo);
			return pstmt.executeUpdate();
		}
	}
	public int countsPlus(int communityNo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update ajaxcommunity set counts=counts+1 where communityNo=?";
		getConnection();
		try(
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, communityNo);
			return pstmt.executeUpdate();
		}
	}

}
