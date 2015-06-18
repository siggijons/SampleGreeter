package com.quizup.greeter;

/**
 * @author siggijons
 */
public class SyncGreeterService implements GreeterService
{
    @Override
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }
}
