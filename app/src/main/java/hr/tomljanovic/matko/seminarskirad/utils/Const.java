package hr.tomljanovic.matko.seminarskirad.utils;

public final class Const {

    public static class Network {
        public static final String BASE_URL = "https://hacker-news.firebaseio.com/";
        public static final String GET_STORIES_URL = "v0/topstories.json?print=pretty";
        public static final String GET_STORY_ITEM = "v0/item/{id}.json?print=pretty";
    }
}
