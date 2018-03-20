package none.sbaixas.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Test1 extends AppCompatActivity {
    private SharedPreferences settings;
    private TextView emailTextView;
    private TextView passwordTextView;
    public static final String LOGIN_PREFERENCES = "LoginPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        settings = getSharedPreferences(LOGIN_PREFERENCES, MODE_PRIVATE);
        emailTextView = (TextView) findViewById(R.id.userEmail);
        passwordTextView = (TextView) findViewById(R.id.userPwd);
        String email = settings.getString("userEmail", null);
        String password = settings.getString("userPwd", null);
        if(email == null && password == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 48);
        }
        else{
            emailTextView.setText(email);
            passwordTextView.setText(password);
        }


    }

    public void OnLogoutClick(View view){
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.remove("userEmail");
        prefEditor.remove("userPwd");
        prefEditor.commit();
        emailTextView.setText("");
        passwordTextView.setText("");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 48);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            SharedPreferences settings = getSharedPreferences(LOGIN_PREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putString("userEmail", data.getExtras().getString("email"));
            prefEditor.putString("userPwd", data.getExtras().getString("password"));
            prefEditor.commit();
            emailTextView.setText(data.getExtras().getString("email"));
            passwordTextView.setText(data.getExtras().getString("password"));
        }
    }

}

