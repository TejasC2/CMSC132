package blackjack;

import java.util.ArrayList;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {
	//private instance variables for BlackjackModel class
	private ArrayList<Card> dealerCards;
	private ArrayList<Card> playerCards;
	private Deck deck;
	//Initializes a copy of the dealer's cards
	public ArrayList<Card> getDealerCards() {
		ArrayList<Card> copy = new ArrayList<Card>(dealerCards);
		return copy;
	}
	//Initializes a copy of the player's cards
	public ArrayList<Card> getPlayerCards() {
		ArrayList<Card> copy = new ArrayList<Card>(playerCards);
		return copy;
	}
	//Sets the parameter cards to the dealer's cards
	public void setDealerCards(ArrayList<Card> cards) {
		this.dealerCards = new ArrayList<Card>();
		this.dealerCards = cards;
	}
	//Sets the parameter cards to the player's cards
	public void setPlayerCards(ArrayList<Card> cards) {
		this.playerCards = new ArrayList<Card>();
		this.playerCards = cards;
	}
	//Creates a new deck and shuffles it
	public void createAndShuffleDeck(Random random) {
		deck = new Deck();
		deck.shuffle(random);
	}
	//Makes the dealer card deck and adds two new cards
	public void initialDealerCards() {
		dealerCards = new ArrayList<Card>();
		dealerCards.add(deck.dealOneCard());
		dealerCards.add(deck.dealOneCard());
	}
	//Makes the player card deck and adds two new cards
	public void initialPlayerCards() {
		playerCards = new ArrayList<Card>();
		playerCards.add(deck.dealOneCard());
		playerCards.add(deck.dealOneCard());
	}
	//Adds one card to the player's deck
	public void playerTakeCard() {
		playerCards.add(deck.dealOneCard());
	}
	//Adds one card to the dealer's deck
	public void dealerTakeCard() {
		dealerCards.add(deck.dealOneCard());
	}
	//Returns an arraylist with the possible hand values of parameter hand
	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {
		ArrayList<Integer> newArr = new ArrayList<Integer>();
		int sum1 = 0;
		int sum2 = 0;
		boolean isAce = false;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getRank() != Rank.ACE) {
				sum1 += hand.get(i).getRank().getValue();
				sum2 += hand.get(i).getRank().getValue();
			} else {
				if (isAce == false) {
					sum1 += 1;
					sum2 += 11;
					isAce = true;
				} else {
					sum1 += 1;
					sum2 += 1;
				}
			}
		}
		if (sum1 <= 21 && sum1 != 0) {
			newArr.add(sum1);
		}
		if (sum2 <= 21 && sum2 != 0 && sum1 != sum2) {
			newArr.add(sum2);
		}
		if (sum1 > 21 && sum2 > 21) {
			boolean lesserSum = sum1 < sum2;
			if (lesserSum == true) {
				newArr.add(sum1);
			} else {
				newArr.add(sum2);
			}
		}
		return newArr;

	}
	//Assesses the hand and returns a HandAssessment
	public static HandAssessment assessHand(ArrayList<Card> hand) {
		ArrayList<Integer> tempArr = possibleHandValues(hand);
		if (hand.size() < 2) {
			return HandAssessment.INSUFFICIENT_CARDS;
		} else if (tempArr.get(tempArr.size() - 1) > 21) {
			return HandAssessment.BUST;
		} else if (hand.size() == 2 && tempArr.get(tempArr.size() - 1) == 21) {
			return HandAssessment.NATURAL_BLACKJACK;
		} else {
			return HandAssessment.NORMAL;
		}
	}
	//Returns a game assessment based on the dealer and players cards
	public GameResult gameAssessment() {
		HandAssessment player = assessHand(this.playerCards);
		HandAssessment dealer = assessHand(this.dealerCards);
		ArrayList<Integer> playerArr = possibleHandValues(this.playerCards);
		ArrayList<Integer> dealerArr = possibleHandValues(this.dealerCards);
		if (player == HandAssessment.NATURAL_BLACKJACK && dealer != 
				HandAssessment.NATURAL_BLACKJACK) {
			return GameResult.NATURAL_BLACKJACK;
		} else if (player == HandAssessment.NATURAL_BLACKJACK && dealer == 
				HandAssessment.NATURAL_BLACKJACK) {
			return GameResult.PUSH;
		} else {
			if (player == HandAssessment.BUST) {
				return GameResult.PLAYER_LOST;
			} else if (player != HandAssessment.BUST && dealer == 
					HandAssessment.BUST) {
				return GameResult.PLAYER_WON;
			} else {
				if (playerArr.get(playerArr.size() - 1) > 
				dealerArr.get(dealerArr.size() - 1)) {
					return GameResult.PLAYER_WON;
				} else if (playerArr.get(playerArr.size() - 1) < 
						dealerArr.get(dealerArr.size() - 1)) {
					return GameResult.PLAYER_LOST;
				} else {
					return GameResult.PUSH;
				}
			}
		}
	}
	//determines whether the dealer should take a card or not
	public boolean dealerShouldTakeCard() {
		ArrayList<Integer> tempArr = possibleHandValues(this.dealerCards);
		boolean isAce = false;
		boolean takeCard = false;
		for (int i = 0; i < dealerCards.size(); i++) {
			if (dealerCards.get(i).getRank() == Rank.ACE) {
				isAce = true;
			} else {
				isAce = false;
			}
		}
		if (isAce == false) {
			if (tempArr.get(tempArr.size() - 1) <= 16) {
				takeCard = true;
			} else if (tempArr.get(tempArr.size() - 1) >= 18) {
				takeCard = false;
			} else {
				takeCard = false;
			}
		} else {
			if (tempArr.get(tempArr.size() - 1) >= 18) {
				takeCard = false;
			} else if (tempArr.get(0) == 7 && tempArr.get(1) == 17) {
				takeCard = true;
			} else if (tempArr.get(tempArr.size() - 1) == 17) {
				takeCard = false;
			}
		}
		return takeCard;
	}
}