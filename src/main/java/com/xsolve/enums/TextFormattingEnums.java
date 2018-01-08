package com.xsolve.enums;

import java.util.List;

public class TextFormattingEnums {

    public enum FontSizes {
        TINY(50),
        SMALL(85),
        NORMAL(100),
        LARGE(150),
        HUGE(200);

        private int value;

        FontSizes (int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum MessageAssertTypes {
        SIMPLE("simple"),
        SMILEY_IMG("smiley");

        private String value;

        MessageAssertTypes (String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static class SimpleTextFormatting {
        public static final String[] FULL_LIST = {"url", "img", "bold", "italic", "underline", "quote", "code", "unorderedList", "orderedList", "asterix"};
        public static final String URL = "url";
        public static final String IMAGE = "img";
        public static final String BOLD = "bold";
        public static final String ITALIC = "italic";
        public static final String UNDERLINE = "underline";
        public static final String QUOTE = "quote";
        public static final String CODE = "code";
        public static final String UNORDERED_LIST = "unorderedList";
        public static final String ORDERED_LIST = "orderedList";
        public static final String ASTERIX = "asterix";
    }
}
