package iOHelper.implementations;

import iOHelper.interfaces.IOHelper;

import java.io.*;

/**
 * Created by root on 20.09.16.
 */
public class IOHelperImpl implements IOHelper {
    private static final int BUFFER_SIZE = 1024;

    public long copy(InputStream in, OutputStream out) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(in);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        // оставил всё как есть, потому что надо вернуть количество скопированных байт

        if (in != null && in.available() > 0) {
            int b;
            long i = 0l;
            while ((b = bin.read()) > -1) {
                bout.write(b);
                i++;
            }
            out.flush();
            return i;
        }
        return 0l;
    }

    public long copy(File source, File target) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            long i = 0l;
            while (inStream.available() != 0) {
                outStream.write(inStream.read());
                i++;
            }
            return i;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Exception e) {
            }
            try {
                outStream.close();
            } catch (Exception e) {
            }
        }
        return 0l;
    }

    public String readFile(File file) {
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            char[] chars = new char[BUFFER_SIZE];
            reader.read(chars);
            int i = 0;
            while (chars[i] != 0) {
                result += chars[i];
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
        return result;
    }

    public String readFile(File file, String encoding) {
        String result = "";
        BufferedReader reader = null;
        try {
            reader =new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            char[] chars = new char[BUFFER_SIZE];
            reader.read(chars);
            int i = 0;
            while (chars[i] != 0) {
                result += chars[i];
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
        return result;
    }

    public void writeFile(File file, String content, String encoding, boolean append) {
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter writer = null;
        try {
            outputStreamWriter = new FileWriter(file, append);
            writer = new BufferedWriter(outputStreamWriter);
            byte[] bytes = content.getBytes(encoding);
            content = "";
            for (byte oneByte:
                 bytes) {
                content += (char)oneByte;
            }
            if (file.canWrite()) {
                writer.write(content);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
            }
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    public InputStream createInputStream(String source) {
        return new ByteArrayInputStream(source.getBytes());
    }

    public InputStream createInputStream(String source, String encoding) {
        try {
            return new ByteArrayInputStream(source.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
