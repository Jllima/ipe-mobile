package sesec.fortaleza.ce.gov.br.ipe.volleyjson;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import sesec.fortaleza.ce.gov.br.ipe.R;
import sesec.fortaleza.ce.gov.br.ipe.fragment.dialog.CustomDialogFragment;

/**
 * Created by Jorge on 20/07/2015.
 */
public class CustomErrorListener implements ErrorListener{

    private Context ctx;
    private ProgressDialog pDialog;
    private CustomDialogFragment alertDialog;
    private FragmentManager fragmentManager;

    /*
    * O contrutor recebe o cotexto da activity, ProgressDialog
    * A FragmentManager serve para ativar o AlertDialog*/
    public CustomErrorListener(Context ctx,ProgressDialog pDialog, FragmentManager fragmentManager){
        this.ctx = ctx;
        this.pDialog = pDialog;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        /*Caso o metodo parseNetworkResponse for sobescrito
                        * é necessario apenas as duas linhas abaixo:*/
        //Log.i("Menssagem 1 ", "status: " + statusCode);
        //Toast.makeText(getActivity(), "Error 1: " + error.getMessage(), Toast.LENGTH_LONG).show();
        String json = null;

        NetworkResponse response = volleyError.networkResponse;

        if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
            //Toast.makeText(ctx, "timeout error or no connection", Toast.LENGTH_LONG).show();
            alertDialog = new CustomDialogFragment(R.string.dialog_alert_no_connection);
            alertDialog.show(fragmentManager,"dialog");
            pDialog.dismiss();
        } else if (volleyError instanceof AuthFailureError) {
            //Toast.makeText(ctx, "AuthFailureError", Toast.LENGTH_LONG).show();
            alertDialog = new CustomDialogFragment(R.string.dialog_alert_AuthFailureError);
            alertDialog.show(fragmentManager,"dialog");
            pDialog.dismiss();
        } else if (volleyError instanceof ServerError) {
            Toast.makeText(ctx, "ServerError", Toast.LENGTH_LONG).show();
            pDialog.dismiss();
        } else if (volleyError instanceof NetworkError) {
            Toast.makeText(ctx, "NetworkError", Toast.LENGTH_LONG).show();
            pDialog.dismiss();
        } else if (volleyError instanceof ParseError) {
            Toast.makeText(ctx, "ParseError", Toast.LENGTH_LONG).show();
            pDialog.dismiss();
        } else if(response != null && response.data != null){
            switch(response.statusCode){
                case 401:
                    json = new String(response.data);
                    json = trimMessage(json, "messagem");
                    if(json != null) {
                        displayMessage(json);
                    }else{
                        Toast.makeText(ctx, "Acesso não autorizado", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
            //Additional cases
        }
    }
    public String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }

    public void displayMessage(String toastString){
        Toast.makeText(ctx, toastString, Toast.LENGTH_LONG).show();
    }
}
