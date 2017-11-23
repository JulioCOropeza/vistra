package com.janeirodigital.xform.webdriver.enums;

public class CommonEnum {

	public enum PageTitles {
		X_FORM_DASH_BOARD("XFORM | Janeiro Digital");

		private final String name;
		private PageTitles(String s) {
			name = s;
		}
		public boolean equalsName(String otherName) {
			return name.equals(otherName);
		}
		public String toString() {
			return this.name;
		}
	}

	public enum PagesURLs {
		X_FORM_DASH_BOARD_URL("https://xform-stage.janeirodigital.com/dashboard"),
		FoundersDashBoardUrl ("https://svb-founders-dev.janeirodigital.com/dashboard");
		private final String name;
		private PagesURLs(String s) {
			name = s;
		}
		public boolean equalsName(String otherName) {
			return name.equals(otherName);
		}
		public String toString() {
			return this.name;
		}
	}
	public enum BodyTitles {
		X_FORM_DASH_BOARD_TITLE("DASHBOARD"),
		X_FORM_USER_MANAGEMENT_TITLE("USER MANAGEMENT"),
		X_FORM_TENANT_MANAGEMENT_TITLE("TENANT MANAGEMENT");
		private final String name;
		private BodyTitles(String s) {
			name = s;
		}
		public boolean equalsName(String otherName) {
			return name.equals(otherName);
		}
		public String toString() {
			return this.name;
		}
	}

	public enum XFormMenuTitles {
		X_FORM_DASH_BOARD_MENU_USR_MNGMT_TITLE("User Management"),
		X_FORM_DASH_BOARD_MENU_TENANT_TITLE("Tenants"),
		X_FORM_DASH_BOARD_MENU_SYS_ADM_TITLE("System Administration");
		private final String name;
		private XFormMenuTitles(String s) {
			name = s;
		}
		public boolean equalsName(String otherName) {
			return name.equals(otherName);
		}
		public String toString() {
			return this.name;
		}
	}
	public enum XFormDataProviders {
		X_FORM_DP_USER_MANAGEMENT("UserManagement"),
		X_FORM_DP_TENANTS_MANAGEMENT("TenantsManagement"),
		X_FORM_DP_SYSTEM_ADMINISTRATION_MANAGEMENT("SystemAdministrationManagement");
		private final String name;
		private XFormDataProviders(String s) {
			name = s;
		}
		public boolean equalsName(String otherName) {
			return name.equals(otherName);
		}
		public String toString() {
			return this.name;
		}
	}

	public enum PageLoadingTimes {
		SHORT_WAIT_TIME (10),
		MEDIUM_WAIT_TIME (30),
		LONG_WAIT_TIME (60);
		private final int value;
		PageLoadingTimes(int newValue) {
			value = newValue;
		}
		public int getValue() { return value; }
	}
}
