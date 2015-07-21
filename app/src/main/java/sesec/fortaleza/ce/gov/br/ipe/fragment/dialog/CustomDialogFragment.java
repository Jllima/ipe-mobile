package sesec.fortaleza.ce.gov.br.ipe.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import sesec.fortaleza.ce.gov.br.ipe.R;

/**
 * Created by Jorge on 21/07/2015.
 */
public class CustomDialogFragment extends  DialogFragment{

    private int idMessage;

    public CustomDialogFragment(int idMessage){
        this.idMessage = idMessage;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create the AlertDialog object and return it
        return alertDialog();
    }
    public Dialog alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(R.string.dialog_alert_title)
                .setMessage(idMessage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })/*
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })*/;
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
