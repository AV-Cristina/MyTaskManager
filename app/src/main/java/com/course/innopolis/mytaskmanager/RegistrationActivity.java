package com.course.innopolis.mytaskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.course.innopolis.mytaskmanager.controls.UserManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private EditText etLogin;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button bRegistration;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = this;

        etLogin = (EditText)findViewById(R.id.login);
        etPassword = (EditText)findViewById(R.id.password);
        etConfirmPassword = (EditText)findViewById(R.id.confirm_password);

        bRegistration = (Button) findViewById(R.id.registrationButton);
        bRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(context, "Заполните, пожалуйста, все поля", Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(login)){
                    Toast.makeText(context, "В качестве логина необходимо ввести e-mail", Toast.LENGTH_LONG).show();
                }
                else if(password.length() < 5) {
                    Toast.makeText(context, "Пароль должен содержать не менее 5 символов", Toast.LENGTH_LONG).show();
                }
                else if (!confirmPassword.equals(password)) {
                    Toast.makeText(context, "Неправильное подтверждение пароля", Toast.LENGTH_LONG).show();
                }
                else {
                      UserManager um = UserManager.getInstance();
                      try{
                          um.addUser(login, password);
                          Intent intent = new Intent(context,LoginActivity.class);
                          //intent.putExtra(User.class.getCanonicalName(), user);
                          context.startActivity(intent);
                      }
                      catch (IllegalArgumentException e){
                          Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                      }
                }
            }
        });
    }

    /**
     * Метод проверяет был ли в качестве логина введен email
     *
     * @param email
     * @return boolean true, если введен  email, иначе false
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
