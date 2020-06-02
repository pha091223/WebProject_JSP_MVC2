package controller.action.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import dao.EventbbsDAO;
import dao.FreebbsDAO;
import dao.NoticebbsDAO;

public class BbsDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int del_no = Integer.valueOf((String)request.getParameter("no"));
		System.out.println("삭제 글 번호 : " + del_no);
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		if(command.indexOf("noticebbs")!=-1) {
			NoticebbsDAO nbDAO = NoticebbsDAO.getInstance();
			
			if(nbDAO.delete(del_no)) {
				System.out.println("글 삭제 성공.");
				request.setAttribute("delcheck", 1);
			} else {
				System.out.println("글 삭제 실패.");
			}
			
			url = "Servlet?command=noticebbs_list";
		} else if(command.indexOf("eventbbs")!=-1) {
			EventbbsDAO ebDAO = EventbbsDAO.getInstance();
			
			if(ebDAO.delete(del_no)) {
				System.out.println("글 삭제 성공.");
				request.setAttribute("delcheck", 1);
			} else {
				System.out.println("글 삭제 실패.");
			}
			
			url = "Servlet?command=eventbbs_list";
		} else if(command.indexOf("freebbs")!=-1) {
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			
			if(fbDAO.delete(del_no)) {
				System.out.println("글 삭제 성공.");
				request.setAttribute("delcheck", 1);
			} else {
				System.out.println("글 삭제 실패.");
			}
			
			url = "Servlet?command=freebbs_list";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
