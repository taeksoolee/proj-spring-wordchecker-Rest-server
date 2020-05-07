<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<script type="text/javascript">
$.ajax({
	  method: "POST",
	  url: "member/login",
	  contentType: "application/json",
	  data: JSON.stringify({
		  "email": "test7@naver.com",
		  "password": "7bceec9b"
		})
	})
	 .done(function( success ) {
		setCookie('jwt', success.jwt, {minute: 1});
	    console.log();
	  });
	  
	  
	  setCookie = function(key, value, expires){
		  // expires = { date: "", hour: "", minute: ""}
		  var expiresDate = new Date();
		  if(typeof expires != "undefined"){
			if(typeof expires.date != "undefined"){
				expiresDate.setDate(expiresDate.getDate() + parseInt(expires.date));
			}
			if(typeof expires.hour != "undefined"){
				expiresDate.setHours(expiresDate.getHours() + parseInt(expires.hour));
			}
			if(typeof expires.minute != "undefined"){
				expiresDate.setMinutes(expiresDate.getMinutes() + parseInt(expires.minute));
			}
		  }
		  console.log(expiresDate);	
		  cookieSet = key + '=' + value + ';path=/;' + 'expires=' + expiresDate.toGMTString() + ';'
		  console.log(cookieSet);
		  document.cookie = cookieSet;
	  }
	  
	  
	  
	  
	  
</script>
</body>
</html>
