package org.collectionspace.csp.api.core;

import org.collectionspace.chain.csp.config.ConfigRoot;
import org.collectionspace.chain.csp.config.Configurable;
import org.collectionspace.csp.api.persistence.Storage;
import org.collectionspace.csp.api.persistence.StorageGenerator;
import org.collectionspace.csp.api.ui.UI;

public interface CSPContext {
	// Config
	public void addConfigRules(Configurable cfg);
	public void setConfigRoot(ConfigRoot cfg);
	public ConfigRoot getConfigRoot();
	
	// Storage
	public void addStorageType(String name,StorageGenerator store);
	
	// UI
	public void addUI(String name,UI ui);
	
	/* Only after configuration */
	
	// Storage
	public StorageGenerator getStorage(String name);
	
	// UI
	public UI getUI(String name);
}
