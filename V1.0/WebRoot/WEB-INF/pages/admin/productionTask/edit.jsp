<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<s:action name="productionTask!view" id="view" executeResult="false" />
<HEAD>
	<title>Eshow_后台_产品</title>
	<link rel="stylesheet" href="<c:url value='/admin/styles/product.css'/>"
		type="text/css" />
	<link media="screen" type="text/css"
		href="<c:url value='/scripts/validate/theme/grey/formcheck.css'/>"
		rel="stylesheet" />
	<script type="text/javascript"
		src="<c:url value='/scripts/validate/lang/zh-CN.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/scripts/validate/formcheck.js'/>"></script>
	<script type="text/javascript">
	 	window.addEvent('domready', function() {
			new FormCheck('productForm');
		});
    </script>
    	<style type="text/css">
			#productionTaskEdit .long{width:400px;padding-left:10px;font-size:18px;}
	</style>	
</HEAD>
<BODY>
	<div id="main">
		<s:include value="../left.jsp"></s:include>
		<div id="mainarea">
			<div id="mainarea_bg">
				<div id="content">
					<div id="mainTop">
						<h2>
							<img src="<c:url value='/admin/images/app_list_product.gif'/>" />
							生产任务详情
						</h2>
					</div>
					<div id="mainTab">
						<ul>
							<li class="on">
								<span class="txt6"><a
									href="<c:url value='/admin/product'/>">生产任务详情</a> </span>
							</li>
						</ul>
					</div>
					<div id="productionTaskEdit">
						<s:form id="productForm" action="product!update.html"
							method="post" enctype="multipart/form-data">
							<s:hidden name="id" value="%{#view.productionTask.id}"></s:hidden>
							<p>
								<span class="long">生产任务详情:&nbsp;</span>
							</p>
							<br>
							<p>
								<table>
									<tr class="long">
										<td>任务序号</td>
										<td>提交时间</td>
										<td>完成时间</td>
										<td>任务状态</td>
										<td>操作员</td>
										
									</tr>
									<tr class="long">
										<td>${view.productionTask.id }</td>
										<td>${view.productionTask.addTime }</td>
										<td>
											<c:if test="${endTime==null}">
												未完成
											</c:if>
											${view.productionTask.endTime }
										</td>
										<td>${view.productionTask.status }</td>
										<td>${view.productionTask.operateusername }</td>
									<tr>
								</table>
							</p>
							
							
							<p>
								<span class="l">产品名称:&nbsp;</span>
								 <input name="product.name" style="width: 300px;" maxlength="50"
										type="text" onblur="this.className='inputtext'" 
										class="inputtext text-input validate['required']" 
										value="${view.product.name}" />
							</p>
							<p>
								<span class="l">产品分类:&nbsp;&nbsp;</span>
								<s:action name="productCategory!search" id="productCategoryList"
									executeResult="false" />
								<select id="productCategoryId" name="productCategoryId">
									<s:iterator value="%{#productCategoryList.productCategories}"
										status="rowStatus">
										<option value="${id}"
											<c:if test="${view.product.productCategory.id==id}">selected="selected"</c:if>>
											${name}
										</option>
									</s:iterator>
								</select>
							</p>
							
							
							<p>
								<span class="l">产品材质:&nbsp;</span>
								 <input name="product.material" style="width: 300px;" maxlength="50"
										type="text" onblur="this.className='inputtext'" 
										class="inputtext text-input validate['required']" 
										value="${view.product.material}" />
							</p>
							<p>
								<span class="l">产品产地:&nbsp;</span>
								 <input name="product.origin" style="width: 300px;" maxlength="50"
										type="text" onblur="this.className='inputtext'" 
										class="inputtext text-input validate['required']" 
										value="${view.product.origin}" />
							</p>
							<p>
								<span class="l">产品尺寸:&nbsp;</span>
								 <input name="product.size" style="width: 300px;" maxlength="50"
										type="text" onblur="this.className='inputtext'" 
										class="inputtext text-input validate['required']" 
										value="${view.product.size}" />
							</p>
							
							
							
							<p>
								<span class="l">选择图片:&nbsp;</span>
								<span class="1"> <input type="file" name="file"
										size="38" class="inputtext text-input validate['required']" /> </span>
							</p>
							<p>
								<span class="l">产品内容:&nbsp;</span>
								<span class="r">&nbsp;
									<script type="text/javascript">
								    KE.show({
								        id : 'ke-text'
								    });
								 </script>
								<textarea id="ke-text" name="product.content"
									style="width: 600px; height: 300px;">${view.product.content}</textarea> </span>
							</p>
							<p align="center">
								<input type="submit" class="botton" value="修改"
									onmouseout="this.className='botton';"
									onmouseover="this.className='botton2';"/>
								<input type="button" class="botton_close1" value="取消"
									onmouseout="this.className='botton_close1';"
									onmouseover="this.className='botton_close2';"
									onclick="javascript:history.back();"/>
							</p>
						</s:form>
					</div>
				</div>
				<div class="c"></div>
			</div>
		</div>
	</div>
</BODY>

