$(function() {
    //form ẩn 1: add student, user
	$("#username_error_message").hide();
	$("#password_error_message").hide();
    $("#fullname_error_message").hide();
	$("#sex_error_message").hide();
    $("#doB_error_message").hide();
	$("#phonenumber_error_message").hide();
    $("#address_error_message").hide();
	$("#email_error_message").hide();
    $("#classID_error_message").hide();
    $("#luong_error_message").hide();
    $("#content_error_message").hide();
    $("#content_error_message2").hide();
    $("#content_error_message3").hide();
    $("#content_error_message4").hide();
    
	var error_username = false, error_password = false;
    var error_fullname = false, error_sex = false;
    var error_doB = false, error_phonenumber = false;
    var error_address = false, error_email = false;
    var error_classID = false, error_luong = false;
    var error_content = false, error_content2 = false;
    var error_content3 = false, error_content4 = false;
    
	$("#username").focusout(function() {
		check_username();
	});

	$("#password").focusout(function() {
		check_password();
	});
    $("#fullname").focusout(function() {
		check_fullname();
	});

	$("#sex").focusout(function() {
		check_sex();
	});
    $("#doB").focusout(function() {
		check_doB();
	});

	$("#phonenumber").focusout(function() {
		check_phonenumber();
	});
    $("#address").focusout(function() {
		check_address();
	});

	$("#email").focusout(function() {
		check_email();
	});
    $("#classID").focusout(function() {
		check_classID();
	});
    $("#luong").focusout(function() {
		check_luong();
	});
    
    $("#content").focusout(function() {
		check_content();
	});

	$("#content2").focusout(function() {
		check_content2();
	});
	$("#content3").focusout(function() {
		check_content3();
	});

	$("#content4").focusout(function() {
		check_content4();
	});
	
	function check_username() {
		var username_length = $("#username").val().length;

		if(username_length ==0) {
			$("#username_error_message").html("Please fill out this field");
			$("#username_error_message").show();
			error_username = true;
		} else if(username_length < 5 || username_length > 20) {
			$("#username_error_message").html("Must be between 5-20 characters");
			$("#username_error_message").show();      
			error_username = true;
		}
		else
		{
			$("#username_error_message").hide();
		}

	}
	
	
	function check_content() {
		var content_length = $("#content").val().length;

		if(content_length < 5 || content_length > 500) {
			$("#content_error_message").html("Must be between 5-500 characters");
			$("#content_error_message").show();      
			error_content = true;
		}
		else
		{
			$("#content_error_message").hide();
		}

	}
	
	function check_content2() {
		var content2_length = $("#content2").val().length;

		if(content2_length < 5 || content2_length > 500) {
			$("#content_error_message2").html("Must be between 5-500 characters");
			$("#content_error_message2").show();      
			error_content2 = true;
		}
		else
		{
			$("#content_error_message2").hide();
		}

	}
	
	
	function check_content3() {
		var content3_length = $("#content3").val().length;

		if(content3_length > 100) {
			$("#content_error_message3").html("Must be between 0-100 characters");
			$("#content_error_message3").show();      
			error_content3 = true;
		}
		else
		{
			$("#content_error_message3").hide();
		}

	}
	
	function check_content4() {
		var content4_length = $("#content4").val().length;

		if(content4_length > 100) {
			$("#content_error_message4").html("Must be between 0-100 characters");
			$("#content_error_message4").show();      
			error_content4 = true;
		}
		else
		{
			$("#content_error_message4").hide();
		}

	}
	function check_password() {

		var password_length = $("#password").val().length;

		if(password_length ==0) {
			$("#password_error_message").html("Please fill out this field");
			$("#password_error_message").show();
			error_password = true;
		} else if(password_length < 8) {
			$("#password_error_message").html("At least 8 characters");
			$("#password_error_message").show();
			error_password = true;

		} else {
			$("#password_error_message").hide();
		}
	}
    
    function check_fullname() {
		var fullname_length = $("#fullname").val().length;

		if(fullname_length ==0) {
			$("#fullname_error_message").html("Please fill out this field");
			$("#fullname_error_message").show();
			error_fullname = true;
		} else if(fullname_length < 5 || fullname_length > 40) {
			$("#fullname_error_message").html("Must be between 5-40 characters");
			$("#fullname_error_message").show();      
			error_fullname = true;
		}
		else
		{
			$("#fullname_error_message").hide();
		}

	}
    
     function check_sex() {
		var sex_length = $("#sex").val().length;

		if(sex_length ==0) {
			$("#sex_error_message").html("Please fill out this field");
			$("#sex_error_message").show();
			error_sex = true;
		} else if(sex_length < 2 || sex_length > 6) {
			$("#sex_error_message").html("Must be between 2-6 characters");
			$("#sex_error_message").show();      
			error_sex = true;
		}
		else
		{
			$("#sex_error_message").hide();
		}

	}

    function check_doB() {
        var pattern = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
	
		if(pattern.test($("#doB").val())) {
            $("#doB_error_message").hide(); 
        }
        else {
       
        	$("#doB_error_message").html("Must be date of birth format");
			$("#doB_error_message").show();      
			error_doB = true;
        }
	}
    
    function check_phonenumber() {
		var phonenumber_length = $("#phonenumber").val().length;

		var filter = /^[0-9-+]+$/;
        
		if(phonenumber_length ==0) {
			$("#phonenumber_error_message").html("Please fill out this field");
			$("#phonenumber_error_message").show();
			error_phonenumber = true;
		} else  if(filter.test($("#phonenumber").val()) < 1) {
            $("#phonenumber_error_message").html("Please enter a valid digit");
            $("#phonenumber_error_message").show();
        } else if(phonenumber_length < 10 || phonenumber_length > 13) {
			$("#phonenumber_error_message").html("Must be between 10-13 characters");
			$("#phonenumber_error_message").show();      
			error_phonenumber = true;
		}
		else
		{
			$("#phonenumber_error_message").hide();
		}

	}
    
     function check_address() {
		var address_length = $("#address").val().length;

		if(address_length == 0) {
			$("#address_error_message").html("Please fill out this field");
			$("#address_error_message").show();
			error_address = true;
		} else if(address_length < 5 || address_length > 50) {
			$("#address_error_message").html("Must be between 5-50 characters");
			$("#address_error_message").show();      
			error_address = true;
		}
		else
		{
			$("#address_error_message").hide();
		}

	}
    
    function check_email() {

		var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
	
		if(pattern.test($("#email").val())) {
			$("#email_error_message").hide();
		} else {
			$("#email_error_message").html("Invalid email address");
			$("#email_error_message").show();
			error_email = true;
		}
	
	}
    
    function check_classID() {
		var classID_length = $("#classID").val().length;

		if(classID_length ==0) {
			$("#classID_error_message").html("Please fill out this field");
			$("#classID_error_message").show();
			error_classID = true;
		} else if(classID_length < 1 || classID_length > 5) {
			$("#classID_error_message").html("Must be between 1-5 characters");
			$("#classID_error_message").show();      
			error_classID = true;
		}
		else
		{
			$("#classID_error_message").hide();
		}

	}
    
    function check_luong() {
		var luong_length = $("#luong").val().length;
        var filter = /^[0-9-+]+$/;
        
        if(luong_length ==0) {
			$("#luong_error_message").html("Please fill out this field");
			$("#luong_error_message").show();
			error_luong = true;
		} else if(filter.test($("#luong").val()) <1 ) {
            $("#luong_error_message").html("Please enter a valid digit");
            $("#luong_error_message").show();
            error_luong = true;
        } else if($("#luong").val() < 1000 || $("#luong").val() > 50000) {
			$("#luong_error_message").html("Must be between 1000-50000$");
			$("#luong_error_message").show();      
			error_luong = true;
		}
		else
		{
			$("#luong_error_message").hide();
		}

	}
    
	$("#login_form").submit(function() {

		error_username = false;
		error_password = false;
        error_fullname=false;
        error_sex=false;
        error_doB=false;
        error_phonenumber=false;
        error_address=false;
        error_email=false;
        error_classID=false;
        
		check_username();
		check_password();
        check_fullname();
        check_sex();
        check_doB();
        check_phonenumber();
        check_address();
        check_email();
        check_classID();
        
		if(error_username == false && error_password == false && error_fullname == false &&
        error_sex == false && error_doB == false && error_phonenumber == false && error_address == false
        && error_email == false && error_classID == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    
    //form ẩn 2: repair student, user
    $("#username_error_message2").hide();
	$("#password_error_message2").hide();
    $("#fullname_error_message2").hide();
	$("#sex_error_message2").hide();
    $("#doB_error_message2").hide();
	$("#phonenumber_error_message2").hide();
    $("#address_error_message2").hide();
	$("#email_error_message2").hide();
    $("#classID_error_message2").hide();
    $("#luong_error_message2").hide();

    var error_username2 = false;
	var error_password2 = false;
    var error_fullname2 = false, error_sex2 = false;
    var error_doB2 = false, error_phonenumber2 = false;
    var error_address2 = false, error_email2 = false;
    var error_classID2 = false, error_luong2 = false;

    
    $("#username2").focusout(function(){
       check_username2(); 
    });
	$("#password2").focusout(function() {
		check_password2();
	});
    $("#fullname2").focusout(function() {
		check_fullname2();
	});

	$("#sex2").focusout(function() {
		check_sex2();
	});
    $("#doB2").focusout(function() {
		check_doB2();
	});

	$("#phonenumber2").focusout(function() {
		check_phonenumber2();
	});
    $("#address2").focusout(function() {
		check_address2();
	});

	$("#email2").focusout(function() {
		check_email2();
	});
    $("#classID2").focusout(function() {
		check_classID2();
	});
    $("#luong2").focusout(function() {
		check_luong2();
	});
    
    function check_username2() {
		var username2_length = $("#username2").val().length;

		if(username2_length ==0) {
			$("#username_error_message2").html("Please fill out this field");
			$("#username_error_message2").show();
			error_username2 = true;
		} else if(username2_length < 5 || username2_length > 20) {
			$("#username_error_message2").html("Must be between 5-20 characters");
			$("#username_error_message2").show();      
			error_username2 = true;
		}
		else
		{
			$("#username_error_message2").hide();
		}

	}
    
	function check_password2() {

		var password2_length = $("#password2").val().length;

		if(password2_length ==0) {
			$("#password_error_message2").html("Please fill out this field");
			$("#password_error_message2").show();
			error_password2 = true;
		} else if(password2_length < 8) {
			$("#password_error_message2").html("At least 8 characters");
			$("#password_error_message2").show();
			error_password2 = true;

		} else {
			$("#password_error_message2").hide();
		}
	}
    
    function check_fullname2() {
		var fullname2_length = $("#fullname2").val().length;

		if(fullname2_length ==0) {
			$("#fullname_error_message2").html("Please fill out this field");
			$("#fullname_error_message2").show();
			error_fullname2 = true;
		} else if(fullname2_length < 5 || fullname2_length > 40) {
			$("#fullname_error_message2").html("Must be between 5-40 characters");
			$("#fullname_error_message2").show();      
			error_fullname2 = true;
		}
		else
		{
			$("#fullname_error_message2").hide();
		}

	}
    
     function check_sex2() {
		var sex2_length = $("#sex2").val().length;

		if(sex2_length ==0) {
			$("#sex_error_message2").html("Please fill out this field");
			$("#sex_error_message2").show();
			error_sex2 = true;
		} else if(sex2_length < 2 || sex2_length > 6) {
			$("#sex_error_message2").html("Must be between 2-6 characters");
			$("#sex_error_message2").show();      
			error_sex2 = true;
		}
		else
		{
			$("#sex_error_message2").hide();
		}

	}

    function check_doB2() {
        var pattern = new RegExp(/\b\d{1,2}[\/-]\d{1,2}[\/-]\d{4}\b/);
	
		if(pattern.test($("#doB2").val())) {
            $("#doB_error_message2").hide(); 
        }
        else {
       
        	$("#doB_error_message2").html("Must be date of birth format");
			$("#doB_error_message2").show();      
			error_doB2 = true;
        }
	}
    
    function check_phonenumber2() {
		var phonenumber2_length = $("#phonenumber2").val().length;
		var filter = /^[0-9-+]+$/;
		if(phonenumber2_length ==0) {
			$("#phonenumber_error_message2").html("Please fill out this field");
			$("#phonenumber_error_message2").show();
			error_phonenumber2 = true;
		} else  if(filter.test($("#phonenumber2").val()) < 1) {
            $("#phonenumber_error_message2").html("Please enter a valid digit");
            $("#phonenumber_error_message2").show();
        } else if(phonenumber2_length < 10 || phonenumber2_length > 13) {
			$("#phonenumber_error_message2").html("Must be between 10-13 characters");
			$("#phonenumber_error_message2").show();      
			error_phonenumber2 = true;
		}
		else
		{
			$("#phonenumber_error_message2").hide();
		}

	}
    
     function check_address2() {
		var address2_length = $("#address2").val().length;

		if(address2_length == 0) {
			$("#address_error_message2").html("Please fill out this field");
			$("#address_error_message2").show();
			error_address2 = true;
		} else if(address2_length < 5 || address2_length > 50) {
			$("#address_error_message2").html("Must be between 5-50 characters");
			$("#address_error_message2").show();      
			error_address2 = true;
		}
		else
		{
			$("#address_error_message2").hide();
		}

	}
    
    function check_email2() {

		var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
	
		if(pattern.test($("#email2").val())) {
			$("#email_error_message2").hide();
		} else {
			$("#email_error_message2").html("Invalid email address");
			$("#email_error_message2").show();
			error_email2= true;
		}
	
	}
    
    function check_classID2() {
		var classID2_length = $("#classID2").val().length;

		if(classID2_length ==0) {
			$("#classID_error_message2").html("Please fill out this field");
			$("#classID_error_message2").show();
			error_classID2 = true;
		} else if(classID2_length < 1 || classID2_length > 5) {
			$("#classID_error_message2").html("Must be between 1-5 characters");
			$("#classID_error_message2").show();      
			error_classID2 = true;
		}
		else
		{
			$("#classID_error_message2").hide();
		}

	}
    function check_luong2() {
		var luong2_length = $("#luong2").val().length;
        var filter = /^[0-9-+]+$/;
        
        if(luong2_length ==0) {
			$("#luong_error_message2").html("Please fill out this field");
			$("#luong_error_message2").show();
			error_luong2 = true;
		} else if(filter.test($("#luong2").val()) <1 ) {
            $("#luong_error_message2").html("Please enter a valid digit");
            $("#luong_error_message2").show();
            error_luong2 = true;
        } else if($("#luong2").val() < 1000 || $("#luong2").val() > 50000) {
			$("#luong_error_message2").html("Must be between 1000-50000$");
			$("#luong_error_message2").show();      
			error_luong2 = true;
		}
		else
		{
			$("#luong_error_message2").hide();
		}

	}
    
      //form ẩn LOGIN
	$("#login_form7").submit(function() {

		error_username = false;
		error_password = false;

        
		check_username();
		check_password();
  
        
		if(error_username == false && error_password == false) {
			return true;
		} else {
			return false;	
		}

	});

    //form ẩn REGISTER
	$("#login_form6").submit(function() {

		error_username2 = false;
		error_password2 = false;
        error_fullname=false;
        error_address=false;
        error_email=false;
        
		check_username2();
		check_password2();
        check_fullname();
        check_address();
        check_email();
        
		if(error_username2 == false && error_password2 == false && error_fullname == false && error_address == false
        && error_email == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    //form add question with 4 options
	$("#questionID_error_message").hide();
	$("#content_error_message").hide();
    
    var error_questionID = false;
    var error_content = false;
    
	$("#questionID").focusout(function() {
		check_questionID();
	});

	$("#content").focusout(function() {
		check_content();
	});
    
    function check_questionID() {
		var questionID_length = $("#questionID").val().length;

		if(questionID_length ==0) {
			$("#questionID_error_message").html("Please fill out this field");
			$("#questionID_error_message").show();
			error_questionID = true;
		} else if(questionID_length < 1 || questionID_length > 5) {
			$("#questionID_error_message").html("Must be between 1-5 characters");
			$("#questionID_error_message").show();      
			error_questionID = true;
		}
		else
		{
			$("#questionID_error_message").hide();
		}

	}
    function check_content() {
		var content_length = $("#content").val().length;

		if(content_length ==0) {
			$("#content_error_message").html("Please fill out this field");
			$("#content_error_message").show();
			error_content = true;
		} else if(content_length < 5 || content_length > 150) {
			$("#content_error_message").html("Must be between 5-150 characters");
			$("#content_error_message").show();      
			error_content = true;
		}
		else
		{
			$("#content_error_message").hide();
		}

	}
    
    $("#login_form_4options").submit(function() {

		error_questionID = false;
		error_content = false;

        
		check_questionID();
		check_content();

        
		if(error_questionID == false && error_content == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
   //form add question with 3 options
    $("#questionID_error_message2").hide();
	$("#content_error_message2").hide();
    
    var error_questionID2 = false;
    var error_content2 = false;
    
    $("#questionID2").focusout(function() {
		check_questionID2();
	});

	$("#content2").focusout(function() {
		check_content2();
	});
    
    function check_questionID2() {
		var questionID2_length = $("#questionID2").val().length;

		if(questionID2_length ==0) {
			$("#questionID_error_message2").html("Please fill out this field");
			$("#questionID_error_message2").show();
			error_questionID2 = true;
		} else if(questionID2_length < 1 || questionID2_length > 5) {
			$("#questionID_error_message2").html("Must be between 1-5 characters");
			$("#questionID_error_message2").show();      
			error_questionID2 = true;
		}
		else
		{
			$("#questionID_error_message2").hide();
		}

	}
    function check_content2() {
		var content2_length = $("#content2").val().length;

		if(content2_length ==0) {
			$("#content_error_message2").html("Please fill out this field");
			$("#content_error_message2").show();
			error_content2 = true;
		} else if(content2_length < 5 || content2_length > 150) {
			$("#content_error_message2").html("Must be between 5-150 characters");
			$("#content_error_message2").show();      
			error_content2 = true;
		}
		else
		{
			$("#content_error_message2").hide();
		}

	}
    $("#login_form_3options").submit(function() {

		error_questionID2 = false;
		error_content2 = false;

        
		check_questionID2();
		check_content2();

        
		if(error_questionID2 == false && error_content2 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    
    //form repair question with 4 options
	$("#content_error_message3").hide();
    
    var error_content3 = false;
    
	$("#content3").focusout(function() {
		check_content3();
	});
    
    function check_content3() {
		var content3_length = $("#content3").val().length;

		if(content3_length ==0) {
			$("#content_error_message3").html("Please fill out this field");
			$("#content_error_message3").show();
			error_content3 = true;
		} else if(content3_length < 5 || content3_length > 150) {
			$("#content_error_message3").html("Must be between 5-150 characters");
			$("#content_error_message3").show();      
			error_content3 = true;
		}
		else
		{
			$("#content_error_message3").hide();
		}

	}
    $("#login_form_repair_4ops").submit(function() {

		error_content3 = false;
        
		check_content3();

		if(error_content3 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    //form repair question with 3 options
	$("#content_error_message4").hide();
    
    var error_content4 = false;
    
	$("#content4").focusout(function() {
		check_content4();
	});
    
    function check_content4() {
		var content4_length = $("#content4").val().length;

		if(content4_length ==0) {
			$("#content_error_message4").html("Please fill out this field");
			$("#content_error_message4").show();
			error_content4 = true;
		} else if(content4_length < 5 || content4_length > 150) {
			$("#content_error_message4").html("Must be between 5-150 characters");
			$("#content_error_message4").show();      
			error_content4 = true;
		}
		else
		{
			$("#content_error_message4").hide();
		}

	}
    
    $("#login_form_repair_3ops").submit(function() {

		error_content4 = false;
        
		check_content4();

		if(error_content4 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    //form add content Group, class, option, authority
    $("#contentGrID_error_message").hide();
	$("#contentGrName_error_message").hide();
	$("#audio_error_message").hide();
	
    var error_contentGrID = false;
    var error_contentGrName = false;
    var error_audio = false;
    
    $("#contentGrID").focusout(function() {
		check_contentGrID();
	});

	$("#contentGrName").focusout(function() {
		check_contentGrName();
	});
	$("#audio").focusout(function() {
		check_audio();
	});
	
    function check_contentGrID() {
		var contentGrID_length = $("#contentGrID").val().length;

		if(contentGrID_length ==0) {
			$("#contentGrID_error_message").html("Please fill out this field");
			$("#contentGrID_error_message").show();
			error_contentGrID = true;
		} else if(contentGrID_length < 1 || contentGrID_length > 5) {
			$("#contentGrID_error_message").html("Must be between 1-5 characters");
			$("#contentGrID_error_message").show();      
			error_contentGrID = true;
		}
		else
		{
			$("#contentGrID_error_message").hide();
		}

	}
    function check_contentGrName() {
		var contentGrName_length = $("#contentGrName").val().length;

		if(contentGrName_length ==0) {
			$("#contentGrName_error_message").html("Please fill out this field");
			$("#contentGrName_error_message").show();
			error_contentGrName = true;
		} else if(contentGrName_length < 5 || contentGrName_length > 20) {
			$("#contentGrName_error_message").html("Must be between 5-20 characters");
			$("#contentGrName_error_message").show();      
			error_contentGrName = true;
		}
		else
		{
			$("#contentGrName_error_message").hide();
		}

	}
    function check_audio() {
		var audio_length = $("#audio").val().length;
		//var urlregex = new RegExp("^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
		
		if(audio_length ==0) {
			$("#audio_error_message").html("Please fill out this field");
			$("#audio_error_message").show();
			error_audio = true;
		} else {
			
			$("#audio_error_message").hide();
		}

	}
    
    $("#login_form_contentGr").submit(function() {

		//error_contentGrID = false;
		error_contentGrName = false;
        
		//check_contentGrID();
		check_contentGrName();
        
		if(error_contentGrName == false) {
			return true;
		} else {
			return false;	
		}

	});
    //form contentgr
    $("#login_form_audio").submit(function() {

		//error_contentGrID = false;
		error_contentGrName = false;
		error_audio = false;
        
		//check_contentGrID();
		check_contentGrName();
		check_audio();
        
		if(error_contentGrName == false && error_audio == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    //form repair content Group, class, option, authority
	$("#contentGrName_error_message2").hide();
	$("#audio_error_message2").hide();
	
    var error_contentGrName2 = false;
    var error_audio2 = false;
    
	$("#contentGrName2").focusout(function() {
		check_contentGrName2();
	});
	$("#audio2").focusout(function() {
		check_audio2();
	});
	
    function check_contentGrName2() {
		var contentGrName2_length = $("#contentGrName2").val().length;

		if(contentGrName2_length ==0) {
			$("#contentGrName_error_message2").html("Please fill out this field");
			$("#contentGrName_error_message2").show();
			error_contentGrName2 = true;
		} else if(contentGrName2_length < 5 || contentGrName2_length > 20) {
			$("#contentGrName_error_message2").html("Must be between 5-20 characters");
			$("#contentGrName_error_message2").show();      
			error_contentGrName2 = true;
		}
		else
		{
			$("#contentGrName_error_message2").hide();
		}

	}
    function check_audio2() {
		var audio2_length = $("#audio2").val().length;
		//var urlregex = new RegExp("^(http:\/\/www.|https:\/\/www.|ftp:\/\/www.|www.){1}([0-9A-Za-z]+\.)");
		
		if(audio2_length == 0) {
			$("#audio_error_message2").html("Please fill out this field");
			$("#audio_error_message2").show();
			error_audio2 = true;
		} else {
			
			$("#audio_error_message2").hide();
		}

	}
    
    $("#login_form_contentGr2").submit(function() {

		error_contentGrName2 = false;
		
		check_contentGrName2();
		
		if(error_contentGrName2 == false) {
			return true;
		} else {
			return false;	
		}

	});
  //form contentgr
    $("#login_form_audio2").submit(function() {

		error_contentGrName2 = false;
		error_audio2 = false;
		
		check_contentGrName2();
		check_audio2();
		
		if(error_contentGrName2 == false && error_audio2 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
  //form class, add
    $("#login_form_class").submit(function() {

		error_contentGrName = false;
		error_classID = false;
		
		check_contentGrName();
		check_classID();
		
		if(error_contentGrName == false && error_classID == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    //add, edit, quess
    $("#login_form_question").submit(function() {

		error_content = false;
		
		check_content();
		
		if(error_content == false) {
			return true;
		} else {
			return false;	
		}
	});
    
    $("#login_form_question2").submit(function() {

		error_content2 = false;
		
		check_content2();
		
		if(error_content2 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
    //add, edit, option
    $("#login_form_option").submit(function() {

		error_content3 = false;
		
		check_content3();
		
		if(error_content3 == false) {
			return true;
		} else {
			return false;	
		}
	});
    
    $("#login_form_option2").submit(function() {

		error_content4 = false;
		
		check_content4();
		
		if(error_content4 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    
  //form class, edit
    $("#login_form_class2").submit(function() {

		error_contentGrName2 = false;
		error_classID2 = false;
		
		check_contentGrName2();
		check_classID2();
		
		if(error_contentGrName2 == false && error_classID2 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    //form xóa
    $("#login_form_5options").submit(function() {

		error_contentGrID = false;
        
		check_contentGrID();
        
		if(error_contentGrID == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    //form add student, user
    $("#login_form_studentuser").submit(function() {

		error_username = false;
		error_password = false;
        error_fullname=false;
        error_phonenumber=false;
        error_address=false;
        error_email=false;
        
		check_username();
		check_password();
        check_fullname();
        check_phonenumber();
        check_address();
        check_email();
        
		if(error_username == false && error_password == false && error_fullname == false &&
         error_phonenumber == false && error_address == false && error_email == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    //form repair student, user
    $("#login_form_studentuser2").submit(function() {
        //error_username2 = false;
		error_password2 = false;
        error_fullname2=false;
        error_phonenumber2=false;
        error_address2=false;
        error_email2=false;
        
        //check_username2()
		check_password2();
        check_fullname2();
        check_phonenumber2();
        check_address2();
        check_email2();
        
		if(error_password2 == false && error_fullname2 == false &&
         error_phonenumber2 == false && error_address2 == false && error_email2 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
  //form add student, user
    $("#login_form_user").submit(function() {

		error_username = false;
		error_password = false;
        error_fullname=false;
        error_phonenumber=false;
        error_address=false;
        error_email=false;
        error_luong = false;
        
		check_username();
		check_password();
        check_fullname();
        check_phonenumber();
        check_address();
        check_email();
        check_luong();
        
		if(error_username == false && error_password == false && error_fullname == false &&
         error_phonenumber == false && error_address == false && error_email == false && error_luong==false) {
			return true;
		} else {
			return false;	
		}

	});
    
    //form repair student, user
    $("#login_form_user2").submit(function() {
        //error_username2 = false;
		error_password2 = false;
        error_fullname2=false;
        error_phonenumber2=false;
        error_address2=false;
        error_email2=false;
        error_luong2 = false;

        //check_username2()
		check_password2();
        check_fullname2();
        check_phonenumber2();
        check_address2();
        check_email2();
        check_luong2();

		if(error_password2 == false && error_fullname2 == false &&
         error_phonenumber2 == false && error_address2 == false && error_email2 == false && error_luong2 == false) {
			return true;
		} else {
			return false;	
		}

	});
    
    /*
     document.getElementById("edit_on").onclick = function Edit_Click() {//nhận vào object là mybutton
            //if(document.getElementById("input_on").disabled=true){
                document.getElementById("fullname").disabled=false;
                document.getElementById("username").disabled=false;
                document.getElementById("password").disabled=false;
                document.getElementById("email").disabled=false;
                document.getElementById("address").disabled=false;
                document.getElementById("doB").disabled=false;
                document.getElementById("phonenumber").disabled=false;
                document.getElementById("radioStacked1").disabled=false;
                document.getElementById("radioStacked2").disabled=false;
                document.getElementById("save_on").disabled=false;
                document.getElementById("edit_on").disabled=true;
            
            //}
        };
        document.getElementById("save_on").onclick = function Save_Click() {//nhận vào object là save_on
            	error_username = false;
		error_password = false;
        error_fullname=false;
        error_phonenumber=false;
        error_address=false;
        error_email=false;
        
		check_username();
		check_password();
        check_fullname();
        check_phonenumber();
        check_address();
        check_email();
            if(error_username == false && error_password == false && error_fullname == false &&
         error_phonenumber == false && error_address == false && error_email == false){
                document.getElementById("fullname").disabled=true;
                document.getElementById("username").disabled=true;
                document.getElementById("password").disabled=true;
                document.getElementById("email").disabled=true;
                document.getElementById("address").disabled=true;
                document.getElementById("doB").disabled=true;
                document.getElementById("phonenumber").disabled=true;
                document.getElementById("radioStacked1").disabled=true;
                document.getElementById("radioStacked2").disabled=true;
                document.getElementById("save_on").disabled=true;
                document.getElementById("edit_on").disabled=false;
            }
            else {
                alert("Xin vui lòng điền tất cả thay đổi.");
            }
        };
    */
  
     // end close button event handler
});
