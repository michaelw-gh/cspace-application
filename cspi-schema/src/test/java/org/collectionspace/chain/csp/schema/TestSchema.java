package org.collectionspace.chain.csp.schema;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.collectionspace.bconfigutils.bootstrap.BootstrapConfigController;
import org.collectionspace.chain.csp.config.ConfigRoot;
import org.collectionspace.chain.csp.inner.BootstrapCSP;
import org.collectionspace.chain.csp.inner.CoreConfig;
import org.collectionspace.csp.api.container.CSPManager;
import org.collectionspace.csp.container.impl.CSPManagerImpl;
import org.junit.Test;
import org.xml.sax.InputSource;

public class TestSchema {
	private InputStream getSource(String file) {
		String name=getClass().getPackage().getName().replaceAll("\\.","/")+"/"+file;
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}
	
	@Test public void testSchema() throws Exception {
		CSPManager cspm=new CSPManagerImpl();
		BootstrapConfigController bootstrap=new BootstrapConfigController(null);
		bootstrap.go();
		cspm.register(new CoreConfig());
		cspm.register(new Spec());
		cspm.register(new BootstrapCSP(bootstrap));
		cspm.go();
		cspm.configure(new InputSource(getSource("config.xml")),null);
		ConfigRoot root=cspm.getConfigRoot();
		Spec spec=(Spec)root.getRoot(Spec.SPEC_ROOT);
		assertNotNull(spec);
		BootstrapConfigController b2=(BootstrapConfigController)root.getRoot(BootstrapCSP.BOOTSTRAP_ROOT);
		assertNotNull(b2);
		
		/*
		RulesImpl rules=new RulesImpl();
		rules.addRule("ROOT",new String[]{"collection-space"},"org.collectionspace.app.cfg.main",null,null);
		rules.addRule("org.collectionspace.app.cfg.main",new String[]{"records"},"records",null,null);
		// RECORDS/record -> RECORD(@id)
		rules.addRule("records",new String[]{"record"},"record",null,new Target(){
			public Object populate(Object parent, ReadOnlySection milestone) {
				Record r=new Record();
				r.id=(String)milestone.getValue("/record/@id");
				r.type=(String)milestone.getValue("/record/type");
				records.add(r);
				return r;
			}
		});
		*/
		/* RECORD/field -> FIELD(type) */
		/*
		rules.addRule("record",new String[]{"field"},"field",new SectionGenerator() {
			public void step(Section milestone,Map<String, String> data) {
				milestone.addValue("field.type",milestone.getParent().getValue("/record/type"));
			}
		},new Target(){
			public Object populate(Object parent, ReadOnlySection milestone) {
				Field f=new Field();
				f.type=(String)milestone.getValue("field.type");
				((Record)parent).fields.add(f);
				return f;
			}
		});
			
		// MAIN/persistence/service -> URL(url)
		rules.addRule("main",new String[]{"persistence","service"},"url",new SectionGenerator() {
			public void step(Section milestone,Map<String, String> data) {
				milestone.addValue("service.url",data.get("/service/url"));
			}
		},null);
		*/
		
	}
}
