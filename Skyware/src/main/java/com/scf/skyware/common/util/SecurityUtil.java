package com.scf.skyware.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>SecurityUtil</code>
 *
 * @author
 * @since 2015. 12. 23.
 * @version 1.0
 */
public class SecurityUtil
{
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	public static Map<String, String> escapeMap = new HashMap<String, String>();
	static
	{
		escapeMap.put("&apos;", "'");
		escapeMap.put("&amp;", "&");
		escapeMap.put("&quot;", "\"");
		escapeMap.put("&lt;", "<");
		escapeMap.put("&gt;", ">");
		escapeMap.put("&nbsp;", " ");
	}

	public static String bytesToHex(byte[] b)
	{
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++)
		{
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}

	/**
	 * SHA256 hash泥섎━
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String getSHA256(String value) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hashedValue = value.getBytes();
		md.update(hashedValue);
		return bytesToHex(md.digest());
	}

	/**
	 * 3DES �븫�샇�솕
	 *
	 * String encrytText = SecurityUtil.get3DESEncrypt("LimeTripleDES","�룊臾�
	 * �궗踰�" , "euc-kr");
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String get3DESEncrypt(String key, String value, String charSet) throws Exception
	{
		TripleDESCrypt tdc = new TripleDESCrypt(key, charSet);// userId �븫�샇�솕
																// 泥섎━
		return tdc.hexEncrypt(value, charSet);
	}

	/**
	 * 3DES 蹂듯샇�솕
	 *
	 * String encrytText = SecurityUtil.get3DESDecrypt("LimeTripleDES","�븫�샇�븯�맂
	 * �궗踰�" , "euc-kr");
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String get3DESDecrypt(String key, String value, String charSet) throws Exception
	{
		TripleDESCrypt tdc = new TripleDESCrypt(key, charSet);
		return tdc.hexDecrypt(value, charSet);
	}

	/**
	 * URL �씤肄붾뵫
	 * 
	 * @param plainText
	 * @param charsetName
	 * @return
	 * @throws Exception
	 */
	public static String encodeUrl(String plainText, String charsetName) throws Exception
	{
		return URLEncoder.encode(plainText, charsetName);
	}

	/**
	 * URL �뵒肄붾뵫
	 * 
	 * @param encodeText
	 * @param charsetName
	 * @return
	 * @throws Exception
	 */
	public static String decodeUrl(String encodeText, String charsetName) throws Exception
	{
		return URLDecoder.decode(encodeText, charsetName);
	}

	/**
	 * BASE64濡� �씤肄붾뵫
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encodeBase64(String plainText)
	{
		if (plainText != null)
			return new String(Base64.encodeBase64(plainText.getBytes()));
		else
			return null;
	}

	/**
	 * BASE64 �뵒肄붾뵫
	 * 
	 * @param encodedText
	 * @return
	 */
	public static String decodeBase64(String encodedText)
	{
		if (encodedText != null)
			return new String(Base64.decodeBase64(encodedText.getBytes()));
		else
			return null;
	}

