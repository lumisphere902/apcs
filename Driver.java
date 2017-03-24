//Timothy Poon P1 12/4/2016
//2 hour 30 minutes (total)
//Overall, this lab was fairly straightforward. I
//followed the instructions, and the end product
//was mostly as I expected. One aspect that somewhat
//confused was the constructor comments. To accomodate
//for that, I implemented dummy variables to represent
//each card's suit. Another problem I had was using
//the wrong iterating variable to generate cards, but
//that was easily fixed.

//Second Reflection:
//Overall the game logic was considerably more complex than
//the starting classes. However, with the help of the assignment
//sheet, it became fairly apparent how the programming was supposed
//to work. The biggest problem I had was off by one errors, which
//caused many problems with the last card in a stack. However, other
//than that, the hardest function to think of was the makeMove function.
public class Driver
{
    public static void main(String[] args) {
        new SpiderSolitaire().play();
        
    }
}
