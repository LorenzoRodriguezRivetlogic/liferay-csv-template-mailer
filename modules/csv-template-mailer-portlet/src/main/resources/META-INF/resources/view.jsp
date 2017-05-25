<%@ include file="/init.jsp" %>

<% 
	String fileId = ParamUtil.getString(request, WebKeys.FILE_ID);

	if (!fileId.isEmpty()) {
		//FileUtil.deleteFileAndFolder(Long.parseLong(fileId));
	}
%>

<h3><liferay-ui:message key="upload-title" /></h3>

<portlet:actionURL name="uploadCsv" var="uploadURL">
</portlet:actionURL>

<aui:form action="<%= uploadURL %>" method="POST" enctype="multipart/form-data"> 
    <aui:row>
        <aui:col columnWidth="50">
        	<aui:input name="attachedFile" type="file" required="true">
				<aui:validator name="acceptFiles">'csv'</aui:validator>
			</aui:input>
        </aui:col>
    </aui:row>

    <aui:button-row>
        <aui:button type="submit" value="continue-button" />
    </aui:button-row>
</aui:form>
