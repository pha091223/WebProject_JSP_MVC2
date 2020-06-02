package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String name = (String)session.getAttribute("nowuser");

		if(name!=null) {
			System.out.println("세션 접속자 설정 : " + name);
			session.setAttribute("nowuser", name);
		} else {
			System.out.println("세션 접속자 없음.");
		}
		
		String command = request.getParameter("command");
		System.out.println("Servlet 요청 받음 : " + command);
		
		// File upload
		if(command.contains("bbs_write")) {
			String savePath = "./bbs/upload";
			ServletContext context = getServletContext();
			String uploadFilePath = context.getRealPath(savePath);
			request.setAttribute("uploadFilePath", uploadFilePath);
			
			System.out.println("* Servlet file address : " + uploadFilePath);
		}

		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);

		if(action!=null) {
			action.execute(request, response);
		}
	}

}
