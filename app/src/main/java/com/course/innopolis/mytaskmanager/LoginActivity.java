package com.course.innopolis.mytaskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.course.innopolis.mytaskmanager.Control.UserManager;
import com.course.innopolis.mytaskmanager.Model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    private EditText etLogin;
    private EditText etPassword;
    private Button bLogin;
    private Button bRegistrationQuery;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        // сюда не надо передавать объект
        /*
        Log.d(LOG_TAG, "getParcelableExtra");
        User user = (User) getIntent().getParcelableExtra(User.class.getCanonicalName());
        Log.d(LOG_TAG, "user: " + user.getLogin() + ", " + user.getPassword());*/

        etLogin = (EditText) findViewById(R.id.login);
        etPassword = (EditText) findViewById(R.id.password);

        bLogin = (Button) findViewById(R.id.loginButton);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();

                if (login.isEmpty()){
                    Toast.makeText(context, "Не заполнено поле логин", Toast.LENGTH_LONG).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(context, "Не заполнено поле пароль", Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(login)){
                    Toast.makeText(context, "В качестве логина необходимо ввести e-mail", Toast.LENGTH_LONG).show();
                }
                else if (login.equals("admin@gmail.com") && password.equals("admin")){
                    Intent intent = new Intent(context, AdminActivity.class);
                    context.startActivity(intent);
                }
                else{
                    UserManager um = UserManager.getInstance();
                    try {
                        // проверка правилности введенных логина и пароля
                        User user = um.getValidUser(login, password);
                        Intent intent = new Intent(context, ScheduleActivity.class);
                        // передача учетных данных пользователя в  основную активность приложения
                        //intent.putExtra(User.class.getCanonicalName(), user);
                        context.startActivity(intent);
                    }
                    catch (IllegalArgumentException e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        bRegistrationQuery = (Button) findViewById(R.id.registrationQueryButton);
        bRegistrationQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,RegistrationActivity.class);
                context.startActivity(intent);
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
