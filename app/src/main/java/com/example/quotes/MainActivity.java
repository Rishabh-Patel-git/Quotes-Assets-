package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mNext, mPrev;
    private FloatingActionButton mShare;
    private TextView mQuoteTextView, mAuthorTextView;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainViewModel = new ViewModelProvider
                (this, new MainViewModelFactory(getApplicationContext()))
                .get(MainViewModel.class);


        mQuoteTextView = findViewById(R.id.quote_text);
        mAuthorTextView = findViewById(R.id.author_text);
        QuoteModel currentQuote = mainViewModel.getQuote();
        setQuote(currentQuote);

        mNext = findViewById(R.id.nxt_button);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuote(mainViewModel.nextQuote());
            }
        });
        mPrev = findViewById(R.id.prev_button);
        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuote(mainViewModel.prevQuote());
            }
        });

        mShare = findViewById(R.id.share_button);
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text);
                startActivity(i);
            }
        });
    }

    public void setQuote(QuoteModel currentQuote) {
        mQuoteTextView.setText(currentQuote.text);
        mAuthorTextView.setText(currentQuote.author);
    }


}