package me.mritd.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.junit.Test;

import me.mritd.utils.CertUtils;
/**
 * 
 * Copyright © 2016 Mritd. All rights reserved.
 * @ClassName: CreartCertTest
 * @Description: TODO 测试类
 * @author: 漠然
 * @date: 2016年3月31日 下午5:25:57
 */
public class CreartCertTest {
	
	@Test
	public void createTest(){
		CertUtils certUtils = new CertUtils();
		try {
			certUtils.createCert("218.107.129.186", "8080", null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
