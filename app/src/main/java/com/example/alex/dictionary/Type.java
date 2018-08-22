package com.example.alex.dictionary;
public class Type  {
   public String word;
   public String translate;
    Type(String word,String translate)
    {
        this.word=word;
        this.translate=translate;
    }
    @Override
    public String toString() {
        return word + "    "+translate;
    }


}
