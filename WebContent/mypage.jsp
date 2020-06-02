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
		<c:when test="${sign=='true' }">
			<script>
	    		if(!alert("회원정보 수정이 완료되었습니다.")){
	    			location.href="Servlet?command=mypage";
	    		}
			</script>
		</c:when>
		<c:when test="${sign=='false' }">
			<script>
	    		if(!alert("회원정보 수정이 실패했습니다.")){
	    			history.back(-1);
	    		}
			</script>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${sign=='true' }">
			<script>
				alert("회원 정보를 수정하였습니다.");
			</script>
		</c:when>
		<c:when test="${sign=='false' }">
			<script>
				alert("회원 정보를 수정하지 못했습니다.")
			</script>
		</c:when>
	</c:choose>
  <div class="topbar clearfix">
    <div class="container">
      <div class="col-lg-12 text-right">
        <div class="social_buttons">
			<span>${sessionScope.nowuser}</span>
          	<a href="Servlet?command=logout" onclick="return logoutcheck();"><span>로그아웃</span></a>
          	<a href="Servlet?command=mypage"><span>마이페이지</span></a>
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
                <li><a href="Servlet?command=guide">수강신청</a>
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
          <li>마이페이지</li>
        </ul>
        <br>
      </div>
    </div>
  </section>
  <!-- end post-wrapper-top -->

  <section class="section1">
    <div class="container clearfix">
      <div class="content col-lg-12 col-md-12 col-sm-12 clearfix">
        <div class="col-lg-9 col-md-9 col-sm-12">
          <h3 class="title">
            <span>마이페이지</span>
          </h3>
          <form id="registerform" method="post" name="registerform" action="Servlet?command=mypage_modify">
            <div class="form-group">
              <p>이름</p>
              <input type="text" class="form-control" name="name" value="${sessionScope.nowuser }" readonly="true">
              <p>* 5글자 이하</p>
            </div>
            <div class="form-group">
              <p>주민등록번호</p>
              <input type="text" class="form-control" name="resident" value="${now.resident }" readonly="true">
              <p>* (-) 제외 숫자 / 앞자리 6자 + 뒷자리 7자</p>
            </div>
            <div class="form-group">
              <p>아이디</p>
              <input type="text" class="form-control" name="id" value="${now.id }" readonly="true">
              <p>* 영소문자, 숫자 / 5 ~ 15글자 사이</p>
            </div>
            <div class="form-group">
              <p>비밀번호</p>
              <input type="password" class="form-control" name="pwd">
              <p>* 영소문자, 숫자 / 5 ~ 15글자 사이</p>
            </div>
            <div class="form-group">
              <p>비밀번호 확인</p>
              <input type="password" class="form-control" name="repwd">
            </div>
            <hr>
            <div class="form-group">
              <p>연락처</p>
              <input type="text" class="form-control" name="phone" value="${now.phone }">
              <p>* (-) 제외 숫자 / 10 ~ 11글자 사이</p>
            </div>
            <div class="form-group">
              <p>이메일</p>
              <input type="email" class="form-control" placeholder="선택사항" name="email" value="${now.email }">
              <p>* ex)000@000.000</p>
            </div>
            <br>
            <div class="form-group">
              <input type="submit" class="btn btn-primary" onclick="return modifycheck();" value="수정">
            </div>
          </form>
        </div>
        <!-- end register -->
        
            <!-- SIDEBAR -->
		    <div id="sidebar" class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
		      <div class="widget">
		        <h4 class="title" style="margin-top: 7px;">
		            <span>My Page</span>
		        </h4>
		        <ul class="pages">
		          <li><a href="Servlet?command=mypage">회원 정보 확인/수정</a></li>
		          <li><a href="#">수강 강좌 관리</a></li>
		          <li><a href="#">1:1 문의 관리</a>
		          <hr>
		          <li><a href="javascript:deletecheck();">회원 탈퇴</a>
		        </ul>
		      </div>
		    </div>
		    <!-- end sidebar -->
      </div>
      <!-- end content -->
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
  <script src="js/join.login.check.js"></script>
  <script src="js/authority.check.js"></script>

</body>
</html>
