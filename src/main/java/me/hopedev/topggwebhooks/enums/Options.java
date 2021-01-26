package me.hopedev.topggwebhooks.enums;


/**
 * OPTIONS<br>
 * <p>
 * IGNORE_VALIDATION: Ignore if the data is valid or not (correct json)<br>
 * IGNORE_AUTHORIZATION: Ignore if the Authorization header either either not given or invalid/incorrect<br>
 * ONLY_LISTEN_FOR_TEST_VOTES: Ignores every voting type, doesn't matter if valid or not, only listens for valid TEST votes, which might come in handy for debugging! (see top.gg docs)
 */
public enum Options {
    IGNORE_VALIDATION,
    IGNORE_AUTHORIZATION,
    ONLY_LISTEN_FOR_TEST_VOTES
}
