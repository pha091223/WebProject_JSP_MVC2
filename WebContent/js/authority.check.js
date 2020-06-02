	function bbs_authority_check(keyword, id){
		if(id==''){
			alert("로그인이 필요합니다.");
		} else {
			switch(keyword){
				case 'freebbs' :
					location.href = "Servlet?command=freebbs_writeform";
					break;
				case 'noticebbs' :
					location.href = "Servlet?command=noticebbs_writeform";
					break;
				case 'eventbbs' :
					location.href = "Servlet?command=eventbbs_writeform";
					break;
			}
		}
	}
	
	function course_authority_check(id){
		if(id==''){
			alert("로그인이 필요합니다.");
		} else {
			location.href = "Servlet?command=course_register_form";
		}
	}
	
	function qna_authority_check(id){
		if(id==''){
			alert("로그인이 필요합니다.");
		} else {
			location.href = "Servlet?command=qna";
		}
	}