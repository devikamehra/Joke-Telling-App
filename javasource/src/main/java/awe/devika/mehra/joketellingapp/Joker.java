package awe.devika.mehra.joketellingapp;

import java.util.Random;

public class Joker {

    private Random random = new Random();

    public String getJoke(){
        String joke;
        switch (random.nextInt(7)) {
            case 0:
                joke = "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"";
                break;
            case 1:
                joke = "Q: How many prolog programmers does it take to change a lightbulb? A: Yes.";
                break;
            case 2:
                joke = "Why do Java developers wear glasses? Because they can't C#";
                break;
            case 3:
                joke = "A recent worldwide survey showed that out of 2,146,703,436 people, 94% were too lazy to actually read that number.";
                break;
            case 4:
                joke = "How come oysters never donate to charity? Because they are shellfish.";
                break;
            case 5:
                joke = "Whoever invented knock knock jokes should get a no bell prize.";
                break;
            default:
                joke = "This is a super awesome funny joke!!!D";
                break;
        }
        return joke;
    }
}
