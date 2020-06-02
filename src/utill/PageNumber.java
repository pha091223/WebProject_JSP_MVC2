package utill;

public class PageNumber {
	private int page;  // 현재 기준 페이지
	private int count;  // 총 글수
	private int start;  // 페이지그룹 시작 번호
	private int end;    // 페이지그룹 끝 번호
	private int nowPageStart;  //표시될 페이지 시작번호
	private int nowPageEnd;    //표시될 페이지 끝번호
	private int prev = 0;;	// 이전 페이지 그룹 여부
	private int next = 0;		// 이후 페이지 그룹 여부
	private int pageCnt = 10;	// 페이지그룹 개수 및 게시물의 수

	public Integer getPage() {
		return page;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPage(Integer page) {
		if(page<1) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setCount(Integer count) {
		if(count<1) {
			return;
		}
		this.count = count;
//		System.out.println("총 페이지 수 = " + count);
		calcPage();
	}

	public int getCount() {
		return count;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getPrev() {
		return prev;
	}

	public int getNext() {
		return next;
	}

	public int getNowPageStart() {
		return nowPageStart;
	}

	public int getNowPageEnd() {
		return nowPageEnd;
	}

	private void calcPage() { // page 계산
		int tempEnd = (int)(Math.ceil(page/(float)pageCnt) * pageCnt);
		// tempEnd : 현재 page의 페이지그룹 끝번호
		// Math.ceil() : 반올림
		
//		System.out.println("this.count = " + this.count);
		this.start = tempEnd - 9;	// 현재 page의 페이지그룹 시작번호
		
		if(start>pageCnt) {
			prev = start - pageCnt;
		}
		
		// 글 갯수가 현재 페이지그룹의 tempEnd까지 채우느냐 못채우느냐 판별
		if (tempEnd * pageCnt>this.count) { 
			// 채우지 못함 > 페이지그룹 End 재설정
			this.end = (int)Math.ceil(this.count/(float)pageCnt);
		} else {
			// 채움 > 페이지그룹 End=tempEnd
			this.end = tempEnd;
			next = end + 1;
		}
		
		nowPageStart = (page-1) * pageCnt + 1;
		nowPageEnd = nowPageStart + pageCnt - 1;
		
		// 글 갯수가 현재 페이지의 끝번호까지 있느냐 없느냐 판별
		if(nowPageEnd>this.count) {
			nowPageEnd = this.count;
		}
		
//		System.out.println("page = " + page);
//		System.out.println("start = " + start);
//		System.out.println("this.end = " + this.end);
//		System.out.println("nowpageStart = " + this.nowPageStart);
//		System.out.println("nowpageEnd = " + this.nowPageEnd);
	}
}
