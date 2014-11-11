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
	public synchronized void init() throws UnsupportedEncodingException {
		this.close();
		ServerConfiguration configuration = Db4oClientServer
				.newServerConfiguration();
		configuration.file().blockSize(80);
		String dbfolder = env.getProperty("dbpath_folder") + File.separator;

		String dbpath = dbfolder + env.getProperty("dbname");
		String classPath = URLDecoder.decode(this.getClass()
				.getProtectionDomain().getCodeSource().getLocation().getPath(),
				"UTF-8");
		String dbFullPath = classPath
				.substring(0, classPath.indexOf("classes"))
				+ "classes"
				+ File.separator + dbpath;

		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().messageLevel(1);
		config.common().objectClass(ZipCode.class).objectField("zipCode")
				.indexed(true);
		config.common().objectClass(ZipCode.class).cascadeOnActivate(true);
		config.common().objectClass(ZipCode.class).cascadeOnUpdate(true);

		try {
			server = Db4oClientServer.openServer(configuration, dbFullPath, 0);
			client = server.openClient();
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
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
}
