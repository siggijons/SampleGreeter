package com.quizup.greeter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import dagger.ObjectGraph;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

import javax.inject.Inject;


public class MainActivity extends Activity implements View.OnClickListener
{

    private TextView text;
    private EditText input;
    private Button hello;
    private Button helloAll;

    @Inject
    Greeter greeter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ObjectGraph.create(new GreeterModule()).inject(this);

        setContentView(R.layout.activity_main);

        text = viewById(R.id.text);
        input = viewById(R.id.input);
        hello = viewById(R.id.hello);
        hello.setOnClickListener(this);
        helloAll = viewById(R.id.hello_all);
        helloAll.setOnClickListener(this);
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
        text.setText(null);
        greeter.greetAllFriends()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>()
                {
                    @Override
                    public void onCompleted()
                    {
                        text.append("onCompleted");

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        text.append("onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String s)
                    {
                        Log.i("SJ", "onNext: " + s);
                        text.append(s + "\n");
                    }
                });
    }

    private void sayHello()
    {
        text.setText(null);
        Observable.just(input.getText().toString()).flatMap(new Func1<String, Observable<String>>()
        {
            @Override
            public Observable<String> call(String s)
            {
                return greeter.sayHello(s);
            }
        }).subscribe(new Action1<String>()
        {
            @Override
            public void call(String s)
            {
                text.append(s);
            }
        }, new Action1<Throwable>()
        {
            @Override
            public void call(Throwable e)
            {
                text.setText("Error: " + e);
            }
        });
    }
}
