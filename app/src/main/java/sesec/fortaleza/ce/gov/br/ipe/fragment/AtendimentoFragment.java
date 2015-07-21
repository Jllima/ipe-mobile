package sesec.fortaleza.ce.gov.br.ipe.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sesec.fortaleza.ce.gov.br.ipe.R;
import sesec.fortaleza.ce.gov.br.ipe.adapter.AtendimentoAdapter;
import sesec.fortaleza.ce.gov.br.ipe.model.Atendimento;
import sesec.fortaleza.ce.gov.br.ipe.volleyjson.AppController;
import sesec.fortaleza.ce.gov.br.ipe.volleyjson.CustomErrorListener;

/**
 * Created by Jorge on 29/06/2015.
 */
public class AtendimentoFragment extends Fragment {
    private static final String JSON = "Json";

    ProgressDialog dialog;

    AtendimentoAdapter adapter;

    List<Atendimento> atendimentoList;

    ListView lv;

    // json object response url
    private String urlJsonObj = "http://172.30.20.131:8080/ipe/service/atendimentos/id";

    // json array response url
    private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";


    private static String TAG = AtendimentoFragment.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest, btnMakeString;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;

    private Map<String,String> params;
    private Map<String,String> headers;

    private EditText etEmail;
    private EditText etPassword;
    private String url;
    private int statusCode;

    public AtendimentoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        atendimentoList = new ArrayList<Atendimento>();
        //new AtendimentosJson().execute();

        View view = inflater.inflate(R.layout.activity_main_demo, container, false);

        url = "http://172.30.20.131:8080/ipe/service/resource/login";

