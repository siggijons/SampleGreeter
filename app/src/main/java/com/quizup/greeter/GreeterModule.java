package com.quizup.greeter;

import dagger.Module;
import dagger.Provides;

/**
 * @author siggijons
 */
@Module(injects = MainActivity.class)
public class GreeterModule
{
    @Provides
    Greeter provideGreeter(GreeterService greeterService, FriendService friendService)
    {
        return new Greeter(greeterService, friendService);
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

}
