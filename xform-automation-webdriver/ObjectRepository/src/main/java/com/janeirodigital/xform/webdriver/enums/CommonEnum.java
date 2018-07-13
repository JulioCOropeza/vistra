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
		X_FORM_USER_LIST_URL("https://xform-stage.janeirodigital.com/users/list"),
		FoundersDashBoardUrl ("https://svb-middleoffice-dev.janeirodigital.com/dashboard");
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
		X_FORM_TENANT_MANAGEMENT_TITLE("TENANT MANAGEMENT"),
        X_FORM_DEFAULT_ROLES_TITLE("DEFAULT ROLES"),
        X_FORM_DEFAULT_LOGO_STYLES_TITLE("DEFAULT LOGO & STYLES"),
        X_FORM_API_DOCUMENTS_TITLE("Interactive API Documentation (Swagger)"),
        X_FORM_ABOUT_TITLE("DEPENDENCIES VERSION");
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
		X_FORM_DASH_BOARD_MENU_SYS_ADM_TITLE("System Administration"),
		X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_ROLES("Default Roles"),
		X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_LOGO_STYLES("Default Logo & Styles"),
		X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_API_DOCUMENTS("API Documents"),
		X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_ABOUT("About");
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
    public enum XFormMenuTitlesIDs {
        X_FORM_DASH_BOARD_MENU_USR_MNGMT_ID(" .fa-user"),
        X_FORM_DASH_BOARD_MENU_TENANT_ID(" .fa-key"),
        X_FORM_DASH_BOARD_MENU_SYS_ADM_ID(" .fa-cog"),
        X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_ROLES_ID("[href='/system-administration/roles']"),
        X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_DEFAULT_LOGO_STYLES_ID("[href='/system-administration/styles']"),
        X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_API_DOCUMENTS_ID("[href='/system-administration/docs']"),
        X_FORM_DASH_BOARD_MENU_SA_SUB_MENU_ABOUT_ID("[href='/system-administration/about']");
        private final String name;
        private XFormMenuTitlesIDs(String s) {
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
		X_FORM_DP_USER_MANAGEMENT("2"),//number of excel sheet
		X_FORM_DP_TENANTS_MANAGEMENT("3"),
		X_FORM_DP_SYSTEM_ADMINISTRATION_MANAGEMENT("4"),
		X_FORM_DP_REDIS_HISTORIES_INJECTION("5");
		private final String sheetNumber;
		private XFormDataProviders(String s) {
			sheetNumber = s;
		}
		public boolean equalsName(String otherName) {
			return sheetNumber.equals(otherName);
		}
		public String toString() {
			return this.sheetNumber;
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

	public enum randomRange {
		MAX_RANGE(100),
		MIN_RANGE(1);
		private final int value;
		randomRange(final int newValue) { value = newValue; }
		public int getValue() { return value; }
	}
}
