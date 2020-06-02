package controller.action.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import dao.EventbbsDAO;
import dao.FreebbsDAO;
import dao.NoticebbsDAO;
import dto.EventbbsDTO;
import dto.FreebbsDTO;
import dto.NoticebbsDTO;

public class BbsModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int mod_no = Integer.valueOf((String)request.getParameter("no"));
		System.out.println("수정 글 번호 : " + mod_no);
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		if(command.indexOf("noticebbs")!=-1) {
			NoticebbsDAO nbDAO = NoticebbsDAO.getInstance();
			NoticebbsDTO nbDTO = nbDAO.selectOne(mod_no);
			
			request.setAttribute("no", nbDTO.getNo());
			request.setAttribute("title", nbDTO.getTitle());
			request.setAttribute("content", nbDTO.getContent());
			request.setAttribute("fname", nbDTO.getFname());
			request.setAttribute("pwd", nbDTO.getPwd());
			
			String fname = nbDTO.getFname();
			
			if(fname!=null) {
				String fileForm = fname.substring(fname.lastIndexOf("."), fname.length());
				request.setAttribute("fileform", fileForm);
			}
			
			url = "noticebbs-write-modify.jsp";
		} else if(command.indexOf("eventbbs")!=-1) {
			EventbbsDAO ebDAO = EventbbsDAO.getInstance();
			EventbbsDTO ebDTO = ebDAO.selectOne(mod_no);
			
			request.setAttribute("no", ebDTO.getNo());
			request.setAttribute("title", ebDTO.getTitle());
			request.setAttribute("content", ebDTO.getContent());
			request.setAttribute("fname", ebDTO.getFname());
			request.setAttribute("pwd", ebDTO.getPwd());
			
			String fname = ebDTO.getFname();
			
			if(fname!=null) {
				String fileForm = fname.substring(fname.lastIndexOf("."), fname.length());
				request.setAttribute("fileform", fileForm);
			}
			
			url = "eventbbs-write-modify.jsp";
		} else if(command.indexOf("freebbs")!=-1) {		
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			FreebbsDTO fbDTO = fbDAO.selectOne(mod_no);
			
			request.setAttribute("no", fbDTO.getNo());
			request.setAttribute("title", fbDTO.getTitle());
			request.setAttribute("content", fbDTO.getContent());
			request.setAttribute("fname", fbDTO.getFname());
			request.setAttribute("pwd", fbDTO.getPwd());
			
			String fname = fbDTO.getFname();
			
			if(fname!=null) {
				String fileForm = fname.substring(fname.lastIndexOf("."), fname.length());
				request.setAttribute("fileform", fileForm);
			}
			
			url = "freebbs-write-modify.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
