package com.scf.skyware.common.util;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class TripleDESCrypt
{

	private static String[] HEXSTR = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f", "10", "11", "12", "13",
			"14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2a", "2b",
			"2c", "2d", "2e", "2f", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b", "3c", "3d", "3e", "3f", "40", "41", "42", "43",
			"44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b",
			"5c", "5d", "5e", "5f", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6a", "6b", "6c", "6d", "6e", "6f", "70", "71", "72", "73",
			"74", "75", "76", "77", "78", "79", "7a", "7b", "7c", "7d", "7e", "7f", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8a", "8b",
			"8c", "8d", "8e", "8f", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e", "9f", "a0", "a1", "a2", "a3",
			"a4", "a5", "a6", "a7", "a8", "a9", "aa", "ab", "ac", "ad", "ae", "af", "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb",
			"bc", "bd", "be", "bf", "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ca", "cb", "cc", "cd", "ce", "cf", "d0", "d1", "d2", "d3",
			"d4", "d5", "d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df", "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea", "eb",
			"ec", "ed", "ee", "ef", "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff" };

	private SecretKey key;
	private IvParameterSpec param;

	public static String toHexString(byte[] data)
	{
		return toHexString(data, 0, data.length);
	}

	public static String toHexString(byte[] data, int start, int length)
	{
		StringBuffer sb = new StringBuffer(length * 2);
		for (int i = start, iend = start + length; i < iend; i++)
			sb.append(HEXSTR[(data[i] + 256) % 256]);
		return sb.toString();
	}

	public static byte[] fromHexString(String str)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		for (int i = 0, iend = str.length(); i < iend; i += 2)
			os.write(Integer.parseInt(str.substring(i, i + 2), 16));
		return os.toByteArray();
	}

	private byte[] getBytes(byte[] data, int length, byte defaultValue)
	{
		byte[] tmp = new byte[length];
		Arrays.fill(tmp, defaultValue);
		System.arraycopy(data, 0, tmp, 0, Math.min(length, data.length));
		return tmp;
	}

	public TripleDESCrypt(String str, String charset)
	{
		try
		{
			byte[] bytes = getBytes(str.getBytes(charset), 24, (byte) ' ');
			DESedeKeySpec spec = new DESedeKeySpec(bytes);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			key = factory.generateSecret(spec);
			param = new IvParameterSpec(bytes, 0, 8);
		}
		catch (Exception e)
		{
			e.printStackTrace(); // To change body of catch statement use
									// Options | File Templates.
		}
	}

	public byte[] encrypt(byte[] data)
	{
		try
		{
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, param);
			return cipher.doFinal(data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public byte[] decrypt(byte[] data)
	{
		try
		{
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, param);
			return cipher.doFinal(data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public String hexEncrypt(String str, String charset)
	{
		try
		{
			return toHexString(encrypt(str.getBytes(charset)));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public String hexDecrypt(String str, String charset)
	{
		try
		{
			return new String(decrypt(fromHexString(str)), charset);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/*
	 * public String base64Encrypt(String str, String charset) { try { return
	 * new BASE64Encoder().encode(encrypt(str.getBytes(charset))); } catch
	 * (Exception e) { return null; } }
	 * 
	 * public String base64Decrypt(String str, String charset) { try { return
	 * new String(decrypt(new BASE64Decoder().decodeBuffer(str)), charset); }
	 * catch (Exception e) { return null; } }
	 */

}
