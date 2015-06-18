package com.quizup.greeter;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.functions.Func2;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author siggijons
 */
public class Greeter
{
    private final GreeterService greeterService;
    private final FriendService friendService;
    private final Scheduler scheduler;

    public Greeter(GreeterService greeterService, FriendService friendService, Scheduler scheduler)
    {
        this.greeterService = greeterService;
        this.friendService = friendService;
        this.scheduler = scheduler;
    }

    public Observable<String> sayHello(String name)
    {
        return greeterService.sayHello(name);
    }

    protected Observable<String> sayHello(final List<String> friends)
    {
        return Observable.from(friends).flatMap(new Func1<String, Observable<String>>()
        {
            @Override
            public Observable<String> call(String friend)
            {
                return sayHello(friend);
            }
        });
    }

    public Observable<String> greetAllFriends()
    {
        return Observable.zip(
                Observable.interval(1, TimeUnit.SECONDS, scheduler),
                friendService.getFriends(),
                new Func2<Long, String, String>()
                {
                    @Override
                    public String call(Long aLong, String s)
                    {
                        return s;
                    }
                }
        ).flatMap(new Func1<String, Observable<String>>()
        {
            @Override
            public Observable<String> call(String s)
            {
                return sayHello(s);
            }
        });
    }

}
