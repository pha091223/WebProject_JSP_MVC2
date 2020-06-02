package controller.action.bbs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.action.Action;
import dao.EventbbsDAO;
import dao.FreebbsDAO;
import dao.NoticebbsDAO;
import dto.EventbbsDTO;
import dto.FreebbsDTO;
import dto.NoticebbsDTO;

public class BbsModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int mod_no = Integer.valueOf((String)request.getParameter("no"));
		System.out.println("수정 글 번호 : " + mod_no);
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		
		if(command.indexOf("noticebbs")!=-1) {
			NoticebbsDAO nbDAO = NoticebbsDAO.getInstance();
			
			if(command.contains("file")) {
				if(nbDAO.update(mod_no)) {
					System.out.println("업로드 파일 삭제 완료.");
				} else {
					System.out.println("업로드 파일 삭제 실패.");
				}
				
				new BbsModifyFormAction().execute(request, response);
			} else {
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
				
				NoticebbsDTO nbDTO = new NoticebbsDTO();
				
				nbDTO.setNo(mod_no);
				nbDTO.setTitle(multi.getParameter("title"));
				nbDTO.setContent(multi.getParameter("content"));
				nbDTO.setFname(fileName);
				nbDTO.setPwd(multi.getParameter("pwd"));
				
				if(nbDAO.update(nbDTO)) {
					System.out.println("글 수정 성공.");
				} else {
					System.out.println("글 수정 실패.");
				}
				
				new BbsViewAction().execute(request, response);
			}
		} else if(command.indexOf("eventbbs")!=-1) {
			EventbbsDAO ebDAO = EventbbsDAO.getInstance();
			
			if(command.contains("file")) {
				if(ebDAO.update(mod_no)) {
					System.out.println("업로드 파일 삭제 완료.");
				} else {
					System.out.println("업로드 파일 삭제 실패.");
				}
				
				new BbsModifyFormAction().execute(request, response);
			} else {
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
				
				EventbbsDTO ebDTO = new EventbbsDTO();
				
				ebDTO.setNo(mod_no);
				ebDTO.setTitle(multi.getParameter("title"));
				ebDTO.setContent(multi.getParameter("content"));
				ebDTO.setFname(fileName);
				ebDTO.setPwd(multi.getParameter("pwd"));
				
				if(ebDAO.update(ebDTO)) {
					System.out.println("글 수정 성공.");
				} else {
					System.out.println("글 수정 실패.");
				}
				
				new BbsViewAction().execute(request, response);
			}
		} else if(command.indexOf("freebbs")!=-1) {
		
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			
			if(command.contains("file")) {
				if(fbDAO.update(mod_no)) {
					System.out.println("업로드 파일 삭제 완료.");
				} else {
					System.out.println("업로드 파일 삭제 실패.");
				}
				
				new BbsModifyFormAction().execute(request, response);
			} else {
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
				
				FreebbsDTO fbDTO = new FreebbsDTO();
				
				fbDTO.setNo(mod_no);
				fbDTO.setTitle(multi.getParameter("title"));
				fbDTO.setContent(multi.getParameter("content"));
				fbDTO.setFname(fileName);
				fbDTO.setPwd(multi.getParameter("pwd"));
				
				if(fbDAO.update(fbDTO)) {
					System.out.println("글 수정 성공.");
				} else {
					System.out.println("글 수정 실패.");
				}
				
				new BbsViewAction().execute(request, response);
			}
		}
		
	}

}
