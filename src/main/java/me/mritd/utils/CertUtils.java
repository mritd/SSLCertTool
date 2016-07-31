package me.mritd.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import me.mritd.swing.SSLCertFrame;

/**
 * 
 * Copyright © 2016 Mritd. All rights reserved.
 * @ClassName: CertUtils
 * @Description: TODO 证书生成工具类
 * @author: 漠然
 * @date: 2016年3月31日 下午2:27:21
 */
public class CertUtils {
	
	Logger logger = Logger.getLogger(CertUtils.class);
	
	// 16进制数组
	private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

	/**
	 * 
	 * @Title createCert
	 * @Description TODO 创建证书文件
	 * @throws  
	 * @return boolean
	 * @author 漠然
	 * @throws KeyStoreException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @date 2016年3月31日 下午2:31:51
	 */
	public boolean createCert(String host,String targetPort,String passwd) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException{
		
		// 生成文件
		File jssecacerts = new File(SSLCertFrame.CERT_PATH); 
		
		// URL 地址
		if (StringUtils.isBlank(host)) {
			logger.error("XXXXXX URL 地址为!");
		}
		
		// 端口
		int port;
		if (StringUtils.isBlank(targetPort)) {
			port = 443;
		}else{
			port= Integer.parseInt(targetPort);
		}
		
		// 证书密码
		char[] passphrase = null ;
		
		if (StringUtils.isNotBlank(passwd)) {
			passphrase = passwd.toCharArray();
		}else{
			passphrase = "changeit".toCharArray();
		}
		

		File file = new File("jssecacerts");
		if (file.isFile() == false) {
			char SEP = File.separatorChar;
			File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
			file = new File(dir, "jssecacerts");
			if (file.isFile() == false) {
				file = new File(dir, "cacerts");
			}
		}
		logger.info("Loading KeyStore " + file + "...");
		InputStream in = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(in, passphrase);
		in.close();

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory factory = context.getSocketFactory();

		logger.info("Opening connection to " + host + ":" + port + "...");
		SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
		socket.setSoTimeout(10000);
		try {
			logger.info("Starting SSL handshake...");
			socket.startHandshake();
			socket.close();
			logger.info("No errors, certificate is already trusted");
		} catch (SSLException e) {
			logger.error("XXXXXX 当前地址SSL证书不受信任!");
		}

		X509Certificate[] chain = tm.chain;
		if (chain == null) {
			logger.info("Could not obtain server certificate chain");
		}

		logger.info("Server sent " + chain.length + " certificate(s):");
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		for (int i = 0; i < chain.length; i++) {
			X509Certificate cert = chain[i];
			logger.info(" " + (i + 1) + " Subject " + cert.getSubjectDN());
			logger.info("   Issuer  " + cert.getIssuerDN());
			sha1.update(cert.getEncoded());
			logger.info("   sha1    " + toHexString(sha1.digest()));
			md5.update(cert.getEncoded());
			logger.info("   md5     " + toHexString(md5.digest()));
			logger.info("\n");
		}

		int k=0;

		X509Certificate cert = chain[k];
		String alias = host + "-" + (k + 1);
		ks.setCertificateEntry(alias, cert);

		OutputStream out = new FileOutputStream(jssecacerts);
		ks.store(out, passphrase);
		out.close();

		logger.info("\n");
		logger.info(cert);
		logger.info("\n");
		logger.info("Added certificate to keystore 'jssecacerts' using alias '" + alias + "'");
		
		
		
		
		return false;
	}
	
	/**
	 * 
	 * @Title toHexString
	 * @Description TODO 二进制转16进制
	 * @throws  
	 * @return String
	 * @author 漠然
	 * @date 2016年3月31日 下午2:37:06
	 */
	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HEXDIGITS[b >> 4]);
			sb.append(HEXDIGITS[b & 15]);
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 * 
	 * Copyright © 2016 Mritd. All rights reserved.
	 * @ClassName: SavingTrustManager
	 * @Description: TODO X509TrustManager 实现
	 * @author: 漠然
	 * @date: 2016年3月31日 下午2:37:36
	 */
	private static class SavingTrustManager implements X509TrustManager {

		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}

	
}
