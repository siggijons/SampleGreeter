package com.quizup.greeter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author siggijons
 */
public class Greeter
{
    public String sayHello(String name)
    {
        return "Hello, " + name;
    }

    public String greetAllFriends()
    {
        StringBuilder sb = new StringBuilder();
        for (Iterator<String> iterator = sayHello(getFriends()).iterator(); iterator.hasNext(); )
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

    protected List<String> sayHello(List<String> friends)
    {
        List<String> result = new ArrayList<>(friends.size());

        for (String friend : friends)
        {
            result.add(sayHello(friend));
        }

        return result;
    }

    protected List<String> getFriends()
    {
        return Arrays.asList(
                "C-3PO",
                "R2-D2",
                "IG-88",
                "4LOM"
        );
    }

}
