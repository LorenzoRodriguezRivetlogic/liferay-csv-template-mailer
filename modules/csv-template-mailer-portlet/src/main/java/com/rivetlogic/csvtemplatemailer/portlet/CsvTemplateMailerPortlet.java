package com.rivetlogic.csvtemplatemailer.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.rivetlogic.csvtemplatemailer.model.Template;
import com.rivetlogic.csvtemplatemailer.service.TemplateLocalServiceUtil;
import com.rivetlogic.csvtemplatemailer.task.SendEmailTask;
import com.rivetlogic.csvtemplatemailer.util.FileColumn;
import com.rivetlogic.csvtemplatemailer.util.FileUtil;
import com.rivetlogic.csvtemplatemailer.util.Utils;
import com.rivetlogic.csvtemplatemailer.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author lorenzorodriguez
 */

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=csv-template-mailer-portlet",
		"com.liferay.portlet.display-category=category.tools",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=CSV Template Mailer",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",		
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-css=/js/ckeditor/skins/moono/editor.css",
		"com.liferay.portlet.header-portlet-javascript=/js/ckeditor/ckeditor.js",
		"com.liferay.portlet.header-portlet-javascript=/js/ckeditor/config.js",
        "com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"mail.throws.exception.on.failure=true",
		"theme.css.fast.load=false",
		"theme.images.fast.load=false",
		"javascript.fast.load=false",
		"javascript.barebone.enabled=false",
		"javascript.log.enabled=false",
		"layout.template.cache.enabled=false",
		"combo.check.timestamp=false",
		"freemarker.engine.cache.storage=soft:1",
		"freemarker.engine.modification.check.interval=0",
		"openoffice.cache.enabled=false",
		"velocity.engine.resource.manager.cache.enabled=false",
		"com.liferay.portal.servlet.filters.cache.CacheFilter=false",
		"com.liferay.portal.servlet.filters.themepreview.ThemePreviewFilter=false",
		"velocity.engine.resource.manager.cache.enabled=false",
		"minifier.inline.content.cache.size=0"
	},
	service = Portlet.class
)
public class CsvTemplateMailerPortlet extends MVCPortlet {
	private static final Log LOG = LogFactoryUtil.getLog(CsvTemplateMailerPortlet.class);
	
	private SendEmailTask emailTask;
	
	public void uploadCsv(ActionRequest request, ActionResponse response) throws PortletException,IOException {
		
        UploadPortletRequest upload = PortalUtil.getUploadPortletRequest(request);
        
        long fileId = 0l;
        try {
            fileId = FileUtil.storeAttachments(upload);
        } catch(Exception e) {
            LOG.error("Error uploading file:", e);
            SessionErrors.add(request, "apply-error");
            SessionMessages.add(request, PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
        }
		
        response.setRenderParameter(WebKeys.JSP_PAGE, WebKeys.DATA_MAPPING_URL);
        response.setRenderParameter(WebKeys.FILE_ID, String.valueOf(fileId));
        PortalUtil.copyRequestParameters(request, response);
        
        disableNotifications(request);
        sendRedirect(request, response);
	}
	
	public void sendData(ActionRequest request, ActionResponse response) 
			throws IOException, PortletException, SystemException, PortalException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<FileColumn> columnsToUse = new ArrayList<FileColumn>();
		
		String fileId = ParamUtil.getString(request, WebKeys.FILE_ID);
		int containsEmail = ParamUtil.getInteger(request, WebKeys.CONTAINS_EMAIL);
		String uses[] = ParamUtil.getParameterValues(request, WebKeys.USE_IN_HTML);
		
		List<FileColumn> columns = FileUtil.getFileColumns(fileId);
		FileColumn columnEmail = new FileColumn();
		
		for (FileColumn fileColumn : columns) {
			fileColumn.setUse(Utils.valueIsInArray(uses, String.valueOf(fileColumn.getId())));
			
			if (fileColumn.getId() == containsEmail) {
				fileColumn.setIsEmail(true);
				columnEmail = fileColumn;
			}
			
			if (fileColumn.getUse()) {
				columnsToUse.add(fileColumn);
			}
		}
		
		if(Utils.haveRepeatedColumns(columnsToUse)) {
			SessionErrors.add(request, "column-repeated-name");
			response.setRenderParameter(WebKeys.JSP_PAGE, WebKeys.DATA_MAPPING_URL);
	        response.setRenderParameter(WebKeys.FILE_ID, String.valueOf(fileId));
	        sendRedirect(request, response);
			return;
		}
		
		response.setRenderParameter(WebKeys.JSP_PAGE, WebKeys.EMAIL_CONFIGURATION_URL);
        response.setRenderParameter(WebKeys.FILE_ID, String.valueOf(fileId));
        response.setRenderParameter(WebKeys.COLUMNS_TO_USE, JSONFactoryUtil.looseSerialize(columnsToUse));
        response.setRenderParameter(WebKeys.COLUMN_EMAIL, JSONFactoryUtil.looseSerialize(columnEmail));
        response.setRenderParameter(WebKeys.SCOPE_ID, String.valueOf(themeDisplay.getScopeGroupId()));
        PortalUtil.copyRequestParameters(request, response);
        
        disableNotifications(request);
        sendRedirect(request, response);
    }
	
