package com.janeirodigital.xform.webdriver.enums;

public enum XmlEnum {
	URL("url"),
	SCREEN_SHOOTS("screen_shoots"),
	GOOGLE_EXE("google_exe"),
	GOOGLE_EXE_LINUX("google_exe_linux"),
	GOOGLE_EXE_MAC("google_exe_mac"),
	GOOGLE_BINARY("google_binary"),
	GOOGLE_BINARY_MAC("google_binary_mac"),
	FIRE_FOX_BINARY("fire_fox_binary"),
	FIRE_FOX_BINARY_MAC("fire_fox_binary_mac"),
	IE_BINARY("ie_binary"),
	PHANTOM_JS("phantom_js"),
	PHANTOM_JS_LINUX_64("phantom_js_linux_64"),
	CHROME_LINUX_32("chrome_linux_32"),
	CHROME_LINUX_64("chrome_linux_64"),
	CHROME_EXTENSION_MOD_HEAD("chrome_extension_mod_head"),
	CHROME_LOAD_EXTENSION_MOD_HEAD("chrome_load_extension_mod_head"),
	PARAMETER_FILE("parameter_file"),
	DOWNLOAD_CHROME_WIN32_WD("download_chrome_win32_wd"),
	DOWNLOAD_CHROME_WIN32_WD_LOCAL_PATH("download_chrome_win32_wd_local_path"),
	DOWNLOAD_CHROME_LINUX_32_WD("download_chrome_linux_32_wd"),
	DOWNLOAD_CHROME_LINUX_32_WD_LOCAL_PATH("download_chrome_linux_32_wd_local_path"),
	DOWNLOAD_CHROME_LINUX_64_WD("download_chrome_linux_64_wd"),
	DOWNLOAD_CHROME_LINUX_64_WD_LOCAL_PATH("download_chrome_linux_64_wd_local_path"),
	DOWNLOAD_CHROME_MAC_64_WD("download_chrome_mac_64_wd"),
	DOWNLOAD_CHROME_MAC_64_WD_LOCAL_PATH("download_chrome_mac_64_wd_local_path"),
	DOWNLOAD_FIRE_FOX_WD("download_fire_fox_wd"),
	DOWNLOAD_FIRE_FOX_WD_LOCAL_PATH("download_fire_fox_wd_local_path"),
	DOWNLOAD_FIRE_FOX_MAC_WD("download_fire_fox_mac_wd"),
	DOWNLOAD_FIRE_FOX_MAC_WD_LOCAL_PATH("download_fire_fox_mac_wd_local_path"),
	DOWNLOAD_IE__WD("download_ie_wd"),
	DOWNLOAD_IE__WD_LOCAL_PATH("download_ie_wd_local_path"),
	DOWNLOAD_PHANTOM_JS_WIN_WD("download_phantom_js_win_wd"),
	DOWNLOAD_PHANTOM_JS_WIN_WD_LOCAL_PATH("download_phantom_js_win_wd_local_path"),
	DOWNLOAD_PHANTOM_JS_LINUX_WD("download_phantom_js_linux_wd"),
	DOWNLOAD_PHANTOM_JS_LINUX_WD_LOCAL_PATH("download_phantom_js_linux_wd_local_path"),
	DOWNLOAD_UNZIP_WD_LOCAL_PATH("download_unzip_wd_local_path"),
	CHROME_LINUX_ORIGINAL_BINARY_NAME("chrome_linux_original_binary_name"),
	CHROME_WIN_BINARY_NAME("chrome_win_binary_name"),
	CHROME_LINUX_64_BINARY_NAME("chrome_linux_64_binary_name"),
	CHROME_LINUX_32_BINARY_NAME("chrome_linux_32_binary_name"),
	FIRE_FOX_BINARY_NAME("fire_fox_binary_name"),
	FIRE_FOX_MAC_BINARY_NAME("fire_fox_mac_binary_name"),
	CHROME_MAC_BINARY_NAME("chrome_mac_binary_name"),
	IE_BINARY_NAME("ie_binary_name"),
	PHANTOM_JS_BINARY_NAME("phantom_js_binary_name"),
	PHANTOM_JS_LINUX_64_BINARY_NAME("phantom_js_linux_64_binary_name"),
	DOWNLOAD_UNZIP_WD_FORCED("download_unzip_wd_forced");

	private final String tagName;

	XmlEnum(String tagName){
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}
}
