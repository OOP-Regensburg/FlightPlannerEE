package dialog;

import java.util.ArrayList;

/**
 * Created by Alexander Bazo on 16/01/16.
 */
public class UserDialogBuilder {
    private String title = "";
    private String prompt = "";
    private ArrayList<String> options;

    public UserDialogBuilder() {
        options = new ArrayList<String>();
    }

    public void addPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void addTitle(String title) {
        this.title = title;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public void clear() {
        title = "";
        prompt = "";
        options.clear();
    }

    public UserDialog getDialog() throws DialogParametersMissingException {
        if (title.isEmpty() || prompt.isEmpty() || options.size() == 0) {
            throw new DialogParametersMissingException();
        }
        UserDialog dialog = new UserDialog(title);
        dialog.setPrompt(prompt);
        for (String option : options) {
            dialog.addOption(option);
        }
        return dialog;
    }
}
