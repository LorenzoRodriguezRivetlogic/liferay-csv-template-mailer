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

package com.rivetlogic.csvtemplatemailer.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.rivetlogic.csvtemplatemailer.exception.NoSuchTemplateException;
import com.rivetlogic.csvtemplatemailer.model.Template;

/**
 * The persistence interface for the template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author lorenzorodriguez
 * @see com.rivetlogic.csvtemplatemailer.service.persistence.impl.TemplatePersistenceImpl
 * @see TemplateUtil
 * @generated
 */
@ProviderType
public interface TemplatePersistence extends BasePersistence<Template> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TemplateUtil} to access the template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching templates
	*/
	public java.util.List<Template> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @return the range of matching templates
	*/
	public java.util.List<Template> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching templates
	*/
	public java.util.List<Template> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns an ordered range of all the templates where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching templates
	*/
	public java.util.List<Template> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Returns the first template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns the last template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Returns the last template in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns the templates before and after the current template in the ordered set where uuid = &#63;.
	*
	* @param templateId the primary key of the current template
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next template
	* @throws NoSuchTemplateException if a template with the primary key could not be found
	*/
	public Template[] findByUuid_PrevAndNext(long templateId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Removes all the templates where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of templates where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching templates
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTemplateException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchTemplateException;

	/**
	* Returns the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the template where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the template that was removed
	*/
	public Template removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchTemplateException;

	/**
	* Returns the number of templates where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching templates
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching templates
	*/
	public java.util.List<Template> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @return the range of matching templates
	*/
	public java.util.List<Template> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching templates
	*/
	public java.util.List<Template> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns an ordered range of all the templates where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching templates
	*/
	public java.util.List<Template> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Returns the first template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns the last template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Returns the last template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns the templates before and after the current template in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param templateId the primary key of the current template
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next template
	* @throws NoSuchTemplateException if a template with the primary key could not be found
	*/
	public Template[] findByUuid_C_PrevAndNext(long templateId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Removes all the templates where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of templates where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching templates
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching templates
	*/
	public java.util.List<Template> findByGroupId(long groupId);

	/**
	* Returns a range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @return the range of matching templates
	*/
	public java.util.List<Template> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching templates
	*/
	public java.util.List<Template> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns an ordered range of all the templates where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching templates
	*/
	public java.util.List<Template> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Returns the first template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns the last template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching template
	* @throws NoSuchTemplateException if a matching template could not be found
	*/
	public Template findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Returns the last template in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching template, or <code>null</code> if a matching template could not be found
	*/
	public Template fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns the templates before and after the current template in the ordered set where groupId = &#63;.
	*
	* @param templateId the primary key of the current template
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next template
	* @throws NoSuchTemplateException if a template with the primary key could not be found
	*/
	public Template[] findByGroupId_PrevAndNext(long templateId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator)
		throws NoSuchTemplateException;

	/**
	* Removes all the templates where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of templates where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching templates
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the template in the entity cache if it is enabled.
	*
	* @param template the template
	*/
	public void cacheResult(Template template);

	/**
	* Caches the templates in the entity cache if it is enabled.
	*
	* @param templates the templates
	*/
	public void cacheResult(java.util.List<Template> templates);

	/**
	* Creates a new template with the primary key. Does not add the template to the database.
	*
	* @param templateId the primary key for the new template
	* @return the new template
	*/
	public Template create(long templateId);

	/**
	* Removes the template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param templateId the primary key of the template
	* @return the template that was removed
	* @throws NoSuchTemplateException if a template with the primary key could not be found
	*/
	public Template remove(long templateId) throws NoSuchTemplateException;

	public Template updateImpl(Template template);

	/**
	* Returns the template with the primary key or throws a {@link NoSuchTemplateException} if it could not be found.
	*
	* @param templateId the primary key of the template
	* @return the template
	* @throws NoSuchTemplateException if a template with the primary key could not be found
	*/
	public Template findByPrimaryKey(long templateId)
		throws NoSuchTemplateException;

	/**
	* Returns the template with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param templateId the primary key of the template
	* @return the template, or <code>null</code> if a template with the primary key could not be found
	*/
	public Template fetchByPrimaryKey(long templateId);

	@Override
	public java.util.Map<java.io.Serializable, Template> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the templates.
	*
	* @return the templates
	*/
	public java.util.List<Template> findAll();

	/**
	* Returns a range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @return the range of templates
	*/
	public java.util.List<Template> findAll(int start, int end);

	/**
	* Returns an ordered range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of templates
	*/
	public java.util.List<Template> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator);

	/**
	* Returns an ordered range of all the templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of templates
	* @param end the upper bound of the range of templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of templates
	*/
	public java.util.List<Template> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Template> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the templates from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of templates.
	*
	* @return the number of templates
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}