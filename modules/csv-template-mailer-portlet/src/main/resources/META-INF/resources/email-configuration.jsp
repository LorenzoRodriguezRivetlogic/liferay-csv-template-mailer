<%@page import="com.rivetlogic.csvtemplatemailer.util.Utils"%>
<%@ include file="/init.jsp" %>

<%
	String fileId = ParamUtil.getString(request, WebKeys.FILE_ID);
	String emailString = ParamUtil.getString(request, WebKeys.COLUMN_EMAIL) ;
	String paramsAttr = ParamUtil.getString(request, WebKeys.COLUMNS_TO_USE);
	String emailSender = ParamUtil.getString(request, WebKeys.SENDER_EMAIL);
	String subject = ParamUtil.getString(request, WebKeys.EMAIL_SUBJECT);
	String template = ParamUtil.getString(request, WebKeys.CONTENT);
	String templateName = ParamUtil.getString(request, WebKeys.TEMPLATE_NAME);
	Long templateId = ParamUtil.getLong(request, WebKeys.TEMPLATE_ID, 0);
	Long scopeGroupIdTmp = ParamUtil.getLong(request, WebKeys.SCOPE_ID, 0);

	List<FileColumn> params = Utils.deserializeColumns(paramsAttr);
	Object emailColumn = JSONFactoryUtil.looseDeserialize(emailString, FileColumn.class);

	System.out.println(scopeGroupIdTmp);
	
	List<Template> templates = TemplateLocalServiceUtil.getTemplates(scopeGroupIdTmp);
	
	String bckTemplate = "";
	
	if (template.isEmpty()) {
		bckTemplate = Utils.generateHtmlTable(params);
	} else {
		bckTemplate += template;
	}
%>

<portlet:renderURL var="returnUrl">
	<portlet:param name="mvcPath" value="<%= WebKeys.DATA_MAPPING_URL %>" />
    <portlet:param name="fileId" value="<%= fileId %>" />
</portlet:renderURL>

<portlet:resourceURL var="resourceURL"/>

<portlet:renderURL var="previewUrlTest">
	<portlet:param name="mvcPath" value="<%= WebKeys.PREVIEW_URL %>" />
    <portlet:param name="fileId" value="<%= fileId %>" />
    <portlet:param name="emailColumn" value="<%= emailString %>" />
    <portlet:param name="columnsToUse" value="<%= paramsAttr %>" />
    <portlet:param name="senderEmail" value="<%= emailSender %>" />
    <portlet:param name="emailSubject" value="<%= subject %>" />
    <portlet:param name="content" value="<%= Utils.removeEscapeChars(template) %>" />
    <portlet:param name="templateId" value="<%= String.valueOf(templateId) %>" />
    <portlet:param name="name" value="<%= templateName %>" />
    <portlet:param name="scopeId" value="<%= String.valueOf(scopeGroupIdTmp) %>" />
</portlet:renderURL>

<portlet:actionURL name="showPreview" var="showPreviewURL">
	<portlet:param name="mvcPath" value="<%= WebKeys.PREVIEW_URL %>" />
</portlet:actionURL>

<liferay-ui:header showBackURL="true" backURL="<%= returnUrl.toString() %>"  title="email-config" />

