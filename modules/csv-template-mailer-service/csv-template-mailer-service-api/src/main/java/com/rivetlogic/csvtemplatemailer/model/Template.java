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

package com.rivetlogic.csvtemplatemailer.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Template service. Represents a row in the &quot;RL_Template&quot; database table, with each column mapped to a property of this class.
 *
 * @author lorenzorodriguez
 * @see TemplateModel
 * @see com.rivetlogic.csvtemplatemailer.model.impl.TemplateImpl
 * @see com.rivetlogic.csvtemplatemailer.model.impl.TemplateModelImpl
 * @generated
 */
@ImplementationClassName("com.rivetlogic.csvtemplatemailer.model.impl.TemplateImpl")
@ProviderType
public interface Template extends TemplateModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.rivetlogic.csvtemplatemailer.model.impl.TemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Template, Long> TEMPLATE_ID_ACCESSOR = new Accessor<Template, Long>() {
			@Override
			public Long get(Template template) {
				return template.getTemplateId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Template> getTypeClass() {
				return Template.class;
			}
		};
}