	public void showPreview(ActionRequest request, ActionResponse response) throws IOException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String columnData = ParamUtil.getString(request, WebKeys.COLUMNS_TO_USE);
		List<FileColumn> params = Utils.deserializeColumns(columnData);
		FileColumn emailColumn = (FileColumn) JSONFactoryUtil.looseDeserialize(ParamUtil.getString(request, WebKeys.COLUMN_EMAIL), FileColumn.class);
		String fileId = ParamUtil.getString(request, WebKeys.FILE_ID);
		String templateId = ParamUtil.getString(request, WebKeys.TEMPLATE_ID);
		String templateName = ParamUtil.getString(request, WebKeys.TEMPLATE_NAME);
		String templateValue = ParamUtil.getString(request, WebKeys.CONTENT);
		String emailSender = ParamUtil.getString(request, WebKeys.SENDER_EMAIL);
	    String subject = ParamUtil.getString(request, WebKeys.EMAIL_SUBJECT);
	    
	    templateValue = Utils.removeEscapeChars(templateValue);
	    
	    response.setRenderParameter(WebKeys.JSP_PAGE, WebKeys.PREVIEW_URL);
	    response.setRenderParameter(WebKeys.FILE_ID, String.valueOf(fileId));
	    response.setRenderParameter(WebKeys.COLUMNS_TO_USE, JSONFactoryUtil.looseSerialize(params));
	    response.setRenderParameter(WebKeys.COLUMN_EMAIL, JSONFactoryUtil.looseSerialize(emailColumn));
	    response.setRenderParameter(WebKeys.CONTENT, templateValue);
	    response.setRenderParameter(WebKeys.SENDER_EMAIL, emailSender);
	    response.setRenderParameter(WebKeys.EMAIL_SUBJECT, subject);
	    response.setRenderParameter(WebKeys.TEMPLATE_ID, templateId);
	    response.setRenderParameter(WebKeys.TEMPLATE_NAME, templateName);
	    response.setRenderParameter(WebKeys.SCOPE_ID , String.valueOf(themeDisplay.getScopeGroupId()));
        
