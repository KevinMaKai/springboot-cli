package com.kevinmakai.springproject.cli;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * The only reason to have this is to be able to compatible with java 1.5 :(
 */
public abstract class SafeEncoder {

	public static final Charset UTF8 = Charset.forName("UTF-8");

	private static final byte[] ONE_BIT = new byte[] { '1' };

	private static final byte[] ZERO_BIT = new byte[] { '0' };

	public static byte[] extractRouteKey(String key, boolean useKeyTag) {
		if (key == null) {
			return null;
		}

		return extractRouteKey(encode(key), useKeyTag);
	}

	public static byte[] extractRouteKey(byte[] key, boolean useKeyTag) {
		if (key == null) {
			return null;
		}

		if (!useKeyTag) {
			return key;
		}

		int s = 0;
		int e = 0;
		for (s = 0; s < key.length; s++) {
			if (key[s] == '{')
				break;
		}

		if (s == key.length) {
			return key;
		}

		for (e = s + 1; e < key.length; e++) {
			if (key[e] == '}')
				break;
		}

		if (e == key.length || e == s + 1) {
			return key;
		}

		return Arrays.copyOfRange(key, s + 1, e);
	}

	public static byte[][] encodeMany(final String... strs) {
		byte[][] many = new byte[strs.length][];
		for (int i = 0; i < strs.length; i++) {
			many[i] = encode(strs[i]);
		}
		return many;
	}

	public static byte[] encode(final String str) {
		if (str == null) {
			return null;
		}
		return str.getBytes(UTF8);
	}

	public static byte[] encode(final int value) {
		return encode(String.valueOf(value));
	}

	public static byte[] encode(final long value) {
		return encode(String.valueOf(value));
	}

	public static byte[] encode(final double value) {
		return encode(String.valueOf(value));
	}

	public static byte[] encode(final boolean value) {
		return value ? ONE_BIT : ZERO_BIT;
	}

	public static String encode(final byte[] data) {
		return new String(data, UTF8);
	}

	public static ByteBuffer buffer(String s) {
		return ByteBuffer.wrap(s.getBytes(UTF8));
	}
}
