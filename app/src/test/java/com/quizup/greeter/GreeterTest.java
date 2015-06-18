package com.quizup.greeter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import rx.Observer;
import rx.observers.TestObserver;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author siggijons
 */
@RunWith(MockitoJUnitRunner.class)
public class GreeterTest
{
    private Greeter greeter;
    private TestScheduler testScheduler;

    @Mock
    GreeterService greeterService;
    @Mock
    FriendService friendService;

    @Before
    public void setUp() throws Exception
    {
        testScheduler = Schedulers.test();
        greeter = new Greeter(greeterService, friendService, testScheduler);
    }

    @Test
    public void testSayHello() throws Exception
    {
        when(greeterService.sayHello("Droids")).thenReturn(Observable.just("Hello, Droids"));

        TestObserver<String> observer = new TestObserver<>();
        greeter.sayHello("Droids").subscribe(observer);

        verify(greeterService).sayHello("Droids");
        observer.assertTerminalEvent();
        observer.assertReceivedOnNext(Collections.singletonList("Hello, Droids"));
    }

    @Test
    public void testSayHelloToFriends() throws Exception
    {
        //noinspection unchecked
        when(greeterService.sayHello("Foo")).thenReturn(Observable.just("Hello, Foo"));
        when(greeterService.sayHello("Bar")).thenReturn(Observable.just("Hello, Bar"));

        when(friendService.getFriends()).thenReturn(Observable.just("Foo", "Bar"));

        TestObserver<String> observer = new TestObserver<>();
        greeter.greetAllFriends().subscribe(observer);

        observer.assertReceivedOnNext(Collections.<String>emptyList());

        verify(greeterService, never()).sayHello(anyString());

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        verify(greeterService).sayHello("Foo");
        observer.assertReceivedOnNext(Collections.singletonList(
                "Hello, Foo"
        ));

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        verify(greeterService).sayHello("Bar");
        observer.assertReceivedOnNext(Arrays.asList(
                "Hello, Foo",
                "Hello, Bar"
        ));


    }
}