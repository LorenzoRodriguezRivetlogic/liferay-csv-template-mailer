<%@ include file="/init.jsp" %>

<%
	String fileId = ParamUtil.getString(request, WebKeys.FILE_ID);
	String emailString = ParamUtil.getString(request, WebKeys.COLUMN_EMAIL) ;
	String paramsAttr = ParamUtil.getString(request, WebKeys.COLUMNS_TO_USE);
	String template = ParamUtil.getString(request, WebKeys.CONTENT);
	String emailSender = ParamUtil.getString(request, WebKeys.SENDER_EMAIL);
	String subject = ParamUtil.getString(request, WebKeys.EMAIL_SUBJECT);
	String templateId = ParamUtil.getString(request, WebKeys.TEMPLATE_ID);
	String templateName = ParamUtil.getString(request, WebKeys.TEMPLATE_NAME);
	Long scopeGroupIdTmp = ParamUtil.getLong(request, WebKeys.SCOPE_ID, 0);
	List<FileColumn> params = Utils.deserializeColumns(paramsAttr);
	FileColumn email = (FileColumn) JSONFactoryUtil.looseDeserialize(emailString, FileColumn.class);
	
	Map<String, String> firstRow = FileUtil.getFirstDataRow(fileId, params, email);
	String tempPreview = Utils.replaceDataFirstRow(fileId, params, email, template);
%>

<portlet:resourceURL var="resourceURL"/>

<portlet:actionURL name="sendEmails" var="sendEmailsURL">
	<portlet:param name="backURL" value="<%= currentURL %>"/>
</portlet:actionURL>

<portlet:renderURL var="returnUrl">
	<portlet:param name="mvcPath" value="<%= WebKeys.EMAIL_CONFIGURATION_URL %>" />
    <portlet:param name="fileId" value="<%= fileId %>" />
    <portlet:param name="emailColumn" value="<%= emailString %>" />
    <portlet:param name="columnsToUse" value="<%= paramsAttr %>" />
    <portlet:param name="senderEmail" value="<%= emailSender %>" />
    <portlet:param name="emailSubject" value="<%= subject %>" />
    <portlet:param name="content" value="<%= Utils.removeEscapeChars(template) %>" />
    <portlet:param name="templateId" value="<%= templateId %>" />
    <portlet:param name="name" value="<%= templateName %>" />
    <portlet:param name="scopeId" value="<%= String.valueOf(scopeGroupIdTmp) %>" />
</portlet:renderURL>

<liferay-ui:header showBackURL="true" backURL="<%= returnUrl.toString() %>"  title="preview" />

<table>
	<tr>
		<td><liferay-ui:message key="label-from" /></td>
		<td><%= emailSender %></td>
	</tr>
	<tr>
		<td><liferay-ui:message key="label-to" /></td>
		<td><%= firstRow.get(WebKeys.EMAIL_TO_SEND) %></td>
	</tr>
	<tr>
		<td><liferay-ui:message key="label-subject" /></td>
		<td><%= subject %></td>
	</tr>
</table>
<div style="border: 1px solid #d3d3d3; padding: 10px;">
	<%= tempPreview %>
</div>

<br />

<div id="<portlet:namespace/>status-box" style="padding: 10px; display: none">
	<b></b><liferay-ui:message key="sending-emails" /></b>
	<ul>
		<li><liferay-ui:message key="emails-sent" />: <span id="<portlet:namespace/>sent">0</span></li>
		<li><liferay-ui:message key="emails-no-sent" />: <span id="<portlet:namespace/>not-sent">0</span></li>
	</ul>
	<span id="<portlet:namespace/>complete" style="display: none"><liferay-ui:message key="mail-sending-completed" /></span>
</div>

<aui:form action="<%= sendEmailsURL %>" method="post" name="fm">
	<aui:input name="fileId" value="<%= fileId %>" type="hidden" />
	<aui:input name="emailColumn" value="<%= emailString %>" type="hidden" />
	<aui:input name="columnsToUse" value="<%= paramsAttr %>" type="hidden" />
	<aui:input name="content" value="<%= template %>" type="hidden" />
	<aui:input name="senderEmail" value="<%= emailSender %>" type="hidden" />
	<aui:input name="emailSubject" value="<%= subject %>" type="hidden" />
	
	<aui:button-row>
		<aui:button name="send-button" type="button" value="send-button" onClick="callSendData()" />
        <aui:button type="submit" value="finish" />
    </aui:button-row>
</aui:form>  

<aui:script>  

var interval;
document.getElementById('<portlet:namespace/>sent').innerHTML = 0;
document.getElementById('<portlet:namespace/>not-sent').innerHTML = 0;
document.getElementById('<portlet:namespace/>complete').style.display = 'none';
	
function callSendData(){
	console.log("Method");
	AUI().use('aui-base', function(A){
		A.one("#<portlet:namespace/>send-button").attr({'disabled' : true});
		document.getElementById('<portlet:namespace/>status-box').style.display = 'inline';
	});
	startCall();
	ajaxCall();
	interval = self.setInterval(function(){ajaxCall();},1000);
}

function ajaxCall(){
	AUI().use('aui-base', function(A){
		A.io.request('<%=resourceURL.toString()%>', {
			method: 'post',
			data: {
				<portlet:namespace />action: 'status-send',
			},
			dataType: 'json',
			on: {
				success: function() {
			     	var result = this.get('responseData');
			     	document.getElementById('<portlet:namespace/>sent').innerHTML = result.sent;
			     	document.getElementById('<portlet:namespace/>not-sent').innerHTML = result.notSent;
			     	var finished = (result.finished === "true");
					if (finished) {
						document.getElementById('<portlet:namespace/>complete').style.display = 'inline';
						clearInterval(interval);
					} else {
						document.getElementById('<portlet:namespace/>complete').style.display = 'none';
					}
			    }
			}
		});
	});
}

function startCall(){
	AUI().use('aui-base', function(A){
		var fileId = A.one('#<portlet:namespace/>fileId').attr('value');
		var emailColumn = A.one('#<portlet:namespace/>emailColumn').attr('value');
		var columnsToUse = A.one('#<portlet:namespace/>columnsToUse').attr('value');
		var content = A.one('#<portlet:namespace/>content').attr('value');
		var senderEmail = A.one('#<portlet:namespace/>senderEmail').attr('value');
		var emailSubject = A.one('#<portlet:namespace/>emailSubject').attr('value');

		A.io.request('<%=resourceURL.toString()%>', {
			method: 'post',
			data: {
				<portlet:namespace />action: 'start-send',
				<portlet:namespace />fileId: fileId,
				<portlet:namespace />emailColumn: emailColumn,
				<portlet:namespace />columnsToUse: columnsToUse,
				<portlet:namespace />content: content,
				<portlet:namespace />senderEmail: senderEmail,
				<portlet:namespace />emailSubject: emailSubject
			},
			on: {
			     success: function() {
			     	var result = this.get('responseData');
					if (result !== 'success') {
						alert('Error sending the emails');
					}
			    }
			}
		});
	});
}
</aui:script>