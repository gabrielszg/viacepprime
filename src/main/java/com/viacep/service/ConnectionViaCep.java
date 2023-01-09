package com.viacep.service;

import java.net.URL;

public interface ConnectionViaCep {
	
	URL zipCodeConnection();
	URL connectionAddress();
	StringBuffer readerZipCode();
	StringBuffer readerAddress();
}
