package me.pc.mobile.helper.v14.crypt;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密工具类，使用7padding方式加密，该加密方式用于标准支付插件前置和标准支付插件加解密，以避免发生双方无法解密。
 * 使用该类需要完成以下两步操作: 1、在工程中引入2个jar包：bcprov-jdk16-139.jar\local_policy.jar，包依赖如下所示
 * <!-- 为了支持aes的7padding方式加密依赖的包 --> <dependency> <groupId>com.encrypt</groupId>
 * <artifactId>bcprov-jdk16-139</artifactId> <version>1.0.0</version>
 * </dependency> <dependency> <groupId>com.encrypt</groupId>
 * <artifactId>local_policy</artifactId> <version>1.0.0</version> </dependency>
 * 2、配置Java.security
 * 
 * 用记事本打开%JDK_Home%\ jre\lib\security\java.security文件，找到如下9行代码：
 * 
 * security.provider.1=sun.security.provider.Sun
 * 
 * security.provider.2=sun.security.rsa.SunRsaSign
 * 
 * security.provider.3=com.sun.net.ssl.internal.ssl.Provider
 * 
 * security.provider.4=com.sun.crypto.provider.SunJCE
 * 
 * security.provider.5=sun.security.jgss.SunProvider
 * 
 * security.provider.6=com.sun.security.sasl.Provider
 * 
 * security.provider.7=org.jcp.xml.dsig.internal.dom.XMLDSigRI
 * 
 * security.provider.8=sun.security.smartcardio.SunPCSC
 * 
 * security.provider.9=sun.security.mscapi.SunMSCAPI
 * 
 * 在这9行之后添加如下两行代码：
 * 
 * #增加BouncyCastleProvider
 * 
 * security.provider.10=org.bouncycastle.jce.provider.BouncyCastleProvider
 * 
 * 保存Java.security文件。
 * 
 * 同样修改%JRE_Home%\lib\security\java.security文件，加入以上两行，保存文件。
 * 
 * @author：JudyHuang
 * @since：2013-3-28 下午08:16:22
 * @version:
 */
public class AES7Padding {
	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		CheckUtils.notEmpty(data, "data");
		CheckUtils.notEmpty(key, "key");
		if (key.length != 16) {
			throw new RuntimeException(
					"Invalid AES key length (must be 16 bytes)");
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(
					ConfigureEncryptAndDecrypt.CIPHER_ALGORITHM, "BC");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, seckey);// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e) {
			throw new RuntimeException("encrypt fail!", e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		CheckUtils.notEmpty(data, "data");
		CheckUtils.notEmpty(key, "key");
		if (key.length != 16) {
			throw new RuntimeException(
					"Invalid AES key length (must be 16 bytes)");
		}
		try {
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(
					ConfigureEncryptAndDecrypt.CIPHER_ALGORITHM, "BC");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, seckey);// 初始化
			byte[] result = cipher.doFinal(data);
			return result; // 加密
		} catch (Exception e) {
			throw new RuntimeException("decrypt fail!", e);
		}
	}

	public static String encryptToBase64(String data, String key) {
		try {
			byte[] valueByte = encrypt(
					data.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING),
					key.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING));
			return new String(Base64.encode(valueByte));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("encrypt fail!", e);
		}

	}

	public static String decryptFromBase64(String data, String key) {
		try {
			byte[] originalData = Base64.decode(data.getBytes());
			byte[] valueByte = decrypt(originalData,
					key.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING));
			return new String(valueByte,
					ConfigureEncryptAndDecrypt.CHAR_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("decrypt fail!", e);
		}
	}

	public static String encryptWithKeyBase64(String data, String key) {
		try {
			byte[] valueByte = encrypt(
					data.getBytes(ConfigureEncryptAndDecrypt.CHAR_ENCODING),
					Base64.decode(key.getBytes()));
			return new String(Base64.encode(valueByte));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("encrypt fail!", e);
		}
	}

	public static String decryptWithKeyBase64(String data, String key) {
		try {
			byte[] originalData = Base64.decode(data.getBytes());
			byte[] valueByte = decrypt(originalData,
					Base64.decode(key.getBytes()));
			return new String(valueByte,
					ConfigureEncryptAndDecrypt.CHAR_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("decrypt fail!", e);
		}
	}

	public static byte[] genarateRandomKey() {
		KeyGenerator keygen = null;
		try {
			keygen = KeyGenerator
					.getInstance(ConfigureEncryptAndDecrypt.AES_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(" genarateRandomKey fail!", e);
		}
		SecureRandom random = new SecureRandom();
		keygen.init(random);
		Key key = keygen.generateKey();
		return key.getEncoded();
	}

	public static String genarateRandomKeyWithBase64() {
		return new String(Base64.encode(genarateRandomKey()));
	}

}
