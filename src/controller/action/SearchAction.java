package controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;
import dto.CourseDTO;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("* 검색 목표 : " + command);
		String url = null;
		
		CourseDAO cDAO = CourseDAO.getInstance();
		
		List<CourseDTO> courseList_search = null;
		
		if(command.indexOf("course")!=-1) {
			if(command.equals("search_course_name")) {
				String sName = request.getParameter("search_name");
				
				System.out.println("* 검색어 : " + sName);
				
				List<CourseDTO> courseList = cDAO.allList();
				courseList_search = new ArrayList<>();
				
				for(CourseDTO c : courseList) {
					String cName = c.getName();
					if(cName.contains(sName)) {
						courseList_search.add(c);
					}
				}
			} else if(command.equals("search_course_day")) {
				String sYear = request.getParameter("search_year");
				String sMonth = request.getParameter("search_month");
				
				courseList_search = cDAO.searchList(sYear, sMonth);
			}
		}
		
		if(courseList_search.size()==0) {
			request.setAttribute("search", "false");
		} else {
			request.setAttribute("search", "true");
			request.setAttribute("courseList", courseList_search);
		}
		
		url = "course-register.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
