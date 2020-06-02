package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

public class JoinIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String url = "./join.jsp";
		String input_Id = request.getParameter("id");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		if(mDAO.idChk(input_Id)) {
			System.out.println("아이디 사용불가.");
			request.setAttribute("chk", 1);
		} else {
			System.out.println("아이디 사용가능.");
			request.setAttribute("chk", 0);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