	/**
	 * SSO �뿰�룞 key�뿉 �뵲瑜� �쑀�슚�꽦 Check
	 *
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidSHA256(String key, String value) throws Exception
	{
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar cal = Calendar.getInstance();
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for (int i = 0; i < 3; i++)
		{
			cal.setTime(date);
			cal.add(Calendar.MINUTE, i - 1);
			String dataString = sf.format(cal.getTime());
			String hashStringValue = dataString + value;
			byte[] valueHash = hashStringValue.getBytes();
			md.update(valueHash);
			String ssoValue = bytesToHex(md.digest());
			if (key != null && key.equals(ssoValue))
				return true;
		}
		return false;
	}

	/**
	 * SSO�떆, 3DES瑜� �씠�슜�븳 蹂듯샇�솕
	 * 
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	public static String[] get3DES_dongkuk_DecrytIdPw(String credential) throws Exception
	{
		return SecurityUtil.get3DESDecrypt("dongkuk iris 2006", credential, "euc-kr").split(":");
	}

	/**
	 * SSO�떆, 3DES瑜� �씠�슜�븳 �븫�샇�솕
	 * 
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public static String get3DES_dongkuk_EncrytIdPw(String id, String pw) throws Exception
	{
		return SecurityUtil.get3DESEncrypt("dongkuk iris 2006", id + ":" + pw, "euc-kr");
	}

	/**
	 * SSO�떆, 3DES瑜� �씠�슜�븳 蹂듯샇�솕
	 * 
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	public static String get3DES_unionsteel_DecrytPw(String pw) throws Exception
	{
		TripleDESCrypt nets = new TripleDESCrypt("unionsteel ep 2012", "euc-kr");
		return nets.hexDecrypt(pw, "euc-kr");
	}

	/**
	 * SSO�떆, 3DES瑜� �씠�슜�븳 �븫�샇�솕
	 * 
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public static String get3DES_unionsteel_EncrytPw(String pw) throws Exception
	{
		return SecurityUtil.get3DESEncrypt("unionsteel ep 2012", pw, "euc-kr");
	}

	public static String get3DES_ssopublickey_DecrytPw(String pw) throws Exception
	{
		return SecurityUtil.get3DESDecrypt("ssopublickey", pw, "euc-kr");
	}

	public static String get3DES_ssopublickey_EncrytPw(String pw) throws Exception
	{
		return SecurityUtil.get3DESEncrypt("ssopublickey", pw, "euc-kr");
	}

	/**
	 * SSO�떆, Base64瑜� �씠�슜�븳 蹂듯샇�솕 �삁) 硫붿떊���뿉�꽌 �겢由��떆
	 * 
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	public static String[] getBase64DecrytIdPw(String credential) throws Exception
	{
		return SecurityUtil.decodeBase64(credential).split(":");
	}

	/**
	 * SSO�떆, Base64瑜� �씠�슜�븳 �븫�샇�솕
	 * 
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public static String getBase64EncrytIdPw(String id, String pw) throws Exception
	{
		return SecurityUtil.encodeBase64(id + ":" + pw);
	}

	public static String unescape(String str)
	{
		return StringEscapeUtils.unescapeXml(str).replaceAll("&nbsp;", " ");
	}

	public static String escape(String str)
	{
		return StringEscapeUtils.escapeXml(str);
	}

	public void unEscapeTest() throws Exception
	{
		String oriStr = "`1234567890-=&nbsp;[]\\;&apos;,./~!@#$%^&amp;*()_+{}|:&quot;&lt;&gt;?&nbsp;&nbsp;";
	}

	public void apiKeyEncodingTest() throws Exception
	{
		String apiKey = "06c84924da0902239b6e741e1fa14e42";
		String apiDesc = "20160406_UNIDOS_�쑕媛�寃곗옱�떆 ���씪�젙�뿉 �궫�엯�븯湲�";
		String nirisKey = "f6fb35756d1c0f230a0298778b8d0046";// g.generateUUID();
	}

	public void sabunTest() throws Exception
	{
		// fcd7b9ea05df67558f8ba4fdf158a2c1
		System.out.println("蹂듯샇�솕    : " + get3DES_unionsteel_DecrytPw("fcd7b9ea05df67558f8ba4fdf158a2c1"));
		// dGFleW9uZzEua2ltOmd1c2VvMTQzMw==
		// http://niris.dongkuk.com:8080/sso.action?ssoCredential=dGFleW9uZzEua2ltOmd1c2VvMTQzMw==
		// /ssomain/ssologincheck.asp?credential=dGFleW9uZzEua2ltOmd1c2VvMTQzMw==&reqURL=http://iris.dongkuk.com
		//
		// --->
		//
		// http://niris.dongkuk.com/sso.action?ssoCredential=dGFleW9uZzEua2ltOmd1c2VvMTQzMw==&reqURL=http://portal.dongkuk.com
		// /ssomain/ssologincheck.asp?credential=dGFleW9uZzEua2ltOmd1c2VvMTQzMw==&reqURL=http://iris.dongkuk.com
		// /ssomain/setcredential.asp?reqURL=http%3A%2F%2Firis%2Edongkuk%2Ecom&logonPwd=%67%75%73%65%6f%31%34%33%33
		// /service/outLogin.action?credential=291775990610be4f4eff0c096f78daff4114237fc0f555e3
		/** BASE64 �씤肄붾뵫 �뀒�뒪�듃 */
		String genCre = "bWluamVvbmcubGVlOjAyMzFhbHN3amR+";
		String id = "minjeong.lee"; // http://portal.dongkuk.com/ssomain/ssologincheck.asp?credential=bWluamVvbmcubGVlOjAyMzFhbHN3amQ=&reqURL=http://portal.dongkuk.com/dk/portal/subsystem.aspx?subtype=umail
		String pw = "0231alswjd~";
		// String genCre = "dGFleW9uZzEua2ltOmd1c2VvMTQzMw==";
		// String id = "taeyong1.kim"; //
		// http://portal.dongkuk.com/ssomain/ssologincheck.asp?credential=bWluamVvbmcubGVlOjAyMzFhbHN3amQ=&reqURL=http://portal.dongkuk.com/dk/portal/subsystem.aspx?subtype=umail
		// String pw = "guseo1433";
		String encodeCre = encodeBase64(id + ":" + pw);
		System.out.println("�썝蹂�    : " + genCre);
		System.out.println("encodeCre : " + encodeCre);
		System.out.println("decodeCre : " + decodeBase64(encodeCre));
		// System.out.println("�썝蹂� : "+id+":"+pw);
		System.out.println("URL ENCODING encodeCre : " + encodeUrl(encodeCre, "UTF-8"));
		System.out.println("URL ENCODING decodeCre : " + decodeUrl(encodeUrl(encodeCre, "UTF-8"), "UTF-8"));
		System.out.println("理쒖쥌 �썝蹂�                 : " + decodeBase64(decodeUrl(encodeUrl(encodeCre, "UTF-8"), "UTF-8")));
		/** �궗踰� 3DES �븫蹂듯샇�솕 */
		// String plainText = "41110094"; // 源��깭�슜 �궗踰�
		// String encryptText = "5225ab3ae70416055d885c267fb72702";// �븫�샇�솕�맂
		// 源��깭�슜 �궗踰�
		// System.out.println("plainText : "+plainText+" --> encryptText : "
		// +SecurityUtil.get3DESEncrypt("dongkuk iris
		// 2006",plainText,"euc-kr"));
		// System.out.println("encryptText :
		// "+SecurityUtil.get3DESDecrypt("dongkuk iris
		// 2006",encryptText,"euc-kr")+" --> decryptText :
		// "+SecurityUtil.get3DESEncrypt("dongkuk iris
		// 2006",encryptText,"euc-kr"));
		// System.out.println("sabun :"+SecurityUtil.get3DESDecrypt("dongkuk
		// iris 2006",plainText,"euc-kr") );
		// System.out.println("decrysabun :
		// "+SecurityUtil.get3DESEncrypt("dongkuk iris 2006",plainText,"euc-kr")
		// );
	}

	public static void main(String[] args) throws Exception
	{
		new SecurityUtil().apiKeyEncodingTest();
	}
}
