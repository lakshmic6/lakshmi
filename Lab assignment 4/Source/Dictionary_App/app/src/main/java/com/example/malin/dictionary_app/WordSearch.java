package com.example.malin.dictionary_app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by malin on 19-09-2016.
 */
public class WordSearch extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    String sourceText;
    TextView outputTextView;
    TextView partOfSpeechView;
    TextView partOfSpeechLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        outputTextView = (TextView) findViewById(R.id.response);
        partOfSpeechView = (TextView) findViewById(R.id.partOfSpeech);
        partOfSpeechLabel = (TextView) findViewById(R.id.textlabel);

        TextView sourceTextView = (TextView) findViewById(R.id.txt_search);
        sourceText = sourceTextView.getText().toString();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void findMeaning(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.txt_search);
        sourceText = sourceTextView.getText().toString();

        RestClient.getInstance().getServiceSample(this).sendDatainURL(sourceText, "application/json", new Callback<DefResponse>() {
            @Override
            public void success(DefResponse response, Response response2) {
                Log.i("Response", response.toString());
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                List<Definitions> resp = response.getDefinitions();
                String meaning = null;
                String partOfSpeech = null;

                for (int i = 0; i < resp.size(); i++) {
                    meaning = resp.get(0).getDefinition();
                    partOfSpeech = resp.get(0).getPartOfSpeech();
                }

                outputTextView.setText(meaning);
                outputTextView.setVisibility(View.VISIBLE);
                partOfSpeechView.setText(partOfSpeech);
                partOfSpeechView.setVisibility(View.VISIBLE);
                partOfSpeechLabel.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "WordSearch Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.malin.dictionary_app/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "WordSearch Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.malin.dictionary_app/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
