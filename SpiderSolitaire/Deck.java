import java.util.ArrayList;
import java.util.Arrays;

public class Deck
{
	private ArrayList<Card> deck;
    public Deck() {
    	deck = new ArrayList<Card>();
    }
    public void shuffle() {
    	ArrayList<Card> newOrder = new ArrayList<Card>();
    	while (deck.size() > 1) {
    		int num = (int) (Math.random() * deck.size());
    		newOrder.add(deck.get(num));
    		fastRemove(deck, num);
    	}
    	newOrder.add(deck.get(0));
    	deck = newOrder;
    }
    public Card draw() {
    	int num = (int) (Math.random() * deck.size());
    	Card ans = deck.get(num);
    	fastRemove(deck, num);
    	return ans;
    }
    public void addCard(Card c) {
    	deck.add(c);
    }
    public void fastRemove(ArrayList<Card> list, int index) {
    	Card temp = list.remove(list.size() - 1);
    	if (index < list.size()) {
    		list.set(index, temp);
    	}
    }
    public int size() {
    	return deck.size();
    }
    public ArrayList<Card> getDeck() {
		return deck;
	}
    public boolean isEmpty() {
    	return deck.size() == 0;
    }
    public void sort() {
    	for (int i = deck.size() - 1; i >= 0; i--) {
			Card max = deck.get(0);
			int maxIndex = 0;
			for (int j = 1; j < i; j++) {
				if (deck.get(j).compareTo(max) > 0) {
					max = deck.get(j);
					maxIndex = j;
				}
			}
			if (maxIndex != i) {
				swap(maxIndex, i);
			}
//			System.out.println(deck);
		}
    }
	private void swap(int a, int b) {
		Card temp = deck.get(a);
		deck.set(a, deck.get(b));
		deck.set(b, temp);
	}
	@Override
    public String toString() {
    	String str = "[";
    	for (int i = 0; i < deck.size(); i++) {
    		str += deck.get(i).toString()+(i < deck.size() - 1 ? " ": "");
    	}
    	str += "]";
    	return str;
    }
}
