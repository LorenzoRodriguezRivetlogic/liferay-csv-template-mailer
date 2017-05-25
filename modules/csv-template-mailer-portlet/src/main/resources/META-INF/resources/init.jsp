<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<%@ taglib uri="http://liferay.com/tld/ui"  prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@ page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil"%>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator"%>

<%@ page import="com.liferay.asset.kernel.model.AssetCategory" %>
<%@ page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.model.AssetVocabulary" %>
<%@ page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %>

<%@ page import="com.liferay.document.library.kernel.service.DLFileEntryServiceUtil"%>
<%@ page import="com.liferay.document.library.kernel.model.DLFileEntry"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.Map"%>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.PortletPreferences"%>

<%@ page import="com.rivetlogic.csvtemplatemailer.util.WebKeys" %>
<%@ page import="com.rivetlogic.csvtemplatemailer.util.PrefsKeys"%>
<%@ page import="com.rivetlogic.csvtemplatemailer.util.MailUtil"%>
<%@ page import="com.rivetlogic.csvtemplatemailer.util.FileUtil"%>
<%@ page import="com.rivetlogic.csvtemplatemailer.util.Utils"%>
<%@ page import="com.rivetlogic.csvtemplatemailer.util.FileColumn"%>
<%@ page import="com.rivetlogic.csvtemplatemailer.model.Template"%>
<%@ page import="com.rivetlogic.csvtemplatemailer.service.TemplateLocalServiceUtil" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />
<%
	String currentURL = PortalUtil.getCurrentURL(request);
	String completeCurrentURL = PortalUtil.getCurrentCompleteURL(request);
	String redirect = ParamUtil.getString(request, "redirect", currentURL);
%>
