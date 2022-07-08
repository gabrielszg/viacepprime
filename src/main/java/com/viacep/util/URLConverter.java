package com.viacep.util;

import java.net.IDN;
import java.net.URI;
import java.net.URL;

public class URLConverter {

	public static URL urlConverter(URL url) throws Exception {
		URI uri = new URI(url.getProtocol(),
				url.getUserInfo(),
				IDN.toASCII(url.getHost()),
				url.getPort(),
				url.getPath(),
				url.getQuery(), url.getRef());
		
		String urlStr = uri.toASCIIString();
		
		URL urlFinal = new URL(urlStr);
		url = urlFinal;
		return url;
	}
	
}
