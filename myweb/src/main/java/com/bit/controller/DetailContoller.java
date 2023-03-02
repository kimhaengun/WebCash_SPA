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
import com.bit.model.CommunityVo;

@WebServlet("/api/detail")
public class DetailContoller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		//System.out.println("디테일 요청 들어옴");
		int communityNo = Integer.parseInt(req.getParameter("communityNo"));
		//System.out.println(communityNo);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		CommunityDao dao = new CommunityDao();
		try {
			dao.countsPlus(communityNo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try(PrintWriter out=resp.getWriter();){
			out.print("{\"community\":[");
			CommunityVo bean = dao.detail(communityNo);
			out.print(bean);
			out.print("]}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
