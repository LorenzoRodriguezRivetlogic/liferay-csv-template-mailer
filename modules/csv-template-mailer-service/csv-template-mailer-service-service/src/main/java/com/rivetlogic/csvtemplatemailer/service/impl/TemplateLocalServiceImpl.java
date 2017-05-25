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

package com.rivetlogic.csvtemplatemailer.service.impl;

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.rivetlogic.csvtemplatemailer.exception.TemplateNameException;
import com.rivetlogic.csvtemplatemailer.exception.TemplateValueException;
import com.rivetlogic.csvtemplatemailer.model.Template;
import com.rivetlogic.csvtemplatemailer.service.base.TemplateLocalServiceBaseImpl;

/**
 * The implementation of the template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.rivetlogic.csvtemplatemailer.service.TemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author lorenzorodriguez
 * @see TemplateLocalServiceBaseImpl
 * @see com.rivetlogic.csvtemplatemailer.service.TemplateLocalServiceUtil
 */
@ProviderType
public class TemplateLocalServiceImpl extends TemplateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.rivetlogic.csvtemplatemailer.service.TemplateLocalServiceUtil} to access the template local service.
	 */
	public List<Template> getTemplates (long groupId) throws SystemException {
	    return templatePersistence.findByGroupId(groupId);
	}

	public List<Template> getTemplates (long groupId, int start, int end)
	   throws SystemException {
		return templatePersistence.findByGroupId(groupId, start, end);
	}
	
	protected void validate (String name, String value) throws PortalException {
	    if (Validator.isNull(name)) {
	       throw new TemplateNameException();
	    }
	    
	    if (Validator.isNull(value)) {
	    	throw new TemplateValueException();
		}
	}
	
	public Template addTemplate(long userId, String name, String value, ServiceContext serviceContext) 
			throws SystemException, PortalException {
		
		long groupId = serviceContext.getScopeGroupId();
		
		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(name, value);

		long templateId = counterLocalService.increment();

		Template template = templatePersistence.create(templateId);

		template.setUuid(serviceContext.getUuid());
		template.setUserId(userId);
		template.setGroupId(groupId);
		template.setCompanyId(user.getCompanyId());
		template.setUserName(user.getFullName());
		template.setCreateDate(serviceContext.getCreateDate(now));
		template.setModifiedDate(serviceContext.getModifiedDate(now));
		template.setName(name);
		template.setValue(value);
		template.setExpandoBridgeAttributes(serviceContext);

		templatePersistence.update(template);
		
		return template;
	}
	
	public Template deleteTemplate(long templateId, ServiceContext serviceContext)
		    throws PortalException, SystemException {

		Template template = getTemplate(templateId);

		resourceLocalService.deleteResource(serviceContext.getCompanyId(), Template.class.getName(),
		ResourceConstants.SCOPE_INDIVIDUAL, templateId);
		
		template = deleteTemplate(templateId);
		
		return template;
	}
	
	public Template updateTemplate(long userId, long templateId, String name, String value, ServiceContext serviceContext)
	    throws PortalException, SystemException {

	    long groupId = serviceContext.getScopeGroupId();

	    User user = userPersistence.findByPrimaryKey(userId);

	    Date now = new Date();

	    validate(name, value);

	    Template template = getTemplate(templateId);

	    template.setUserId(userId);
	    template.setUserName(user.getFullName());
	    template.setName(name);
	    template.setValue(value);
	    template.setModifiedDate(serviceContext.getModifiedDate(now));
	    template.setExpandoBridgeAttributes(serviceContext);

	    templatePersistence.update(template);

	    resourceLocalService.updateResources(
	        user.getCompanyId(), groupId, Template.class.getName(), templateId, serviceContext.getGroupPermissions(),
	        serviceContext.getGuestPermissions());

	    return template;
	}
}