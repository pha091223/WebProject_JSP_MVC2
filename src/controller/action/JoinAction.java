package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String url = "./join.jsp";
		String command = request.getParameter("command");
		System.out.println(command + " 요청");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		MemberDTO mDTO = new MemberDTO();
		mDTO.setName(request.getParameter("name"));
		mDTO.setResident(request.getParameter("resident"));
		mDTO.setId(request.getParameter("id"));
		mDTO.setPwd(request.getParameter("pwd"));
		mDTO.setPhone(request.getParameter("phone"));
		mDTO.setEmail(request.getParameter("email"));
		
		if(mDAO.insert(mDTO)) {
			System.out.println("회원가입 완료.");
			request.setAttribute("sign", "true");
		} else {
			System.out.println("회원가입 실패.");
			request.setAttribute("sign", "false");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
