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
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestHeaderParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;
import org.mule.api.annotations.rest.RestExceptionOn;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.mule.modules.digitalocean.common.ImageType;
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
     * @return 	AccountResponse	object containing account information for the authenticated user.
     * @throws 	IOException	
     * 				A problem communication with DigitalOcean occurred.
     * @see		AccountResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#get-user-information">Get User Information</a>
     */
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/account", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    		exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract AccountResponse getUserInformation() throws IOException;  
    
    // Actions
    /**
     * List all actions that have been executed on the current account.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-actions}
     *
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	ActionCollectionResponse object containing a of actions.
     * @throws 	IOException
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-actions">List all Actions</a>
     */    
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/actions?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ActionCollectionResponse listAllActions(
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
 
    /**
     * Retrieve a specific action object.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-action}
     *
     * @param  	actionId	
     * 				ID of the action.
     * @return 	ActionResponse object containing an action.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-action">Retrieve an existing Action</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/actions/{action}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ActionResponse retrieveExistingAction(
    		@RestUriParam("action") Integer actionId) 
    				throws IOException;  
    
    // Domains
    /**
     * List all domains.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-domains}
     * 
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	DomainCollectionResponse object containing a list of domains.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-domains">List all Domains</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DomainCollectionResponse listAllDomains(
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;    
 
    /**
     * Get details about a specific domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-domain}
     *
     * @param  	domainName	
     * 				Name of the domain.
     * @return 	DomainResponse object containing a domain.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-domain">Retrieve an existing Domain</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DomainResponse retrieveExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    /**
     * Create a new domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-domain}
     *
     * @param  	createDomain		
     * 				The CreateDomainRequest object to be created.
     * @return 	DomainResponse object containing a domain.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-domain">Create a new Domain</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains", 
    		method = HttpMethod.POST, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DomainResponse createNewDomain(
    		CreateDomainRequest createDomain) 
    				throws IOException;  
    
    /**
     * Delete a domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-domain}
     *
     * @param  	domainName 	
     * 				Name of the domain.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#delete-a-domain">Delete a Domain</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}", 
    		method = HttpMethod.DELETE,
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract void deleteExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    // Domain Records
    /**
     * List all domain records for a domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-domain-records}
     *
     * @param  	domainName 	
     * 				Name of the domain.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	DomainRecordCollectionResponse object containing a list of domain records.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainRecordCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-domain-records">List all Domain Records</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}/records?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	domainName 	
     * 				Name of the domain.
     * @param  	recordId 	
     * 				Id of the domain record.
     * @return 	DomainRecordResponse object containing a domain record.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainRecordResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-domain-record">Retrieve an existing Domain Record</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DomainRecordResponse retrieveExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") Integer recordId) 
    				throws IOException;  
    
    /**
     * Create a new domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-domain-record}
     *
     * @param  	domainName 	
     * 				Name of the domain.
     * @param  	createDomainRecord
     * 				The CreateDomainRecordRequest object to be created.
     * @return 	DomainRecordResponse object containing a domain record.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainRecordResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-domain-record">Create a new Domain Record</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}/records", 
    		method = HttpMethod.POST, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DomainRecordResponse createNewDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		CreateDomainRecordRequest createDomainRecord) 
    				throws IOException;  
    
    /**
     * Update a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-existing-domain-record}
     *
     * @param  	domainName 	
     * 				Name of the domain.
     * @param  	recordId 	
     * 				Id of the domain record.
     * @param  	updateDomainRecord
     * 				The UpdateDomainRecordRequest object to be created.
     * @return 	DomainRecordResponse object containing a domain record.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DomainRecordResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#update-a-domain-record">Update a Domain Record</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method = HttpMethod.PUT, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DomainRecordResponse updateExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") Integer recordId, 
    		UpdateDomainRecordRequest updateDomainRecord) 
    				throws IOException;  
    
    /**
     * Delete a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-domain-record}
     *
     * @param  	domainName 	
     * 				Name of the domain.
     * @param  	recordId 	
     * 				Id of the domain record.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#delete-a-domain-record">Delete a Domain Record</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method = HttpMethod.DELETE,
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	DropletCollectionResponse object containing a list of droplets.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DropletCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-droplets">List all Droplets</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DropletCollectionResponse listAllDroplets(
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-droplet}
     *
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @return 	DropletResponse object containing a droplet.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DropletResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-droplet-by-id">Retrieve an existing Droplet by id</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DropletResponse retrieveExistingDroplet(
    		@RestUriParam("droplet") Integer dropletId) 
    				throws IOException;  
    
    /**
     * List all available kernels for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-available-kernels-for-droplet}
     *
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	KernelCollectionResponse object containing a list of kernels.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		KernelCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-available-kernels-for-a-droplet">List all available Kernels for a Droplet</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/kernels?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	ImageCollectionResponse containing a list of snapshots.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ImageCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-snapshots-for-a-droplet">List snapshots for a Droplet</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/snapshots?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	ImageCollectionResponse object containing a list of backups.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ImageCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-backups-for-a-droplet">List backups for a Droplet</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/backups?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	ActionCollectionResponse object containing a list of actions.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-actions-for-a-droplet">List actions for a Droplet</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/actions?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @return 	DropletCollectionResponse object containing a list of droplets.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DropletCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-neighbors-for-a-droplet">List Neighbors for a Droplet</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/neighbors", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DropletCollectionResponse listAllNeighborsForDroplet(
    		@RestUriParam("droplet") Integer dropletId) 
    				throws IOException;  
    
    /**
     * Create a new droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-droplet}
     *
     * @param  	createDroplet
     * 			 	The CreateDropletRequest object to be created.
     * @return 	DropletResponse object containing a droplet.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		DropletResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-droplet">Create a new Droplet</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets", 
    		method = HttpMethod.POST, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract DropletResponse createNewDroplet(
    		CreateDropletRequest createDroplet) 
    				throws IOException;  
    
    /**
     * Delete a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-droplet}
     *
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#delete-a-droplet">Delete a Droplet</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method = HttpMethod.DELETE,
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract void deleteExistingDroplet(
    		@RestUriParam("droplet") Integer dropletId) 
    				throws IOException;  
    
    /**
     * List all droplet neighbors.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-droplet-neighbors}
     *
     * @return 	NeighborsCollectionResponse object containing list of droplets' neighbors.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		NeighborsCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-droplet-neighbors">List all Droplet Neighbors</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/reports/droplet_neighbors", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract NeighborsCollectionResponse listAllDropletNeighbors() throws IOException;  
    
    /**
     * List all droplets scheduled to be upgraded.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-droplet-upgrades}
     *
     * @return 	UpgradeCollectionResponse object containing a list of droplets to be upgraded.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		UpgradeCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-droplet-upgrades">List Droplet Upgrades</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplet_upgrades", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract UpgradeCollectionResponse listDropletUpgrades() throws IOException;  
    
    // Droplet Actions
    /**
     * Executes an action on a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:execute-droplet-action}
     *
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @param  	executeAction
     * 			 	The ExecuteActionRequest object to be created.
     * @return 	ActionResponse object containing an action.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#disable-backups">Disable Backups</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#reboot-a-droplet">Reboot a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#power-cycle-a-droplet">Power Cycle a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#shutdown-a-droplet">Shutdown a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#power-off-a-droplet">Power Off a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#power-on-a-droplet">Power On a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#restore-a-droplet">Restore a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#password-reset-a-droplet">Password Reset a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#resize-a-droplet">Resize a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#rebuild-a-droplet">Rebuild a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#rename-a-droplet">Rename a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#change-the-kernel">Change the Kernel</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#enable-ipv6">Enable IPv6</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#enable-private-networking">Enable Private Networking</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#snapshot-a-droplet">Snapshot a Droplet</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#upgrade-a-droplet">Upgrade a Droplet</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/actions", 
    		method = HttpMethod.POST, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ActionResponse executeDropletAction(
    		@RestUriParam("droplet") Integer dropletId, 
    		ExecuteActionRequest executeAction) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-droplet-action}
     *
     * @param  	dropletId 	
     * 				Id of a droplet.
     * @param  	actionId 	
     * 				Id of an action.
     * @return 	ActionResponse object containing an action.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-a-droplet-action">Retrieve a Droplet Action</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/droplets/{droplet}/actions/{action}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param 	imageType	
     * 				Type of images to return: application, distribution, all.  The default is all.
     * @param 	privateImages
     * 				Boolean to return the authenticated user's private images.  The default is false, which returns all images.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	ImageCollectionResponse object containing a list of images.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ImageCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-images">List all Images</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-distribution-images">List all Distribution Images</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-application-images">List all Application Images</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-a-user-s-images">List a User's Images</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images?type=all&private=false&page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ImageCollectionResponse listAllImages(
    		@RestQueryParam("type") @Default("all") ImageType imageType, 
    		@RestQueryParam("private") @Default("false") String privateImages,
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-image}
     *
     * @param  	imageIdOrSlug
     * 				Id or slug of an image.
     * @return 	ImageResponse object containing an image.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ImageResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-by-id">Retrieve an existing Image by id</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-by-slug">Retrieve an existing Image by slug</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images/{image}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ImageResponse retrieveExistingImage(
    		@RestUriParam("image") String imageIdOrSlug) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-actions-for-image}
     *
     * @param  	imageId 	
     * 				Id of an image.
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	ActionCollectionResponse object containing a list of actions.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-actions-for-an-image">List all Actions for an Image</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images/{image}/actions?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	imageId 	
     * 				Id of an image.
     * @param  	updateImage
     * 			 	The UpdateImageRequest object to be created.
     * @return 	ImageResponse object containing an image.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ImageResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#update-an-image">Update an Image</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images/{image}", 
    		method = HttpMethod.PUT, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ImageResponse updateExistingImage(
    		@RestUriParam("image") Integer imageId, 
    		UpdateImageRequest updateImage) 
    				throws IOException;  
    
    /**
     * Delete an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-image}
     *
     * @param  	imageId 	
     * 				Id of an image.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#delete-an-image">Delete an Image</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images/{image}", 
    		method = HttpMethod.DELETE,
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract void deleteExistingImage(
    		@RestUriParam("image") Integer imageId) 
    				throws IOException;  

    // Image Actions
    /**
     * Executes an action on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:execute-image-action}
     *
     * @param  	imageId 	
     * 				Id of an image.
     * @param  	executeAction
     * 		 		The ExecuteActionRequest object to be created.
     * @return 	ActionResponse object containing an action.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#transfer-an-image">Transfer an Image</a>
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#convert-an-image-to-a-snapshot">Convert an Image to a Snapshot</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images/{image}/actions", 
    		method = HttpMethod.POST, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract ActionResponse executeImageAction(
    		@RestUriParam("image") Integer imageId, 
    		ExecuteActionRequest executeAction) 
    				throws IOException;  
    
    /**
     * Get details about a specific image action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-image-action}
     *
     * @param  	imageId  	
     * 				Id of an image.
     * @param  	actionId 	
     * 				Id of an action.
     * @return 	ActionResponse object containing an action.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		ActionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-action">Retrieve an existing Image Action</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/images/{image}/actions/{action}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	KeyCollectionResponse object containing a list of SSH keys.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		KeyCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-keys">List all Keys</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/account/keys?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract KeyCollectionResponse listAllKeys(
    		@RestQueryParam("page") @Default("1") Integer page,
    		@RestQueryParam("per_page") @Default("20") Integer perPage) 
    				throws IOException;  
    
    /**
     * Get details about a specific SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieve-existing-key}
     *
     * @param  	keyIdOrFingerprint
     * 				Id of an SSH key.
     * @return 	KeyResponse object containing a SSH Key.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		KeyResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-key">Retrieve an existing Key</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/account/keys/{key}", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract KeyResponse retrieveExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint) 
    				throws IOException;  
    
    /**
     * Create a new SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:create-new-key}
     *
     * @param  	createKey 	
     * 				The CreateKeyRequest object to be created.
     * @return 	KeyResponse object containing a SSH Key.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		KeyResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-key">Create a new Key</a>
     */  
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/account/keys", 
    		method = HttpMethod.POST, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract KeyResponse createNewKey(
    		CreateKeyRequest createKey) 
    				throws IOException;  
    
    /**
     * Update an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:update-existing-key}
     *
     * @param  	keyIdOrFingerprint
     * 				Id of an SSH key.
     * @param  	updateKey 	
     * 				The UpdateKeyRequest object to be created.
     * @return 	KeyResponse object containing a SSH Key.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		KeyResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#update-a-key">Update a Key</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/account/keys/{key}", 
    		method = HttpMethod.PUT, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract KeyResponse updateExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint, 
    		UpdateKeyRequest updateKey) 
    				throws IOException;  
    
    /**
     * Delete an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:delete-existing-key}
     *
     * @param  	keyIdOrFingerprint
     * 				Id of an SSH key.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#destroy-a-key">Destroy a Key</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/account/keys/{key}", 
    		method = HttpMethod.DELETE,
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
    public abstract void deleteExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint) 
    				throws IOException;  
      
    // Regions
    /**
     * List all regions.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:list-all-regions}
     *
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	RegionCollectionResponse object containing a list of regions.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		RegionCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-regions">List all Regions</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/regions?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param	page		
     * 				Specified page in the result set. Default value is 1.
     * @param	perPage		
     * 				Number of items return per page in the result set. Default value is 20.
     * @return 	SizeCollectionResponse object containing a list of sizes.
     * @throws 	IOException 
     * 				A problem communication with DigitalOcean occurred.
     * @see		SizeCollectionResponse
     * @see 	<a href="https://developers.digitalocean.com/documentation/v2/#list-all-sizes">List all Sizes</a>
     */ 
    @Processor
    @RestCall(
    		uri = "https://api.digitalocean.com/v2/sizes?page=1&per_page=20", 
    		method = HttpMethod.GET, 
    		contentType = "application/json",
    	    exceptions = {@RestExceptionOn(expression = "#[!message.inboundProperties['http.status'].startsWith('2')]")})
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
     * @param  	json		
     * 				JSON representation.
     * @return 	AccountResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	ActionCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	ActionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	DomainCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	DomainResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	DomainRecordCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	DomainRecordResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	DropletCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	DropletResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	KernelCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	NeighborsCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	UpgradeCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	ImageCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	ImageResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	KeyCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	KeyResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	RegionCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	json		
     * 				JSON representation.
     * @return 	SizeCollectionResponse object.
     * @throws 	JsonSyntaxException
     * 				If json is not a valid representation for an object of the specified class.
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
     * @param  	request		
     * 				CreateDomainRecordRequest object.
     * @return 	JSON representation of CreateDomainRecordRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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
     * @param  	request		
     * 				CreateDomainRequest object.
     * @return 	JSON representation of CreateDomainRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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
     * @param  	request		
     * 				CreateDropletRequest object.
     * @return 	JSON representation of CreateDropletRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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
     * @param  	request		
     * 				CreateKeyRequest object.
     * @return 	JSON representation of CreateKeyRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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
     * @param  	request		
     * 				UpdateDomainRecordRequest object.
     * @return 	JSON representation of UpdateDomainRecordRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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
     * @param  	request		
     * 				UpdateImageRequest object.
     * @return 	JSON representation of UpdateImageRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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
     * @param  	request		
     * 				UpdateKeyRequest object.
     * @return 	JSON representation of UpdateKeyRequest object.
     * @throws 	JsonIOException
     * 				If there was a problem writing to the writer.
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