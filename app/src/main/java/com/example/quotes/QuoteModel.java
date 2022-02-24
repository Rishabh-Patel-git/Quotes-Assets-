package com.example.quotes;

import com.google.gson.annotations.SerializedName;

public class QuoteModel {
   @SerializedName("author")
    public String author;
   @SerializedName("text")
    public String text;

    public QuoteModel(String text,String author){
        this.author = author;
        this.text = text ;
    }




}
