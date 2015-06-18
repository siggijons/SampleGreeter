package com.quizup.greeter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author siggijons
 */
@RunWith(MockitoJUnitRunner.class)
public class GreeterTest
{
    private Greeter greeter;

    @Mock
    GreeterService greeterService;
    @Mock
    FriendService friendService;

    @Before
    public void setUp() throws Exception
    {
        greeter = new Greeter(greeterService, friendService);
    }

    @Test
    public void testSayHello() throws Exception
    {
        when(greeterService.sayHello("Droids")).thenReturn("Hello, Droids");
        assertEquals("Hello, Droids", greeter.sayHello("Droids"));
        verify(greeterService).sayHello("Droids");
    }

    @Test
    public void testSayHelloToFriends() throws Exception
    {
        when(greeterService.sayHello(anyString())).thenReturn("Hello, Foo", "Hello, Bar");
        when(friendService.getFriends()).thenReturn(Arrays.asList("Foo", "Bar"));

        assertEquals("Hello, Foo\nHello, Bar", greeter.greetAllFriends());
    }
}