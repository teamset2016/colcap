<!DOCTYPE html>
<html ng-app="colcap">

<base href="/">

<head>
<jsp:include page="page-head.jsp" />
</head>

<body ng-controller="MainCtrl as mainCtrl" id="{{$state.current.name}}">
	<div ui-view></div>
	<jsp:include page="page-foot.jsp" />
</body>

</html>
