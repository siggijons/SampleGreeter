package com.quizup.greeter;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * @author siggijons
 */
@Module(injects = MainActivity.class)
public class GreeterModule
{
    @Provides
    Greeter provideGreeter(GreeterService greeterService, FriendService friendService, Scheduler scheduler)
    {
        return new Greeter(greeterService, friendService, scheduler);
    }

    @Provides
    GreeterService provideGreeterService()
    {
        return new SyncGreeterService();
    }

    @Provides
    FriendService provideFriendService()
    {
        return new DroidFriendService();
    }

    @Provides
    Scheduler provideComputationScheduler()
    {
        return Schedulers.computation();
    }

}
