<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>
<%@ taglib prefix="timestampTag" uri="/WEB-INF/tags/timestamp.tld"%>
<html>
<head>

<!--common css for all pages-->
<%@ include file="../shared/importCss.jsp"%>

<!--css for this page-->

</head>

<body>

	<section id="container"> <!--header start--> <%@ include file="../shared/pageHeader.jsp"%>
	<!--header end--> <!--sidebar start--> <%@ include file="../shared/sidebarMenu.jsp"%> <!--sidebar end-->

	<!--main content start--> <section id="main-content"> <section class="wrapper"> <!-- page start-->

	<div class="row">
		<div class="col-lg-12">
			<section class="panel"> 
			<form:form modelAttribute="searchModel" action="ad_list" method="GET">
				<header class="panel-heading">
				<h4>数据搜索</h4>
				<div>
					标题：
					<form:input path="title" />
					频道：
					<form:select path="adType">
						<option value="">全部</option>
						<form:options items="${opChannels}" itemValue="channelId" itemLabel="name" />

					</form:select>
					应用类型
					<form:select path="appType">
						<form:option value="xcloud" selected="true">菠萝人事</form:option>
						<form:option value="timechick">时光机</form:option>
						<form:option value="simi">私秘</form:option>
						<form:option value="sutongbao">速通宝</form:option>

					</form:select>
					

					<input type="submit" value="搜索">
				</div>
				</header>
			</form:form> <header class="panel-heading"> 运营广告管理

			<div class="pull-right">
				<button onClick="btn_add('/op/adForm?id=0')" class="btn btn-primary" type="button">
					<i class="icon-expand-alt"></i>新增
				</button>
			</div>
			</header>

			<hr style="width: 100%; color: black; height: 1px; background-color: black;" />


			<table class="table table-striped table-advance table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>应用类型</th>
						<th>标题</th>
						<th>图片地址</th>
						<th>频道</th>
						<th>跳转标识</th>
						<th>操作标识</th>
						<th>跳转地址</th>


						<th>添加时间</th>

						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${contentModel.list}" var="item">
						<tr>
							<td>${ item.no}</td>
							<td>${ item.appType}</td>
							<td>${ item.title}</td>
							<td><img src="${ item.imgUrl }" width="300px" height="300px" /></td>
							<td>${ item.channelNames}</td>
							<td>${ item.gotoType}</td>
							<td>${ item.action}</td>
							<td><a href="${ item.gotoUrl }" target="_blank">点击</a></td>

							

							<td><timestampTag:timestamp patten="yyyy-MM-dd" t="${item.addTime * 1000}" /></td>
							<td>
								<button id="btn_update" onClick="btn_update('/op/adForm?id=${item.id}')" class="btn btn-primary btn-xs"
									title="修改">
									<i class="icon-pencil"></i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


			</section>

			<c:import url="../shared/paging.jsp">
				<c:param name="pageModelName" value="contentModel" />
				<c:param name="urlAddress" value="/op/ad_list" />
			</c:import>
		</div>
	</div>
	<!-- page end--> </section> </section> <!--main content end--> <!--footer start--> <%@ include file="../shared/pageFooter.jsp"%>
	<!--footer end--> </section>

	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>

	<!--script for this page-->
	<script src="<c:url value='/js/simi/account/list.js'/>"></script>

</body>
</html>