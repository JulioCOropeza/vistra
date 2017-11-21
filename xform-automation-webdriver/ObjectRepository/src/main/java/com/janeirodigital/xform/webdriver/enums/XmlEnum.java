package com.janeirodigital.xform.webdriver.enums;

public enum XmlEnum {
	URL("url"),
	SCREEN_SHOOTS("screen_shoots"),
	GOOGLE_EXE("google_exe"),
	GOOGLE_EXE_LINUX("google_exe_linux"),
	GOOGLE_BINARY("google_binary"),
	FIRE_FOX_BINARY("fire_fox_binary"),
	IE_BINARY("ie_binary"),
	PHANTOM_JS("phantom_js"),
	PHANTOM_JS_LINUX_64("phantom_js_linux_64"),
	CHROME_LINUX_32("chrome_linux_32"),
	CHROME_LINUX_64("chrome_linux_64"),
	CHROME_EXTENSION_MOD_HEAD("chrome_extension_mod_head"),
	CHROME_LOAD_EXTENSION_MOD_HEAD("chrome_load_extension_mod_head"),
	PARAMETER_FILE("parameter_file");

	private final String tagName;

	XmlEnum(String tagName){
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}
}
