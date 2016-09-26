package iOHelper.implementations;

import iOHelper.interfaces.IOHelper;

import java.io.*;

/**
 * Created by root on 20.09.16.
 */
public class IOHelperImpl implements IOHelper {

    public long copy(InputStream in, OutputStream out) throws IOException {
        if (in != null && in.available() > 0) {
            int b, i = 0;
            while ((b = in.read()) > -1) {
                out.write(b);
                i++;
            }
            out.flush();
            return i;
        }
        return 0;
    }

    public long copy(File source, File target) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            int i = 0;
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
        return 0;
    }

    public String readFile(File file) {
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int s = reader.read();
            while (s != -1) {
                result += (char) s;
                s = reader.read();
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
            int s = reader.read();
            while (s != -1) {
                result += (char) s;
                s = reader.read();
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
