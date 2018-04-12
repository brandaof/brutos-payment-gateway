	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title>Brutos Payment gateway example</title>
	
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">

	<script type="text/javascript">
		
		contextPath = "${pageContext.request.contextPath}";

		function get(resource, dest){
			if(dest == null){
				location.href = contextPath + resource;
			}
			else{
				execute("get", resource, dest);
			}
		}

		function execute(method, resource, dest){
			$.ajax({
			    type: method,
			    url: contextPath + resource,
			    success: function(data) {
			    	if(dest == null){
				    	document.write(data);
				    	document.close();
			    	}
			    	else{
						$("#" + dest).html(data);
			    	}
	            }			    
			});
		}
		
		$(function(){
			$('#paymentType').change(function(event) {
				var paymentType = $('#paymentType').val();
				get('/payment/' + paymentType, "paymentContent");
		    }); 			
		});
		
	</script>
	<style>
		html {
		  position: relative;
		  min-height: 100%;
		}
		body {
			padding-top: 60px;
		  	margin-bottom: 60px;
		}
		
		footer {
		  position: absolute;
		  bottom: 0;
		  width: 100%;
		  height: 60px; /* Set the fixed height of the footer here */
		}
		
		/*	
		body {
			padding-top: 56px;
		}

		section {
			width: 100%;
		}
		
		@media ( min-width : 992px) {
			body {
				padding-top: 58px;
			}
		}
		*/
	</style>
