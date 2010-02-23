package org.collectionspace.chain.csp.inner;

import java.util.HashMap;
import java.util.Map;

import org.collectionspace.chain.csp.config.ConfigRoot;
import org.collectionspace.chain.csp.config.Configurable;
import org.collectionspace.chain.csp.config.ReadOnlySection;
import org.collectionspace.chain.csp.config.Rules;
import org.collectionspace.chain.csp.config.Target;
import org.collectionspace.csp.api.core.CSP;
import org.collectionspace.csp.api.core.CSPContext;

// XXX call order DependencyNotSatisfiedException
public class CoreConfig implements CSP, Configurable, ConfigRoot {
	private Map<String,Object> roots=new HashMap<String,Object>();
	
	public void go(CSPContext ctx) { 
		ctx.addConfigRules(this);
		ctx.setConfigRoot(this);
		ctx.addConfigRules(this);
	}
	
	public String getName() { return "config.core"; }

	public void configure(Rules rules) {
		/* ROOT/collection-space -> MAIN */
		rules.addRule("ROOT",new String[]{"collection-space"},"org.collectionspace.app.cfg.main",null,new Target() {
			public Object populate(Object parent, ReadOnlySection milestone) {
				return CoreConfig.this;
			}			
		});
	}
	
	public void setRoot(String key,Object value) { roots.put(key,value); }
	public Object getRoot(String key) { return roots.get(key); }

	public void config_finish() {}
}
