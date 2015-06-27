/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.digitalocean;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Payload;
import org.mule.api.annotations.Processor;

import java.io.IOException;

import org.mule.api.annotations.ReconnectOn;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestHeaderParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="digitalocean", friendlyName="DigitalOcean")
public abstract class DigitalOceanConnector {

    /**
     * Previously generated OAuth Token
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
     * @return The account information for the authenticated user.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#get-user-information">Get User Information</a>
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account", 
    		method=HttpMethod.GET)
    public abstract String getUserInformation() throws IOException;  
    
    // Actions
    /**
     * List all actions that have been executed on the current account.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllActions}
     *
     * @return List of actions.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-actions">List all Actions</a>
     */    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/actions", 
    		method=HttpMethod.GET)
    public abstract String listAllActions() throws IOException;  
 
    /**
     * Retrieve a specific action object.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingAction}
     *
     * @param  actionId	ID of the action.
     * @return An action.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-action">Retrieve an existing Action</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/actions/{action}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingAction(
    		@RestUriParam("action") String actionId) 
    				throws IOException;  
    
    // Domains
    /**
     * List all domains.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDomains}
     *
     * @return List of domains.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-domains">List all Domains</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains", 
    		method=HttpMethod.GET)
    public abstract String listAllDomains() throws IOException;  
 
    /**
     * Get details about a specific domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDomain}
     *
     * @param  domainName Name of the domain.
     * @return A domain.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-domain">Retrieve an existing Domain</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    /**
     * Create a new domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewDomain}
     *
     * @param  message The JSON request body submitted via #[payload].
     * @return A domain.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-domain">Create a new Domain</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewDomain(
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete a domain.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingDomain}
     *
     * @param  domainName Name of the domain.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#delete-a-domain">Delete a Domain</a>
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
     * @param  domainName Name of the domain.
     * @return List of domain records.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-domain-records">List all Domain Records</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records", 
    		method=HttpMethod.GET)
    public abstract String listAllDomainRecords(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    /**
     * Get details about a specific domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDomainRecord}
     *
     * @param  domainName Name of the domain.
     * @param  recordId Id of the domain record.
     * @return A domain record.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-domain-record">Retrieve an existing Domain Record</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") String recordId) 
    				throws IOException;  
    
    /**
     * Create a new domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewDomainRecord}
     *
     * @param  domainName Name of the domain.
     * @param  message The JSON request body submitted via #[payload].
     * @return A domain record.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-domain-record">Create a new Domain Record</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Update a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:updateExistingDomainRecord}
     *
     * @param  domainName Name of the domain.
     * @param  recordId Id of the domain record.
     * @param  message The JSON request body submitted via #[payload].
     * @return A domain record.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#update-a-domain-record">Update a Domain Record</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.PUT, 
    		contentType = "application/json")
    public abstract String updateExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") String recordId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete a domain record.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingDomainRecord}
     *
     * @param  domainName Name of the domain.
     * @param  recordId Id of the domain record.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#delete-a-domain-record">Delete a Domain Record</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") String recordId) 
    				throws IOException;  
    
    // Droplets
    /**
     * List all droplets.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDroplets}
     *
     * @return List of droplets.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-droplets">List all Droplets</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets", 
    		method=HttpMethod.GET)
    public abstract String listAllDroplets() throws IOException;  
    
    /**
     * Get details about a specific droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @return A droplet.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-droplet-by-id">Retrieve an existing Droplet by id</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * List all available kernels for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllAvailableKernelsForDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @return List of kernels.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-available-kernels-for-a-droplet">List all available Kernels for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/kernels", 
    		method=HttpMethod.GET)
    public abstract String listAllAvailableKernelsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * List all snapshots for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllSnapshotsForDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @return List of snapshots.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-snapshots-for-a-droplet">List snapshots for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/snapshots", 
    		method=HttpMethod.GET)
    public abstract String listAllSnapshotsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * List all backups for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllBackupsForDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @return List of backups.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-backups-for-a-droplet">List backups for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/backups", 
    		method=HttpMethod.GET)
    public abstract String listAllBackupsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllActionsForDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @return List of actions.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-actions-for-a-droplet">List actions for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions", 
    		method=HttpMethod.GET)
    public abstract String listAllActionsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * List all neighbors for a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllNeighborsForDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @return List of droplets.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-neighbors-for-a-droplet">List Neighbors for a Droplet</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/neighbors", 
    		method=HttpMethod.GET)
    public abstract String listAllNeighborsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * Create a new droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewDroplet}
     *
     * @param  message The JSON request body submitted via #[payload].
     * @return A droplet.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-droplet">Create a new Droplet</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewDroplet(
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingDroplet}
     *
     * @param  dropletId Id of a droplet.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#delete-a-droplet">Delete a Droplet</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    /**
     * List all droplet neighbors.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllDropletNeighbors}
     *
     * @return List of droplets.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-droplet-neighbors">List all Droplet Neighbors</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/reports/droplet_neighbors", 
    		method=HttpMethod.GET)
    public abstract String listAllDropletNeighbors() throws IOException;  
    
    /**
     * List all droplets schedule to be upgraded.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listDropletUpgrades}
     *
     * @return List of droplets.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-droplet-upgrades">List Droplet Upgrades</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplet_upgrades", 
    		method=HttpMethod.GET)
    public abstract String listDropletUpgrades() throws IOException;  
    
    // Droplet Actions
    /**
     * Executes an action on a droplet.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:executeDropletAction}
     *
     * @param  dropletId Id of a droplet.
     * @param  message The JSON request body submitted via #[payload].
     * @return A droplet.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#disable-backups">Disable Backups</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#reboot-a-droplet">Reboot a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#power-cycle-a-droplet">Power Cycle a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#shutdown-a-droplet">Shutdown a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#power-off-a-droplet">Power Off a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#power-on-a-droplet">Power On a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#restore-a-droplet">Restore a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#password-reset-a-droplet">Password Reset a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#resize-a-droplet">Resize a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#rebuild-a-droplet">Rebuild a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#rename-a-droplet">Rename a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#change-the-kernel">Change the Kernel</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#enable-ipv6">Enable IPv6</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#enable-private-networking">Enable Private Networking</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#snapshot-a-droplet">Snapshot a Droplet</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#upgrade-a-droplet">Upgrade a Droplet</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String executeDropletAction(
    		@RestUriParam("droplet") String dropletId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Get details about a specific droplet action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingDropletAction}
     *
     * @param  dropletId Id of a droplet.
     * @param  actionId Id of an action.
     * @return A droplet.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-a-droplet-action">Retrieve a Droplet Action</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions/{action}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDropletAction(
    		@RestUriParam("droplet") String dropletId, 
    		@RestUriParam("action") String actionId) 
    				throws IOException;      
    
    // Images
    /**
     * List all images.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllImages}
     *
     * @param imageType	
     * 				Type of images to return: application, distribution, all.  The default is all.
     * @param privateImages
     * 				Boolean to return the authenticated user's private images.  The default is false, which returns all images.
     * @return List of images.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-images">List all Images</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-distribution-images">List all Distribution Images</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-application-images">List all Application Images</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-a-user-s-images">List a User's Images</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images?type=all&private=false", 
    		method=HttpMethod.GET)
    public abstract String listAllImages(
    		@RestQueryParam("type") @Default("all") String imageType, 
    		@RestQueryParam("private") @Default("false") String privateImages) 
    				throws IOException;  
    
    /**
     * Get details about a specific image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingImage}
     *
     * @param  imageId Id of an image.
     * @return An image.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-by-id">Retrieve an existing Image by id</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-by-slug">Retrieve an existing Image by slug</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingImage(
    		@RestUriParam("image") String imageId) 
    				throws IOException;  
    
    /**
     * List all actions that have been executed on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllActionsForImage}
     *
     * @param  imageId Id of an image.
     * @return List of actions.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-actions-for-an-image">List all Actions for an Image</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions", 
    		method=HttpMethod.GET)
    public abstract String listAllActionsForImage(
    		@RestUriParam("image") String imageId) 
    				throws IOException;  
    
    /**
     * Update an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:updateExistingImage}
     *
     * @param  imageId Id of an image.
     * @param  message The JSON request body submitted via #[payload].
     * @return An image.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#update-an-image">Update an Image</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.PUT, 
    		contentType = "application/json")
    public abstract String updateExistingImage(
    		@RestUriParam("image") String imageId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingImage}
     *
     * @param  imageId Id of an image.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#delete-an-image">Delete an Image</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingImage(
    		@RestUriParam("image") String imageId) 
    				throws IOException;  

    // Image Actions
    /**
     * Executes an action on an image.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:executeImageAction}
     *
     * @param  imageId Id of an image.
     * @param  message The JSON request body submitted via #[payload].
     * @return An image.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#transfer-an-image">Transfer an Image</a>
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#convert-an-image-to-a-snapshot">Convert an Image to a Snapshot</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String executeImageAction(
    		@RestUriParam("image") String imageId, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Get details about a specific image action.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingImageAction}
     *
     * @param  imageId  Id of an image.
     * @param  actionId Id of an action.
     * @return An image.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-image-action">Retrieve an existing Image Action</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions/{action}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingImageAction(
    		@RestUriParam("image") String imageId, 
    		@RestUriParam("action") String actionId) 
    				throws IOException;  
       
    // SSH Keys
    /**
     * List all SSH keys.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllKeys}
     *
     * @return List of SSH keys.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-keys">List all Keys</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys", 
    		method=HttpMethod.GET)
    public abstract String listAllKeys() throws IOException;  
    
    /**
     * Get details about a specific SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:retrieveExistingKey}
     *
     * @param  keyIdOrFingerprint	
     * 				Id or fingerprint of an SSH key.
     * @return An SSH Key.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#retrieve-an-existing-key">Retrieve an existing Key</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint) 
    				throws IOException;  
    
    /**
     * Create a new SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:createNewKey}
     *
     * @param  message The JSON request body submitted via #[payload].
     * @return An SSH key.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#create-a-new-key">Create a new Key</a>
     */  
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewKey(
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Update an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:updateExistingKey}
     *
     * @param  keyIdOrFingerprint	
     * 				Id or fingerprint of an SSH key.
     * @param  message The JSON request body submitted via #[payload].
     * @return An SSH key.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#update-a-key">Update a Key</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.PUT, 
    		contentType = "application/json")
    public abstract String updateExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint, 
    		@Payload String message) 
    				throws IOException;  
    
    /**
     * Delete an SSH key.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:deleteExistingKey}
     *
     * @param  keyIdOrFingerprint	
     * 				Id or fingerprint of an SSH key.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#destroy-a-key">Destroy a Key</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.DELETE)
    public abstract void deleteExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint) 
    				throws IOException;  
      
    // Regions
    /**
     * List all regions.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllRegions}
     *
     * @return List of regions.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-regions">List all Regions</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/regions", 
    		method=HttpMethod.GET)
    public abstract String listAllRegions() throws IOException;  
     
    // Sizes
    /**
     * List all sizes.
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:listAllSizes}
     *
     * @return List of sizes.
     * @throws IOException A problem communication with DigitalOcean occurred.
     * @see <a href="https://developers.digitalocean.com/documentation/v2/#list-all-sizes">List all Sizes</a>
     */ 
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/sizes", 
    		method=HttpMethod.GET)
    public abstract String listAllSizes() throws IOException;  
    
    
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