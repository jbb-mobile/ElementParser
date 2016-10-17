package jbbmobile.example.com.elementparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener{

    private UploadRequest upload;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        this.submitButton = (Button) findViewById(R.id.send_element);
        submitButton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.send_element:
                ArrayList<Element> elements = TSVReader.getElements(getApplicationContext(), R.raw.tabela_elementos);

                for (final Element element: elements) {
                    upload = new UploadRequest(element);

                    upload.request(getApplicationContext(), new UploadRequest.Callback() {
                        @Override
                        public void callbackResponse(boolean success) {
                            Log.i("UPLOAD", "Upload do elemento " + element.getIdElement() + " com sucesso!");

                            if (!success) { // The element already exists, so update the elements;
                                UpdateRequest update = new UpdateRequest(element);
                                update.request(getApplicationContext(), new UpdateRequest.Callback() {
                                    @Override
                                    public void callbackResponse(boolean success) {
                                        Log.i("UPDATE", "Update do elemento " + element.getIdElement() + " com sucesso!");

                                        if (!success) {
                                            Log.e("UPDATE", "Update do elemento " + element.getIdElement() + " falhou!");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
        }
    }
}

