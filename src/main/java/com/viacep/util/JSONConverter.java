package com.viacep.util;

import java.io.BufferedReader;
import java.io.IOException;

public class JSONConverter {

	public static StringBuffer convertToJson(BufferedReader reader) {
		StringBuffer json = new StringBuffer();
		String line;

		try {
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
