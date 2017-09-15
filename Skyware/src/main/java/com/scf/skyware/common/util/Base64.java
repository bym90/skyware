package com.scf.skyware.common.util;

import java.io.*;

public class Base64
{
	/**
	 * BASE64 Encoder
	 * 
	 * @param str
	 * @return
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("restriction")
	public static String base64Encode(String str)
	{
		String result = "";
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		byte[] b1 = str.getBytes();
		result = encoder.encode(b1);
		return result;
	}

	/**
	 * BASE64 Decoder
	 * 
	 * @param str
	 * @return
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("restriction")
	public static String base64Decode(String str)
	{
		String result = "";
		try
		{
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			byte[] b1 = decoder.decodeBuffer(str);
			result = new String(b1);
		}
		catch (IOException ex)
		{
		}
		return result;
	}
}
