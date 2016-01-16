package processor;

import java.util.HashMap;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class SplitterRecycler  {
    private HashMap<String, StringSplitter> availableSplitters;
    private static SplitterRecycler instance = null;

    private SplitterRecycler() {
        availableSplitters = new HashMap<String, StringSplitter>();
    }

    public static SplitterRecycler getInstance() {
        if(instance == null) {
            instance = new SplitterRecycler();
        }
        return instance;
    }

    public StringSplitter getSplitter(String delimiter) {
        StringSplitter splitter = availableSplitters.get(delimiter);
        if(splitter == null) {
            splitter = new StringSplitter(delimiter);
            availableSplitters.put(delimiter, splitter);
        }
        return splitter;
    }

}
