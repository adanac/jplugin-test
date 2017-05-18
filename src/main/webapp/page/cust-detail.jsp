<!DOCTYPE html>
<html lang="en">
<head>
<title>Matrix Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="../common/css/bootstrap.min.css" />
<link rel="stylesheet" href="../common/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="../common/css/colorpicker.css" />
<link rel="stylesheet" href="../common/css/datepicker.css" />
<link rel="stylesheet" href="../common/css/uniform.css" />
<link rel="stylesheet" href="../common/css/select2.css" />
<link rel="stylesheet" href="../common/css/matrix-style2.css" />
<link rel="stylesheet" href="../common/css/matrix-media.css" />
<link rel="stylesheet" href="../common/css/bootstrap-wysihtml5.css" />
<link href="../common/font-awesome/css/font-awesome.css"
	rel="stylesheet" />


<script src="../common/js/jquery.min.js"></script>
<script src="../common/js/jquery.easyui.min.js"></script>
<script src="../common/js/bootstrap.min.js"></script>
<script src="../common/js/bootstrap-colorpicker.js"></script>
<script src="../common/js/bootstrap-datepicker.js"></script>
<script src="../common/js/jquery.uniform.js"></script>
<script src="../common/js/select2.min.js"></script>
<script src="../common/js/matrix.js"></script>
<script src="../jplugin_3rd.js"></script>
<script src="../jplugin_common.js"></script>
<script src="dict-appcenter.js"></script>

<!-- 
<script src="../common/js/jquery.min.js"></script>
<script src="../common/js/jquery.ui.custom.js"></script>
<script src="../common/js/jquery.sparkline.min.js"></script>

<script src="../common/js/bootstrap.min.js"></script>
<script src="../common/js/bootstrap-colorpicker.js"></script>
<script src="../common/js/bootstrap-datepicker.js"></script>
<script src="../common/js/bootstrap-toggleButtons.js"></script>
<script src="../common/js/masked.js"></script>
<script src="../common/js/jquery.uniform.js"></script>
<script src="../common/js/select2.min.js"></script>
<script src="../common/js/matrix.js"></script>
<script src="../common/js/matrix.form_common.js"></script>
<script src="../common/js/wysihtml5-0.3.0.js"></script>
<script src="../common/js/jquery.peity.min.js"></script>
<script src="../common/js/bootstrap-wysihtml5.js"></script>
 -->

<style>
.control-group .controls label {
	display: inline-block;
}
</style>
</head>
<body>



	<div id="content">
		<div id="content-header">
			<h1>
				客户-<span id="custName"></span>
			</h1>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6" style="width: 100%">
					<form action="#" method="get" class="form-horizontal">
						<div class="widget-box" id="input">
							<div class="widget-title">
								<span class="icon"> <i class="icon-align-justify"></i>
								</span>
								<h5>客户基本信息</h5>
							</div>
							<div class="widget-content nopadding">
								<div class="control-group">
									<label class="control-label" id="lb1">客户ID:</label> <label
										class="control-label" id="custId" value="${cust.custId}"></label>
								</div>
								<div class="control-group">
									<label class="control-label" data-wa-req="true" id="lbname">客户名称:</label>
									<label class="control-label" id="custName">${cust.custName}</label>
								</div>
								<div class="control-group">
									<label class="control-label" data-wa-req="true" id="lbname">客户状态:</label>
									<label class="control-label" id="status">${cust.status}</label>
								</div>
							</div>
							<div class="widget-title">
								<span class="icon"> <i class="icon-align-justify"></i>
								</span>
								<h5>客户其他信息</h5>
							</div>
							<div class="widget-content nopadding">
								<div class="control-group">
									<label class="control-label" id="lbname">家庭地址:</label>
									<label class="control-label" id="custAddress">${cust.custAddress}</label>
								</div>
								<div class="control-group">
									<label class="control-label">性别:</label> 
									<label class="control-label" id="sex">${cust.sex}</label>
								</div>

							</div>

							<div class="form-actions">
								<button id="edit" type="button" class="btn btn-success">修改</button>
								<button id="back" type="button" class="btn btn-success">返回客户列表</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
