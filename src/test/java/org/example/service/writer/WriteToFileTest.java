package org.example.service.writer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class WriteToFileTest {

    @Test
    public void testWrite() {
        WriteToFile writeToFile = new WriteToFile();

        String content = "This is a test content.";
        writeToFile.write(content);

        try (BufferedReader br = new BufferedReader(new FileReader(writeToFile.getFilename()))) {
            String line;
            boolean foundContent = false;
            while ((line = br.readLine()) != null) {
                if (line.equals(content)) {
                    foundContent = true;
                    break;
                }
            }

            assertTrue(foundContent);
        } catch (IOException e) {
            fail("An error occurred while reading the file.");
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateFolderAndFileIfNotExists() {
        WriteToFile writeToFile = new WriteToFile();

        writeToFile.createFolderAndFileIfNotExists();

        File file = new File(writeToFile.getFilename());
        assertTrue(file.exists());
    }

}