package com.bit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.CommunityDao;

@WebServlet("/api/write")
public class WriteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		//System.out.println("글쓰기 등록 요청옴");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		//System.out.println(title+" : "+content);
		CommunityDao dao = new CommunityDao();
		try {
			int result =dao.write(title, content);
			if(result == 1 ) {
				resp.setStatus(resp.SC_CREATED); //201
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendError(resp.SC_BAD_REQUEST);
		}
	}
}
