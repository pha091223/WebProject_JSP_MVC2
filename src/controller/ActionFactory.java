package controller;

import controller.action.Action;
import controller.action.FaqAction;
import controller.action.GuideAction;
import controller.action.JoinAction;
import controller.action.JoinIdCheckAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.MyPageDeleteAction;
import controller.action.MyPageModifyAction;
import controller.action.MyPageModifyFormAction;
import controller.action.SearchAction;
import controller.action.bbs.BbsCommentWriteAction;
import controller.action.bbs.BbsDeleteAction;
import controller.action.bbs.BbsListAction;
import controller.action.bbs.BbsModifyAction;
import controller.action.bbs.BbsModifyFormAction;
import controller.action.bbs.BbsViewAction;
import controller.action.bbs.BbsWriteAction;
import controller.action.bbs.BbsWriteFormAction;
import controller.action.bbs.BbsWriteReplyAction;
import controller.action.course.CourseRegisterAction;
import controller.action.course.CourseRegisterBasketAction;
import controller.action.course.CourseRegisterFormAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory 요청 받음 : " + command);
		
		if(command.equals("login")) {
			action = new LoginAction();
		} else if(command.equals("logout")) {
			action = new LogoutAction();
		} else if(command.equals("join")) {
			action = new JoinAction();
		} else if(command.equals("join_id_check")) {
			action = new JoinIdCheckAction();
		} else if(command.equals("mypage")) {
			action = new MyPageModifyFormAction();
		} else if(command.equals("mypage_modify")) {
			action = new MyPageModifyAction();
		} else if(command.equals("mypage_delete")) {
			action = new MyPageDeleteAction();
		} else if(command.indexOf("writeform")!=-1 || command.equals("qna")) {
			action = new BbsWriteFormAction();
		} else if(command.indexOf("list")!=-1) {
			action = new BbsListAction();
		} else if(command.indexOf("bbs_write")!=-1 || command.equals("qna_write")) {
			action = new BbsWriteAction();
		} else if(command.indexOf("bbs_view")!=-1) {
			action = new BbsViewAction();
		} else if(command.indexOf("bbs_reply_write")!=-1) {
			action = new BbsWriteReplyAction();
		} else if(command.equals("freebbs_comment_write")) {
			action = new BbsCommentWriteAction();
		} else if(command.indexOf("bbs_delete")!=-1) {
			action = new BbsDeleteAction();
		} else if(command.indexOf("bbs_modify")!=-1) {
			action = new BbsModifyFormAction();
		} else if(command.indexOf("bbs_write_modify")!=-1) {
			action = new BbsModifyAction();
		} else if(command.equals("fqa")) {
			action = new FaqAction();
		} else if(command.equals("guide")) {
			action = new GuideAction();
		} else if(command.equals("course_register_form")) {
			action = new CourseRegisterFormAction();
		} else if(command.indexOf("course_register_basket")!=-1) {
			action = new CourseRegisterBasketAction();
		} else if(command.equals("course_register")) {
			action = new CourseRegisterAction();
		} else if(command.indexOf("search")!=-1) {
			action = new SearchAction();
		}
		
		return action;
	}
}
