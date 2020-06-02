package controller.action.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;

public class BbsWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		if(command.indexOf("noticebbs")!=-1) {
			url = "noticebbs-write.jsp";
		} else if(command.indexOf("eventbbs")!=-1) {
			url = "eventbbs-write.jsp";
		} else if(command.indexOf("freebbs")!=-1) {
			if(command.indexOf("reply")!=-1) {
				url = "freebbs-write-reply.jsp";						
			} else {
				url = "freebbs-write.jsp";
			}
		} else if(command.equals("qna")) {
			url = "qna-write.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
