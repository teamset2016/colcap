<!DOCTYPE html>
<html ng-app="colcap">

<base href="/sample/">

<head>
<jsp:include page="page-head.jsp" />
</head>

<body ng-controller="SampleCtrl as sample" id="{{$state.current.name}}">
	<div ui-view></div>
	<jsp:include page="page-foot.jsp" />
</body>

</html>
