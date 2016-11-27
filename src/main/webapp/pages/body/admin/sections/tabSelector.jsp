<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab-selector">
	<c:choose>
		<c:when test="${ tab eq 'overview' }">
			<button class="active">Overview</button>
		</c:when>
		<c:otherwise>
			<button onclick="goToPage('overview')">Overview</button>
		</c:otherwise>
	</c:choose>
	<!-- hidden form -->
	<form id="tabSubmitForm" action="TestTab" method="post">
		<input id="tabSubmitField" type="hidden" name="tab" value="" />
	</form>
	<script>
		function goToPage(tab)
		{
			$("#tabSubmitField").val(tab);
			$("#tabSubmitForm").submit();
		}
	</script>
</div>