package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.CommunityDao;

@WebServlet("/api/list")
public class ListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin","*");
		resp.setHeader("Access-Control-Allow-Methods","GET");
		System.out.println("리스트 요청옴!!!!!!!!!!!!!!!");
		//페이징
		String sPage = req.getParameter("page");
		int page;
		if(sPage==null || "null".equals(sPage)) {
			page=1;
		}else {
			page = Integer.parseInt(sPage);
		}
		int limit = 5;
		int offset = (limit*(page-1));
		
		CommunityDao dao = new CommunityDao();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		//총 페이지 수
		int pageCount = 0;
		try {
			pageCount = dao.pageCount(limit);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 목록
		System.out.println("pageCount : "+pageCount);
		try(PrintWriter out = resp.getWriter();){
			out.print("{\"community\":");
			try {
				out.print(dao.listAll(limit, offset, page,pageCount).toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print("[]");
			}
			out.print("}");
		}
	}
}
