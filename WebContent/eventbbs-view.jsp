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
                <li class="active"><a href="#">기관소개</a>
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
          <li>기관소개</li>
          <li><a href="Servlet?command=noticebbs_list">행사</a></li>
          <li>${one.title } </li>
        </ul>
        <br>
      </div>
    </div>
  </section>
  <!-- end post-wrapper-top -->

  <section class="section1">
    <div class="container clearfix">
      <div class="content col-lg-12 col-md-12 col-sm-12 col-xs-12 clearfix">

        <!-- SLIDE POST -->
        <article class="blog-wrap">

          <header class="page-header blog-title">
            <h3 class="title">${one.title }</h3>
            <div class="post-meta">
              <p>
              	<span>글 번호 : ${one.no }</span>
                <span class="sep">/</span><span>작성자 : ${one.name }</span>
                <span class="sep">/</span><span class="publish-on">작성날짜 : ${one.day }</span>
                <span class="sep">/</span><span>조회수 : ${one.readcount }</span>
              </p>
            </div>
          </header>
          <br><br>
          <div class="post-desc">
            <p>${one.content }</p>
            <br>
            <h4>첨부파일</h4>
            <hr>
            <c:choose>
            	<c:when test="${one.fname==null }">
            		<span>첨부파일 없음</span>
            	</c:when>
            	<c:when test="${one.fname!=null }">
            		<span>${one.fname }</span>
            		<br>
            		<c:if test="${fileform=='.jpg' }">
            			<img src="./bbs/upload/${one.fname }" width=500>
            		</c:if>
            		<c:if test="${fileform=='.png' }">
            			<img src="./bbs/upload/${one.fname }" width=500>
            		</c:if>
            	</c:when>
            </c:choose>
          </div>
          <br>
        </article>
        <!-- end author_box -->
        <hr>
          
      <div id="comments_wrapper">
       	<c:if test="${sessionScope.nowuser==admin }">
	       <a href="Servlet?command=eventbbs_modify&no=${one.no}">
	       <input type="button" class="btn btn-primary" value="수정"></a>
	       <a href="Servlet?command=eventbbs_delete&no=${one.no }">
	       <input type="button" class="btn btn-primary" value="삭제" onclick="return deletecheck();"></a>
         </c:if>
          <a href="Servlet?command=eventbbs_list"><input type="button" class="btn btn-primary" value="목록" style="float: right"></a>
        </div>
        <!-- div comments -->
      </div>
      <!-- end content -->
      </div>
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
  <script src="js/bbs.check.js"></script>
  <script src="js/authority.check.js"></script>

</body>
</html>
