package sesec.fortaleza.ce.gov.br.ipe.volleyjson;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Jorge on 18/07/2015.
 * Esta classe otimiza o envio de reuisicoes atraves do volley
 * Possibilta a cricao de parametros e cabecalhos
 * Esta classe segue po padra da comunidade
 */
public class CustomJsonObjectRequest extends Request<JSONObject> {

    private Listener<JSONObject> response;

    private  Map<String, String> params;

    public CustomJsonObjectRequest(int method, String url, Map<String, String> params, Listener<JSONObject> response, ErrorListener listener) {
        super(method, url, listener);
        this.response = response;
        this.params = params;
    }

    public CustomJsonObjectRequest(String url, Map<String, String> params, Listener<JSONObject> response, ErrorListener listener) {
        super(Method.GET, url, listener);
        this.response = response;
        this.params = params;
    }

    public Map<String,String> getParams() throws AuthFailureError{
        return params;
    }
    /*
    * tratamentos de dados*/
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response.success(new JSONObject(js),HttpHeaderParser.parseCacheHeaders(response)));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        this.response.onResponse(response);
    }
}
