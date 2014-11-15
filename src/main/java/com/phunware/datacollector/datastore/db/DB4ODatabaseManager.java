package com.phunware.datacollector.datastore.db;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ServerConfiguration;
import com.db4o.ext.DatabaseFileLockedException;
import com.phunware.datacollector.domain.Weather;
import com.phunware.datacollector.domain.ZipCode;

@Component
@PropertySource(value = "classpath:/env.properties")
public class DB4ODatabaseManager {

	@Autowired
	private Environment env;

	private ObjectContainer client;

	private ObjectServer server;

	private boolean encrypt = true;

	private static final Logger logger = LoggerFactory
			.getLogger(DB4ODatabaseManager.class);

	@PostConstruct
	public synchronized void init() {
		logger.info("Starting the DB4O server and client connection");
		this.close();
		ServerConfiguration configuration = Db4oClientServer
				.newServerConfiguration();
		configuration.file().blockSize(80);
		String dbFolderName = env.getProperty("dbpath_folder");

		String dbFileName =  env.getProperty("dbname");
		StringBuffer dbAbsolutePath = new StringBuffer(); 
		try {
			String classPath = URLDecoder.decode(this.getClass()
					.getProtectionDomain().getCodeSource().getLocation().getPath(),
					"UTF-8");
			
			dbAbsolutePath.append(classPath.substring(0, classPath.indexOf("classes")));
			dbAbsolutePath.append("classes");
			dbAbsolutePath.append(File.separator);
			dbAbsolutePath.append(dbFolderName);
			dbAbsolutePath.append(File.separator);
			dbAbsolutePath.append(dbFileName);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error while URL encoding to find class path",e);
		} catch (RuntimeException e){
			logger.error("Unknown exception occured",e);
		}

		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().messageLevel(1);
		config.common().objectClass(ZipCode.class).objectField("zipCode")
				.indexed(true);
		config.common().objectClass(ZipCode.class).cascadeOnActivate(true);
		config.common().objectClass(ZipCode.class).cascadeOnUpdate(true);
		config.common().objectClass(Weather.class).objectField("zipCode")
		.indexed(true);
		config.common().objectClass(Weather.class).cascadeOnActivate(true);
		config.common().objectClass(Weather.class).cascadeOnUpdate(true);

		try {
			server = Db4oClientServer.openServer(configuration, dbAbsolutePath.toString(), 0);
			client = server.openClient();
			logger.info("Started the DB4O server");
		} catch (DatabaseFileLockedException e) {
			logger.error("DB file locked : ",e);
		} catch (RuntimeException e){
			logger.error("Unknown exception occured",e);
		}
	}

	public ObjectContainer getClient() {
		return client;
	}

	private synchronized void close() {
		if (client != null) {
			client.close();
			client = null;
		}
		if (server != null) {
			server.close();
		}
		server = null;
	}

	@PreDestroy
	public void shutdown() {
		close();
	}

	public boolean isEncrypt() {
		return encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}
	
	public synchronized void updateObject(Object object) {
		client.store(object);
		client.commit();
	}
	
}
