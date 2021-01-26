package me.hopedev.topggwebhooks.enums;


/**
 * OPTIONS<br>
 * <p>
 * UPVOTE: Genuine vote through the top.gg bot page<br>
 * TEST: Test vote done through the test button of your webhook settings<br>
 * INVALID: anything else (incase of errors or ignoring validation
 */
public enum VotingType {
    UPVOTE,
    TEST,
    INVALID
}
