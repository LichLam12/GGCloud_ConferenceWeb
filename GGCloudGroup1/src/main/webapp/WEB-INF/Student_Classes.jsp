<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Monggogo Toeic - Admin</title>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <!-- Icon của button login -->
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
   
      
    <!-- Bootstrap core CSS -->
    <link href="Style/css/bootstrap.css" rel="stylesheet">
    <link href="Style/css/img-hover.css" rel="stylesheet">
    <link href="Style/css/header.css" rel="stylesheet">
    <link href="Style/css/style.css" rel="stylesheet">
    <link href="Style/css/hiddenform_management.css" rel="stylesheet">

    <script src="Style/js/jquery-3.2.1.min.js"></script>
    <!-- thư viện jquery -->
    <script src="Style/js/glm-ajax.js"></script>
    <script src="Style/js/login_large.js"></script>
    <!-- validate from by jquery -->
    <script src="Style/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
        	
        	function autopage(){
        		$('.page-number').remove(); //k xóa là nó sinh ra phân trang hoài
        		$('table.paginated').each(function() {
					var currentPage = 0;
					var numPerPage = 6;  /* Muốn hiển thị 1 page có bao nhiêu hàng */
					var $table = $(this);
					$table.bind('repaginate', function() {
						$table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
					});
					$table.trigger('repaginate');
					var numRows = $table.find('tbody tr').length;
					var numPages = Math.ceil(numRows / numPerPage);
					var $pager = $('<div class="pager"></div>');
                   var pre="",next="next";
                   if(numPages>1){
                   	pre="<";
                   	next=">";
                   }else{
                   	pre="";
                   	next="";
                   }
                   if(numPages>1){
                       $('<span class="page-number"></span>').text(pre).bind('click', {
                               newPage: page
                           }, function(event) {
                               currentPage = currentPage-1;
                           if(currentPage<0){
                               currentPage=0;
                           }else if(currentPage>numPages){
                               currentPage=numPages-1;
                           }
                                   $table.trigger('repaginate');
                                   $(this).addClass('active').siblings().removeClass('active');

                           }).appendTo($pager).addClass('clickable');
                   }
					for (var page = 0; page < numPages; page++) {
						$('<span class="page-number"></span>').text(page + 1).bind('click', {
							newPage: page
						}, function(event) {
							currentPage = event.data['newPage'];
							$table.trigger('repaginate');
							$(this).addClass('active').siblings().removeClass('active');
						}).appendTo($pager).addClass('clickable');
					}
					if(numPages>1){
                   $('<span class="page-number"></span>').text(next).bind('click', {
                               newPage: page
                           }, function(event) {
                               currentPage = currentPage+1;
                       if(currentPage<0){
                               currentPage=0;
                           }else if(currentPage>numPages){
                               currentPage=numPages-1;
                           }
                       
                           if(currentPage<numPages){                                                                                             $table.trigger('repaginate');
                               $(this).addClass('active').siblings().removeClass('active');

                           }
                           }).appendTo($pager).addClass('clickable');
                   
					$pager.insertAfter($table).find('span.page-number:first').addClass('active').css({'margin-left':'50px'});
					}
				});
        	}
        	
        	var studentID=document.getElementById('yourname').innerText;
        	
        	 $.post("act_Student",{action : '7',studentID : studentID}, function(responseJson) {          // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
 		        //var $table = $("<table>").appendTo($("#somediv")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
 		        if (responseJson.check == "fail") {
		                    //$('#message').text("List isEmpty! Name not found!");
		                    //$('#message').css('color', 'red');
		                  	alert("You don't any class!");
		                    return;
		        }
		        var $table = document.getElementById("row");
 		        $.each(responseJson, function(index, product) {    // Iterate over the JSON array.
 		        	var edate = new Date(product.examDate);
 		        	//alert(edate.getHours()+":"+edate.getMinutes()+":"+edate.getSeconds());
					var nowdate=new Date();

					if((edate.getDate()==nowdate.getDate() && edate.getMonth()==nowdate.getMonth() &&
							edate.getFullYear()==nowdate.getFullYear() && edate.getHours()==nowdate.getHours()
							&& edate.getMinutes()==nowdate.getMinutes() && edate.getSeconds()==nowdate.getSeconds())
							|| (edate.getDate()<nowdate.getDate() && edate.getMonth()==nowdate.getMonth() && edate.getFullYear()==nowdate.getFullYear())
							|| edate.getFullYear()<nowdate.getFullYear() || (edate.getMonth()<nowdate.getMonth() && edate.getFullYear()==nowdate.getFullYear())
							|| (edate.getHours()<nowdate.getHours() && edate.getDate()==nowdate.getDate() && edate.getMonth()==nowdate.getMonth() && edate.getFullYear()==nowdate.getFullYear())
							|| (edate.getMinutes()<nowdate.getMinutes() && edate.getHours()==nowdate.getHours() && edate.getDate()==nowdate.getDate() && edate.getMonth()==nowdate.getMonth() && edate.getFullYear()==nowdate.getFullYear())
							|| (edate.getSeconds()<nowdate.getSeconds() && edate.getMinutes()==nowdate.getMinutes() && edate.getHours()==nowdate.getHours() && edate.getDate()==nowdate.getDate() && edate.getMonth()==nowdate.getMonth() && edate.getFullYear()==nowdate.getFullYear())){
						$("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
 		                .append($("<td>").text(product.className))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
 		               	.append($("<td>").text(product.examTime))
 		               	.append($("<td>").text(edate.getDate() + "/" + (edate.getMonth() + 1) + "/" + edate.getFullYear()))
 		               	.append($("<td>").text(product.mark))
 		                .append($("<td>")
 		                		.append($("<a>")
 		                				.append($("<i>").addClass("fa fa-play"))
 		                			   )
 		                				.click(function(){
 		                					if(product.mark > 0 && product.mark <= 10){
 		                						alert("You finished this exam.");
 		                					}
 		                					else{
  	  		                		           window.location.href = 'ForwardToExamPage?examID='+product.examID+'&className='+product.className+'&studentID='+studentID;
 		                					}

 		                				})
 		                )
					}else{
						$("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
 		                .append($("<td>").text(product.className))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
 		               	.append($("<td>").text(product.examTime))
 		               	.append($("<td>").text(edate.getDate() + "/" + (edate.getMonth() + 1) + "/" + edate.getFullYear()))
 		               	.append($("<td>").text(product.mark))
 		                .append($("<td>")
 		                		.append($("<a>")
 		                				.append($("<i>").addClass("fa fa-circle-o-notch fa-spin fa-1x fa-fw"))
 		                			   )
 		                				.click(function(){
 		                		           	alert("Now is not a exam time. Please check it.");
 		                				})
 		                )
					}
 		        	
 		        });autopage();
 		    });
        });
	</script>
