package controller.action.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import dao.CourseRegDAO;
import dto.CourseDTO;
import dto.CourseRegDTO;

public class CourseRegisterAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("* 명령 : " + command);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("nowuser_Id");
		
		List<CourseDTO> select_cList = (List<CourseDTO>)session.getAttribute("sList");
		
		String input_course[] = request.getParameterValues("course_select");
		String url = null;
		
		// 수강 정보 Table에 저장
		CourseRegDAO crDAO = CourseRegDAO.getInstance();
		
		for(int i=0; i<input_course.length; i++) {
			CourseRegDTO crDTO = new CourseRegDTO();
			crDTO.setCno(Integer.valueOf(input_course[i]));
			crDTO.setId(id);
			
			if(crDAO.insert(crDTO)) {
				System.out.println(id + "/" + Integer.valueOf(input_course[i]) + " 강좌 신청 성공.");
				request.setAttribute("reg_check", "true");
				
				for(int j=0; j<select_cList.size(); j++) {
					if(Integer.valueOf(input_course[i])==(select_cList.get(j).getNo())) {
						select_cList.remove(j);
						break;
					}
				}
			} else {
				System.out.println(id + "/" + Integer.valueOf(input_course[i]) + " 강좌 신청 실패.");
				request.setAttribute("reg_check", "false");
			}
		}
		
		url = "Servlet?command=course_register_form";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
