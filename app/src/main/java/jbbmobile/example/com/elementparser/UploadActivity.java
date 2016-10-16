package jbbmobile.example.com.elementparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

public class UploadActivity extends AppCompatActivity {

    private UploadRequest upload;
    private Element element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        element = new Element(1, Pair.create(-213.213, -32424.4343), "Teste", "This is a test!");
        upload = new UploadRequest(element);

        upload.request(getApplicationContext(), new UploadRequest.Callback() {
            @Override
            public void callbackResponse(boolean success) {
                Log.i("UPLOAD", "Upload realizado com sucesso!");
            }
        });
    }
}
