package controller.action.bbs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.action.Action;
import dao.FreebbsDAO;
import dto.FreebbsDTO;

public class BbsWriteReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html; charset=UTF-8");
		
		int reply_no = Integer.valueOf((String)request.getParameter("no"));
		
		String command = request.getParameter("command");
		System.out.println("* 게시판 종류 : " + command);
		String url = null;
		
		if(command.indexOf("freebbs")!=-1) {
			FreebbsDAO fbDAO = FreebbsDAO.getInstance();
			FreebbsDTO fbDTO = fbDAO.selectOne(reply_no);
			
			int write_no = fbDAO.getWriteNo();
			
			int grp = fbDTO.getGrp();
			int seq = fbDTO.getSeq();
			int lvl = fbDTO.getLvl();
			
			if(seq==0) {
				seq = fbDAO.getSeqNo(grp) + 1;
			} else {
				int seq_temp = seq + 1;
				
				for(;;) {
					if(fbDAO.depthLevelGrp(grp, seq_temp, lvl)) {
						seq_temp++;
					} else {
						break;
					}
				}
				
				fbDAO.addSeqCount(grp, seq_temp);
				seq = seq_temp;
			}
			
			lvl = lvl + 1;
			
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
			
			FreebbsDTO fbDTO_r = new FreebbsDTO();
			
			fbDTO_r.setNo(write_no);
			fbDTO_r.setTitle(multi.getParameter("title"));
			fbDTO_r.setName(multi.getParameter("name"));
			fbDTO_r.setId(multi.getParameter("id"));
			fbDTO_r.setContent(multi.getParameter("content"));
			fbDTO_r.setFname(fileName);
			fbDTO_r.setPwd(multi.getParameter("pwd"));
			fbDTO_r.setGrp(grp);
			fbDTO_r.setSeq(seq);
			fbDTO_r.setLvl(lvl);
			
			url = "Servlet?command=freebbs_list";
			
			if(fbDAO.insert_reply(fbDTO_r)) {
				System.out.println("자유게시판 " + reply_no + " 답글 등록 성공.");
				request.setAttribute("writecheck", "true");
			} else {
				System.out.println("자유게시판 " + reply_no + " 답글 등록 실패.");
				request.setAttribute("writecheck", "false");
			}
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
