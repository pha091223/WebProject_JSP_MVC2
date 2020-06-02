	function course_namecheck() {
		if(document.cartinfo.search_name.value=="") {
			alert('검색어를 입력해주세요.');
			return false;
		} else if(document.cartinfo.search_name.value.length<2) {
			alert('두 글자 이상 입력해주세요.');
			return false;
		}
		return true;
	}