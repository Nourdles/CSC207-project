package data_access;
import entity.Book;
import use_case.book_search.BookSearchDataAccessInterface;

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

    /**
     * Returns an array of Books corresponding to the search results from the search query through a call to the Open
     * Library API
     * @param searchQuery a String that can correspond to an author, title, ISBN, etc
     * @return An array of Books corresponding to the search result
     */
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

                String isbn = bookJson.optJSONArray("isbn") != null
                        ? bookJson.optJSONArray("isbn").getString(0)
                        : "Unknown";
                int inStock = 0;
                String coverId = bookJson.optString("cover_i");
                String coverUrl = coverId.isEmpty() ? null : "https://covers.openlibrary.org/b/id/" + coverId + "-L.jpg";
                String language = bookJson.optString("language", "Unknown");
                ArrayList<String> subjects = new ArrayList<>();
                JSONArray subjectsArray = bookJson.optJSONArray("subject");
                if (subjectsArray != null) {
                    for (int j = 0; j < subjectsArray.length(); j++) {
                        subjects.add(subjectsArray.optString(j));
                    }
                }

                Book book = new Book(title, year, author, "", isbn, inStock, coverUrl, language, subjects);
                books.add(book);
            }

            return books;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            System.err.println("Error fetching or parsing the book search results: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
