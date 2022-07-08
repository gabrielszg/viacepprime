package com.viacep.util;

import java.io.BufferedReader;
import java.io.IOException;

public class JSONConverter {

	public static StringBuffer convertToJson(BufferedReader br) {
		StringBuffer json = new StringBuffer();
		String line;

		try {
			while ((line = br.readLine()) != null) {
				json.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
