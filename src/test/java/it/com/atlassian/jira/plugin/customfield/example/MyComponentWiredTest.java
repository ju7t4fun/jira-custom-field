package it.com.atlassian.jira.plugin.customfield.example;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.plugin.customfield.example.PositiveNumberFieldMarker;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AtlassianPluginsTestRunner.class)
public class MyComponentWiredTest
{
    private final ApplicationProperties applicationProperties;

    private final PositiveNumberFieldMarker jiraCustomField;

    public MyComponentWiredTest(ApplicationProperties applicationProperties,PositiveNumberFieldMarker jiraCustomField)
    {
        this.applicationProperties = applicationProperties;
        this.jiraCustomField = jiraCustomField;
    }

    @Test
    public void testComponentExist()
    {
        assertNotNull(applicationProperties);
        assertNotNull(jiraCustomField);
    }

    @Test(expected = FieldValidationException.class)
    public void testCustomField()
    {
        jiraCustomField.getSingularObjectFromString("-1");
    }

}
