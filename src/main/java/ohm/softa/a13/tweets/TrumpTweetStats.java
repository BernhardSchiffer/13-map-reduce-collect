package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Peter Kurfer
 */
public class TrumpTweetStats {

    public static Map<String, Long> calculateSourceAppStats(Stream<Tweet> tweetStream) {
        /* group the tweets by the `sourceApp` they were created with and count how many it were per `sourceApp` */
		return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.counting()));
    }

    public static Map<String, Set<Tweet>> calculateTweetsBySourceApp(Stream<Tweet> tweetStream) {
        /* group the tweets by the `sourceApp`
         * collect the tweets in `Set`s for each source app */
		return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.toSet()));
    }

    public static Map<String, Integer> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords) {
        /* Remark: implement this method at last */
        /* split the tweets, lower them, trim them, remove all words that are in the `stopWords`,
         * reduce the result to a Map<String, Integer> to count how often each word were in the tweets
         * optionally you could filter for all words that were used more than 10 times */
        return tweetStream.map(t -> t.getText().toLowerCase())
			.map(s -> {
				String[] split = s.split("( )+");
				return split;
			})
			.flatMap(sa -> Arrays.stream(sa))
			.filter(w -> stopWords.stream().noneMatch(sw -> sw.equals(w)))
			.reduce(new HashMap<String, Integer>(),
				(acc, w) -> {
					if(acc.containsKey(w))
						acc.put(w, acc.get(w) + 1);
					else
						acc.put(w, 1);

					return acc;
				},
				(m1, m2) -> {
					m1.forEach((k, v) -> m2.merge(k, v, (v1, v2) -> v1 + v2));
					return m1;
				})
			.entrySet()
			.stream()
			.filter(e -> e.getValue() > 10)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
