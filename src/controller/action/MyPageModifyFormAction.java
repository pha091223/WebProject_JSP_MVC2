package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;

public class MyPageModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("nowuser_Id");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		MemberDTO mDTO = mDAO.selectOne(id);
		
		String resident = mDTO.getResident();
		String front = resident.substring(0, 7);
		String back = "******";
		String view_resident = front + back;
		
		mDTO.setResident(view_resident);
		
		request.setAttribute("now", mDTO);
		
		String url = "./mypage.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
