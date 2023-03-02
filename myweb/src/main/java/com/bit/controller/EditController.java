package com.bit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.CommunityDao;

@WebServlet("/api/edit")
public class EditController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		//System.out.println("수정 요청 들어옴");
		int communityNo = Integer.parseInt(req.getParameter("communityNo"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		//System.out.println(communityNo+" : "+title+" : "+content);
		CommunityDao dao = new CommunityDao();
		try {
			dao.edit(communityNo, title, content);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
