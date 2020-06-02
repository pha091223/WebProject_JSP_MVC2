	function writecheck(keyword) {
		var s = null;
		
		switch(keyword){
			case 'write' :
				s = "작성하시겠습니까?";
				break;
			case 'modify' :
				s = "수정하시겠습니까?"
		}
		
		if(confirm(s)){
			if(document.writeform.title.value.length<1) {
				alert("제목을 입력해주세요.");
				writeform.title.focus();
				return false;
			} else if(document.writeform.title.value.length<5) {
				alert("제목은 5글자 이상만 가능합니다.");
				writeform.title.focus();
				return false;
			}
			if(document.writeform.content.value.length<1) {
				alert("내용을 입력해주세요.");
				writeform.content.focus();
				return false;
			}
			if(document.writeform.pwd.value.length<1) {
				alert("비밀번호를 입력해주세요.");
				writeform.pwd.focus();
				return false;
			} else if(document.writeform.pwd.value.length<4){
				alert("비밀번호는 4글자 이상만 가능합니다.");
				writeform.pwd.focus();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	function deletecheck() {
		if(confirm("삭제하시겠습니까?")){
			return true;
		} else {
			return false;
		}
	}
	
	function replycheck() {
		if(confirm("댓글을 작성하시겠습니까?")){
			if(document.comments_form.comments.value.length<1) {
				alert("내용을 입력해주세요.");
				writeform.title.focus();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}