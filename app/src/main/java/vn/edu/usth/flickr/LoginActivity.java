package vn.edu.usth.flickr;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sp = getSharedPreferences("login",MODE_PRIVATE);
//        sp.edit().putBoolean("logged", false).apply();

        if(sp.getBoolean("logged",false)){
            goToMainActivity();
        }

        final EditText usernameEditText = (EditText) findViewById(R.id.username);
        final EditText passwordEditText = (EditText) findViewById(R.id.password);
        final Button loginButton = (Button) findViewById(R.id.sign_in);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("fine", "here, okay?");
                updateUiWithUser(usernameEditText.getText().toString());
                sp.edit().putBoolean("logged", true).apply();
                goToMainActivity();
                finish();
            }
        });
    }

    public void goToMainActivity(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void updateUiWithUser(String name) {
        String welcome = "Welcome " + name;
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

}