        //txtResponse = (TextView)  view.findViewById(R.id.txtResponse);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        //btnMakeArrayRequest = (Button) view.findViewById(R.id.jsonArray);
        //btnMakeObjectRequest = (Button) view.findViewById(R.id.jsonObject);
        btnMakeString = (Button) view.findViewById(R.id.jsonString);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);


        lv = (ListView) view.findViewById(R.id.listView);

        /*
        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callByJsonArrayRequest();
            }
        });

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callByJsonObjectRequest();
            }
        });
        */
        btnMakeString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callByStringRequest();
            }
        });

        return view;
    }


    public void callByStringRequest(){
        showpDialog();
        final StringRequest request = new StringRequest(Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (statusCode == 201) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String aut_token = jsonObject.getString("auth_token");
                                Toast.makeText(getActivity(), "Token: " + aut_token, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            hidepDialog();
                        }else if(statusCode == 409){
                            hidepDialog();
                        }

                    }
                },
                new CustomErrorListener(getActivity(),pDialog,getFragmentManager()))
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<String,String>();
                params.put("username",etEmail.getText().toString());
                params.put("password", etPassword.getText().toString());
                return(params);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                headers = new HashMap<String,String>();
                headers.put("Accept","*/*");
                headers.put("service_key","f80ebc87-ad5c-4b29-9366-5359768df5a1");
                return(headers);
            }
            /*Este metodo retorna as informacoes dos headers como:
            * status code, cabecalhos dos headers e outros*/
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = response.statusCode;
                /*String parsed;
                if (statusCode == 201){
                    try {
                        parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        return (Response.success(new JSONObject(parsed),HttpHeaderParser.parseCacheHeaders(response)));
                    }catch (UnsupportedEncodingException e){
                        return Response.error(new ParseError(e));
                    }  catch (JSONException e) {
                        return Response.error(new ParseError(e));
                    }
                }*/
                return super.parseNetworkResponse(response);
            }

            /*
            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                    VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                    volleyError = error;
                }
                return volleyError;
            }
            */
        };

        request.setTag("tag");
        AppController.getInstance().addToRequestQueue(request);

    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    /*/ CALLS VOLLEY
    public void callByJsonObjectRequest(){
        Log.i("Script", "ENTREI: callByJsonObjectRequest()");

        params = new HashMap<String, String>();
        params.put("email", etEmail.getText().toString());
        params.put("pasword", etPassword.getText().toString());

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Method.GET,
                url,
                params,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Script", "SUCCESS: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        AppController.getInstance().addToRequestQueue(request);

    }
    */
    /*
    public void callByJsonArrayRequest(){
        params = new HashMap<String, String>();
        params.put("email", etEmail.getText().toString());
        params.put("pasword", etPassword.getText().toString());

        CustomJsonArrayRequest request = new CustomJsonArrayRequest(Method.GET,
                url,
                params,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Script", "SUCCESS: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        request.setTag("tag");
        AppController.getInstance().addToRequestQueue(request);

    }
    */
    /**
     * Method to make json object request where json response starts wtih {
     * */
    private void makeJsonObjectRequest() {

        showpDialog();
        /*
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String protocolo = response.getString("protocolo");
                    String descFinal = response.getString("descFinal");
                    String descInicial = response.getString("descInicial");
                    String dataConclusao = response.getString("dataConclusao");
                    String dataSolicitacao = response.getString("dataSolicitacao");
                    String equipe = response.getString("equipe");
                    String horaConclusao = response.getString("horaConclusao");
                    String horaSolicitacao = response.getString("horaSolicitacao");
                    String localizacao = response.getString("localizacao");
                    String observacao = response.getString("observacao");
                    String pontoReferencia = response.getString("pontoReferencia");
                    String procedencia = response.getString("procedencia");
                    String proprietarioResidencia = response.getString("proprietarioResidencia");
                    String protProcedencia = response.getString("protProcedencia");
                    String qtdComodosCasa = response.getString("qtdComodosCasa");
                    String qtdPessoasAtingidas = response.getString("qtdPessoasAtingidas");
                    String responsavel = response.getString("responsavel");
                    String solicitante = response.getString("solicitante");
                    String telSolicitante = response.getString("telSolicitante");
                    String titulo = response.getString("titulo");
                    String vtr = response.getString("vtr");

                    JSONObject idBairro = response.getJSONObject("idBairro");
                    String idB = idBairro.getString("id");
                    String nomeB = idBairro.getString("nome");

                    //JSONObject idSer = response.getJSONObject("idSer");
                    //String  idS = idSer.getString("id");
                    //String nomeSer = idSer.getString("nome");

                    JSONObject idEstatus = response.getJSONObject("idEstatus");
                    String  idE = idEstatus.getString("id");
                    String nomeE = idEstatus.getString("nome");

                    JSONObject idTipologia = response.getJSONObject("idTipologia");
                    String  idT = idTipologia.getString("id");
                    String nomeT = idTipologia.getString("nome");

                    jsonResponse = "";
                    jsonResponse += "descFinal: " + descFinal + "\n\n";
                    jsonResponse += "protocolo: " + protocolo + "\n\n";
                    jsonResponse += "descInicial: " + descInicial + "\n\n";
                    jsonResponse += "dataConclusao: " + dataConclusao + "\n\n";
                    jsonResponse += "dataSolicitacao: " + dataSolicitacao + "\n\n";
                    jsonResponse += "equipe: " + equipe + "\n\n";
                    jsonResponse += "horaConclusao: " + horaConclusao + "\n\n";
                    jsonResponse += "horaSolicitacao: " + horaSolicitacao + "\n\n";
                    jsonResponse += "localizacao: " + localizacao + "\n\n";
                    jsonResponse += "observacao: " + observacao + "\n\n";
                    jsonResponse += "pontoReferencia: " + pontoReferencia + "\n\n";
                    jsonResponse += "procedencia: " + procedencia + "\n\n";
                    jsonResponse += "proprietarioResidencia: " + proprietarioResidencia + "\n\n";
                    jsonResponse += "protProcedencia: " + protProcedencia + "\n\n";
                    jsonResponse += "qtdComodosCasa: " + qtdComodosCasa + "\n\n";
                    jsonResponse += "qtdPessoasAtingidas: " + qtdPessoasAtingidas + "\n\n";
                    jsonResponse += "responsavel: " + responsavel + "\n\n";
                    jsonResponse += "solicitante: " + solicitante + "\n\n";
                    jsonResponse += "telSolicitante: " + telSolicitante + "\n\n";
                    jsonResponse += "titulo: " + titulo + "\n\n";
                    jsonResponse += "vtr: " + vtr + "\n\n";

                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        */

    }

    /**
     * Method to make json array request where response starts with [
     * *
    private void makeJsonArrayRequest() {

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                String name = person.getString("name");
                                String email = person.getString("email");
                                JSONObject phone = person
                                        .getJSONObject("phone");
                                String home = phone.getString("home");
                                String mobile = phone.getString("mobile");

                                jsonResponse += "Name: " + name + "\n\n";
                                jsonResponse += "Email: " + email + "\n\n";
                                jsonResponse += "Home: " + home + "\n\n";
                                jsonResponse += "Mobile: " + mobile + "\n\n\n";

                            }

                            txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
    */
    /*
    class AtendimentosJson extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = "atendimentos/gson";

            try {
                String json = new JSONParser().get(urlString);
                atendimentoList = getAtendimentos(json);
            } catch (Throwable e) {
                Log.i("ERRO", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (atendimentoList.size() > 0) {
                adapter = new AtendimentoAdapter(getActivity(),atendimentoList);
                lv.setAdapter(adapter);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity())
                        .setTitle("ATENCAO")
                        .setMessage(
                                "Nao foi possivel carregar os atendimentos")
                        .setPositiveButton("OK", null);
                builder.create().show();
            }
        }

        // Retorna uma lista de pessoas com as informacoes retornadas do JSON
        private List<Atendimento> getAtendimentos(String json) {
            List<Atendimento> atendimentos = new ArrayList<Atendimento>();

            try {
                JSONArray atendimentosJson = new JSONArray(json);
                JSONObject atendimento;

                for (int i = 0; i < atendimentosJson.length(); i++) {
                    atendimento = new JSONObject(atendimentosJson.getString(i));
                    Log.i("Pessoa encontrada: ", "nome " + atendimento.getString("protocolo"));
                    Atendimento objetoAtendimento = new Atendimento();
                    objetoAtendimento.setProtocolo(atendimento.getString("protocolo"));
                    objetoAtendimento.setTitulo(atendimento.getString("titulo"));
                    atendimentos.add(objetoAtendimento);
                }

            } catch (JSONException e) {
                Log.e(JSON, "Erro no parse do JSON", e);
            }

            return atendimentos;
        }

    }
    */
}
