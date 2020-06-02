package controller.action.bbs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import dao.CommentDAO;
import dao.EventbbsDAO;
import dao.FreebbsDAO;
import dao.NoticebbsDAO;
import dto.CommentDTO;
import dto.EventbbsDTO;
import dto.FreebbsDTO;
import dto.NoticebbsDTO;

public class BbsViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String nowuser_Id = (String)session.getAttribute("nowuser_Id");
		
		int number = Integer.valueOf(request.getParameter("no"));
		System.out.println("글 번호 : " + number);
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		if(command.indexOf("noticebbs")!=-1) {
			NoticebbsDAO nbDAO = NoticebbsDAO.getInstance();
			
			nbDAO.addReadCount(number);
			NoticebbsDTO nbDTO = nbDAO.selectOne(number);
			
			request.setAttribute("one", nbDTO);
			
			String fname = nbDTO.getFname();
			String fileForm = null;
			
			if(fname!=null) {
				fileForm = fname.substring(fname.lastIndexOf("."), fname.length());
				System.out.println("* fileFrom : " + fileForm);
				request.setAttribute("fileform", fileForm);
			}
			
			url = "/noticebbs-view.jsp";
		} else if(command.indexOf("eventbbs")!=-1) {
			EventbbsDAO ebDAO = EventbbsDAO.getInstance();
			
			ebDAO.addReadCount(number);
			EventbbsDTO ebDTO = ebDAO.selectOne(number);
			
			request.setAttribute("one", ebDTO);
			
			String fname = ebDTO.getFname();
			String fileForm = null;
			
			if(fname!=null) {
				fileForm = fname.substring(fname.lastIndexOf("."), fname.length());
				System.out.println("* fileFrom : " + fileForm);
				request.setAttribute("fileform", fileForm);
			}
			
			url = "/eventbbs-view.jsp";
		} else if(command.indexOf("freebbs")!=-1) {
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			
			fbDAO.addReadCount(number);
			FreebbsDTO fbDTO = fbDAO.selectOne(number);
			
			request.setAttribute("one", fbDTO);
			
			if(nowuser_Id!=null) {
				if(nowuser_Id.equals(fbDTO.getId())) {
					request.setAttribute("idcheck", true);
				} else if(!nowuser_Id.equals(fbDTO.getId())) {
					request.setAttribute("idcheck", false);
				}
			} else if(nowuser_Id==null) {
				request.setAttribute("idcheck", false);
			}
			
			String fname = fbDTO.getFname();
			String fileForm = null;
			
			if(fname!=null) {
				fileForm = fname.substring(fname.lastIndexOf("."), fname.length());
				System.out.println("* fileFrom : " + fileForm);
				request.setAttribute("fileform", fileForm);
			}
			
			// Comment
			CommentDAO cDAO = CommentDAO.getInstance();
			
			List<CommentDTO> cList = cDAO.allList(0, 0, number, "free");
			int allCommentCount = cDAO.allListCount(number);
			
			if(cList!=null) {
				request.setAttribute("commentList", cList);
				request.setAttribute("commentcount", allCommentCount);
			}
			
			url = "/freebbs-view.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
