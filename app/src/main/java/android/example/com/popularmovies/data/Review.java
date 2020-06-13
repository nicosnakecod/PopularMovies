package android.example.com.popularmovies.data;

public class Review {

    private final String id;
    private final String author;
    private final String content;
    private final String url;

    public Review(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String toString(){
        return String.format("Review id: %s, Url: %s, Author: %s \nContent: %s",
                id, url, author, content);
    }
}
