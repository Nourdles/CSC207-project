package data_access;
import entity.Book;
import use_case.booksearch.BookSearchDataAccessInterface;

import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class OpenLibraryDB implements BookSearchDataAccessInterface {
    private final OkHttpClient client = new OkHttpClient();


    public ArrayList<Book> getSearchResult(String searchQuery) {
        String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
        String url = "https://openlibrary.org/search.json?q=" + encodedQuery;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            if (response.body() == null) {
                throw new IOException("Response body is null");
            }

            String responseBodyString = response.body().string();
            JSONObject responseBody = new JSONObject(responseBodyString);
            JSONArray docsArray = responseBody.getJSONArray("docs");

            ArrayList<Book> books = new ArrayList<>();

            for (int i = 0; i < docsArray.length(); i++) {
                JSONObject bookJson = docsArray.getJSONObject(i);
                String title = bookJson.optString("title");
                int year = bookJson.optInt("first_publish_year", -1);
                String author = bookJson.optJSONArray("author_name") != null
                        ? bookJson.optJSONArray("author_name").getString(0)
                        : "Unknown";
                // Summaries will be fetched later, as needed
                String isbn = bookJson.optJSONArray("isbn") != null
                        ? bookJson.optJSONArray("isbn").getString(0)
                        : "Unknown";
                int inStock = 0;  // Placeholder, update as per your application logic
                String coverId = bookJson.optString("cover_i");
                String coverUrl = coverId.isEmpty() ? null : "https://covers.openlibrary.org/b/id/" + coverId + "-L.jpg";

                Book book = new Book(title, year, author, "", isbn, inStock, coverUrl);
                books.add(book);
            }

            return books;

        } catch (IOException | JSONException e) {
            // Enhanced error handling
            e.printStackTrace();
            System.err.println("Error fetching or parsing the book search results: " + e.getMessage());
            // Depending on your application's design, you might want to return an empty list or throw a custom exception
            return new ArrayList<>();
        }
    }

}
