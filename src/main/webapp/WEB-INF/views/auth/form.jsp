<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group">
	<label for="firstNameInput">First name</label>
	<input type="text" class="form-control" id="firstNameInput" placeholder="Enter the first name" name="firstName"/>
</div>
<div class="form-group">
	<label for="lastNameInput">Last name</label>
	<input type="text" class="form-control" id="lastNameInput" placeholder="Enter the last name" name="lastName"/>
</div>
<div class="form-group">
	<label for="thirdNameInput">Third name</label>
	<input type="text" class="form-control" id="thirdNameInput" placeholder="Enter the third name" name="thirdName"/>
</div>
<div class="form-group">
	<label for="emailInput">Email address</label>
	<input type="email" class="form-control" id="emailInput" placeholder="Enter the email" name="email"/>
</div>
<div class="form-group">
	<label for="phoneInput">Phone</label>
	<input type="text" class="form-control" id="phoneInput" placeholder="Enter the phone" name="phone"/>
</div>
<div class="form-group">
	<label for="countryInput">Country</label>
	<select class="form-control" id="countryInput"  name="country">
		<option selected="selected" value="0">Select country</option>
		<c:forEach var="country" items="${countries}">
		<option value='${country.key}'>${country.value}</option>
		</c:forEach>	      	
	</select>
</div>
<div class="form-group">
	<label for="addressInput">Address</label>
	<input type="text" class="form-control" id="addressInput" placeholder="Enter the address" name="address"/>
</div>
<div class="form-group" style="overflow:hidden">
	<label style="width:100%">Your birthday</label>
	<select class="form-control"  name="birthDay" style="width:70px;float:left">
	<% for (int i = 1; i < 31; i++) {%>
		<option value='<%=i%>'><%=i%></option>
	<% }%>
	</select>
	<select class="form-control"  name="birthMonth" style="margin: 0 5px; width:179px;float:left">
	<c:forEach items="${months}" var="month"  varStatus="loopStatus">
		<option value='${loopStatus.index}'>${month}</option>
	</c:forEach>
	</select>
	<select class="form-control"  name="birthYear" style="width:90px;">
		<% for (int i = 2005; i > 1930; i--) {%>
		<option value='<%=i%>'><%=i%></option>
		<% }%>
	</select>
</div>


