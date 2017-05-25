/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.rivetlogic.csvtemplatemailer.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TemplateLocalService}.
 *
 * @author lorenzorodriguez
 * @see TemplateLocalService
 * @generated
 */
@ProviderType
public class TemplateLocalServiceWrapper implements TemplateLocalService,
	ServiceWrapper<TemplateLocalService> {
	public TemplateLocalServiceWrapper(
		TemplateLocalService templateLocalService) {
		_templateLocalService = templateLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _templateLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _templateLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _templateLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _templateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _templateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _templateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the template to the database. Also notifies the appropriate model listeners.
	*
	* @param template the template
	* @return the template that was added
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template addTemplate(
		com.rivetlogic.csvtemplatemailer.model.Template template) {
		return _templateLocalService.addTemplate(template);
	}

	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template addTemplate(
		long userId, java.lang.String name, java.lang.String value,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.addTemplate(userId, name, value,
			serviceContext);
	}

	/**
	* Creates a new template with the primary key. Does not add the template to the database.
	*
	* @param templateId the primary key for the new template
	* @return the new template
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template createTemplate(
		long templateId) {
		return _templateLocalService.createTemplate(templateId);
	}

	/**
	* Deletes the template from the database. Also notifies the appropriate model listeners.
	*
	* @param template the template
	* @return the template that was removed
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template deleteTemplate(
		com.rivetlogic.csvtemplatemailer.model.Template template) {
		return _templateLocalService.deleteTemplate(template);
	}

	/**
	* Deletes the template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the template
	* @return the template that was removed
	* @throws PortalException if a template with the primary key could not be found
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template deleteTemplate(
		long templateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _templateLocalService.deleteTemplate(templateId);
	}

	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template deleteTemplate(
		long templateId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.deleteTemplate(templateId, serviceContext);
	}

	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template fetchTemplate(
		long templateId) {
		return _templateLocalService.fetchTemplate(templateId);
	}

	/**
	* Returns the template matching the UUID and group.
	*
	* @param uuid the template's UUID
	* @param groupId the primary key of the group
	* @return the matching template, or <code>null</code> if a matching template could not be found
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template fetchTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _templateLocalService.fetchTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the template with the primary key.
	*
	* @param templateId the primary key of the template
	* @return the template
	* @throws PortalException if a template with the primary key could not be found
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template getTemplate(
		long templateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _templateLocalService.getTemplate(templateId);
	}

	/**
	* Returns the template matching the UUID and group.
	*
	* @param uuid the template's UUID
	* @param groupId the primary key of the group
	* @return the matching template
	* @throws PortalException if a matching template could not be found
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template getTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _templateLocalService.getTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param template the template
	* @return the template that was updated
	*/
	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template updateTemplate(
		com.rivetlogic.csvtemplatemailer.model.Template template) {
		return _templateLocalService.updateTemplate(template);
	}

	@Override
	public com.rivetlogic.csvtemplatemailer.model.Template updateTemplate(
		long userId, long templateId, java.lang.String name,
		java.lang.String value,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.updateTemplate(userId, templateId, name,
			value, serviceContext);
	}

	/**
	* Returns the number of templates.
	*
	* @return the number of templates
	*/
	@Override
	public int getTemplatesCount() {
		return _templateLocalService.getTemplatesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _templateLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _templateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.csvtemplatemailer.model.impl.TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _templateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.csvtemplatemailer.model.impl.TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _templateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.rivetlogic.csvtemplatemailer.model.impl.TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @return the range of templates
	*/
	@Override
	public java.util.List<com.rivetlogic.csvtemplatemailer.model.Template> getTemplates(
		int start, int end) {
		return _templateLocalService.getTemplates(start, end);
	}

	@Override
	public java.util.List<com.rivetlogic.csvtemplatemailer.model.Template> getTemplates(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplates(groupId);
	}

	@Override
	public java.util.List<com.rivetlogic.csvtemplatemailer.model.Template> getTemplates(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _templateLocalService.getTemplates(groupId, start, end);
	}

	/**
	* Returns all the templates matching the UUID and company.
	*
	* @param uuid the UUID of the templates
	* @param companyId the primary key of the company
	* @return the matching templates, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.rivetlogic.csvtemplatemailer.model.Template> getTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _templateLocalService.getTemplatesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of templates matching the UUID and company.
	*
	* @param uuid the UUID of the templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching templates, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.rivetlogic.csvtemplatemailer.model.Template> getTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.rivetlogic.csvtemplatemailer.model.Template> orderByComparator) {
		return _templateLocalService.getTemplatesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _templateLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _templateLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public TemplateLocalService getWrappedService() {
		return _templateLocalService;
	}

	@Override
	public void setWrappedService(TemplateLocalService templateLocalService) {
		_templateLocalService = templateLocalService;
	}

	private TemplateLocalService _templateLocalService;
}