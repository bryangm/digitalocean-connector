
package org.mule.modules.digitalocean.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.digitalocean.automation.DigitaloceanTestParent;
import org.mule.modules.digitalocean.automation.RegressionTests;
import org.mule.modules.digitalocean.automation.SmokeTests;

public class CreateNewDomainTestCases
    extends DigitaloceanTestParent
{


    @Before
    public void setup()
        throws Exception
    {
        //TODO: Add setup required to run test or remove method
        initializeTestRunMessage("createNewDomainTestData");
    }

    @After
    public void tearDown()
        throws Exception
    {
        //TODO: Add code to reset sandbox state to the one before the test was run or remove
    }

    @Category({
        RegressionTests.class,
        SmokeTests.class
    })
    @Test
    public void testCreateNewDomain()
        throws Exception
    {
        Object result = runFlowAndGetPayload("create-new-domain");
        throw new RuntimeException("NOT IMPLEMENTED METHOD");
    }

}