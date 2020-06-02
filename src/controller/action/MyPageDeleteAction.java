package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;

public class MyPageDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("nowuser_Id");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		if(mDAO.delete(id)) {
			System.out.println("회원 탈퇴 완료.");
			
			url = "Servlet?command=home_list";
			
			session.removeAttribute("nowuser");
			session.removeAttribute("nowuser_Id");
			session.removeAttribute("cList");
			
			session.invalidate();
			
			if(request.getSession(false)==null) {
				System.out.println("세션 접속자 삭제.");
			}
		} else {
			System.out.println("회원 탈퇴 실패.");
			
			url = "Servlet?command=mypage";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
