package com.quizup.greeter;

import rx.Observable;

/**
 * @author siggijons
 */
public interface FriendService
{
    Observable<String> getFriends();
}
