package src.data_access;

import okhttp3.*;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class OpenLibraryTest {


    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        Request getRequest = new Request.Builder()
                .url("https://openlibrary.org/api/books?bibkeys=ISBN:9781789543537&jscmd=data&format=json")
                .build();

        try {
            Response response = client.newCall(getRequest).execute();
            JsonElement jsonElement = JsonParser.parseString(response.body().string());
            String prettyJson = gson.toJson(jsonElement);
            System.out.println(prettyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
