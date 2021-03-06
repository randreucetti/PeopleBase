<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Welcome to PeopleBase!</title>
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css"
	href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/plug-ins/1.10.7/integration/jqueryui/dataTables.jqueryui.css" />
<!-- jQuery -->
<script type="text/javascript" charset="utf8"
	src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!--  DataTables -->
<script type="text/javascript" charset="utf8"
	src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<script
	src="//cdn.datatables.net/plug-ins/1.10.7/integration/jqueryui/dataTables.jqueryui.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script>
	$(document).ready(
			function() {
				var table = $('#peopleTable').DataTable({
					"columnDefs" : [ {
						"targets" : [ 3 ],
						"visible" : false,
						"searchable" : false
					} ]
				});
				$.post("search-Person_search.action", {

				}, function(data, status) {
					var people = $.parseJSON(data).list;
					console.log(people);
					for (var i = 0; i < people.length; i++) {
						var person = people[i];
						genRow(person);
					}
				});

				function genRow(person) {
					table.row.add(
							[ person.firstName, person.surname,
									person.creationTime, person.id ]).draw();
				}

				$('#submit').click(
						function() {
							$("#errorMessagesDiv").html('');
							$.post("save-Person.action", {
								firstName : $('#firstName').val(),
								surname : $('#surname').val(),
								phone : $('#phone').val(),
								email : $('#email').val(),
								country : $('#country').val(),
								dateOfBirth : $('#dateOfBirth').val()
							}, function(data, status) {
								var errorList = $.parseJSON(data).errorList;
								if (errorList.length > 0) {
									for (var i = 0; i < errorList.length; i++) {
										$('#errorMessagesDiv').append(
												'<span class="red">'
														+ errorList[i].msg
														+ '</span>');
									}
								} else {
									var person = $.parseJSON(data).person;
									genRow(person);
									$("#displayDialog").dialog('close');
									clearInput();
								}
							});
							return false;
						});

				$("#displayDialog").dialog({
					autoOpen : false,
					width : 'auto'
				});

				$("#addPersonButton").button();

				$("#dateOfBirth").datepicker({
					maxDate : '0'
				});

				$("#addPersonButton").click(function() {
					$("#displayDialog").dialog('open');
					return false;
				});

				function clearInput() {
					$("#firstName").val('');
					$("#surname").val('');
					$("#phone").val('');
					$("#email").val('');
					$("#country").val('');
					$("#gender").val('');
					$("#dateOfBirth").val('');
				}
			});
</script>
<style>
.red {
	color: red;
	display: block;
	font-size: 11px;
	font-style: italic;
	font-size: 11px;
}
</style>
</head>
<body>
	<div id="tableDiv">
		<table id="peopleTable" class="display">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Surname</th>
					<th>Creation Time</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<br />
		<button id="addPersonButton">Add new</button>
	</div>


	<div id="displayDialog" title="Person display">
		<div id="errorMessagesDiv"></div>
		<s:form>
			<s:textfield id="firstName" name="firstName" label="First name"
				maxlength="30" requiredLabel="true" />
			<s:textfield id="surname" name="surname" label="Surname"
				maxlength="30" requiredLabel="true" />
			<s:textfield id="phone" name="phone" label="Phone number"
				maxlength="30" />
			<s:textfield id="email" name="email" label="Email address"
				maxlength="40" />
			<s:textfield id="country" name="country" label="Country" />
			<s:textfield id="gender" name="gender" label="Gender" />
			<s:textfield id="dateOfBirth" name="dateOfBirth"
				label="Date of birth" requiredLabel="true" />

			<s:submit id="submit" />
		</s:form>
	</div>
</body>
</html>