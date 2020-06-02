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

public class CourseRegisterBasketAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");
		System.out.println("* 수강신청 담기 명령 종류 : " + command);
		
		String url = null;
		
		HttpSession session = request.getSession();
		
		List<CourseDTO> select_cList = (ArrayList<CourseDTO>)session.getAttribute("sList");
		String[] course_no = null;
		
		// List<CourseDTO> select_cList = new ArrayList<>();
		
		CourseDAO cDAO = CourseDAO.getInstance();

		if(command.indexOf("add")!=-1) {
			course_no = request.getParameterValues("course");
			
			CourseDTO crDTO = null;
			
			if(course_no!=null) {
				for(int j=0; j<course_no.length; j++) {
					crDTO = cDAO.selectOne(Integer.valueOf(course_no[j]));
					if(crDTO!=null) {
						select_cList.add(crDTO);
					}
				}
			}
		} else if(command.indexOf("del")!=-1) {
			course_no = request.getParameterValues("course_select");
			
			if(course_no!=null) {
				for(int i=0; i<course_no.length; i++) {
					for(int j=0; j<select_cList.size(); j++) {
						if(Integer.valueOf(course_no[i])==(select_cList.get(j).getNo())) {
							select_cList.remove(j);
							break;
						}
					}
				}
			}
		}
		
		request.setAttribute("selectList", select_cList);
		request.setAttribute("selectListSize", select_cList.size());
		
		url = "Servlet?command=course_register_form";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
