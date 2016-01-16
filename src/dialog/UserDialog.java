package dialog;

import custom.CustomInputStream;
import custom.CustomOutputStream;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import values.Values;

import java.util.ArrayList;

/**
 * Created by Alexander Bazo on 16/01/16.
 */
public class UserDialog {
    private String title;
    private String prompt;
    private int selectedOptionIndex = -1;
    private ArrayList<String> options;

    public UserDialog(String title) {
        this.title = title;
        options = new ArrayList<String>();
    }

    public void addOption(String option) {
        options.add(option);
    }

    public String getTitle() {
        return title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getOption(int index) throws InvalidOptionIndexSelectedException {
        String option = options.get(index);
        if (option == null) {
            throw new InvalidOptionIndexSelectedException();
        }
        return option;
    }

    public String getSelectedOption() throws NoOptionSelectedException {
        String option = options.get(selectedOptionIndex);
        if (option == null) {
            throw new NoOptionSelectedException();
        }
        return option;
    }

    public void showDialog(CustomOutputStream outputStream, CustomInputStream inputStream, UserDialogListener listener) {
        outputStream.println(prompt);
        for (int i = 1; i <= options.size(); i++) {
            outputStream.println(i + Values.Strings.OPTIONS_INDEX_DELIMITER_FOR_OUTPUT + "\t" + options.get(i - 1));
        }
        int selectedOption = -1;
        while (selectedOption < 1 || selectedOption > options.size() + 1) {
            selectedOption = inputStream.readInt(Values.Strings.EN.SELECT_OPTION_PROMPT);
        }
        selectedOptionIndex = selectedOption - 1;
        listener.onDialogOptionSelected(this);
    }


}
