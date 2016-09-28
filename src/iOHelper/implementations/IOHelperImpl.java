package iOHelper.implementations;

import iOHelper.interfaces.IOHelper;

import java.io.*;

/**
 * Created by root on 20.09.16.
 */
public class IOHelperImpl implements IOHelper {
    private static final int BUFFER_SIZE = 1024;

    public long copy(InputStream in, OutputStream out) throws IOException {
        if (in != null && in.available() > 0) {
            BufferedInputStream bin = new BufferedInputStream(in);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            byte[] buffer = new byte[BUFFER_SIZE];
            long i , result = 0l;
            while ((i = (long) bin.read(buffer)) != -1) {
                bout.write(buffer);
                result += i;
            }
            bout.flush();
            return result;
        }
        return 0l;
    }

    public long copy(File source, File target) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            byte[] buffer = new byte[BUFFER_SIZE];
            long i , result = 0l;
            while ((i = (long) inStream.read(buffer)) != -1) {
                outStream.write(buffer);
                result += i;
            }
            outStream.flush();
            return result;
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
            int i;
            while ((i = reader.read(chars)) != -1) {
                result += new String(chars, 0, i);
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
            int i;
            while ((i = reader.read(chars)) != -1) {
                result += new String(chars, 0, i);
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
            content = new String(bytes);
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
