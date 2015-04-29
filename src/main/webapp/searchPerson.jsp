<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Welcome to PeopleBase!</title>
<link rel="stylesheet" href="css/jquery-ui.css" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
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
							console.log(person);
							$("#peopleTable tbody")
									.append(
											"<tr>"
													+ '<td><a href="display-Person_open.action?id='
													+ person.id
													+ '" style="text-decoration:none;">'
													+ person.firstName
													+ "</td>" + "<td>"
													+ person.surname + "</td>"
													+ "<td>"
													+ person.creationTime
													+ "</td>" + "</tr>");
							addStyle();
						}

						function addStyle() {
							$("tbody tr").hover(function() {
								$(this).addClass("ui-state-hover");
							}, function() {
								$(this).removeClass("ui-state-hover");
							});
							$("tbody tr").click(
									function() {
										window.location.href = $(this)
												.find("a").attr("href");
									});
						}
						$("#inputPerson").dialog({
							autoOpen : false
						});
						$("#addNew").button();

						$("#addNew").click(function() {
							$('#inputPerson').dialog('open');
							return false;
						});
					});
</script>
</head>
<body>
	<section>
	<table id="peopleTable" class="ui-widget"
		style="border-collapse: collapse;">
		<thead class="ui-widget-header">
			<tr>
				<th rowspan="2">First Name</th>
				<th rowspan="2">Surname</th>
				<th rowspan="2">Creation Time</th>
			</tr>
		</thead>
		<tbody class="ui-widget-content">
		</tbody>
	</table>
	<button id="addNew">Add Person</button>
	</section>
	<div id="inputPerson" title="Add/Modify Person">
		<form>
		</form>
	</div>
</body>
</html>