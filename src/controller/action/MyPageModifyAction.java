package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

public class MyPageModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		MemberDTO mDTO = new MemberDTO();
		mDTO.setId(request.getParameter("id"));
		mDTO.setPwd(request.getParameter("pwd"));
		mDTO.setPhone(request.getParameter("phone"));
		mDTO.setEmail(request.getParameter("email"));
		
		if(mDAO.update(mDTO)) {
			System.out.println("회원 정보 수정 완료.");
			request.setAttribute("sign", "true");
		} else {
			System.out.println("회원 정보 수정 실패.");
			request.setAttribute("sign", "false");
		}
		
		String url = "Servlet?command=mypage";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
