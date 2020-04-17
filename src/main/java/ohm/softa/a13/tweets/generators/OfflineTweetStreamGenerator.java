package ohm.softa.a13.tweets.generators;

import com.google.gson.Gson;
import ohm.softa.a13.model.Tweet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.stream.Stream;

public class OfflineTweetStreamGenerator implements TweetStreamGenerator {

	Gson gson;

	public OfflineTweetStreamGenerator() {
		gson = new Gson();
	}

	@Override
	public Stream<Tweet> getTweetStream() {
		Tweet[] tweets;
		try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/trump_tweets.json"));) {
			tweets = gson.fromJson(reader, Tweet[].class);
			return Arrays.stream(tweets);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Stream.of();
	}
}
