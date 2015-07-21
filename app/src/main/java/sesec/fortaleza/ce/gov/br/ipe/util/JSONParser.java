package sesec.fortaleza.ce.gov.br.ipe.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

/**
 * Created by Jorge on 29/06/2015.
 */
public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    static String uri = "http://192.168.43.164:8080/ipe/service/";

    public String get(String url) {
        String urlString = uri+url;
        HttpGet httpget = new HttpGet(urlString);
        HttpResponse response;
        try {
            response = HttpUtil.getInstance().execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String json = getStringFromInputStream(instream);
                instream.close();
                return json;
            }
        } catch (IOException e) {
            Log.e("get", e.getMessage(), e);
        }
        return null;

    }

    public String[] post(String url, String json) {
        String[] result = new String[2];
        try {
            HttpPost httpPost = new HttpPost(new URI(uri+url));
            httpPost.setHeader("Content-type", "application/json");
            StringEntity sEntity = new StringEntity(json, "UTF-8");
            httpPost.setEntity(sEntity);

            HttpResponse response;
            response = HttpUtil.getInstance().execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

//			HttpEntity entity = response.getEntity();

            if (statusCode == 201) {
                HttpEntity entity = response.getEntity();
                result = new String[2];
                result[0] = String.valueOf(response.getStatusLine()
                        .getStatusCode());
                InputStream inStream = entity.getContent();
                result[1] = toString(inStream);
                inStream.close();
                Log.d("post", "Result from post JsonPost : " + result[0]
                        + " : " + result[1]);
            }else if(statusCode == 409){
                result[0] = "0";
                result[1] = "email existente";
            }
        } catch (Exception e) {
            Log.e("NGVL", "Falha ao acessar Web service", e);
            result[0] = "0";
            result[1] = "Falha de rede!";
        }
        return result;
    }

    private String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    private String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
}
