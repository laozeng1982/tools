package tools.utilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Chinese to English using PinYin4j.jar
 *
 * @author JianGe
 */
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChineseToEnglish {

    //Transfer Chinese to Full PinYin
    public static String getPingYin(String src) {

        char[] src2CharArray = src.toCharArray();
        String[] t2 = new String[src2CharArray.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        try {
            for (int idx = 0; idx < src2CharArray.length; idx++) {
                // Estimate the char is a Chinese char
                if (java.lang.Character.toString(src2CharArray[idx]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(src2CharArray[idx], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(src2CharArray[idx]);
                }
            }
            // System.out.println(t4);  
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return t4;
    }

    // get the head char of Chinese 
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int idx = 0; idx < str.length(); idx++) {
            char word = str.charAt(idx);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    // Transfer to ASCII code  
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

}
