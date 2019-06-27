package ohm.softa.a13.tweets.generators;

import ohm.softa.a13.model.Tweet;

import java.util.stream.Stream;

/**
 * Generator for a Stream of tweets
 * @author Peter Kurfer
 */
public interface TweetStreamGenerator {

    /**
     * Get a new Stream of tweets
     * @return Stream of tweets - length may be unknown
     */
    Stream<Tweet> getTweetStream();
}
