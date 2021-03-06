<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>

<html>
<head>

<!--common css for all pages-->
<%@ include file="../shared/importCss.jsp"%>

<!--css for this page-->

<link href="<c:url value='/assets/js/zTree/css/awesomeStyle/awesome.css'/>" rel="stylesheet">
<link href="<c:url value='/assets/js/amazeui.datatables/amazeui.datatables.min.css'/>"
	rel="stylesheet">

</head>

<body>
	<!--header start-->
	<%@ include file="../shared/pageHeader.jsp"%>
	<!--header end-->

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@ include file="../xz/xz-menu.jsp"%>
		<!-- sidebar end -->

		<!-- content start -->
		<div class="admin-content">

			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">茶点饮用水服务记录</strong> / <small>茶点饮用水服务记录一览</small>
				</div>
				
				<div class="am-u-sm-12 am-u-md-3 am-fr">
					<div class="am-btn-toolbar am-fr">
						<div class="am-btn-group am-btn-group-sm ">
							<button type="button" id="btn-water-add" class="am-btn am-btn-warning am-radius">
								<span class="am-icon-plus"></span> 一键下单
							</button>
						</div>
					</div>
				</div>
			</div>
			<hr>

				<div class="am-g">
				<div class="am-u-sm-12">
					<form class="am-form">
						<table id="list-table" class="am-table am-table-bordered am-table-striped">
							<thead>
								<tr class="am-primary">
									<!-- <th class="table-date am-hide-sm-only">服务大类名称</th> -->
									<th class="table-title am-text-center">商品名称</th>
									<th class="table-id am-text-center">数量</th>
									<th class="table-title am-text-center">金额</th>
									<th class="table-title am-text-center">地址</th>
									<th class="table-title am-text-center">订单状态</th>
									<th class="table-set am-text-center">下单时间</th>
									<th class="table-title am-text-center">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${contentModel.list}" var="item">
									<tr>
										<%-- <td>${item.serviceTypeName}</td> --%>
										<td>${item.servicePriceName}</td>
										<td>${ item.serviceNum }</td>
										<td>${ item.orderPay }元</td>
										<td>${item.addrName}</td>
										<td class="am-hide-sm-only">${item.orderStatusName}</td>
										
										<td class="am-hide-sm-only">${item.addTimeStr}</td>
										<c:if test="${item.orderExtStatus == 0 || item.orderExtStatus == 1 }">
											<td>
												<a href ="#" onClick="javascript:waterSign(${item.userId},${item.orderId})" class="am-icon-pencil" title="签收"></a>
											</td>
										</c:if>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<c:import url="../shared/paging.jsp">
							<c:param name="pageModelName" value="contentModel" />
							<c:param name="urlAddress" value="/xz/water/list" />
						</c:import>
					</form>
				</div>
			</div>
		</div>

		</div>
		<!-- content end -->

	</div>

	<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
		data-am-offcanvas="{target: '#admin-offcanvas'}"></a>


	<!--footer start-->
	<%@ include file="../shared/pageFooter.jsp"%>
	<!--footer end-->

	<!-- js placed at the end of the document so the pages load faster -->
	<!--common script for all pages-->
	<%@ include file="../shared/importJs.jsp"%>

	<!--script for this page-->

	<script src="<c:url value='/assets/js/amazeui.datatables/amazeui.datatables.min.js'/>"></script>
	<script src="<c:url value='/assets/js/amazeui.datatables/dataTables.responsive.min.js'/>"></script>
	<script src="<c:url value='/assets/js/xcloud/xz/water-list.js'/>"></script>
</body>
</html>
