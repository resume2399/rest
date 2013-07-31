<html>
<head>
	<script type="text/javascript" src="resources/jquery/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
		  $("#helloBtn").click(function(){
			  var name = $("#name").val();
			  $.get("rest/hello/"+name,function(response,status){
				    alert("Response: " + response);
			  });
		  });
		});

	</script>
</head>
	<body>
		<form>
			<p>
				Enter Name <input name="name" id="name">
				<input type="button" id="helloBtn" value="Click"> 
			</p>
		</form>
	</body>
</html>