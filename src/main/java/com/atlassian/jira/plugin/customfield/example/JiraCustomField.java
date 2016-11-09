package com.atlassian.jira.plugin.customfield.example;

import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.util.concurrent.Nullable;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Nonnull;


public class JiraCustomField extends AbstractSingleFieldType<Integer> implements PositiveNumberFieldMarker {

	public JiraCustomField(CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager) {

		super(customFieldValuePersister, genericConfigManager);
	}

	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_DECIMAL;
	}

	@Nullable

	protected Object getDbValueFromObject(Integer o) {
		return o;
	}

	@Nullable

	protected Integer getObjectFromDbValue(@Nonnull Object o) throws FieldValidationException {
		return (Integer) o;
	}


	public String getStringFromSingularObject(Integer o) {
		if (o == null)
			return null;
		else
			return String.valueOf(o);
	}

	public Integer getSingularObjectFromString(String s) throws FieldValidationException {
		if(NumberUtils.isNumber(s)){
			Integer integer = NumberUtils.createInteger(s);
			if(integer >= 0){
				return integer;
			}
		}
		throw new FieldValidationException("Entered value must be a positive number!");

	}

}

