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
 * Provides a wrapper for {@link TemplateService}.
 *
 * @author lorenzorodriguez
 * @see TemplateService
 * @generated
 */
@ProviderType
public class TemplateServiceWrapper implements TemplateService,
	ServiceWrapper<TemplateService> {
	public TemplateServiceWrapper(TemplateService templateService) {
		_templateService = templateService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _templateService.getOSGiServiceIdentifier();
	}

	@Override
	public TemplateService getWrappedService() {
		return _templateService;
	}

	@Override
	public void setWrappedService(TemplateService templateService) {
		_templateService = templateService;
	}

	private TemplateService _templateService;
}