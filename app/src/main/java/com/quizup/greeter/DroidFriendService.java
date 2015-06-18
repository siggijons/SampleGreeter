package com.quizup.greeter;

import java.util.Arrays;
import java.util.List;

/**
 * @author siggijons
 */
public class DroidFriendService implements FriendService
{
    @Override
    public List<String> getFriends()
    {
        return Arrays.asList(
                "C-3PO",
                "R2-D2",
                "IG-88",
                "4LOM"
        );
    }
}
