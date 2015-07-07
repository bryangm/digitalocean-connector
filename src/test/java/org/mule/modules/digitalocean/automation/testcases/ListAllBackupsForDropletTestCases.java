
package org.mule.modules.digitalocean.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.digitalocean.automation.DigitaloceanTestParent;
import org.mule.modules.digitalocean.automation.RegressionTests;
import org.mule.modules.digitalocean.automation.SmokeTests;

public class ListAllBackupsForDropletTestCases
    extends DigitaloceanTestParent
{


    @Before
    public void setup()
        throws Exception
    {
        //TODO: Add setup required to run test or remove method
        initializeTestRunMessage("listAllBackupsForDropletTestData");
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
    public void testListAllBackupsForDroplet()
        throws Exception
    {
        Object result = runFlowAndGetPayload("list-all-backups-for-droplet");
        throw new RuntimeException("NOT IMPLEMENTED METHOD");
    }

}
