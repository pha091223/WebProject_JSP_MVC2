package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String url = null;
		String command = request.getParameter("command");
		System.out.println(command + " 요청");
		
		String input_Id = request.getParameter("id");
		String input_Pwd = request.getParameter("pwd");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		MemberDTO mDTO = mDAO.login(input_Id, input_Pwd);
		
		if(mDTO==null) {
			System.out.println("ID 없음.");
			request.setAttribute("chk", 0);
			url = "./login.jsp";
		} else if(mDTO!=null) {
			if(mDTO.getPwd()==null) {
				System.out.println("비밀번호 틀림.");
				request.setAttribute("chk", 1);
				url = "./login.jsp";
			} else {
				System.out.println("로그인 성공.");
				
				HttpSession session = request.getSession();
				session.setAttribute("nowuser", mDTO.getName());
				session.setAttribute("nowuser_Id", mDTO.getId());
				
				url = "Servlet?command=home_list";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
