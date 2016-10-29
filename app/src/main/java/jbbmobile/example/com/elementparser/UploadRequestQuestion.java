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

public class UploadRequestQuestion {
    private static final String UPLOAD_REQUEST_URL =  "http://rogerlenke.site88.net/parser/UploadQuestion.php";
    private Map<String,String> params;
    private Question question;

    public UploadRequestQuestion(Question question){
        this.question = question;
    }

    public void request(Context context, final UploadRequest.Callback callback) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.d("RESPONSE", response);
                    boolean successQuestion = jsonObject.getBoolean("successQuestion");
                    boolean successAlternatives  = jsonObject.getBoolean("successAlternative");
                    callback.callbackResponse(successQuestion && successAlternatives);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_REQUEST_URL, listener, null){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                params = new HashMap<>();
                params.put("id", String.valueOf(question.getId()));
                params.put("description", question.getDescription());
                params.put("correctAnswer", question.getCorrectAnswer());
                params.put("a", question.getAlternatives().get("a"));
                params.put("b", question.getAlternatives().get("b"));
                params.put("c", question.getAlternatives().get("c"));
                params.put("d", question.getAlternatives().get("d"));
                params.put("e", question.getAlternatives().get("e"));
                params.put("#alternatives", String.valueOf(question.getAlternativeQuantity()));
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
