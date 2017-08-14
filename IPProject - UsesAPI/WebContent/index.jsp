<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Use Patient API - IP Project</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body onload="showPatients()">

	<h1>Overview patients (from Patient API)</h1>
	<div id="patients"></div>
	
	<%@include file="footer.jspf" %>

	<script type="text/javascript">
		function showPatients() {
			var patientsDiv = document.getElementById("patients");
			patientsDiv.innerHTML = " ";
			$.getJSON('http://localhost:8080/ip-project/rest/patients.htm',
					function(result) {
						var patients = " ";
						$.each(result, function(i, patient) {
							var patientInfo = " ";
							patientInfo += "<div class=\"patient\">";
							patientInfo = "<h3> " + patient.firstName + " "
									+ patient.lastName + "</h3>";
							patientInfo += "Address: " + patient.address.street
									+ " " + patient.address.houseNumber + " "
									+ patient.address.addOn + ", "
									+ patient.address.city + " "
									+ patient.address.state + ", "
									+ patient.address.city + "<br>";
							patientInfo += "Weight: " + patient.weightInKg
									+ "kg <br>";
							patientInfo += "Height: " + patient.heightInCm
									+ "cm <br>";
							patientInfo += "Age: " + patient.age + "<br>";
							patientInfo += "</div>"
							patients += patientInfo;
						});
						$('#patients').html(patients);
						setTimeout(showPatients, 5000);
					});
		}
	</script>

</body>
</html>