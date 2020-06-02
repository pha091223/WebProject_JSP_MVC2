package controller.action.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import dao.CommentDAO;
import dto.CommentDTO;

public class BbsCommentWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int no = Integer.valueOf((String)request.getParameter("no"));
		
		CommentDAO cDAO = CommentDAO.getInstance();
		
		CommentDTO cDTO = new CommentDTO();
		
		cDTO.setCno(cDAO.getWriteNo());
		cDTO.setNo(no);
		cDTO.setId(request.getParameter("id"));
		cDTO.setName(request.getParameter("name"));
		cDTO.setComments(request.getParameter("comments"));
		cDTO.setType(request.getParameter("type"));
		
		if(cDAO.insert(cDTO)) {
			System.out.println("댓글 등록 완료.");
		} else {
			System.out.println("댓글 등록 실패.");
		}
		
		new BbsViewAction().execute(request, response);
		
	}

}