</head>
<body>
	<!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="padding: 0 10px">
		<a class="navbar-brand" href="ForwardToLoginedHome2?username=${perinfo.username }"><img src="Style/icon/logo.png" height="50px" style="margin-left: 30px"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    		<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse lead" id="navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="ForwardToLoginedHome2?username=${perinfo.username }">HOME <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="ForwardToPerinfo_Student?username=${perinfo.username }">PERSIONAL INFO</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="ForwardToClass_Student?username=${perinfo.username }">CLASSES</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="HomeForward">LOG OUT</a>
				</li>
			</ul>
			<div id="imaginary_container"> 
                <div class="input-group stylish-input-group input-append">
                    <input type="text" class="form-control"  placeholder="Search" >
                    <span class="input-group-addon">
                        <button type="submit">
                            <i class="fa fa-search" aria-hidden="true"></i>
                        </button>  
                    </span>
                </div>
            </div>
		</div>
	</nav>


    <!-- Cover -->
    <div id="cover" class="carousel">
        <img class="d-block w-100" src="Style/images/cover/cover1.jpg" alt="First slide">

        <div class="carousel-caption">
            <h2><span class="fullname_avt">${perinfo.fullName }</span><span> (</span><span class="fullname_avt" id="yourname">${perinfo.studentID }</span><span>)</span></h2>
        </div>
    </div>
  
   
    <!-- Authority Manegement -->
    
     <div class="container tieude" id="content-gr">
         		<h2 class="text-center" style="color: red;font-weight: bold;margin-bottom: 60px !important;">SCHEDULE INFOMATION</h2>
        <p class="text-right">
        </p>
	
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped pre-scrollable2" id="productTable">
						<thead>
							<tr class="text-center">
								<th class="text-center">Class Name</th>
                                <th class="text-center">Exam Time</th>
                                <th class="text-center">Exam Date</th>
                                <th class="text-center">Result</th>
                                <th class="text-center">Start Exam</th>
							</tr>
						</thead>
						<tbody id="row" class="text-center">
						
						
						
						</tbody>
					</table>
				
				</div>
		<script src="Style/js/classie.js"></script>
   	<script src="Style/js/modalEffects.js"></script>
    </div>
     

    <!-- Footer -->
    <footer id=contact>
        <div class="splitter"></div>
        <ul>
            <li>
                <div class="text">
                    <h4>MONGGOGO TOEIC</h4>
                    <div>
                        <p class="lead"><i class="fa fa-map" aria-hidden="true" style="padding-right: 12px"></i>Address: 1040 Vo Van Ngan, Thu Duc District, HCMC</p>
                        <p class="lead"><i class="fa fa-address-book" aria-hidden="true" style="padding-right: 12px"></i>Phone: (088) 6677022</p>
                        <p class="lead"><i class="fa fa-envelope-open" aria-hidden="true" style="padding-right: 12px"></i>Email: monggogotoeic@edu.vn</p>
                        <a href="#">Read more...</a></div>
                </div>
            </li>

            <li>
                <div class="text">
                    <h4>SOCIAL</h4>
                    <p class="lead social-icons">Connect us:
                        <a href=""> <i class="fa fa-facebook-square"></i></a>
                        <a href="" class="tw"> <i class="fa fa-twitter-square"></i></a>
                        <a href="" class="rs"> <i class="fa fa-rss-square"></i></a>
                        <a href="" class="yt"> <i class="fa fa-youtube-square"></i></a>
                        <a href="" class="li"> <i class="fa fa-linkedin-square"></i></a>
                        <a href="" class="gp"> <i class="fa fa-google-plus-square"></i></a>
                    </p>


                    <p class="lead">Please rate your overall experience:</p>
                    <fieldset class="rating">
                        <input type="radio" id="star5" name="rating" value="5" /><label class="full" for="star5" title="Awesome - 5 stars"></label>
                        <input type="radio" id="star4half" name="rating" value="4 and a half" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
                        <input type="radio" id="star4" name="rating" value="4" /><label class="full" for="star4" title="Pretty good - 4 stars"></label>
                        <input type="radio" id="star3half" name="rating" value="3 and a half" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
                        <input type="radio" id="star3" name="rating" value="3" /><label class="full" for="star3" title="Meh - 3 stars"></label>
                        <input type="radio" id="star2half" name="rating" value="2 and a half" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
                        <input type="radio" id="star2" name="rating" value="2" /><label class="full" for="star2" title="Kinda bad - 2 stars"></label>
                        <input type="radio" id="star1half" name="rating" value="1 and a half" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
                        <input type="radio" id="star1" name="rating" value="1" /><label class="full" for="star1" title="Sucks big time - 1 star"></label>
                        <input type="radio" id="starhalf" name="rating" value="half" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
                    </fieldset>

                </div>
            </li>
        </ul>

        <div class="bar">
            <div class="clear"></div>
            <div class="copyright lead">&copy; 2017 Monggogo Toeic All Rights Reserved</div>
        </div>
    </footer>

<style>
    div.pager {
    text-align: center;
}

div.pager span {
    display: inline-block;
    width: 32px;
    height: 38px;
    text-align: center;
    cursor: pointer;
    background: #f2f2f2;
    color: #000;
    margin-top: 15px;
	font-size: 16px;
	margin-right: 0px;
	font-weight: normal;
	border: 1px solid #d9d9d9;
	padding-top:5px;
}

div.pager span.active {
    background: #d9d9d9;
    color: #4d88ff;
}
div.pager span:hover {
    background: #d9d9d9;
    color: #4d88ff;
}
    </style>



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<!--     <script src="./Carousel Template for Bootstrap_files/jquery-3.2.1.slim.min.js.download" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->

<!--     <script src="./Carousel Template for Bootstrap_files/popper.min.js.download"></script> -->
<!--     <script src="./Carousel Template for Bootstrap_files/bootstrap.min.js.download"></script> -->
<!--     Just to make our placeholder images work. Don't actually copy the next line! -->
<!--     <script src="./Carousel Template for Bootstrap_files/holder.min.js.download"></script> -->
<!--     IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!--     <script src="./Carousel Template for Bootstrap_files/ie10-viewport-bug-workaround.js.download"></script> -->
</body>
</html>