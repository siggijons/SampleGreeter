package com.quizup.greeter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener
{

    private TextView text;
    private EditText input;
    private Button hello;
    private Button helloAll;
    private Greeter greeter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = viewById(R.id.text);
        input = viewById(R.id.input);
        hello = viewById(R.id.hello);
        hello.setOnClickListener(this);
        helloAll = viewById(R.id.hello_all);
        helloAll.setOnClickListener(this);

        greeter = new Greeter();
    }

    @SuppressWarnings("unchecked")
    private <T> T viewById(int id)
    {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View v)
    {
        if (v == hello)
        {
            sayHello();
        }
        else if (v == helloAll)
        {
            sayHelloToAll();
        }
    }

    private void sayHelloToAll()
    {
        text.setText(greeter.greetAllFriends());
    }

    private void sayHello()
    {
        String greeting = greeter.sayHello(input.getText().toString());
        text.setText(greeting);
    }
}
