package io.futurestud.retrofit1.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.futurestud.retrofit1.R;

public class LoginActivity extends AppCompatActivity {

    EditText emailET;
    EditText first_nameET;
    EditText last_nameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailET = (EditText) findViewById(R.id.login_email_ET);
        first_nameET = (EditText) findViewById(R.id.first_name_ET);
        last_nameET = (EditText) findViewById(R.id.last_name_ET);

        Button register_btn = (Button) findViewById(R.id.register_button);
        Button login_btn = (Button) findViewById(R.id.login_button);

        setup_register_btn(register_btn);
        setup_login_btn(login_btn);



    }

    private void setup_login_btn(Button login_btn) {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String first_name = first_nameET.getText().toString();
                String last_name = last_nameET.getText().toString();
            }
        });
    }

    private void setup_register_btn(Button register_btn) {
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String first_name = first_nameET.getText().toString();
                String last_name = last_nameET.getText().toString();
            }
        });
    }
}
