package controller.action.course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import dao.CourseDAO;
import dto.CourseDTO;
import utill.PageNumber;

public class CourseRegisterFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String url = "./course-register.jsp";
		CourseDAO cDAO = CourseDAO.getInstance();
		
		int nowPage = 1;
		if(request.getParameter("page")!=null) {
			nowPage = Integer.valueOf(request.getParameter("page"));
		}
		
		int cSize = cDAO.allListCount();
		
		PageNumber pagemaker = new PageNumber();
		pagemaker.setPage(nowPage);
		pagemaker.setCount(cSize);
		
		List<CourseDTO> courseList = new ArrayList<>();;
		
		if(request.getAttribute("courseList")==null) {
			courseList = cDAO.allList(pagemaker.getNowPageStart(), pagemaker.getNowPageEnd());
			request.setAttribute("courseList", courseList);
		} else if(request.getAttribute("courseList")!=null) {
			request.setAttribute("courseList", request.getAttribute("courseList"));
		}
		
		if(courseList!=null) {
			request.setAttribute("pageMaker", pagemaker);
			request.setAttribute("courseCount", cSize);
		}
		
		HttpSession session = request.getSession();
		
		List<CourseDTO> courseBasket = new ArrayList<>();

		if(request.getAttribute("selectList")!=null) {
			request.setAttribute("selectList", request.getAttribute("selectList"));
			request.setAttribute("selectListSize", request.getAttribute("selectListSize"));
			
			session.setAttribute("sList", request.getAttribute("selectList"));
		} else {
			request.setAttribute("selectList", courseBasket);
			request.setAttribute("selectListSize", courseBasket.size());
			
			session.setAttribute("sList", courseBasket);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
