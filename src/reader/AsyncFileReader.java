package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class AsyncFileReader {
    private boolean skipEmptyLines;

    public AsyncFileReader() {
        skipEmptyLines = false;
    }

    public AsyncFileReader(boolean skipEmptyLines) {
        this.skipEmptyLines = skipEmptyLines;
    }

    public void readFile(File file, FileReaderListener listener) {
        listener.onStartFileReading();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    listener.onEndOfFileReached();
                    break;
                } else {
                    if (line.isEmpty()) {
                        continue;
                    } else {
                        listener.onLineRead(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
