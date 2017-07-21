/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import tools.utilities.Logs;

/**
 *
 * @author deep
 */
public class IOHelper {

    /**
     * method to read ASCII file
     *
     * @param filePath
     * @param code,
     * @return
     */
    public static ArrayList<String> readAsciiFile(String filePath, String code) {

        ArrayList<String> contentsArrayList = readAsciiFile(new File(filePath), code);

        return contentsArrayList;
    }

    /**
     * method to read ASCII file
     *
     * @param file
     * @param code,
     * @return
     */
    public static ArrayList<String> readAsciiFile(File file, String code) {

        BufferedReader reader = null;
        ArrayList<String> contentsArrayList = new ArrayList<>();

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            String tempString;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                contentsArrayList.add(tempString);
                line++;

            }
            reader.close();
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return contentsArrayList;
    }

    /**
     * method to read binary file
     *
     * @param filePath
     * @param byteCount
     * @return
     */
    public static ArrayList<String> readBinaryFile(String filePath, int byteCount) {
        byte[] buffer = new byte[byteCount];
        ArrayList<String> contentsArrayList = new ArrayList();
        File file = new File(filePath);

        try (InputStream inputStream = new FileInputStream(file)) {

            while (-1 != inputStream.read(buffer)) {
                //byte to String
                contentsArrayList.add(new String(buffer));
            }

        } catch (IOException ex) {
        }
        return contentsArrayList;
    }

    /**
     *
     * @param filePath
     * @param code , can be "UTF-8" or "GB2312"
     * @return
     */
    public static Map<String, String> readTwoColumnsInfo(String filePath, String code) {
        Map<String, String> info = new LinkedHashMap<>();
        File file = new File(filePath);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            String tempLine = null;

            while ((tempLine = reader.readLine()) != null) {
                //Replace all space(" ") to one space(" ")
                String[] tempStrings = tempLine.replaceAll(" +", " ").split(" ");
                if (tempStrings.length <= 1) {
                    info.put(tempStrings[0], " ");
                } else {
                    info.put(tempStrings[0], tempStrings[1]);
                }

            }
            reader.close();
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return info;
    }

    /**
     * ���ֽ�Ϊ��λ��ȡ�ļ��������ڶ��������ļ�����ͼƬ��������Ӱ����ļ���
     *
     * @param fileName
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            Logs.e("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
            // һ�ζ�һ���ֽ�
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            return;
        }
        try {
            Logs.e("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ֽ�
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            showAvailableBytes(in);
            // �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (IOException e1) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
     *
     * @param fileName
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            Logs.e("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
            // һ�ζ�һ���ַ�
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
                // ������������ַ��ֿ���ʾʱ���ỻ�����С�
                // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (IOException e) {
        }
        try {
            Logs.e("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ַ�
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
            while ((charread = reader.read(tempchars)) != -1) {
                // ͬ�����ε�\r����ʾ
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] != '\r') {
                            System.out.print(tempchars[i]);
                        } else {
                        }
                    }
                }
            }

        } catch (IOException e1) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
     *
     * @param fileName
     * @param start
     * @param end
     */
    public static void readFileByChars(String fileName, int start, int end) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            Logs.e("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ַ�
            if (end - start <= 0) {
                System.err.println("Wrong input");
                return;
            }
            char[] tempchars = new char[end - start];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
            while ((charread = reader.read(tempchars)) != -1) {
                // ͬ�����ε�\r����ʾ

                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (IOException e1) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
     *
     * @param fileName
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            Logs.e("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                Logs.e("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
     */
    public static ArrayList<String> readLines(String fileName) {
        ArrayList<String> output = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ����
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            //һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
                output.add(tempString);
//                if (tempString.contains("New")) {
//                Logs.e("line " + line + ": " + tempString);
//                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return output;
    }

    /**
     * �����ȡ�ļ�����
     */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            Logs.e("�����ȡһ���ļ����ݣ�");
            // ��һ����������ļ�������ֻ����ʽ
            randomFile = new RandomAccessFile(fileName, "r");
            // �ļ����ȣ��ֽ���
            long fileLength = randomFile.length();
            // ���ļ�����ʼλ��
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // �����ļ��Ŀ�ʼλ���Ƶ�beginIndexλ�á�
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // һ�ζ�10���ֽڣ�����ļ����ݲ���10���ֽڣ����ʣ�µ��ֽڡ�
            // ��һ�ζ�ȡ���ֽ�������byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * ��ʾ�������л�ʣ���ֽ���
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            Logs.e("��ǰ�ֽ��������е��ֽ���Ϊ:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveArrayListToFile(String fileName, ArrayList<String> contents) {
        File file = new File(fileName);
        FileWriter fileWritter;
        BufferedWriter buffWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWritter = new FileWriter(fileName);
            buffWriter = new BufferedWriter(fileWritter);
            for (int idx = 0; idx < contents.size(); idx++) {
                buffWriter.write(contents.get(idx));
                buffWriter.newLine();
//                Logs.e(idx + " " + contents.get(idx));
            }
        } catch (IOException e) {
        } finally {
            if (buffWriter != null) {
                try {
                    buffWriter.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void saveMapDataToFile(String fileName, Map<String, String> contents) {
        File file = new File(fileName);
        FileWriter fileWritter;
        BufferedWriter buffWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWritter = new FileWriter(fileName);
            buffWriter = new BufferedWriter(fileWritter);

            Iterator i = contents.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                buffWriter.write(entry.getKey() + "    " + entry.getValue());
                buffWriter.newLine();
            }

        } catch (IOException e) {
        } finally {
            if (buffWriter != null) {
                try {
                    buffWriter.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void save2DArrayListToFile(String fileName, ArrayList<ArrayList<String>> contents) {
        File file = new File(fileName);
        FileWriter fileWritter;
        BufferedWriter buffWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWritter = new FileWriter(fileName);
            buffWriter = new BufferedWriter(fileWritter);

            int totalLines = contents.get(0).size();

            StringBuilder values = new StringBuilder();
            for (int line = 0; line < totalLines; line++) {
                for (int idx = 0; idx < contents.size(); idx++) {
                    values.append(contents.get(idx).get(line)).append("    ");
                }
                buffWriter.write(values.toString());
                buffWriter.newLine();
                values.delete(0, values.length());
            }

        } catch (IOException e) {
        } finally {
            if (buffWriter != null) {
                try {
                    buffWriter.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     *
     * @param fileName
     * @param info
     */
    public static void saveAsciiToFile(String fileName, String info) {
        FileOutputStream out;
        BufferedWriter writer = null;

        try {
            out = new FileOutputStream(fileName, true);
            writer = new BufferedWriter(new OutputStreamWriter(out));

            writer.write(info);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }

}
