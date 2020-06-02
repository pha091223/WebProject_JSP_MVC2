	function logincheck() {
		if(document.loginform.id.value=="") {
			alert("아이디를 입력해주세요.");
			loginform.id.focus();
			return false;
		}
		if(document.loginform.pwd.value=="") {
			alert("비밀번호를 입력해주세요.");
			loginform.pwd.focus();
			return false;
		}
		return true;
	}
	
	function logoutcheck(){
		if(confirm("로그아웃 하시겠습니까?")) {
			return true;
		}
		return false;
	}

	function joincheck() {
		// 이름
		var name = document.registerform.name.value;
		if(name=="") {
			alert("이름을 입력해주세요.");
			registerform.name.focus();
			return false;
		} else {
			if(name.length>6) {
				alert("이름은 5글자 이하만 가능합니다.");
				registerform.name.focus();
				return false;
			} else {
				if(name=="admin"){
					alert("사용불가능한 이름입니다.");
					registerform.name.focus();
					return false;
				}
			}
		}
		// 주민등록번호
		var resident = document.registerform.resident.value;
		if(resident=="") {
			alert("주민등록번호를 입력해주세요.");
			registerform.resident.focus();
			return false;
		} else {
			if(resident.indexOf("-")!=-1) {
				alert("(-)은 제외하고 입력해주세요.");
				registerform.resident.focus();
				return false;
			} else {
				if(valuecheck(resident, "resident")){
					if(resident.length!=13) {
						alert("주민등록번호 13자리를 입력해주세요.");
						registerform.resident.focus();
						return false;
					} else if(resident.length==13) {
						if(resident.substring(6, 7)==0 || resident.substring(6, 7)>4) {
							alert("주민등록번호 뒷자리 첫 글자는 1~4 사이의 숫자만 가능합니다.");
							registerform.resident.focus();
							return false;
						}
					}
				} else{
					alert("주민등록번호는 숫자만 입력 가능합니다.");
					registerform.resident.focus();
					return false;
				}
			}
		}
		// 아이디
		var id = document.registerform.id.value;
		if(id=="") {
			alert("아이디를 입력해주세요.");
			registerform.id.focus();
			return false;
		} else {
			if(id.length<5 || id>=15){
				alert("아이디는 5~15글자 사이만 가능합니다.");
				registerform.id.focus();
				return false;
			} else {
				if(valuecheck(id, "id")==false){
					alert("아이디는 영소문자와 숫자만 입력 가능합니다.");
					registerform.id.focus();
					return false;
				}
			}
		}
		// 중복체크 여부
		if((document.registerform.check.value)=="false"){
			alert("아이디 중복체크는 필수입니다.");
			return false;
		}
		// 비밀번호
		var pwd = document.registerform.pwd.value;
		if(pwd=="") {
			alert("비밀번호를 입력해주세요.");
			registerform.pwd.focus();
			return false;
		} else {
			if(pwd.length<5 || pwd>=15){
				alert("비밀번호는 5~15글자 사이만 가능합니다.");
				registerform.pwd.focus();
				return false;
			} else {
				if(valuecheck(pwd, "pwd")==false){
					alert("비밀번호는 영소문자와 숫자만 입력 가능합니다.");
					registerform.pwd.focus();
					return false;
				}
			}
		}
		// 비밀번호 확인
		var repwd = document.registerform.repwd.value;
		if(repwd=="") {
			alert("비밀번호 확인을 입력해주세요.");
			registerform.repwd.focus();
			return false;
		} else {
			if(!repwd.equals(pwd)){
				alert("비밀번호가 맞지 않습니다.");
				registerform.repwd.focus();
				return false;
			}
		}
		// 연락처
		var phone = document.registerform.phone.value;
		if(phone=="") {
			alert("연락처를 입력해주세요.");
			registerform.phone.focus();
			return false;
		} else {
			if(valuecheck(phone, "phone")){
				if(phone.length!=10 || phone.length!=11){
					alert("연락처는 10~11글자만 가능합니다.");
					registerform.phone.focus();
					return false;
				}
			} else {
				alert("연락처는 숫자만 입력 가능합니다.");
				registerform.phone.focus();
				return false;
			}
		}
		// 이메일
		var email = document.registerform.email.value;
		if(email!="") {
			if(email.indexOf("@")==-1){
				alert("올바른 이메일 주소를 입력해주세요.");
				registerform.phone.focus();
				return false;
			}
		}
		return true;
	}
	
	function valuecheck(nowvalue, keyword){
		if(keyword=="id" || keyword=="pwd") {
			for(i=0; i<nowvalue.length; i++) {
				let asciiNum = nowvalue.charCodeAt(i);
				if(asciiNum<97 || asciiNum>121) {
					if(asciiNum<48 || asciiNum>57) {
						return false;
					}
				}
			}
		} else if(keyword=="resident" || keyword=="phone") {
			for(i=0; i<nowvalue.length; i++) {
				let asciiNum = nowvalue.charCodeAt(i);
				if(asciiNum<48 || asciiNum>57) {
					return false;
				}
			}
		}
		return true;
	}
	
	function idcheck(){
		if(document.registerform.id.value=="") {
			alert("아이디를 입력해주세요.");
			registerform.id.focus();
			return false;
		}
		document.registerform.check.value = "true";
			
		var url = "Servlet?command=join_id_check&id=" + document.registerform.id.value;
		window.open(url, "_blank_1",
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200," +
			"left=300, top=250");
	}
	
	function modifycheck(){
		// 비밀번호
		var pwd = document.registerform.pwd.value;
		if(pwd=="") {
			alert("비밀번호를 입력해주세요.");
			registerform.pwd.focus();
			return false;
		} else {
			if(pwd.length<5 || pwd>=15){
				alert("비밀번호는 5~15글자 사이만 가능합니다.");
				registerform.pwd.focus();
				return false;
			} else {
				if(valuecheck(pwd, "pwd")==false){
					alert("비밀번호는 영소문자와 숫자만 입력 가능합니다.");
					registerform.pwd.focus();
					return false;
				}
			}
		}
		// 비밀번호 확인
		var repwd = document.registerform.repwd.value;
		if(repwd=="") {
			alert("비밀번호 확인을 입력해주세요.");
			registerform.repwd.focus();
			return false;
		} else {
			if(!repwd.equals(pwd)){
				alert("비밀번호가 맞지 않습니다.");
				registerform.repwd.focus();
				return false;
			}
		}
		// 연락처
		var phone = document.registerform.phone.value;
		if(phone=="") {
			alert("연락처를 입력해주세요.");
			registerform.phone.focus();
			return false;
		} else {
			if(valuecheck(phone, "phone")){
				if(phone.length!=10 || phone.length!=11){
					alert("연락처는 10~11글자만 가능합니다.");
					registerform.phone.focus();
					return false;
				}
			} else {
				alert("연락처는 숫자만 입력 가능합니다.");
				registerform.phone.focus();
				return false;
			}
		}
		// 이메일
		var email = document.registerform.email.value;
		if(email!="") {
			if(email.indexOf("@")==-1){
				alert("올바른 이메일 주소를 입력해주세요.");
				registerform.phone.focus();
				return false;
			}
		}
		return true;
	}
	
	function deletecheck(){
		if(confirm("정말 탈퇴 하시겠습니까?")) {
			location.href = "Servlet?command=mypage_delete";
		}
	}
	