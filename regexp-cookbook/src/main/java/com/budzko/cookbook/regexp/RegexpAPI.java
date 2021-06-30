package com.budzko.cookbook.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpAPI {
    public static void main(String[] args) {
        regexpGroups();
    }

    private static void regexpGroups() {
        Pattern p = Pattern.compile("A(B|C|D)(X|Y)");
        Matcher m = p.matcher("NACY KNADXN OPACXP\\nADY");

        String s = "   ";
        Pattern compile = Pattern.compile("^(?!\\s*$).+");
        System.out.println("XX" + compile.matcher(s).matches());

        int i = 0;
        while (m.find()) {
            StringBuilder builder = new StringBuilder();
            builder.append(i++).append(" ")
                    .append(m.groupCount())
                    .append(" ")
                    .append(m.group())
                    .append(" start:")
                    .append(m.start())
                    .append(" end:")
                    .append(m.end());
            for (int j = 0; j <= m.groupCount(); j++) {
                builder.append(" ").append(m.group(j));
            }
            System.out.println(builder.toString());
        }
    }
}
