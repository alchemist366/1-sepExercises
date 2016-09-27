package iOHelper.tests;

import iOHelper.implementations.IOHelperImpl;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by root on 21.09.16.
 */
public class IOHelperImplTest {
    private static final String ENCODING = "UTF-8";
    private static final String FILE1 = "/home/aisalin/Документы/file",
    FILE2 = "/home/aisalin/Документы/file2", FILE3 = "/home/aisalin/Документы/file3";
    private static final String CONTENT = "fail3 is filled\n";

    private IOHelperImpl ioHelperImpl = new IOHelperImpl();

    @Test
    public void copyInstreamIntoOutstream() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(FILE1);
            outputStream = new FileOutputStream(FILE2);
            assertEquals((long)inputStream.available(), ioHelperImpl.copy(inputStream, outputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Exception e){
            }
            try {
                outputStream.close();
            } catch (Exception e){
            }
        }
    }

    @Test
    public void copyOneFileIntoAnother() {
        File file1 = new File(FILE1);
        File file2 = new File(FILE2);
        assertEquals(file1.length(), ioHelperImpl.copy(file1, file2));
    }

    @Test
    public void readFile() {
        File file = new File(FILE1);
        String str = "Привет мир!";
        assertEquals(str, ioHelperImpl.readFile(file));
    }

    @Test
    public void readFileWithEncoding() {
        File file1 = new File(FILE1);
        String  str = "Привет мир!";
        assertEquals(str, ioHelperImpl.readFile(file1, ENCODING));
    }

    @Test
    public void writeFileAppendTrue() {
        File file = new File(FILE3);
        boolean append = true;
        ioHelperImpl.writeFile(file, CONTENT, ENCODING, append);
    }

    @Test
    public void writeFileAppendFalse() {
        File file = new File(FILE3);
        boolean append = false;
        ioHelperImpl.writeFile(file, CONTENT, ENCODING, append);
    }


    @Test
    public void createInputStream() {
        String str = new String("asdfghjk");
        assertNotNull(ioHelperImpl.createInputStream(str));
    }

    @Test
    public void createInputStreamWithEncoding() {
        String str = new String("asdfghjk");
        assertNotNull(ioHelperImpl.createInputStream(str, ENCODING));
    }
}
