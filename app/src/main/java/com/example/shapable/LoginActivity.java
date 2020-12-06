package com.example.shapable;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shapable.R;
//import com.example.shapable.ui.login.LoggedInUserView;
//import com.example.shapable.ui.login.LoginFormState;
//import com.example.shapable.ui.login.LoginResult;
import com.example.shapable.ui.login.LoginViewModel;
import com.example.shapable.ui.login.LoginViewModelFactory;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper database;
    private EditText mEditText_email, mEditText_password;
    private TextView mTextView_register;
    private Button mButton_Login;
    private Spinner userType_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = new DatabaseHelper(this);
        mEditText_email = (EditText)findViewById(R.id.editText_email);
        mEditText_password = (EditText)findViewById(R.id.editText_password);
        mTextView_register = (TextView)findViewById(R.id.textView_login);
        mButton_Login = (Button)findViewById(R.id.button_login);
        userType_spinner=(Spinner)findViewById(R.id.userType_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(LoginActivity.this,R.array.userType,R.layout.support_simple_spinner_dropdown_item);
        userType_spinner.setAdapter(adapter);


        mTextView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });

        mButton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = userType_spinner.getSelectedItem().toString();
                if(item.equals("Child")) {
                    validateEmail();
                    validatePassword();

                    if (TextUtils.isEmpty(mEditText_email.getText().toString()) || TextUtils.isEmpty(mEditText_password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean findUserInDatabase = database.checkUser(mEditText_email.getText().toString().trim(), mEditText_password.getText().toString().trim());
                        // If the fields are validated, it will take the user to the login page
                        if ((findUserInDatabase == true) && (validateEmail() && validatePassword()) == true) {
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, CardViewGames.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(item.equals("Parent")){
                    validateEmail();
                    validatePassword();

                    if (TextUtils.isEmpty(mEditText_email.getText().toString()) || TextUtils.isEmpty(mEditText_password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean findUserInDatabase = database.checkUser(mEditText_email.getText().toString().trim(), mEditText_password.getText().toString().trim());
                        // If the fields are validated, it will take the user to the login page
                        if ((findUserInDatabase == true) && (validateEmail() && validatePassword()) == true) {
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, ParentPortal.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    // This method will validate email
    protected boolean validateEmail(){
        String emailInput = mEditText_email.getText().toString().trim();
        if(!EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mEditText_email.setError("Please enter a valid email address");
            return false;
        }
        else{
            return true;
        }
    }

    // This is the pattern to validate Email
    public static final Pattern EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    // This method will validate the password
    public boolean validatePassword(){
        String passwordInput = mEditText_password.getText().toString().trim();
        if(!PASSWORD_VALIDATION.matcher(passwordInput).matches()) {
            mEditText_password.setError("Password too weak");
            return false;
        }
        else {
            return true;
        }
    }

    // This is the pattern to validate the password
    public static final Pattern PASSWORD_VALIDATION = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{1,}");
}
