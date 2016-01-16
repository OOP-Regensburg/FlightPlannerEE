package reader;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public interface FileReaderListener {
    public void onStartFileReading();
    public void onLineRead(String line);
    public void onEndOfFileReached();
}