	    disableNotifications(request);
        sendRedirect(request, response);
	}
	
	public void sendEmails(ActionRequest request, ActionResponse response) throws IOException {
		String fileId = ParamUtil.getString(request, WebKeys.FILE_ID);
		FileUtil.deleteFileAndFolder(Long.parseLong(fileId));
	}

	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {

		String action = ParamUtil.getString(resourceRequest, "action");
		
		if (action.equals(WebKeys.ACTION_LOAD)) {
			loadAction(resourceRequest, resourceResponse);
		}
		   
		if (action.equals(WebKeys.ACTION_UPDATE)) {
			updateAction(resourceRequest, resourceResponse);
		}
		
		if (action.equals(WebKeys.ACTION_SAVE)) {
			saveAction(resourceRequest, resourceResponse);
		}
		
		if (action.equals(WebKeys.ACTION_DELETE)) {
			deleteAction(resourceRequest, resourceResponse);
		}
		
		if (action.equals(WebKeys.ACTION_START_SEND)) {
			startSend(resourceRequest, resourceResponse);
		}
		
		if (action.equals(WebKeys.ACTION_STATUS_SEND)) {
			statusSend(resourceRequest, resourceResponse);
		}
    }
	
	private void loadAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {
		
		String templateId = ParamUtil.getString(resourceRequest, "templateId");
        Template template = null;
        try {
        	template = TemplateLocalServiceUtil.getTemplate(Long.parseLong(templateId));
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
 
        resourceResponse.setContentType("text/html");
        PrintWriter writer = resourceResponse.getWriter();
        if(template == null){
            writer.print("");
        }else{
        	writer.print(template.getValue());
        }
        writer.flush();
        writer.close();
        super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void updateAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {
		
		Long templateId = Long.parseLong(ParamUtil.getString(resourceRequest, "templateId"));
		String name = ParamUtil.getString(resourceRequest, "name");
		String template = ParamUtil.getString(resourceRequest, "template");
        
		resourceResponse.setContentType("text/html");
        PrintWriter writer = resourceResponse.getWriter();

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Template.class.getName(), resourceRequest);
			TemplateLocalServiceUtil.updateTemplate(serviceContext.getUserId(), templateId, name, template, serviceContext);
			
			writer.print("success");
		} catch (PortalException e) {
			writer.print("error");
			e.printStackTrace();
		} catch (SystemException e) {
			writer.print("error");
			e.printStackTrace();
		}
		
        writer.flush();
        writer.close();
        super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void saveAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {
		
		String name = ParamUtil.getString(resourceRequest, "name");
		String template = ParamUtil.getString(resourceRequest, "template");
        
		resourceResponse.setContentType("text/html");
        PrintWriter writer = resourceResponse.getWriter();

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Template.class.getName(), resourceRequest);
			Template newTemplate = TemplateLocalServiceUtil.addTemplate(serviceContext.getUserId(), name, template, serviceContext);
			
			writer.print(newTemplate.getTemplateId());
		} catch (PortalException e) {
			writer.print("error");
			e.printStackTrace();
		} catch (SystemException e) {
			writer.print("error");
			e.printStackTrace();
		}
		
        writer.flush();
        writer.close();
        super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void deleteAction(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {
		
		Long templateId = Long.parseLong(ParamUtil.getString(resourceRequest, "templateId"));
        
		resourceResponse.setContentType("text/html");
        PrintWriter writer = resourceResponse.getWriter();

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Template.class.getName(), resourceRequest);
		    TemplateLocalServiceUtil.deleteTemplate(templateId, serviceContext);
		    
			writer.print("success");
		} catch (PortalException e) {
			writer.print("error");
			e.printStackTrace();
		} catch (SystemException e) {
			writer.print("error");
			e.printStackTrace();
		}
		
        writer.flush();
        writer.close();
        super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void startSend(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {
		
		String fileId = ParamUtil.getString(resourceRequest, WebKeys.FILE_ID);
		String emailColumn = ParamUtil.getString(resourceRequest, WebKeys.COLUMN_EMAIL);
		String columnsToUse = ParamUtil.getString(resourceRequest, WebKeys.COLUMNS_TO_USE);
	    String content = ParamUtil.getString(resourceRequest, WebKeys.CONTENT);
	    String senderEmail = ParamUtil.getString(resourceRequest, WebKeys.SENDER_EMAIL);
	    String emailSubject = ParamUtil.getString(resourceRequest, WebKeys.EMAIL_SUBJECT);
	    
	    List<FileColumn> columns = Utils.deserializeColumns(columnsToUse);
	    FileColumn email = (FileColumn) JSONFactoryUtil.looseDeserialize(emailColumn, FileColumn.class);
	    
	    List<Map<String, String>> data = FileUtil.getFileRows(fileId, columns, email);
	    
		emailTask = new SendEmailTask();
		emailTask.start(data, senderEmail, content, emailSubject, fileId);
		
		resourceResponse.setContentType("text/html");
        PrintWriter writer = resourceResponse.getWriter();

		try {
			writer.print("success");
		} catch (SystemException e) {
			writer.print("error");
			e.printStackTrace();
		}
		
        writer.flush();
        writer.close();
        super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void statusSend(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws IOException, PortletException {
		
		Map<String, String> data = new HashMap<>();
		data.put("sent", String.valueOf(0));
		data.put("notSent", String.valueOf(0));
		data.put("finished", String.valueOf(false));
		
		if(emailTask != null) {
			data.put("sent", String.valueOf(emailTask.getSent()));
			data.put("notSent", String.valueOf(emailTask.getNotSent()));
			
			if (emailTask.getState() == Thread.State.TERMINATED) {
				data.put("finished", String.valueOf(true));
				emailTask.clean();
			} else {
				data.put("finished", String.valueOf(false));
			}
		}

        PrintWriter writer = resourceResponse.getWriter();
        	
		try {
			String jsonData = JSONFactoryUtil.looseSerialize(data);
			writer.print(jsonData);
		} catch (SystemException e) {
			writer.print("error");
			e.printStackTrace();
		}
		
        writer.flush();
        writer.close();
        super.serveResource(resourceRequest, resourceResponse);
	}
	
	private void disableNotifications(ActionRequest request) {
		PortletConfig portletConfig = (PortletConfig) request.getAttribute(JavaConstants.JAVAX_PORTLET_CONFIG);
        LiferayPortletConfig liferayPortletConfig = (LiferayPortletConfig) portletConfig;
        SessionMessages.add(request, liferayPortletConfig.getPortletId() + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
	}
}