package processor;

import javax.lang.model.element.NestingKind;

/**
 * Created by Alexander Bazo on 15/01/16.
 */
public class StringSplitter {
    public static final String DEFAULT_DELIMITER = ",";
    private String delimiter;

    public StringSplitter() {
        delimiter = DEFAULT_DELIMITER;
    }

    public StringSplitter(String delimiterString) {
        this.delimiter = delimiterString;
    }

    public String[] split(String string) throws StringSplitterException {
        String[] result = string.split(delimiter);
        if(string.isEmpty()) {
            throw new CanNotSplitEmptyStringException();
        }
        if(string.indexOf(delimiter) == -1) {
            throw new StringDoesNotContainDelimiterException();
        }
        if(result.length == 1) {
            throw new EmptyRightSideAfterSplitException();
        }
        return result;
    }


}
