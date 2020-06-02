package controller.action.bbs;

import java.io.IOException;
import java.util.List;

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
import utill.PageNumber;

public class BbsListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		int nowPage = 1;
		if(request.getParameter("page")!=null) {
			nowPage = Integer.valueOf(request.getParameter("page"));
		}
		
		PageNumber pagemaker = new PageNumber();
		pagemaker.setPage(nowPage);
		
		NoticebbsDAO nbDAO = NoticebbsDAO.getInstance();
		EventbbsDAO ebDAO = EventbbsDAO.getInstance();
		
		if(command.indexOf("home")!=-1) {
			url = "./home.jsp";
			
			List<NoticebbsDTO> homeNoticeList = nbDAO.homeList();
			List<EventbbsDTO> homeEventList = ebDAO.homeList();
			
			if(homeNoticeList!=null) {
				request.setAttribute("noticeList", homeNoticeList);
			}
			if(homeEventList!=null) {
				request.setAttribute("eventList", homeEventList);
			}
		} else if(command.indexOf("noticebbs")!=-1) {
			url = "./noticebbs.jsp";
			
			pagemaker.setCount(nbDAO.allListCount());
			
			List<NoticebbsDTO> boardList = nbDAO.allList(pagemaker.getNowPageStart(), pagemaker.getNowPageEnd());
			
			if(boardList!=null) {
				request.setAttribute("boardList", boardList);
				request.setAttribute("pageMaker", pagemaker);
			}
		} else if(command.indexOf("eventbbs")!=-1) {
			url = "./eventbbs.jsp";
			
			pagemaker.setCount(nbDAO.allListCount());
			
			List<EventbbsDTO> boardList = ebDAO.allList(pagemaker.getNowPageStart(), pagemaker.getNowPageEnd());
			
			if(boardList!=null) {
				request.setAttribute("boardList", boardList);
				request.setAttribute("pageMaker", pagemaker);
			}
		} else if(command.indexOf("freebbs")!=-1) {
			url = "./freebbs.jsp";
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			
			pagemaker.setCount(fbDAO.allListCount());
			
			List<FreebbsDTO> boardList = fbDAO.allList(pagemaker.getNowPageStart(), pagemaker.getNowPageEnd());
			
			if(boardList!=null) {
				request.setAttribute("boardList", boardList);
				request.setAttribute("pageMaker", pagemaker);
			}
		} else if(command.indexOf("gallery")!=-1) {
			url = "gallery.jsp";
			
			// 갤러리 목록 가져오기
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
