package com.quizup.greeter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author siggijons
 */
public class GreeterTest
{
    private Greeter greeter;

    @Before
    public void setUp() throws Exception
    {
        greeter = new Greeter();
    }

    @Test
    public void testSayHello() throws Exception
    {
        assertEquals("Hello, Droids", greeter.sayHello("Droids"));
    }

    @Test
    public void testSayHelloToFriends() throws Exception
    {
        assertEquals(Arrays.asList(
                "Hello, Foo",
                "Hello, Bar"
        ), greeter.sayHello(Arrays.asList("Foo", "Bar")));
    }
}