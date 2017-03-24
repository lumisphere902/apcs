import java.util.ArrayList;
import java.util.List;

public class Board
{   
	private Deck drawpile;
	private Deck[] stacks;
	private ArrayList<Card> completed;

	public Board(int numStacks, int numDecks, int numSuits) {
		drawpile = new Deck();
		stacks = new Deck[numStacks];
		completed = new ArrayList<Card>();
		for (int i = 0; i < numStacks; i++) {
			stacks[i] = new Deck();
		}
		for (int i = 0; i < numDecks; i++) {
			for (int j = 0; j < numSuits; j++) {
				for (int k = 0; k < 13; k++) {
					String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
					int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
					drawpile.addCard(new Card(symbols[12 - k], values[12 - k]));
				}
			}
		}
		for (int i = 0, ii = (int) (drawpile.size() / 2); i < ii; i++) {
			stacks[i%numStacks].addCard(drawpile.draw());
		}
		for (int i = 0; i < numStacks; i++) {
			if (stacks[i].getDeck().size() > 0) {
				stacks[i].getDeck().get(stacks[i].getDeck().size() - 1).setFaceUp(true);
			}
		}
	}

	public void makeMove(String symbol, int src, int dest) {
		if (symbol == "X") {
			System.out.println("INVALID MOVE: Card is face down");
			return;
		}
		for (int i = stacks[src].size() - 1; i >= 0; i--) {
			if (stacks[src].getDeck().get(i).getSymbol().equals(symbol)) {
				if (dest < 0 || dest >= stacks.length || src < 0 || src >= stacks.length) {
					System.out.println("INVALID INPUT");
					return;
				}
				if (validRun(stacks[src].getDeck(), i) && (stacks[dest].isEmpty()
						||stacks[dest].getDeck().get(stacks[dest].size() - 1).getValue() 
						== stacks[src].getDeck().get(i).getValue() + 1)) {
					List<Card> view = stacks[src].getDeck().subList(i, stacks[src].getDeck().size());
					stacks[dest].getDeck().addAll(view);
					view.clear();
					if (stacks[src].getDeck().size() > 0) {
						stacks[src].getDeck().get(stacks[src].getDeck().size() - 1).setFaceUp(true);
					}
					//System.out.println("VALID MOVE: Complete run");
					break;
				}
				else {
					System.out.println("INVALID MOVE: Incomplete run");
				}
			}
		}
	}

	private boolean validRun(ArrayList<Card> deck, int startIndex) {
		int lastValue = deck.get(startIndex).getValue();
		for (int i = startIndex + 1; i < deck.size(); i++) {
			if (deck.get(i).getValue() != --lastValue) {
				return false;
			}
		}
		return true;
	}
	
	public void drawCards() {
		for (int i = 0; i < stacks.length; i++) {
			if (drawpile.isEmpty()) {
				System.out.println("INVALID MOVE: Cannot draw with empty stacks");
				return;
			}
		}
		for (int i = 0; i < stacks.length && !drawpile.isEmpty(); i++) {
			Card newCard = drawpile.draw();
			newCard.setFaceUp(true);
			stacks[i].addCard(newCard);
		}
	}

	/**
	 *  Returns true if all stacks and the draw pile are all empty
	 */ 
	public boolean isEmpty() {
		for (int i = 0; i < stacks.length; i++) {
			if (!stacks[i].isEmpty()) {
				return false;
			}
		}
		if (!drawpile.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 *  If there is a run of A through K starting at the end of sourceStack
	 *  then the run is removed from the game or placed into a completed
	 *  stacks area.
	 *  
	 *  If there is not a run of A through K starting at the end of sourceStack
	 *  then an invalid move message is displayed and the Board is not changed.
	 */
	public void clear(int sourceStack) {
		ArrayList<Card> deck = stacks[sourceStack].getDeck();
		int lastValue = 0;
		for (int i = deck.size() - 1; i >= 0 && lastValue < 13 ; i--) {
			if (deck.get(i).getValue() != ++lastValue) {
				System.out.println("INVALID MOVE: Cannot clear incomplete stack");
				return;
			}
		}
		if (lastValue < 13) {
			System.out.println("INVALID MOVE: Cannot clear incomplete stack");
			return;
		}
		deck.subList(deck.size() - 12, deck.size()).clear();
		completed.add(deck.remove(deck.size() - 1));
	}

	/**
	 * Prints the board to the terminal window by displaying the stacks, draw
	 * pile, and done stacks (if you chose to have them)
	 */
	public void printBoard() {
		System.out.println("Stacks:");
		for (int i = 0; i < stacks.length; i++) {
			System.out.print((i + 1) + ": ");
			System.out.println(stacks[i]);
		}
		System.out.println("\n Deck:");
		System.out.println(drawpile);
	}
}
