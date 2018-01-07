package com.xsolve.enums;

public class CommonEnums {

    public enum Attributes {
        ALT("alt"),
        HREF("href"),
        NAME("name"),
        TITLE("title"),
        CLASS("class"),
        ID("id");

        private String value;

        Attributes (String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
