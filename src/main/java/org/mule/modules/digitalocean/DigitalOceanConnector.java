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
     * Greeting message
     */
	@RestHeaderParam("Authorization") 
    @Configurable
    private String token;

    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/digitalocean-connector.xml.sample digitalocean:getUserInformation}
     *
     * @return The account information for the authenticated user.
     * @throws IOException Comment for Exception
     */
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account", 
    		method=HttpMethod.GET)
    public abstract String getUserInformation() throws IOException;  
    
    
    // Actions
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/actions", 
    		method=HttpMethod.GET)
    public abstract String listAllActions() throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/actions/{action}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingAction(
    		@RestUriParam("action") String actionId) 
    				throws IOException;  
    
    
    //Domains
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains", 
    		method=HttpMethod.GET)
    public abstract String listAllDomains() throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewDomain(
    		@Payload String message) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}", 
    		method=HttpMethod.DELETE)
    public abstract String deleteExistingDomain(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    
    //Domain Records
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records", 
    		method=HttpMethod.GET)
    public abstract String listAllDomainRecords(
    		@RestUriParam("domain") String domainName) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") String recordId) 
    				throws IOException;  
    
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
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/domains/{domain}/records/{record}", 
    		method=HttpMethod.DELETE)
    public abstract String deleteExistingDomainRecord(
    		@RestUriParam("domain") String domainName, 
    		@RestUriParam("record") String recordId) 
    				throws IOException;  
    
    
    //Droplets
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets", 
    		method=HttpMethod.GET)
    public abstract String listAllDroplets() throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/kernels", 
    		method=HttpMethod.GET)
    public abstract String listAllAvailableKernelsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/snapshots", 
    		method=HttpMethod.GET)
    public abstract String listAllSnapshotsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/backups", 
    		method=HttpMethod.GET)
    public abstract String listAllBackupsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions", 
    		method=HttpMethod.GET)
    public abstract String listAllActionsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/neighbors", 
    		method=HttpMethod.GET)
    public abstract String listAllNeighborsForDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewDroplet(
    		@Payload String message) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}", 
    		method=HttpMethod.DELETE)
    public abstract String deleteExistingDroplet(
    		@RestUriParam("droplet") String dropletId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/reports/droplet_neighbors", 
    		method=HttpMethod.GET)
    public abstract String listAllDropletNeighbors() throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplet_upgrades", 
    		method=HttpMethod.GET)
    public abstract String listDropletUpgrades() throws IOException;  
    
    
    //Droplet Actions
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
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/droplets/{droplet}/actions/{action}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingDropletAction(
    		@RestUriParam("droplet") String dropletId, 
    		@RestUriParam("action") String actionId) 
    				throws IOException;      
    
    
    //Images
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images?type=all&private=false", 
    		method=HttpMethod.GET)
    public abstract String listAllImages(
    		@RestQueryParam("type") @Default("all") String imageType, 
    		@RestQueryParam("private") @Default("false") String privateImages) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingImage(
    		@RestUriParam("image") String imageId) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions", 
    		method=HttpMethod.GET)
    public abstract String listAllActionsForImage(
    		@RestUriParam("image") String imageId) 
    				throws IOException;  
    
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
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}", 
    		method=HttpMethod.DELETE)
    public abstract String deleteExistingImage(
    		@RestUriParam("image") String imageId) 
    				throws IOException;  
    
    
    //Image Actions
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
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/images/{image}/actions/{action}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingImageAction(
    		@RestUriParam("image") String imageId, 
    		@RestUriParam("action") String actionId) 
    				throws IOException;  
    
    
    //SSH Keys
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys", 
    		method=HttpMethod.GET)
    public abstract String listAllKeys() throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.GET)
    public abstract String retrieveExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint) 
    				throws IOException;  
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys", 
    		method=HttpMethod.POST, 
    		contentType = "application/json")
    public abstract String createNewKey(
    		@Payload String message) 
    				throws IOException;  
    
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
    
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/account/keys/{key}", 
    		method=HttpMethod.DELETE)
    public abstract String deleteExistingKey(
    		@RestUriParam("key") String keyIdOrFingerprint) 
    				throws IOException;  
    
    
    //Regions
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/regions", 
    		method=HttpMethod.GET)
    public abstract String listAllRegions() throws IOException;  
    
    
    //Sizes
    @Processor
    @ReconnectOn(exceptions = { Exception.class })
    @RestCall(
    		uri="https://api.digitalocean.com/v2/sizes", 
    		method=HttpMethod.GET)
    public abstract String listAllSizes() throws IOException;  
    
    
    /**
     * Set greeting message
     *
     * @param greeting the message
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get greeting message
     */
    public String getToken() {
        return "Bearer " + this.token;
    }
}