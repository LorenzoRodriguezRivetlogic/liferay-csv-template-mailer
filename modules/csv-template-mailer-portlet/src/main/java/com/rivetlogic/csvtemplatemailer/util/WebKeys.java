package com.rivetlogic.csvtemplatemailer.util;

public interface WebKeys extends com.liferay.portal.kernel.util.WebKeys {
	String PARAM_CSV = "csv";
	String PARAM_MIME = "text/csv";
	String DATA_MAPPING_URL = "/data-mapping.jsp";
	String EMAIL_CONFIGURATION_URL = "/email-configuration.jsp";
	String PREVIEW_URL = "/preview-email.jsp";
	String VIEW_URL = "/view.jsp";
	String TEMPLATE_EDIT_URL = "/edit_template.jsp";
	String JSP_PAGE = "jspPage";
	String FILE_ID = "fileId";
	String CONTAINS_EMAIL = "containsEmail";
	String USE_IN_HTML = "useInHtml";
	String COLUMNS_TO_USE = "columnsToUse";
	String COLUMN_EMAIL = "emailColumn";
	String CONTENT = "content";
	String SENDER_EMAIL = "senderEmail";
	String EMAIL_SUBJECT = "emailSubject";
	String EMAIL_TO_SEND = "emailToSend";
	String DEFAULT_FILE_NAME = "CSV - ";
	String DEFAULT_FOLDER_NAME = "Temp Files";
    String DEFAULT_FOLFER_DESC = "Uploaded temp files";
    String TEMPLATE_NAME = "name";
    String TEMPLATE_VALUE = "value";
    String TEMPLATE_ID = "templateId";
    String SCOPE_ID = "scopeId";
    String ACTION_LOAD = "load";
    String ACTION_UPDATE = "update";
    String ACTION_SAVE = "save";
    String ACTION_DELETE = "delete";
    String ACTION_START_SEND = "start-send";
    String ACTION_STATUS_SEND = "status-send";
}
