package vn.edu.usth.flickr;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth1Token;

import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sp;

    private final String apiKey = "b73450b170505cfc1020ef349a0a8cb4";
    private final String sharedSecret = "679e34e410b67118";
    private Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());

    String tokenKey;
    OAuth1RequestToken requestToken;
    AuthInterface authInterface;
    Auth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp.edit().putBoolean("logged", false).apply();

        if(sp.getBoolean("logged",false)){
            goToMainActivity();
        } else {
            getTokenTask ft = new getTokenTask();
            ft.execute();

            final EditText tokenEditText = (EditText) findViewById(R.id.token);

            final Button loginButton = (Button) findViewById(R.id.sign_in);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tokenKey = tokenEditText.getText().toString();
                    authTask at = new authTask();
                    at.execute();

                    updateUiWithUser(tokenEditText.getText().toString());
                    sp.edit().putBoolean("logged", true).apply();
                    goToMainActivity();
                    finish();
                }
            });
        }

    }

    public void goToMainActivity(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void updateUiWithUser(String name) {
        String welcome = "Welcome ";
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private class getTokenTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute(){
            Toast.makeText(getBaseContext(), "Opening browser. Please wait.", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            authInterface = flickr.getAuthInterface();
            requestToken = authInterface.getRequestToken();
            return authInterface.getAuthorizationUrl(requestToken, Permission.DELETE);
        }

        @Override
        protected void onPostExecute(String url) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    private class authTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids){
            Log.i("haha ded",requestToken.toString());
            try {
                OAuth1Token accessToken = authInterface.getAccessToken(
                        requestToken, tokenKey);
                auth = authInterface.checkToken(accessToken);
            } catch (FlickrException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}