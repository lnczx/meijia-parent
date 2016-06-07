<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../shared/taglib.jsp"%>
<html>
<head>
<!--common css for all pages-->
<%@ include file="../shared/importCss.jsp"%>
<!--css for this page-->
</head>
<body>
	<!--header start-->
	<%@ include file="../shared/pageHeader.jsp"%>
	<!--header end-->
	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@ include file="../hr/hr-menu.jsp"%>
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">人事档案 
				</div>
			</div>
			<hr>
			<form:form modelAttribute="contentModel" method="POST" id="staff-form" class="am-form am-form-horizontal"
				enctype="multipart/form-data">
				<form:hidden path="companyId" />
				<form:hidden path="id" />
				<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active">
							<a href="#staff-info">基本信息</a>
						</li>
						<li>
							<a href="#staff-job">职位信息</a>
						</li>
					</ul>
					<div class="am-tabs-bd">
						<!----------------- 基本信息 ------------------------------------>
						<div class="am-tab-panel am-fade am-in am-active" id="staff-info">
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">头像:</label>
								<div class="am-u-sm-5">
									<div class="am-form-group am-form-file">
										<div class="am-u-sm-5">
											<button type="button" id="btnUpload" name="btnUpload" class="am-btn am-btn-danger am-btn-sm">
												<i class="am-icon-cloud-upload"></i>
												选择要上传的图片
											</button>
											<input id="file" type="file" name="file" accept="image/*">
										</div>
										<label id="file-list" class="am-u-sm-3 am-form-label"></label>
									</div>
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group ">
								<label class="am-u-sm-2 am-form-label">
									<font color="red">*</font>员工编号:
								</label>
								<div class="am-u-sm-5">
									<form:input path="jobNumber" class="am-form-field am-radius js-pattern-pinteger" maxLength="4"
										data-validation-message="" placeholder="" required="required"
										readOnly="${(contentModel.id > 0) ? 'readonly' : ''}" />
								</div>
								<div class="am-u-sm-4"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">
									<font color="red">*</font>姓名:
								</label>
								<div class="am-u-sm-5">
									<form:input path="name" class="am-form-field am-radius" placeholder="" maxLength="64" required="required" />
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">
									<font color="red">*</font>手机号码:
								</label>
								<div class="am-u-sm-5">
									<form:input path="mobile" class="am-form-field am-radius js-pattern-mobile" maxLength="11"
										data-validation-message="" placeholder="手机号" required="required" />
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">性别:</label>
								<div class="am-u-sm-5">
									<label class="am-radio-inline">
										<form:radiobutton path="sex" value="男" />
										先生
									</label>
									<label class="am-radio-inline">
										<form:radiobutton path="sex" value="女" />
										女士
									</label>
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">邮箱:</label>
								<div class="am-u-sm-5">
									<form:input path="companyEmail" class="am-form-field am-radius js-pattern-email" maxLength="64" />
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">身份证号:</label>
								<div class="am-u-sm-5"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">身份证照:</label>
								<div class="am-u-sm-5"></div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">银行卡号:</label>
								<div class="am-u-sm-5"></div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">开户行:</label>
								<div class="am-u-sm-5"></div>
								<div class="am-u-sm-3"></div>
							</div>
						</div>
						<!----------------- 职位信息 ------------------------------------>
						<div class="am-tab-panel am-fade am-in am-active" id="staff-job">
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">入职时间:</label>
								<div class="am-u-sm-5">
									<fmt:formatDate var='formattedDate' value='${contentModel.joinDate}' type='both' pattern="yyyy-MM-dd" />
									<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd', viewMode: 'years'}">
										<input type="text" id="joinDate" name="joinDate" value="${formattedDate}" class="am-form-field" placeholder=""
											readonly required>
										<span class="am-input-group-btn am-datepicker-add-on">
											<button class="am-btn am-btn-default" type="button">
												<span class="am-icon-calendar"></span>
											</button>
										</span>
									</div>
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">员工类型:</label>
								<div class="am-u-sm-5">
									<form:select path="staffType" class="am-form-field am-radius">
										<form:option value="0">全职</form:option>
										<form:option value="1">兼职</form:option>
										<form:option value="2">实习</form:option>
									</form:select>
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">所在部门:</label>
								<div class="am-u-sm-5">
									<form:select path="deptId" class="am-form-field am-radius">
										<form:options items="${deptList}" itemValue="deptId" itemLabel="name" />
									</form:select>
								</div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">职位:</label>
								<div class="am-u-sm-5">
									<form:input path="jobName" class="am-form-field am-radius" placeholder="" maxLength="64" />
								</div>
								<div class="am-u-sm-3"></div>
							</div>

							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">转正日期:</label>
								<div class="am-u-sm-5"></div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">合同开始日期:</label>
								<div class="am-u-sm-5"></div>
								<div class="am-u-sm-3"></div>
							</div>
							<div class="am-form-group">
								<label class="am-u-sm-2 am-form-label">合同年限:</label>
								<div class="am-u-sm-5">下拉框 6个月 1 - 10年</div>
								<div class="am-u-sm-3"></div>
							</div>
						</div>
					</div>
					<div class="am-margin">
						<div class="am-u-sm-5 am-u-sm-push-3">
							<button type="button" class="am-btn am-btn-danger" id="btn-staff-submit">保存</button>
							<button type="button" class="am-btn am-btn-success" id="btn-return">返回</button>
						</div>
					</div>
				</div>
				<div class="am-form-group">
					<label class="am-u-sm-3 am-form-label"></label>
					<div class="am-u-sm-5">
						<form:errors path="mobile" class="am-alert am-alert-danger center"></form:errors>
					</div>
				</div>
			</form:form>
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
		<script src="<c:url value='/assets/js/xcloud/common/validate-methods.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/xcloud/hr/staff-form.js'/>"></script>
</body>
</html>