package com.quizup.greeter;

import rx.Observable;

/**
 * @author siggijons
 */
public class DroidFriendService implements FriendService
{
    @Override
    public Observable<String> getFriends()
    {
        return Observable.just(
                "C-3PO",
                "R2-D2",
                "IG-88",
                "4LOM"
        );
    }
}
