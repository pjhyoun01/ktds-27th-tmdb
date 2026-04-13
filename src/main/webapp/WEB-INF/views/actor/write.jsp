<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/templates/header.jsp">
<jsp:param value="배우 등록" name="title"/>
<jsp:param value="<script type='text/javascript' src='/js/actor-write.js'></script>" name="scripts"/>
</jsp:include>

	<h1>배우 등록</h1>
	<form:form modelAttribute="actorWriteVO" method="post" action="/actor"
		enctype="multipart/form-data">
		<div class="grid write">
			<label for="actorName">이름</label>
			<div class="input-div">
				<input type="text" id="actorName" name="actorName"
					placeholder="이름을 입력하세요." value="${inputData.actorName}" />
				<form:errors path="actorName" cssClass="validation-error"
					element="div" />
			</div>
			
			<label for="actorProfileUrl">프로필 URL</label>
			<div class="input-div">
				<input type="text" id="actorProfileUrl" name="actorProfileUrl"
					placeholder="프로필 URL을 입력하세요." value="${inputData.actorProfileUrl}" />
				<form:errors path="actorProfileUrl" cssClass="validation-error"
					element="div" />
			</div>


		</div>
		<label for="attach-files">배우 프로필 사진</label>
		<div id="attach-files" class="attach-files">
			<input type="file" name="attachFiles" />
		</div>



		<div class="btn-group">
			<div class="right-align">
				<input type="submit" value="저장" />
			</div>
		</div>
	</form:form>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>