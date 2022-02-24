package com.example.quotes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;


public class MainViewModel extends ViewModel {
    private Context context;
    private int index = 0;
    private QuoteModel[] quote;
    private  int size = 0;

    public MainViewModel(Context context) {
        this.context = context;
        String json1 = loadQuotes();
        Gson gson = new Gson();
        if(json1 != null){
             quote = gson.fromJson(json1, QuoteModel[].class);
             size = quote.length;
        }
        else{
            quote = new QuoteModel[]{new QuoteModel("Genius is one  inspiration and ninety-nine percent perspiration. ", "Bruce Wayne"),
                    new QuoteModel("Here is one quality that one must possess to win, and that is definiteness of purpose, the knowledge of what one wants, and a burning desire to possess it. ", "Napoleon Hill")};

        }
    }

    public String loadQuotes() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("quotes.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ioException) {
            Log.d("fuck off", "motherfucker");
            return null;
        }

        return json;

    }

    public QuoteModel getQuote() {
        return quote[index];
    }

    public QuoteModel nextQuote() {
        if (index >= size - 1) {
            Toast.makeText(context, "No more quotes", Toast.LENGTH_SHORT).show();
            return quote[index];
        }


        return quote[++index];

    }

    public QuoteModel prevQuote() {
        if (index <= 0) {
            Toast.makeText(context, "This is the first quote", Toast.LENGTH_SHORT).show();
            return quote[index];
        }
        return quote[--index];
    }
}
