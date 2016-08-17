package com.androjokes;

import java.util.Random;

public class Joker {
    Random rand = new Random();

    public String getAJoke() {
        return Jokes.jokeList.get(rand.nextInt(Jokes.jokeList.size()));
    }
}
