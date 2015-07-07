
package org.mule.modules.digitalocean.automation.testrunners;

import org.junit.runner.RunWith;
import org.mule.modules.digitalocean.automation.RegressionTests;
import org.mule.modules.digitalocean.automation.testcases.ChangeDropletKernelTestCases;
import org.mule.modules.digitalocean.automation.testcases.ConvertImageToSnapshopTestCases;
import org.mule.modules.digitalocean.automation.testcases.CreateNewDomainRecordTestCases;
import org.mule.modules.digitalocean.automation.testcases.CreateNewDomainTestCases;
import org.mule.modules.digitalocean.automation.testcases.CreateNewDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.CreateNewKeyTestCases;
import org.mule.modules.digitalocean.automation.testcases.DeleteExistingDomainRecordTestCases;
import org.mule.modules.digitalocean.automation.testcases.DeleteExistingDomainTestCases;
import org.mule.modules.digitalocean.automation.testcases.DeleteExistingDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.DeleteExistingImageTestCases;
import org.mule.modules.digitalocean.automation.testcases.DeleteExistingKeyTestCases;
import org.mule.modules.digitalocean.automation.testcases.DisableDropletBackupsTestCases;
import org.mule.modules.digitalocean.automation.testcases.EnableDropletIpv6TestCases;
import org.mule.modules.digitalocean.automation.testcases.EnableDropletPrivateNetworkingTestCases;
import org.mule.modules.digitalocean.automation.testcases.GetUserInformationTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllActionsForDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllActionsForImageTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllActionsTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllAvailableKernelsForDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllBackupsForDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllDomainRecordsTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllDomainsTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllDropletNeighborsTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllDropletsTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllImagesTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllKeysTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllNeighborsForDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllRegionsTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllSizesTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListAllSnapshotsForDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.ListDropletUpgradesTestCases;
import org.mule.modules.digitalocean.automation.testcases.PowerCycleDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.PowerOffDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.PowerOnDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.RebootDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.RebuildDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.RenameDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.ResetDropletPasswordTestCases;
import org.mule.modules.digitalocean.automation.testcases.ResizeDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.RestoreDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingActionTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingDomainRecordTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingDomainTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingDropletActionTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingImageActionTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingImageTestCases;
import org.mule.modules.digitalocean.automation.testcases.RetrieveExistingKeyTestCases;
import org.mule.modules.digitalocean.automation.testcases.ShutdownDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.SnapshotDropletTestCases;
import org.mule.modules.digitalocean.automation.testcases.TransferImageTestCases;
import org.mule.modules.digitalocean.automation.testcases.UpdateExistingDomainRecordTestCases;
import org.mule.modules.digitalocean.automation.testcases.UpdateExistingImageTestCases;
import org.mule.modules.digitalocean.automation.testcases.UpdateExistingKeyTestCases;
import org.mule.modules.digitalocean.automation.testcases.UpgradeDropletTestCases;

@RunWith(org.junit.experimental.categories.Categories.class)
@org.junit.experimental.categories.Categories.IncludeCategory(RegressionTests.class)
@org.junit.runners.Suite.SuiteClasses({
    GetUserInformationTestCases.class,
    ListAllActionsTestCases.class,
    RetrieveExistingActionTestCases.class,
    ListAllDomainsTestCases.class,
    RetrieveExistingDomainTestCases.class,
    CreateNewDomainTestCases.class,
    DeleteExistingDomainTestCases.class,
    ListAllDomainRecordsTestCases.class,
    RetrieveExistingDomainRecordTestCases.class,
    CreateNewDomainRecordTestCases.class,
    UpdateExistingDomainRecordTestCases.class,
    DeleteExistingDomainRecordTestCases.class,
    ListAllDropletsTestCases.class,
    RetrieveExistingDropletTestCases.class,
    ListAllAvailableKernelsForDropletTestCases.class,
    ListAllSnapshotsForDropletTestCases.class,
    ListAllBackupsForDropletTestCases.class,
    ListAllActionsForDropletTestCases.class,
    ListAllNeighborsForDropletTestCases.class,
    CreateNewDropletTestCases.class,
    DeleteExistingDropletTestCases.class,
    ListAllDropletNeighborsTestCases.class,
    ListDropletUpgradesTestCases.class,
    DisableDropletBackupsTestCases.class,
    RebootDropletTestCases.class,
    PowerCycleDropletTestCases.class,
    ShutdownDropletTestCases.class,
    PowerOffDropletTestCases.class,
    PowerOnDropletTestCases.class,
    RestoreDropletTestCases.class,
    ResetDropletPasswordTestCases.class,
    ResizeDropletTestCases.class,
    RebuildDropletTestCases.class,
    RenameDropletTestCases.class,
    ChangeDropletKernelTestCases.class,
    EnableDropletIpv6TestCases.class,
    EnableDropletPrivateNetworkingTestCases.class,
    SnapshotDropletTestCases.class,
    UpgradeDropletTestCases.class,
    RetrieveExistingDropletActionTestCases.class,
    ListAllImagesTestCases.class,
    RetrieveExistingImageTestCases.class,
    ListAllActionsForImageTestCases.class,
    UpdateExistingImageTestCases.class,
    DeleteExistingImageTestCases.class,
    TransferImageTestCases.class,
    ConvertImageToSnapshopTestCases.class,
    RetrieveExistingImageActionTestCases.class,
    ListAllKeysTestCases.class,
    RetrieveExistingKeyTestCases.class,
    CreateNewKeyTestCases.class,
    UpdateExistingKeyTestCases.class,
    DeleteExistingKeyTestCases.class,
    ListAllRegionsTestCases.class,
    ListAllSizesTestCases.class
})
public class RegressionTestSuite {


}
