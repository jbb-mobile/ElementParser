package jbbmobile.example.com.elementparser;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UploadRequest {
    private static final String UPLOAD_REQUEST_URL =  "http://rogerlenke.site88.net/parser/Upload.php";
    private Map<String,String> params;
    private Element element;

    public UploadRequest(Element element){
        this.element = element;
    }

    public void request(Context context, final Callback callback) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("RESPONSE", response);
                    Log.i("Test", String.valueOf(jsonObject.getBoolean("success")));
                    callback.callbackResponse(jsonObject.getBoolean("success"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_REQUEST_URL, listener, null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<>();
                params.put("id", String.valueOf(element.getIdElement()));
                params.put("name", element.getNameElement());
                params.put("x", String.valueOf(element.getCoordinates().first));
                params.put("y", String.valueOf(element.getCoordinates().second));
                params.put("description", element.getTextDescription());
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

    public interface Callback{
        void callbackResponse(boolean success);
    }
}