<aui:container>
	<aui:row>
		<aui:col>
			<aui:form action="<%= showPreviewURL %>" name="<portlet:namespace />fm"  method="post">

				<aui:fieldset>
					<aui:input name="columnsToUse" value="<%= paramsAttr %>" type="hidden" />
					<aui:input name="emailColumn" value="<%= emailString %>" type="hidden" />
					<aui:input name="fileId" value="<%= fileId %>" type="hidden" />
					<aui:input name="templateId" value="" type="hidden" />
					<aui:input name="content" value="" type="hidden" />
				
					<aui:input name="senderEmail" label="subscription-email" required="true" value="<%= emailSender %>">
						<aui:validator name="email"/>
					</aui:input>
					
					<aui:input name="emailSubject" label="subscription-subject" required="true" value="<%= subject %>" />			
					
					<div class="columns">
						<div class="editor">
							<liferay-ui:input-editor 
								name="editor1" initMethod="initEditor" width="100" height="400" 
  								resizable="true" >
 							</liferay-ui:input-editor>
						</div>
							
						<div class="contacts">
						    <h3>List of Droppable Tags</h3>
						    <ul id="contactList">
						        <% 
								int count = 0;
								for (FileColumn fileColumn : params) {
								%>
								<li>
								    <div class="contact h-card" data-contact="<%= count %>" draggable="true" tabindex="0" ><%= fileColumn.getName() %></div>
								</li>
								<% 
								count++;
								}
								%>
					        </ul>
					    </div>
					</div>
				</aui:fieldset>
						
				<aui:button-row>
					<aui:button type="submit" value="show-preview" onClick="submitToPreview();" />
				</aui:button-row>
				<div class="templateSelector">
					<aui:select id="templates" name="templates" label="templates" showEmptyOption="true" >
						<% 
						for (Template templateSel : templates) {
						%>
							<aui:option value="<%=templateSel.getTemplateId()%>"  selected="<%= templateId == templateSel.getTemplateId() %>">
								<liferay-ui:message key="<%=templateSel.getName()%>" />
							</aui:option>
						<% 
						}
						%>
					</aui:select>
					<aui:input name="name" value="<%= templateName %>"/>
					<aui:button-row>
						<aui:button type="button" id="load" value="load" onClick="callServeResource()" />
						<aui:button type="button" id="update" value="update" onClick="callUpdateResource()" />
						<aui:button type="button" id="delete" value="delete" onClick="callDeleteResource()" />
						<aui:button type="button" id="save" value="save" onClick="callSaveResource()" />
					</aui:button-row>
				</div>
			</aui:form>
		</aui:col>
	</aui:row>
</aui:container>

<aui:script>

(function() {
	var tagsElements = document.getElementsByClassName("contact");
    var CONTACTS =[];

    for(var i=0; i<tagsElements.length; i++) {

        var item = {};
        var name = tagsElements[i].innerHTML;
        name = name.toLowerCase();
        name = name.replace(" ", "_");
        item['name'] = '@{' + name + '}';

        CONTACTS.push(item);
    }

    CKEDITOR.disableAutoInline = true;
    CKEDITOR.plugins.registered
    
    if (!('hcard' in CKEDITOR.plugins.registered)) {
    	CKEDITOR.plugins.add( 'hcard', {
    		requires: 'widget',
            init: function( editor ) {
            	editor.widgets.add( 'hcard', {
                    allowedContent: 'span(!h-card); !p-name',
                    requiredContent: 'span(h-card)',
                    pathName: 'hcard',

                    upcast: function( el ) {
                        return el.name == 'span' && el.hasClass( 'h-card' );
                    }
                });
            	editor.addFeature( editor.widgets.registered.hcard );
            	editor.on( 'paste', function( evt ) {
                    var contact = evt.data.dataTransfer.getData( 'contact' );
                    if ( !contact ) {
                        return;
                    }

                    evt.data.dataValue = '<span class="h-card">' + contact.name + '</span>';
                });
            }
    	});
    }
    
    CKEDITOR.document.getById( 'contactList' ).on( 'dragstart', function( evt ) {
    	var target = evt.data.getTarget().getAscendant( 'div', true );
    	CKEDITOR.plugins.clipboard.initDragDataTransfer( evt );
        var dataTransfer = evt.data.dataTransfer;
        dataTransfer.setData( 'contact', CONTACTS[ target.data( 'contact' ) ] );
        dataTransfer.setData( 'text/html', '@{' + target.getText().toLowerCase().replace(" ", "_") + '}' );
    });
    
    CKEDITOR.config.extraPlugins = 'hcard';
})();

function <portlet:namespace/>initEditor(){
	 return  '<%= bckTemplate %>';
}
 
function submitToPreview() {
	var template = window.<portlet:namespace />editor1.getHTML();
	document.getElementById("<portlet:namespace />content").value = template;
}

