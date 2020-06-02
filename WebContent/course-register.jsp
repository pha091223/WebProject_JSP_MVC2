<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>가나다문화센터</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Ruda:400,900,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/prettyphoto/css/prettyphoto.css" rel="stylesheet">
  <link href="lib/hover/hoverex-all.css" rel="stylesheet">
  <link href="lib/jetmenu/jetmenu.css" rel="stylesheet">
  <link href="lib/owl-carousel/owl-carousel.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="css/colors/blue.css">

  <!-- =======================================================
    Template Name: MaxiBiz
    Template URL: https://templatemag.com/maxibiz-bootstrap-business-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>
	<c:choose>
		<c:when test="${reg_check=='true' }">
			<script>
				alert("신청이 완료되었습니다.");
			</script>
		</c:when>
		<c:when test="${reg_check=='false' }">
			<script>
				alert("이미 신청하신 강좌가 포함되어 있습니다.");
			</script>
		</c:when>
	</c:choose>
	<c:if test="${search=='false' }">
		<script>
			alert("검색결과가 없습니다.");
		</script>
	</c:if>
  <div class="topbar clearfix">
    <div class="container">
      <div class="col-lg-12 text-right">
        <div class="social_buttons">
          <c:choose>
          	<c:when test="${sessionScope.nowuser==null }">
          		<span>손님</span>
          		<a href="login.jsp"><span>로그인</span></a>
          		<a href="join.jsp"><span>회원가입</span></a>
          	</c:when>
          	<c:when test="${sessionScope.nowuser!=null }">
          		<span>${sessionScope.nowuser}</span>
          		<a href="Servlet?command=logout" onclick="return logoutcheck();"><span>로그아웃</span></a>
          		<a href="Servlet?command=mypage"><span>마이페이지</span></a>
          	</c:when>
          </c:choose>
        </div>
      </div>
    </div>
    <!-- end container -->
  </div>
  <!-- end topbar -->

  <header class="header">
    <div class="container">
      <div class="site-header clearfix">
        <div class="col-lg-3 col-md-3 col-sm-12 title-area">
          <div class="site-title" id="title">
            <a href="Servlet?command=home_list" title="">
              <h1>가나다<span>문화센터</span></h1>
            </a>
          </div>
        </div>
        <!-- title area -->
        <div class="col-lg-9 col-md-12 col-sm-12">
          <div id="nav" class="right">
            <div class="container clearfix">
              <ul id="jetmenu" class="jetmenu blue">
                <li>
                	<a href="Servlet?command=home_list">Home</a>
                </li>
                <!-- 기관소개 -->
                <li><a href="#">기관소개</a>
                  <ul class="dropdown">
                    <li><a href="#">소개</a></li>
                    <li><a href="#">시설</a></li>
                    <li><a href="Servlet?command=noticebbs_list">공지사항</a></li>
                    <li><a href="Servlet?command=eventbbs_list">행사</a></li>
                  </ul>
                </li>

                <!-- 수강신청 -->
                <li class="active"><a href="Servlet?command=guide">수강신청</a>
                  <ul class="dropdown">
                    <li><a href="Servlet?command=guide">가이드</a></li>
                    <li><a href="#">개설 강좌</a></li>
                    <li><a href="javascript:course_authority_check('${sessionScope.nowuser }');">신청</a></li>
                  </ul>
                </li>

                <!-- 커뮤니티 -->
                <li><a href="Servlet?command=freebbs_list">커뮤니티</a>
                  <ul class="dropdown">
                    <li><a href="Servlet?command=freebbs_list">자유게시판</a></li>
                    <li><a href="Servlet?command=gallery_list">갤러리</a></li>
                  </ul>
                </li>
                <!-- 고객센터 -->
                <li><a href="Servlet?command=fqa">고객센터</a>
                  <ul class="dropdown">
                    <li><a href="Servlet?command=fqa">자주 묻는 질문</a></li>
                    <li><a href="javascript:qna_authority_check('${sessionScope.nowuser }');">1:1 문의</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
          <!-- nav -->
        </div>
        <!-- title area -->
      </div>
      <!-- site header -->
    </div>
    <!-- end container -->
  </header>
  <!-- end header -->

  <section class="post-wrapper-top">
    <div class="container">
      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
        <ul class="breadcrumb">
          <li><a href="Servlet?command=home_list">Home</a></li>
          <li><a href="Servlet?command=guide">수강신청</a></li>
          <li>신청</li>
        </ul>
        <br>
      </div>
    </div>
  </section>
  <!-- end post-wrapper-top -->
  
  <section class="section1">
    <div class="container clearfix">
      <div class="content col-lg-12 col-md-12 col-sm-12 col-xs-12 clearfix">

        <h3 class="title">
          <span>수강신청</span>
        </h3>
        <br>
        <form id="cartinfo" action="" name="cartinfo" method="post">
          <label for="cardnumber">강좌명 검색</label>
          <input type="text" name="search_name" id="cardnumber" class="form-control" placeholder="검색어 입력" style="width:40%">
          <button type="submit" class="btn btn-primary" onclick="return course_namecheck();"  formaction="Servlet?command=search_course_name">검색</button>
          
          <br><br>

          <label for="ncard">달별 보기 (년 / 월)</label>

          <div class="clearfix"></div>

          <select class="form-control" name="search_year" style="width:19%; float:left; margin-right:2%">
          	<c:set var="years" value="2020" />
          	<c:forEach var="i" begin="0" end="5">
          		<option>${years-i }</option>
          	</c:forEach>
		  </select>
          <select class="form-control" name="search_month" style="width:19%;float:left">
          	<c:forEach begin="1" end="12" varStatus="month">
          		<c:if test="${month.count<10 }">
          			<option>0${month.count }</option>
          		</c:if>
          		<c:if test="${month.count>=10 }">
          			<option>${month.count }</option>
          		</c:if>
          	</c:forEach>
          </select>

          <div class="clearfix"></div>

          <button type="submit" class="btn btn-primary" formaction="Servlet?command=search_course_day">검색</button>
        </form>

        <div class="clearfix"></div>
        <div class="divider"></div>
        
		<form action="Servlet?command=course_register_basket_add" name="course_list" method="POST">
 	       <table class="table table-striped checkout" data-effect="fade">
	          <thead>
	            <tr>
	              <th>check</th>
	              <th>강좌명</th>
	              <th>강사</th>
	              <th>시작</th>
	              <th>요일</th>
	              <th>시간</th>
	              <th>인원</th>
	            </tr>
	          </thead>
	          <tbody>
	            <c:forEach items="${courseList }" var="c" varStatus="status">
		           <tr>
					 <td style="text-align:center" width="10%">
					   <label class="checkbox-inline" style="padding-bottom:20px">
					   	 <c:set var="p" value="${c.people }" />
					   	 <c:choose>
					   	 	<c:when test="${c.register==p }">
					   	 		<input id="inlineCheckbox1" type="checkbox" name="course" value="${c.no }" disabled="disabled">
					   	 	</c:when>
					   	 	<c:when test="${c.register!=p }">
					   	 		<input id="inlineCheckbox1" type="checkbox" name="course" value="${c.no }">
					   	 	</c:when>
					   	 </c:choose>
					   </label>
					 </td>
					 <td width="30%" height="50px"><a href="#">${c.name }</a></td>
					 <td style="text-align:center" width="15%">${c.teacher }</td>
					 <td width="10%" style="text-align:center">${c.day }</td>
					 <td width="10%" style="text-align:center">${c.week }</td>
					 <td width="10%" style="text-align:center">${c.stime } ~ ${c.etime }</td>
					 <td style="text-align:center">${c.register} / ${c.people }</td>
			       </tr>
	            </c:forEach>
	          </tbody>
	        </table>
	        <div class="clearfix"></div>
	        
	        <div class="well text-right"><strong>등록된 총 강좌 수 : ${courseCount }</strong></div>
	        <input type="hidden" name="course_select_count" value="${selectListSize }">
	        
	        <div class=" text-center">
	          <ul class="pagination">
	          	<c:if test="${pageMaker.prev>0 }">
					<li><a href="Servlet?command=course_register_form&page=${pageMaker.prev}">«</a></li>
				</c:if>
	            <c:forEach begin="${pageMaker.start }" end="${pageMaker.end}" var="idx">
		            <li><a href="Servlet?command=course_register_form&page=${idx}">${idx }</a></li>
	            </c:forEach>
	            <c:if test="${pageMaker.next>0 }">
					<li><a href="Servlet?command=course_register_form&page=${pageMaker.next}">»</a></li>
				</c:if>
	          </ul>
	       </div>
	        <div class="clearfix"></div>
	
	        <div class="pull-right">
	          <input type="submit" class="button" value="담기" onclick="return countcheck('course');">
	        </div>
        </form>

        <div class="clearfix"></div>
        <div class="divider"></div>
      </div>
    </div>
    <!-- end container -->
  </section>
  <!-- end section -->
  
    <section class="section1">
    <div class="container clearfix">
      <div class="content col-lg-12 col-md-12 col-sm-12 col-xs-12 clearfix" style="padding-bottom:0px;">
      
      	<h3 class="title">
          <span>담은 강좌 확인</span>
        </h3>
        
        <form action="" name="course_select_list" method="POST">
	        <table class="table table-striped checkout" data-effect="fade">
	          <thead>
	            <tr>
	              <th>check</th>
	              <th>강좌명</th>
	              <th>강사</th>
	              <th>시작</th>
	              <th>요일</th>
	              <th>시간</th>
	              <th>인원</th>
	            </tr>
	          </thead>
	          <tbody>
	            <c:forEach items="${selectList }" var="s" varStatus="status">
	              <tr>
	              	  <td style="text-align:center" width="10%">
		                <label class="checkbox-inline" style="padding-bottom:20px">
		                  <input id="inlineCheckbox1" type="checkbox" name="course_select" value="${s.no }">
		                </label>
		              </td>
		              <td width="30%" height="50px"><a href="#">${s.name }</a></td>
		              <td style="text-align:center" width="15%">${s.teacher }</td>
		              <td width="10%" style="text-align:center">${s.day }</td>
		              <td width="10%" style="text-align:center">${s.week }</td>
		              <td width="10%" style="text-align:center">${s.stime } ~ ${s.etime }</td>
		              <td style="text-align:center">${s.register} / ${s.people }</td>
	              </tr>
	            </c:forEach>
	          </tbody>
	        </table>
	        <div class="clearfix"></div>
			
			<c:if test="${selectListSize==null }">
	        	<div class="well text-right"><strong>선택한 총 강좌 수 : 0</strong></div>
			</c:if>
			<c:if test="${selectListSize!=null }">
				<div class="well text-right"><strong>선택한 총 강좌 수 : ${selectListSize }</strong></div>
			</c:if>
			
			<input type="hidden" name="course_select_count" value="${selectListSize }">
	
	        <div class="clearfix"></div>
	        
	       <div class="pull-right">
	          <input type="submit" class="button" value="신청" onclick="return countcheck('course_select_reg');" formaction="Servlet?command=course_register">
	          <input type="submit" class="button" value="목록에서 삭제" onclick="return countcheck('course_select_del');" formaction="Servlet?command=course_register_basket_del">
	       </div>
        </form>

        <div class="clearfix"></div>
        <div class="divider"></div>
      </div>
    </div>
    <!-- end container -->
  </section>
  <!-- end section -->

  <footer class="footer">
    <div class="container">
      <div class="widget col-lg-4 col-md-4 col-sm-12">
        <h4 class="title">가나다문화센터</h4>
        <ul class="contact_details">
          <li><i class="fa fa-envelope-o"></i> E-mail : aaa@bbb.ccc</li>
          <li><i class="fa fa-phone-square"></i> 전화번호 : 041 - 999 - 9999</li>
          <li><i class="fa fa-search"></i> 운영시간 : 09:00 ~ 18:00</li>
          <li><i class="fa fa-home"></i> 주소 : A도 B시 C로 21-9 </li>
          <!-- <li><a href="#"><i class="fa fa-map-marker"></i> View large map</a></li> -->
          <br>
          <a href="https://www.facebook.com" target="_blank" data-toggle="tooltip" data-placement="bottom" title="Facebook"><i class="fa fa-facebook"></i></a>
          &emsp;
          <a href="https://www.twitter.com" target="_blank" data-toggle="tooltip" data-placement="bottom" title="Twitter"><i class="fa fa-twitter"></i></a>
          &emsp;
          <a href="https://www.google.com" target="_blank" data-toggle="tooltip" data-placement="bottom" title="Google+"><i class="fa fa-google-plus"></i></a>
          &emsp;
          <a href="https://www.instagram.com" target="_blank" data-toggle="tooltip" data-placement="bottom" title="Instagram"><i class="fa fa-rss"></i></a>
        </ul>
        <!-- contact_details -->
      </div>
      <!-- end widget -->
    </div>
    <!-- end container -->

    <div class="copyrights">
      <div class="container">
        <div class="col-lg-6 col-md-6 col-sm-12 columns footer-left">
          <p>Copyright © 2014 - All rights reserved.</p>
          <div class="credits">
            <!--
              You are NOT allowed to delete the credit link to TemplateMag with free version.
              You can delete the credit link only if you bought the pro version.
              Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/maxibiz-bootstrap-business-template/
              Licensing information: https://templatemag.com/license/
            -->
            Created with MaxiBiz template by <a href="https://templatemag.com/">TemplateMag</a>
          </div>
        </div>
      </div>
      <!-- end container -->
    </div>
    <!-- end copyrights -->
  </footer>
  <!-- end footer -->
  <div class="dmtop">Scroll to Top</div>

  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="lib/php-mail-form/validate.js"></script>
  <script src="lib/prettyphoto/js/prettyphoto.js"></script>
  <script src="lib/isotope/isotope.min.js"></script>
  <script src="lib/hover/hoverdir.js"></script>
  <script src="lib/hover/hoverex.min.js"></script>
  <script src="lib/unveil-effects/unveil-effects.js"></script>
  <script src="lib/owl-carousel/owl-carousel.js"></script>
  <script src="lib/jetmenu/jetmenu.js"></script>
  <script src="lib/animate-enhanced/animate-enhanced.min.js"></script>
  <script src="lib/jigowatt/jigowatt.js"></script>
  <script src="lib/easypiechart/easypiechart.min.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>
  <script src="js/course.check.js"></script>
  <script src="js/authority.check.js"></script>
  <script src="js/search.check.js"></script>

</body>
</html>
