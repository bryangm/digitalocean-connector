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
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.mule.modules.digitalocean.objects.requests.*;
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:get-user-information}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-actions}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
 
    /**
     * Retrieve a specific action object.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-action}
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
    		@RestUriParam("action") Integer actionId) 
    				throws IOException;  
    
    // Domains
    /**
     * List all domains.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-domains}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;    
 
    /**
     * Get details about a specific domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-domain}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-domain}
     *
     * @param  	domain		The CreateDomainRequest object to be created.
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
    		CreateDomainRequest domain) 
    				throws IOException;  
    
    /**
     * Delete a domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-domain}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-domain-records}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-domain-record}
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
    		@RestUriParam("record") Integer recordId) 
    				throws IOException;  
    
    /**
     * Create a new domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-domain-record}
     *
     * @param  	domainName 	Name of the domain.
     * @param  	domainRecord
     * 				The CreateDomainRecordRequest object to be created.
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
    		CreateDomainRecordRequest domainRecord) 
    				throws IOException;  
    
    /**
     * Update a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-existing-domain-record}
     *
     * @param  	domainName 	Name of the domain.
     * @param  	recordId 	Id of the domain record.
     * @param  	domainRecord
     * 				The UpdateDomainRecordRequest object to be created.
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
    		@RestUriParam("record") Integer recordId, 
    		UpdateDomainRecordRequest domainRecord) 
    				throws IOException;  
    
    /**
     * Delete a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-domain-record}
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
    		@RestUriParam("record") Integer recordId) 
    				throws IOException;  
    
    // Droplets
    /**
     * List all droplets.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-droplets}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-droplet}
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
    		@RestUriParam("droplet") Integer dropletId) 
    				throws IOException;  
    
    /**
     * List all available kernels for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-available-kernels-for-droplet}
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
    		@RestUriParam("droplet") Integer dropletId,
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * List all snapshots for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-snapshots-for-droplet}
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
    		@RestUriParam("droplet") Integer dropletId,
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * List all backups for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-backups-for-droplet}
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
    		@RestUriParam("droplet") Integer dropletId,
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-actions-for-droplet}
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
    		@RestUriParam("droplet") Integer dropletId,
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * List all neighbors for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-neighbors-for-droplet}
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
    		@RestUriParam("droplet") Integer dropletId) 
    				throws IOException;  
    
    /**
     * Create a new droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-droplet}
     *
     * @param  	droplet 	The CreateDropletRequest object to be created.
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
    		CreateDropletRequest droplet) 
    				throws IOException;  
    
    /**
     * Delete a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-droplet}
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
    		@RestUriParam("droplet") Integer dropletId) 
    				throws IOException;  
    
    /**
     * List all droplet neighbors.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-droplet-neighbors}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-droplet-upgrades}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:execute-droplet-action}
     *
     * @param  	dropletId 	Id of a droplet.
     * @param  	action	 	The ExecuteActionRequest object to be created.
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
    		@RestUriParam("droplet") Integer dropletId, 
    		ExecuteActionRequest action) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-droplet-action}
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
    		@RestUriParam("droplet") Integer dropletId, 
    		@RestUriParam("action") Integer actionId) 
    				throws IOException;      
    
    // Images
    /**
     * List all images.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-images}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-image}
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
    		@RestUriParam("image") Integer imageId) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-actions-for-image}
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
    		@RestUriParam("image") Integer imageId,
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Update an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-existing-image}
     *
     * @param  	imageId 	Id of an image.
     * @param  	image	 	The UpdateImageRequest object to be created.
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
    		@RestUriParam("image") Integer imageId, 
    		UpdateImageRequest image) 
    				throws IOException;  
    
    /**
     * Delete an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-image}
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
    		@RestUriParam("image") Integer imageId) 
    				throws IOException;  

    // Image Actions
    /**
     * Executes an action on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:execute-image-action}
     *
     * @param  	imageId 	Id of an image.
     * @param  	action 		The ExecuteActionRequest object to be created.
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
    		@RestUriParam("image") Integer imageId, 
    		ExecuteActionRequest action) 
    				throws IOException;  
    
    /**
     * Get details about a specific image action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-image-action}
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
    		@RestUriParam("image") Integer imageId, 
    		@RestUriParam("action") Integer actionId) 
    				throws IOException;  
       
    // SSH Keys
    /**
     * List all SSH keys.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-keys}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-key}
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
    		@RestUriParam("key") Integer keyId) 
    				throws IOException;  
    
    /**
     * Create a new SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-key}
     *
     * @param  	key		 	The CreateKeyRequest object to be created.
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
    		CreateKeyRequest key) 
    				throws IOException;  
    
    /**
     * Update an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-existing-key}
     *
     * @param  	keyId		Id of an SSH key.
     * @param  	key		 	The UpdateKeyRequest object to be created.
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
    		@RestUriParam("key") Integer keyId, 
    		UpdateKeyRequest key) 
    				throws IOException;  
    
    /**
     * Delete an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-key}
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
    		@RestUriParam("key") Integer keyId) 
    				throws IOException;  
      
    // Regions
    /**
     * List all regions.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-regions}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
     
    // Sizes
    /**
     * List all sizes.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-sizes}
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
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    // Transformers
    /**
     * Transforms a JSON string into an AccountResponse
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-account-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-action-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-action-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-domain-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-domain-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-domain-record-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-domain-record-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-droplet-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-droplet-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-kernel-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-neighbors-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-upgrade-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-image-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-image-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-key-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-key-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-region-collection-response}
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
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:string-to-size-collection-response}
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
     * Transforms a CreateDomainRecordRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-domain-record-request-to-string}
     * 
     * @param  	request		CreateDomainRecordRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of CreateDomainRecordRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {CreateDomainRecordRequest.class})
    public static String createDomainRecordRequestToString(CreateDomainRecordRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
    	return response;
    }
    
    /**
     * Transforms a CreateDomainRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-domain-request-to-string}
     * 
     * @param  	request		CreateDomainRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of CreateDomainRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {CreateDomainRequest.class})
    public static String createDomainRequestToString(CreateDomainRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
    	return response;
    }
    
    /**
     * Transforms a CreateDropletRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-droplet-request-to-string}
     * 
     * @param  	request		CreateDropletRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of CreateDropletRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {CreateDropletRequest.class})
    public static String createDropletRequestToString(CreateDropletRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
    	return response;
    }
    
    /**
     * Transforms a CreateKeyRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-key-request-to-string}
     * 
     * @param  	request		CreateKeyRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of CreateKeyRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {CreateKeyRequest.class})
    public static String createKeyRequestToString(CreateKeyRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
    	return response;
    }
    
    /**
     * Transforms a UpdateDomainRecordRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-domain-record-request-to-string}
     * 
     * @param  	request		UpdateDomainRecordRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of UpdateDomainRecordRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {UpdateDomainRecordRequest.class})
    public static String updateDomainRecordRequestToString(UpdateDomainRecordRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
    	return response;
    }
    
    /**
     * Transforms a UpdateImageRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-image-request-to-string}
     * 
     * @param  	request		UpdateImageRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of UpdateImageRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {UpdateImageRequest.class})
    public static String updateImageRequestToString(UpdateImageRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
    	return response;
    }
    
    /**
     * Transforms a UpdateKeyRequest into a JSON string
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-key-request-to-string}
     * 
     * @param  	request		UpdateKeyRequest object to be sent to DigitalOcean.
     * @return 				JSON representation of UpdateKeyRequest object.
     * @throws 	JsonIOException
     * 						If there was a problem writing to the writer.
     */ 
    @Transformer(sourceTypes = {UpdateKeyRequest.class})
    public static String updateKeyRequestToString(UpdateKeyRequest request) throws JsonIOException {
    	Gson gson = new Gson();
    	String response = gson.toJson(request);
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