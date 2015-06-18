package com.quizup.greeter;

import rx.Observable;

/**
 * @author siggijons
 */
public interface GreeterService
{
    Observable<String> sayHello(String name);
}
