package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "Servlet?command=home_list";
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("nowuser");
		session.removeAttribute("nowuser_Id");
		session.removeAttribute("cList");
		
		session.invalidate();
		
		if(request.getSession(false)==null) {
			System.out.println("로그아웃 성공.");
			System.out.println("세션 접속자 삭제.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
