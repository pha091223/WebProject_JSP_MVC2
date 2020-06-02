	function countcheck(check) {
		var k = check;
		
		if(check.lastIndexOf('_')!=-1) {
			k = check.substring(0, check.lastIndexOf('_'));
		}
		
        var check_count = document.getElementsByName(k).length;
        var count = 0;
        
        for(var i=0; i<check_count; i++) {
            if(document.getElementsByName(k)[i].checked==true) {
            	count++;
                // alert(document.getElementsByName(s)[i].value);
            }
        }
        
        if(k=='course'){        	
            if(count==0) {
            	alert("담을 강좌를 선택해주세요.");
            	return false;
            } else if(count>6) {
            	alert("한 번에 5개 이하만 담을 수 있습니다.");
            	return false;
            } else if(document.course_select_list.course_select_count.value==5){
            	alert("이미 5개가 담겨있습니다.");
            	return false;
            } else {
            	var cnt = 0;
            	for(var i=0; i<check_count; i++) {
            		if(document.getElementsByName(k)[i].checked==true){
            			for(var j=0; j<document.getElementsByName('course_select').length; j++){
            				if(document.getElementsByName(k)[i].value
            						==document.getElementsByName('course_select')[j].value) {
            					cnt++;
            					break;
            				}
            			}
            			if(cnt==1){
            				alert("목록에 이미 추가한 강좌가 포함되어 있습니다.");
            				return false;
            			}
            		}
            	}
            }
        } else if(k=='course_select') {
        	var s = check.substring(check.lastIndexOf('_')+1, check.length);
        	
        	if(s=='reg') {
        		if(count==0) {
        			alert("신청할 강좌를 선택해주세요.");
        			return false;
        		}
        	} else if(s=='del') {
	        	if(count==0) {
	        		alert("삭제할 강좌를 선택해주세요.");
	        		return false;
	        	}
        	}
        }
        return true;
	}