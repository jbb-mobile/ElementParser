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
    private Button submitQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        this.submitButton = (Button) findViewById(R.id.send_element);
        submitButton.setOnClickListener((View.OnClickListener) this);

        this.submitQuestionButton = (Button) findViewById(R.id.send_question);
        submitQuestionButton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.send_element:
                sendElements();
                break;
            case R.id.send_question:
                 sendQuestions();
                 break;
        }
    }

    private void sendElements() {
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

    private void sendQuestions() {
        UploadRequestQuestion uploadQuestion;
        ArrayList<Question> questions = TSVQuestionReader.getQuestion(getApplicationContext(), R.raw.tabela_questoes);

        for (final Question question: questions) {
            uploadQuestion = new UploadRequestQuestion(question);

            uploadQuestion.request(getApplicationContext(), new UploadRequest.Callback() {
                @Override
                public void callbackResponse(boolean success) {
                    Log.i("UPLOAD", "Upload de questao " + question.getId() + " com sucesso!");

                    if (!success) { // The question already exists, so update the questions;
                        UpdateRequestQuestion update = new UpdateRequestQuestion(question);
                        update.request(getApplicationContext(), new UpdateRequest.Callback() {
                            @Override
                            public void callbackResponse(boolean success) {
                                Log.i("UPDATE", "Update de questao " + question.getId() + " com sucesso!");

                                if (!success) {
                                    Log.e("UPDATE", "Update de questao " + question.getId() + " falhou!");
                                }
                            }
                        });
                    }
                }
            });
        }
    }


}

