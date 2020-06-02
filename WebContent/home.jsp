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
  <link href="<%=request.getContextPath()%>/img/favicon.png" rel="icon">
  <link href="<%=request.getContextPath()%>/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Ruda:400,900,700" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="<%=request.getContextPath()%>/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="<%=request.getContextPath()%>/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/lib/prettyphoto/css/prettyphoto.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/lib/hover/hoverex-all.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/lib/jetmenu/jetmenu.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/lib/owl-carousel/owl-carousel.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/colors/blue.css">

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
                <li class="active">
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

  <section id="intro">
  </section>

  <section class="section1">
    <div class="container">
      <div class="col-lg-4 col-md-4 col-sm-4">
        <div class="servicebox text-center">
          <div class="service-icon">
            <div class="dm-icon-effect-1" data-effect="slide-left">
              <a href="#" class=""> <i class="active dm-icon fa fa-bars fa-3x"></i> </a>
            </div>
            <div class="servicetitle">
              <h4>개설 강좌</h4>
              <hr>
            </div>
          </div>
          <!-- service-icon -->
        </div>
        <!-- servicebox -->
      </div>
      <!-- large-4 -->

      <div class="col-lg-4 col-md-4 col-sm-4">
        <div class="servicebox text-center">
          <div class="service-icon">
            <div class="dm-icon-effect-1" data-effect="slide-bottom">
              <a href="javascript:course_authority_check('${sessionScope.nowuser }');" class=""> <i class="active dm-icon fa fa-laptop fa-3x"></i> </a>
            </div>
            <div class="servicetitle">
              <h4>수강 신청</h4>
              <hr>
            </div>
          </div>
          <!-- service-icon -->
        </div>
        <!-- servicebox -->
      </div>
      <!-- large-4 -->

      <div class="col-lg-4 col-md-4 col-sm-4">
        <div class="servicebox text-center">
          <div class="service-icon">
            <div class="dm-icon-effect-1" data-effect="slide-right">
              <a href="Servlet?command=guide" class=""> <i class="active dm-icon fa fa-book fa-3x"></i> </a>
            </div>
            <div class="servicetitle">
              <h4>신청 가이드</h4>
              <hr>
            </div>
          </div>
          <!-- service-icon -->
        </div>
        <!-- servicebox -->
      </div>
      <!-- large-4 -->
    </div>
    <!-- end container -->
  </section>
  <!-- end section -->

  <section class="section1 text-center">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-4 col-xs-12">
          <div class="custom-box">
            <div class="servicetitle">
              <h4>공지사항</h4>
              <hr>
            </div>
            <br>
            <ul class="pricing">
              <c:forEach items="${noticeList }" var="n" varStatus="bs">
              	<li>
	               <span id="title">${n.title }</span>
	               <span id="date">${n.day }</span>      		
              	</li>
              </c:forEach>
            </ul>
            <a class="btn btn-primary" href="Servlet?command=noticebbs_list">더 보기</a>
          </div>
          <!-- end custombox -->
        </div>
        <!-- end col-4 -->

        <div class="col-lg-6 col-md-6 col-sm-4 col-xs-12">
          <div class="custom-box">
            <div class="servicetitle">
              <h4>행사</h4>
              <hr>
            </div>
            <br>
            <ul class="pricing">
              <c:forEach items="${eventList }" var="e" varStatus="bs">
              	<li>
	               <span id="title">${e.title }</span>
	               <span id="date">${e.day }</span>      		
              	</li>
              </c:forEach>
            </ul>
            <a class="btn btn-primary" href="Servlet?command=eventbbs_list">더 보기</a>
          </div>
          <!-- end custombox -->
        </div>
        <!-- end col-4 -->
      </div>
    </div>
    <!-- end container -->
  </section>
  <!-- end section1 -->

  <section class="section4 text-center">
    <div class="general-title">
      <h3>갤러리</h3>
      <hr>
    </div>
    <div class="portfolio-wrapper">
      <div id="owl-demo" class="owl-carousel">

        <div class="item">
          <a data-rel="prettyPhoto" href="img/portfolio_01.jpg">
                        	<img class="lazyOwl" src="img/portfolio_01.jpg" data-src="img/portfolio_01.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-search"></i>
                            </div>
                        </a>
        </div>

        <div class="item">
          <a data-rel="prettyPhoto" href="img/portfolio_02.jpg">
                        	<img class="lazyOwl" src="img/portfolio_02.jpg" data-src="img/portfolio_02.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-search"></i>
                            </div>
                        </a>
        </div>

        <div class="item">
          <a href="single-portfolio-1.html">
                        	<img class="lazyOwl" src="img/portfolio_07.jpg" data-src="img/portfolio_07.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-link"></i>
                            </div>
                        </a>
        </div>

        <div class="item">
          <a href="single-portfolio-1.html">
                        	<img class="lazyOwl" src="img/portfolio_05.jpg" data-src="img/portfolio_05.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-link"></i>
                            </div>
                        </a>
        </div>

        <div class="item">
          <a data-rel="prettyPhoto" href="img/portfolio_09.jpg">
                        	<img class="lazyOwl" src="img/portfolio_09.jpg" data-src="img/portfolio_09.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-search"></i>
                            </div>
                        </a>
        </div>

        <div class="item">
          <a data-rel="prettyPhoto" href="img/portfolio_10.jpg">
                        	<img class="lazyOwl" src="img/portfolio_10.jpg" data-src="img/portfolio_10.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-search"></i>
                            </div>
                        </a>
        </div>

        <div class="item">
          <a data-rel="prettyPhoto" href="img/portfolio_06.jpg">
                        	<img class="lazyOwl" src="img/portfolio_06.jpg" data-src="img/portfolio_06.jpg" alt="">
                        	<div>
                                <small>Product Design</small>
                                <span>Project Name Here</span>
                                <i class="fa fa-search"></i>
                            </div>
                        </a>
        </div>

      </div>
      <!-- end owl-demo -->
      <br>
    </div>
    <!-- end portfolio-wrapper -->
  </section>
  <!-- end section1 -->

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
  <script src="<%=request.getContextPath()%>/lib/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/lib/php-mail-form/validate.js"></script>
  <script src="<%=request.getContextPath()%>/lib/prettyphoto/js/prettyphoto.js"></script>
  <script src="<%=request.getContextPath()%>/lib/isotope/isotope.min.js"></script>
  <script src="<%=request.getContextPath()%>/lib/hover/hoverdir.js"></script>
  <script src="<%=request.getContextPath()%>/lib/hover/hoverex.min.js"></script>
  <script src="<%=request.getContextPath()%>/lib/unveil-effects/unveil-effects.js"></script>
  <script src="<%=request.getContextPath()%>/lib/owl-carousel/owl-carousel.js"></script>
  <script src="<%=request.getContextPath()%>/lib/jetmenu/jetmenu.js"></script>
  <script src="<%=request.getContextPath()%>/lib/animate-enhanced/animate-enhanced.min.js"></script>
  <script src="<%=request.getContextPath()%>/lib/jigowatt/jigowatt.js"></script>
  <script src="<%=request.getContextPath()%>/lib/easypiechart/easypiechart.min.js"></script>

  <!-- Template Main Javascript File -->
  <script src="<%=request.getContextPath()%>/js/main.js"></script>
  <script src="<%=request.getContextPath()%>/js/join.login.check.js"></script>
  <script src="<%=request.getContextPath()%>/js/authority.check.js"></script>

</body>
</html>