package processor;

import values.Values;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class StringSplitter {
    private String delimiter;

    public StringSplitter() {
        delimiter = Values.Strings.DEFAULT_SPLITTER_DELIMITER;
    }

    public StringSplitter(String delimiterString) {
        this.delimiter = delimiterString;
    }

    public String[] split(String string) throws StringSplitterException {
        String[] result = string.split(delimiter);
        if (string.isEmpty()) {
            throw new CanNotSplitEmptyStringException();
        }
        if (string.indexOf(delimiter) == -1) {
            throw new StringDoesNotContainDelimiterException();
        }
        if (result.length == 1) {
            throw new EmptyRightSideAfterSplitException();
        }
        return result;
    }


}
