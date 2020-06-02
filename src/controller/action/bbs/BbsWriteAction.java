package controller.action.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.action.Action;
import dao.EventbbsDAO;
import dao.FreebbsDAO;
import dao.NoticebbsDAO;
import dao.QnaDAO;
import dto.EventbbsDTO;
import dto.FreebbsDTO;
import dto.NoticebbsDTO;
import dto.QnaDTO;

public class BbsWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// File upload
		int uploadFileSizeLimit = 20 * 1024 * 1024;
		String encType = "UTF-8";
		String uploadFilePath = (String)request.getAttribute("uploadFilePath");

		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit,
					encType, new DefaultFileRenamePolicy());
		String fileName = multi.getFilesystemName("uploadFile");
		
		System.out.println("* file address : " + uploadFilePath);
		System.out.println("* file name : " + fileName);
		
		if(fileName==null) {
			System.out.print("업로드 파일 없음.");
		} else {
			System.out.println("업로드 파일 : " + fileName);
		}
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		if(command.indexOf("noticebbs")!=-1) {
			NoticebbsDAO nbDAO = NoticebbsDAO.getInstance();
			
			NoticebbsDTO nbDTO = new NoticebbsDTO();
			
			nbDTO.setNo(nbDAO.getWriteNo());
			nbDTO.setTitle(multi.getParameter("title"));
			nbDTO.setName(multi.getParameter("name"));
			nbDTO.setId(multi.getParameter("id"));
			nbDTO.setContent(multi.getParameter("content"));
			nbDTO.setFname(fileName);
			nbDTO.setPwd(multi.getParameter("pwd"));
			
			url = "Servlet?command=noticebbs_list";
			
			if(nbDAO.insert(nbDTO)) {
				System.out.println("공지사항 글 등록 성공.");
				request.setAttribute("writecheck", "true");
			} else {
				System.out.println("공지사항 글 등록 실패.");
				request.setAttribute("writecheck", "false");
			}
		} else if(command.indexOf("eventbbs")!=-1) {
			EventbbsDAO ebDAO = EventbbsDAO.getInstance();
			
			EventbbsDTO ebDTO = new EventbbsDTO();
			
			ebDTO.setNo(ebDAO.getWriteNo());
			ebDTO.setTitle(multi.getParameter("title"));
			ebDTO.setName(multi.getParameter("name"));
			ebDTO.setId(multi.getParameter("id"));
			ebDTO.setContent(multi.getParameter("content"));
			ebDTO.setFname(fileName);
			ebDTO.setPwd(multi.getParameter("pwd"));
			
			url = "Servlet?command=eventbbs_list";
			
			if(ebDAO.insert(ebDTO)) {
				System.out.println("행사 글 등록 성공.");
				request.setAttribute("writecheck", "true");
			} else {
				System.out.println("행사 글 등록 실패.");
				request.setAttribute("writecheck", "false");
			}
		} else if(command.indexOf("freebbs")!=-1) {
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			
			FreebbsDTO fbDTO = new FreebbsDTO();
			
			fbDTO.setNo(fbDAO.getWriteNo());
			fbDTO.setTitle(multi.getParameter("title"));
			fbDTO.setName(multi.getParameter("name"));
			fbDTO.setId(multi.getParameter("id"));
			fbDTO.setContent(multi.getParameter("content"));
			fbDTO.setFname(fileName);
			fbDTO.setPwd(multi.getParameter("pwd"));
			fbDTO.setGrp(fbDAO.getGrpNo()+1);
			
			url = "Servlet?command=freebbs_list";
			
			if(fbDAO.insert(fbDTO)) {
				System.out.println("자유게시판 글 등록 성공.");
				request.setAttribute("writecheck", "true");
			} else {
				System.out.println("자유게시판 글 등록 실패.");
				request.setAttribute("writecheck", "false");
			}
		} else if(command.equals("qna_write")) {
			QnaDAO qDAO = QnaDAO.getInstance();
			
			QnaDTO qDTO = new QnaDTO();
			
			qDTO.setNo(qDAO.getWriteNo());
			qDTO.setTitle(multi.getParameter("title"));
			qDTO.setName(multi.getParameter("name"));
			qDTO.setId(multi.getParameter("id"));
			qDTO.setContent(multi.getParameter("content"));
			qDTO.setFname(fileName);
			qDTO.setPwd(multi.getParameter("pwd"));
			qDTO.setGrp(qDAO.getGrpNo()+1);
			
			url = "Servlet?command=qna";
			
			if(qDAO.insert(qDTO)) {
				System.out.println("QnA 보내기 성공.");
				request.setAttribute("writecheck", "true");
			} else {
				System.out.println("QnA 보내기 실패.");
				request.setAttribute("writecheck", "false");
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
