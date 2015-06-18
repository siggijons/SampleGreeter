package com.quizup.greeter;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

/**
 * @author siggijons
 */
public class DaggerFun
{

    private ObjectGraph applicationGraph;
    private ObjectGraph activityGraphA;
    private ObjectGraph activityGraphB;

    @Singleton
    public static class AppSingleton
    {
        public final Integer i;

        @Inject
        public AppSingleton(Integer i)
        {
            this.i = i;
        }
    }

    @Singleton
    public static class ActivitySingleton
    {
        public final String s;

        @Inject
        public ActivitySingleton(String s)
        {
            this.s = s;
        }
    }

    public static class NotASingleton
    {
        public final String s;
        public final Integer i;

        @Inject
        public NotASingleton(String s, Integer i)
        {
            this.s = s;
            this.i = i;
        }
    }

    public static class Target
    {
        @Inject
        AppSingleton appSingleton;
        @Inject
        ActivitySingleton activitySingleton;
        @Inject
        NotASingleton notASingleton;
    }

    @Module(library = true, injects = AppSingleton.class)
    public static class ApplicationModule
    {
        @Provides
        Integer provideInt()
        {
            return 42;
        }
    }

    @Module(addsTo = ApplicationModule.class, injects = Target.class)
    public static class ActivityModule
    {
        @Provides
        public String provideS()
        {
            return "String";
        }
    }

    @Before
    public void setUp() throws Exception
    {
        applicationGraph = ObjectGraph.create(new ApplicationModule());
        activityGraphA = applicationGraph.plus(new ActivityModule());
        activityGraphB = applicationGraph.plus(new ActivityModule());
    }

    @Test
    public void testSingletons() throws Exception
    {
        Target targetA = new Target();
        Target targetB = new Target();
        Target targetC = new Target();

        activityGraphA.inject(targetA);
        activityGraphB.inject(targetB);
        activityGraphB.inject(targetC);

        assertSame(targetA.appSingleton, targetB.appSingleton);
        assertNotSame(targetA.activitySingleton, targetB.activitySingleton);

        assertSame(targetB.activitySingleton, targetC.activitySingleton);
        assertSame(targetB.appSingleton, targetC.appSingleton);
        assertNotSame(targetB.notASingleton, targetC.notASingleton);

    }
}
