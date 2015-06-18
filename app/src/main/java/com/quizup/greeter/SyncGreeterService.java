package com.quizup.greeter;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author siggijons
 */
public class SyncGreeterService implements GreeterService
{
    @Override
    public Observable<String> sayHello(String name)
    {
        return Observable.just(name).map(new Func1<String, String>()
        {
            @Override
            public String call(String s)
            {
                return "Hello, " + s;
            }
        });
    }
}
