package com.quizup.greeter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author siggijons
 */
public class Greeter
{
    private final GreeterService greeterService;
    private final FriendService friendService;

    public Greeter(GreeterService greeterService, FriendService friendService)
    {
        this.greeterService = greeterService;
        this.friendService = friendService;
    }

    public String sayHello(String name)
    {
        return greeterService.sayHello(name);
    }

    protected List<String> sayHello(List<String> friends)
    {
        List<String> result = new ArrayList<>(friends.size());

        for (String friend : friends)
        {
            result.add(sayHello(friend));
        }

        return result;
    }

    public String greetAllFriends()
    {
        StringBuilder sb = new StringBuilder();
        List<String> friends = friendService.getFriends();
        for (Iterator<String> iterator = sayHello(friends).iterator(); iterator.hasNext(); )
        {
            String s = iterator.next();
            sb.append(s);

            if (iterator.hasNext())
            {
                sb.append("\n");
            }
        }

        return sb.toString();

    }

}
