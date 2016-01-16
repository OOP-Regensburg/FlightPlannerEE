package dialog;

import custom.CustomInputStream;
import custom.CustomOutputStream;
import dialog.UserDialog;

import java.util.HashMap;

/**
 * Created by Alexander Bazo on 16/01/16.
 */

//@TODO: Should be a UserDialogProxy, probablly ad a superclass
public class DialogProxy {
    private HashMap<String, UserDialog> dialogs;
    private UserDialogListener listener;

    public DialogProxy(UserDialogListener listener) {
        dialogs = new HashMap<String, UserDialog>();
        this.listener = listener;
    }

    public void addDialog(UserDialog dialog) throws DialogAlreadyExistsException {
        if(dialogs.get(dialog.getTitle()) != null) {
            throw new DialogAlreadyExistsException();
        } else {
            dialogs.put(dialog.getTitle(), dialog);
        }
    }

    public void showDialog(String dialogTitle, CustomOutputStream outputStream, CustomInputStream inputStream) throws DialogNotAvailableException {
        UserDialog dialog = dialogs.get(dialogTitle);
        if(dialog == null) {
            throw new DialogNotAvailableException();
        } else {
            dialog.showDialog(outputStream, inputStream, listener);
        }
    }
}
