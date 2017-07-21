/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.utilities;

/**
 *
 * @author JianGe
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tools.utilities.Logs;

public class StringUtils {

    // According to Unicode, compeletily estimate the char's type
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    // According to Unicode, compeletily estimate the String's type
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // Only can estimate a part of CJK String (by CJK uni chinese code)
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }

    // Only can estimate a part of CJK String (by CJK uni chinese code)
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // "\\p" means include, "\\P" means not
        // "\\p{Cn}" means the Unicode has not been defined, "\\P{Cn}" means the Unicode has been defined!
        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }

    public static int countOfChineseChar(String str) {
        int count = 0;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Formated print String
     *
     *
     * @param str
     * @return
     */
    public static String formatLeftS(String str) {
        int min_length = 24;
        if (str == null || str.isEmpty()) {
            str = "null";
        }
        String format = "%-" + (min_length - countOfChineseChar(str)) + "s";

        return String.format(format, str);
    }

    /**
     * Formated print String
     *
     *
     * @param str
     * @param minLength
     * @return
     */
    public static String formatLeftS(String str, int minLength) {

        if (str == null || str.isEmpty()) {
            str = "";
        }

        if (minLength - countOfChineseChar(str) < 0) {
            Logs.e("Error length.");
            return null;
        }
        String format = "%-" + (minLength - countOfChineseChar(str)) + "s";

        return String.format(format, str);
    }

    public static String removeAllSpaceAndTabs(String str) {
        return str.replaceAll("\\t", " ").replaceAll(" +", " ");
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String gb2312ToUtf8(String str) {
        String urlEncode = "";
        try {
            urlEncode = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return urlEncode;
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (UnsupportedEncodingException exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (UnsupportedEncodingException exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (UnsupportedEncodingException exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (UnsupportedEncodingException exception3) {
        }
        return "";
    }

    public static int getNumeric(String str) {
        int number = 0;
        StringBuilder numberString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                numberString.append(str.charAt(i));
            }
        }
        try {
            number = Integer.valueOf(numberString.toString());
        } catch (NumberFormatException e) {
        }

        return number;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumeric2(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric3(String s) {
        if (s != null && !"".equals(s.trim())) {
            return s.matches("^[0-9]*$");
        } else {
            return false;
        }
    }

    public static boolean isNumeric4(String str) {
        for (int i = str.length(); --i >= 0;) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