function callServeResource(){
    AUI().use('aui-base', function(A){
    	var option = A.one('#<portlet:namespace/>templates');
		var template = option.val();
		if (template === '') {
			alert('Select a template');
			return;
		}
		
		var name = A.one('#<portlet:namespace/>name').attr('value');
		var repeated = false;

		var currTemplate = document.getElementById('<portlet:namespace />templateId').value;
		if(!(name.length === 0) && currTemplate == template){
			alert('Template already loaded');
			repeated = true;
		}
		
		if(repeated == false) {
			A.io.request('<%=resourceURL.toString()%>', {
				method: 'post',
				data: {
					<portlet:namespace />action: 'load',
					<portlet:namespace />templateId: template
				},
				on: {
				     success: function() {
				    	var label = A.one('#<portlet:namespace/>templates option:selected').attr('text');
						document.getElementById('<portlet:namespace />name').value = label;
						document.getElementById('<portlet:namespace />templateId').value = template;
						window.<portlet:namespace />editor1.setHTML(this.get('responseData'));
				     }
				}
			});
		}
    });
}

function callSaveResource(){
    AUI().use('aui-base', function(A){
    	var name = A.one('#<portlet:namespace/>name').attr('value');
		if (name === '') {
			alert('The name cannot be empty');
			return;
		}
		
		var template = window.<portlet:namespace />editor1.getHTML();
		if (template === '') {
			alert('The template cannot be empty');
			return;
		}
		
		var count = 0;
		var repeated = false;
		var select = A.one('#<portlet:namespace/>templates');
		var selectElement = select.outerHTML();
		var htmlObject = $(selectElement);
		
		while(count < htmlObject[0].options.length) {
			if(htmlObject[0].options[count].text == $.trim(name)){
				alert('Cannot save repeated template name');
				repeated = true;
				break;
			}
			count = count + 1;
		}
		
		if(repeated == false) {
			A.io.request('<%=resourceURL.toString()%>', {
				method: 'post',
				data: {
					<portlet:namespace />action: 'save',
					<portlet:namespace />name: name,
					<portlet:namespace />template: template
				},
				on: {
				     success: function() {
				     	var result = this.get('responseData');
				    	if (result === 'error') {
				    		alert('Error save the template save');
						} else {
							var select = A.one('#<portlet:namespace/>templates');
				    		var option  = A.Node.create( '<option value=\"'+result+'\">'+name+'</option>');
				    		select.append(option);
							
							alert('Template saved');
						}
				     }
				}
			});
		}
    });
}

function callUpdateResource(){
    AUI().use('aui-base', function(A){
    	var option = A.one('#<portlet:namespace/>templates');
		var templateId = option.val();
		if (templateId === '') {
			alert('Select a template');
		}
		
		var name = A.one('#<portlet:namespace/>name').attr('value');
		if (name === '') {
			alert('The name cannot be empty');
			return;
		}
		
		var template = window.<portlet:namespace />editor1.getHTML();
		if (template === '') {
			alert('The template cannot be empty');
			return;
		}
		
		A.io.request('<%=resourceURL.toString()%>', {
			method: 'post',
			data: {
				<portlet:namespace />action: 'update',
				<portlet:namespace />templateId: templateId,
				<portlet:namespace />name: name,
				<portlet:namespace />template: template
			},
			on: {
			     success: function() {
			    	 var result = this.get('responseData');
			    	 if (result === 'success') {
			    		alert('Template updated');
					 } else {
						alert('Error updating the template save');
					 }
			     }
			}
		});
    });
}

function callDeleteResource(){
    AUI().use('aui-base', function(A){
    	var option = A.one('#<portlet:namespace/>templates');
		var template = option.val();
		if (template === '') {
			alert('Select a template');
		}
		
		A.io.request('<%=resourceURL.toString()%>', {
			method: 'post',
			data: {
				<portlet:namespace />action: 'delete',
				<portlet:namespace />templateId: template,
			},
			on: {
			     success: function() {
			    	 var result = this.get('responseData');
			    	 if (result === 'success') {
			    	 	A.one('#<portlet:namespace/>templates option[value=\''+template+'\']').remove();
			    	 	document.getElementById('<portlet:namespace />name').value = '';
			    	 	alert('Template deleted');
					 } else {
						alert('Error deleting the template save');
					 }
			     }
			}
		});
    });
}

</aui:script>