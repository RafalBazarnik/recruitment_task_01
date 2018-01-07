package com.xsolve.enums;

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
}
