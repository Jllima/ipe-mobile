package sesec.fortaleza.ce.gov.br.ipe.util;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Created by Jorge on 29/06/2015.
 */
public class HttpUtil {
    protected static final String CATEGORIA = "HttpUtil";
    private static final int JSON_CONNECTION_TIMEOUT = 5000;
    private static final int JSON_SOCKET_TIMEOUT = 5000;
    private HttpParams httpParameters;
    private DefaultHttpClient httpclient;
    public static HttpUtil instancia;

    public HttpUtil() {
        httpParameters = new BasicHttpParams();
        setTimeOut(httpParameters);
        httpclient = new DefaultHttpClient(httpParameters);
    }

    public static DefaultHttpClient getInstance() {
        if (instancia == null)
            instancia = new HttpUtil();
        return instancia.httpclient;
    }

    private void setTimeOut(HttpParams params) {
        HttpConnectionParams.setConnectionTimeout(params,
                JSON_CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, JSON_SOCKET_TIMEOUT);
    }
}
