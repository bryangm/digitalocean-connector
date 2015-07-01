/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.digitalocean;
import java.io.IOException;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Transformer;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Payload;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestHeaderParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.mule.modules.digitalocean.objects.responses.*;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="digitalocean", friendlyName="DigitalOcean")
public abstract class DigitalOceanConnector {

    /**
     * OAuth Token
     * Generated here: https://cloud.digitalocean.com/settings/applications
     */
	@RestHeaderParam("Authorization") 
    @Configurable
    private String token;

	// Account
    /**
     * Get account information for the authenticated user.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:getUserInformation}
     *
     * @return 				The account information for the authenticated user.
     * @throws 	IOException	A problem communication with DigitalOcean occurred.
     * @see					AccountResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#get-user-information">Get User Information</a>
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract AccountResponse getUserInformation() throws IOException;  
    
    // Actions
    /**
     * List all actions that have been executed on the current account.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllActions}
     *
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of actions.
     * @throws 	IOException	A problem communication with DigitalOcean occurred.
     * @see					ActionCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-actions">List all Actions</a>
     */    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/actions?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ActionCollectionResponse listAllActions(
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
 
    /**
     * Retrieve a specific action object.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingAction}
     *
     * @param  	actionId	ID of the action.
     * @return 				An action.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-action">Retrieve an existing Action</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/actions/{action}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ActionResponse retrieveExistingAction(
    		@RestUriParam("action") int actionId) 
    				throws IOException;  
    
    // Domains
    /**
     * List all domains.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDomains}
     * 
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of domains.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-domains">List all Domains</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DomainCollectionResponse listAllDomains(
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;    
 
    /**
     * Get details about a specific domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDomain}
     *
     * @param  	domainName	Name of the domain.
     * @return 				A domain.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-domain">Retrieve an existing Domain</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DomainResponse retrieveExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    /**
     * Create a new domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewDomain}
     *
     * @param  	message		The JSON request body submitted via #[payload].
     * @return 				A domain.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-domain">Create a new Domain</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract DomainResponse createNewDomain(
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete a domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingDomain}
     *
     * @param  	domainName 	Name of the domain.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#delete-a-domain">Delete a Domain</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    // Domain Records
    /**
     * List all domain records for a domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDomainRecords}
     *
     * @param  	domainName 	Name of the domain.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of domain records.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainRecordCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-domain-records">List all Domain Records</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DomainRecordCollectionResponse listAllDomainRecords(
    		@RestUriParam("domain") String domainName,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDomainRecord}
     *
     * @param  	domainName 	Name of the domain.
     * @param  	recordId 	Id of the domain record.
     * @return 				A domain record.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainRecordResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-domain-record">Retrieve an existing Domain Record</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DomainRecordResponse retrieveExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") int recordId) 
    				throws IOException;  
    
    /**
     * Create a new domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewDomainRecord}
     *
     * @param  	domainName 	Name of the domain.
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				A domain record.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainRecordResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-domain-record">Create a new Domain Record</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract DomainRecordResponse createNewDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Update a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:updateExistingDomainRecord}
     *
     * @param  	domainName 	Name of the domain.
     * @param  	recordId 	Id of the domain record.
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				A domain record.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DomainRecordResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#update-a-domain-record">Update a Domain Record</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.PUT, 
    		contentType = "application/json")
    public abstract DomainRecordResponse updateExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") int recordId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingDomainRecord}
     *
     * @param  	domainName 	Name of the domain.
     * @param  	recordId 	Id of the domain record.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#delete-a-domain-record">Delete a Domain Record</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") int recordId) 
    				throws IOException;  
    
    // Droplets
    /**
     * List all droplets.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDroplets}
     *
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of droplets.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DropletCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-droplets">List all Droplets</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DropletCollectionResponse listAllDroplets(
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @return 				A droplet.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DropletResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-droplet-by-id">Retrieve an existing Droplet by id</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DropletResponse retrieveExistingDroplet(
    		@RestUriParam("droplet") int dropletId) 
    				throws IOException;  
    
    /**
     * List all available kernels for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllAvailableKernelsForDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of kernels.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					KernelCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-available-kernels-for-a-droplet">List all available Kernels for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/kernels?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract KernelCollectionResponse listAllAvailableKernelsForDroplet(
    		@RestUriParam("droplet") int dropletId,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * List all snapshots for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllSnapshotsForDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of snapshots.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ImageCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-snapshots-for-a-droplet">List snapshots for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/snapshots?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ImageCollectionResponse listAllSnapshotsForDroplet(
    		@RestUriParam("droplet") int dropletId,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * List all backups for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllBackupsForDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of backups.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ImageCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-backups-for-a-droplet">List backups for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/backups?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ImageCollectionResponse listAllBackupsForDroplet(
    		@RestUriParam("droplet") int dropletId,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllActionsForDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of actions.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-actions-for-a-droplet">List actions for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ActionCollectionResponse listAllActionsForDroplet(
    		@RestUriParam("droplet") int dropletId,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * List all neighbors for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllNeighborsForDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @return 				List of droplets.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DropletCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-neighbors-for-a-droplet">List Neighbors for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/neighbors", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract DropletCollectionResponse listAllNeighborsForDroplet(
    		@RestUriParam("droplet") int dropletId) 
    				throws IOException;  
    
    /**
     * Create a new droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewDroplet}
     *
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				A droplet.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					DropletResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-droplet">Create a new Droplet</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract DropletResponse createNewDroplet(
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingDroplet}
     *
     * @param  	dropletId 	Id of a droplet.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#delete-a-droplet">Delete a Droplet</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingDroplet(
    		@RestUriParam("droplet") int dropletId) 
    				throws IOException;  
    
    /**
     * List all droplet neighbors.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDropletNeighbors}
     *
     * @return 				List of droplets.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					NeighborsCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-droplet-neighbors">List all Droplet Neighbors</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/reports/droplet_neighbors", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract NeighborsCollectionResponse listAllDropletNeighbors() throws IOException;  
    
    /**
     * List all droplets scheduled to be upgraded.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listDropletUpgrades}
     *
     * @return 				List of droplets.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					UpgradeCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-droplet-upgrades">List Droplet Upgrades</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplet_upgrades", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract UpgradeCollectionResponse listDropletUpgrades() throws IOException;  
    
    // Droplet Actions
    /**
     * Executes an action on a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:executeDropletAction}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				A droplet.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#disable-backups">Disable Backups</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#reboot-a-droplet">Reboot a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#power-cycle-a-droplet">Power Cycle a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#shutdown-a-droplet">Shutdown a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#power-off-a-droplet">Power Off a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#power-on-a-droplet">Power On a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#restore-a-droplet">Restore a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#password-reset-a-droplet">Password Reset a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#resize-a-droplet">Resize a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#rebuild-a-droplet">Rebuild a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#rename-a-droplet">Rename a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#change-the-kernel">Change the Kernel</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#enable-ipv6">Enable IPv6</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#enable-private-networking">Enable Private Networking</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#snapshot-a-droplet">Snapshot a Droplet</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#upgrade-a-droplet">Upgrade a Droplet</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract ActionResponse executeDropletAction(
    		@RestUriParam("droplet") int dropletId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDropletAction}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param  	actionId 	Id of an action.
     * @return 				A droplet.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-a-droplet-action">Retrieve a Droplet Action</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions/{action}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ActionResponse retrieveExistingDropletAction(
    		@RestUriParam("droplet") int dropletId, 
    		@RestUriParam("action") int actionId) 
    				throws IOException;      
    
    // Images
    /**
     * List all images.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllImages}
     *
     * @param 	imageType	Type of images to return: application, distribution, all.  The default is all.
     * @param 	privateImages
     * 						Boolean to return the authenticated user's private images.  The default is false, which returns all images.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of images.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ImageCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-images">List all Images</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-distribution-images">List all Distribution Images</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-application-images">List all Application Images</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-a-user-s-images">List a User's Images</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images?type=all&private=false&page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ImageCollectionResponse listAllImages(
    		@RestQueryParam("type") @Default("all") String imageType, 
    		@RestQueryParam("private") @Default("false") String privateImages,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingImage}
     *
     * @param  	imageId 	Id of an image.
     * @return 				An image.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ImageResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-by-id">Retrieve an existing Image by id</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-by-slug">Retrieve an existing Image by slug</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ImageResponse retrieveExistingImage(
    		@RestUriParam("image") int imageId) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllActionsForImage}
     *
     * @param  	imageId 	Id of an image.
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of actions.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-actions-for-an-image">List all Actions for an Image</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ActionCollectionResponse listAllActionsForImage(
    		@RestUriParam("image") int imageId,
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * Update an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:updateExistingImage}
     *
     * @param  	imageId 	Id of an image.
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				An image.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ImageResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#update-an-image">Update an Image</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.PUT, 
    		contentType = "application/json")
    public abstract ImageResponse updateExistingImage(
    		@RestUriParam("image") int imageId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingImage}
     *
     * @param  	imageId 	Id of an image.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#delete-an-image">Delete an Image</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingImage(
    		@RestUriParam("image") int imageId) 
    				throws IOException;  

    // Image Actions
    /**
     * Executes an action on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:executeImageAction}
     *
     * @param  	imageId 	Id of an image.
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				An image.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#transfer-an-image">Transfer an Image</a>
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#convert-an-image-to-a-snapshot">Convert an Image to a Snapshot</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract ActionResponse executeImageAction(
    		@RestUriParam("image") int imageId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Get details about a specific image action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingImageAction}
     *
     * @param  	imageId  	Id of an image.
     * @param  	actionId 	Id of an action.
     * @return 				An image.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					ActionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-action">Retrieve an existing Image Action</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions/{action}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract ActionResponse retrieveExistingImageAction(
    		@RestUriParam("image") int imageId, 
    		@RestUriParam("action") int actionId) 
    				throws IOException;  
       
    // SSH Keys
    /**
     * List all SSH keys.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllKeys}
     *
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of SSH keys.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					KeyCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-keys">List all Keys</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract KeyCollectionResponse listAllKeys(
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingKey}
     *
     * @param  	keyId		Id of an SSH key.
     * @return 				An SSH Key.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					KeyResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-key">Retrieve an existing Key</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract KeyResponse retrieveExistingKey(
    		@RestUriParam("key") String keyId) 
    				throws IOException;  
    
    /**
     * Create a new SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewKey}
     *
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				An SSH key.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					KeyResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-key">Create a new Key</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract KeyResponse createNewKey(
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Update an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:updateExistingKey}
     *
     * @param  	keyId		Id of an SSH key.
     * @param  	message 	The JSON request body submitted via #[payload].
     * @return 				An SSH key.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					KeyResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#update-a-key">Update a Key</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.PUT, 
    		contentType = "application/json")
    public abstract KeyResponse updateExistingKey(
    		@RestUriParam("key") int keyId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingKey}
     *
     * @param  	keyId		Id of an SSH key.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#destroy-a-key">Destroy a Key</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingKey(
    		@RestUriParam("key") int keyId) 
    				throws IOException;  
      
    // Regions
    /**
     * List all regions.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllRegions}
     *
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of regions.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					RegionCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-regions">List all Regions</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/regions?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract RegionCollectionResponse listAllRegions(
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
     
    // Sizes
    /**
     * List all sizes.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllSizes}
     *
     * @param	page		Specified page in the result set. Default value is 1.
     * @param	perPage		Number of items return per page in the result set. Default value is 20.
     * @return 				List of sizes.
     * @throws 	IOException A problem communication with DigitalOcean occurred.
     * @see					SizeCollectionResponse
     * @see 				<a href="https://developers.digitalocean.com/documentation/v2/#list-all-sizes">List all Sizes</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/sizes?page=1&per_page=20", 
    		method=HttpMethod.GET, 
    		contentType = "application/json")
    public abstract SizeCollectionResponse listAllSizes(
    		@RestQueryParam("page") @Default("1") int page,
    		@RestQueryParam("per_page") @Default("20") int perPage) 
    				throws IOException;  
    
    // Transformers
    /**
     * Transforms a JSON string into an AccountResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToAccountResponse}
     *
     * @param  	json		JSON response from DigitalOcean.
     * @return 				An account.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static AccountResponse stringToAccountResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	AccountResponse response = gson.fromJson(json, AccountResponse.class);
    	return response;
    }
    
    /**
     * Transforms a JSON string into an ActionCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToActionCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of actions.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static ActionCollectionResponse stringToActionCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	ActionCollectionResponse response = gson.fromJson(json, ActionCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an ActionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToActionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				An action.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static ActionResponse stringToActionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	ActionResponse response = gson.fromJson(json, ActionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an DomainCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToDomainCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of domains.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static DomainCollectionResponse stringToDomainCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	DomainCollectionResponse response = gson.fromJson(json, DomainCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an DomainResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToDomainResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				A domain.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static DomainResponse stringToDomainResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	DomainResponse response = gson.fromJson(json, DomainResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an DomainRecordCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToDomainRecordCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of domain records.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static DomainRecordCollectionResponse stringToDomainRecordCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	DomainRecordCollectionResponse response = gson.fromJson(json, DomainRecordCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an DomainRecordResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToDomainRecordResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				A domain record.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static DomainRecordResponse stringToDomainRecordResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	DomainRecordResponse response = gson.fromJson(json, DomainRecordResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an DropletCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToDropletCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of droplets.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static DropletCollectionResponse stringToDropletCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	DropletCollectionResponse response = gson.fromJson(json, DropletCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an DropletResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToDropletResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				A droplet.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static DropletResponse stringToDropletResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	DropletResponse response = gson.fromJson(json, DropletResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an KernelCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToKernelCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of kernels.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static KernelCollectionResponse stringToKernelCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	KernelCollectionResponse response = gson.fromJson(json, KernelCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an NeighborsCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToNeighborsCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				Lists of neighbor droplets.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static NeighborsCollectionResponse stringToNeighborsCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	NeighborsCollectionResponse response = gson.fromJson(json, NeighborsCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an UpgradeCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToUpgradeCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of droplets to be upgraded.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static UpgradeCollectionResponse stringToUpgradeCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	UpgradeCollectionResponse response = gson.fromJson(json, UpgradeCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an ImageCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToImageCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of images.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static ImageCollectionResponse stringToImageCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	ImageCollectionResponse response = gson.fromJson(json, ImageCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an ImageResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToImageResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				An image.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static ImageResponse stringToImageResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	ImageResponse response = gson.fromJson(json, ImageResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an KeyCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToKeyCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of keys.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static KeyCollectionResponse stringToKeyCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	KeyCollectionResponse response = gson.fromJson(json, KeyCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an KeyResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToKeyResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				A key.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static KeyResponse stringToKeyResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	KeyResponse response = gson.fromJson(json, KeyResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an RegionCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToRegionCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of regions.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static RegionCollectionResponse stringToRegionCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	RegionCollectionResponse response = gson.fromJson(json, RegionCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Transforms a JSON string into an SizeCollectionResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:stringToSizeCollectionResponse}
     * 
     * @param  	json		JSON response from DigitalOcean.
     * @return 				List of sizes.
     * @throws 	JsonSyntaxException
     * 						If json is not a valid representation for an object of the specified class.
     */ 
    @Transformer(sourceTypes = {String.class})
    public static SizeCollectionResponse stringToSizeCollectionResponse(String json) throws JsonSyntaxException {
    	Gson gson = new Gson();
    	SizeCollectionResponse response = gson.fromJson(json, SizeCollectionResponse.class);
    	return response;
    } 
    
    /**
     * Set OAuth Token
     *
     * @param token	OAuth Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get OAuth Token
     */
    public String getToken() {
        return "Bearer " + this.token;
    }
}