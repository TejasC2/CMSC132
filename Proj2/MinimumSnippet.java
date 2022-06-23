import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;

public class MinimumSnippet {
	private int snipFront;
	private int snipEnd;
	private int minSnipSize;
	private ArrayList<String> minSnip;
	private ArrayList<String> convDoc;
	private Iterable<String> tempDoc;
	private List<String> tempTerms;

	//Compute minimum snippet in document with all terms
	public MinimumSnippet(Iterable<String> document, List<String> terms) {
		this.minSnip = new ArrayList<String>();
		this.tempDoc = document;
		this.tempTerms = terms;
		this.minSnipSize = terms.size();
		ArrayList<String> tempSnip = new ArrayList<String>();
		createArr(this.tempDoc);

		if (terms.isEmpty()) {
			throw new IllegalArgumentException("No terms were entered.");
		} else {
			boolean x = false;
			int currentPos = 0;
			if (foundAllTerms() == true) {
				while ((tempSnip.size() != this.minSnipSize)) {
					for (int i = currentPos; i < this.convDoc.size(); i++) {
						if (tempSnip.contains(terms) == false) {
							for (String s : terms) {
								if (s.equals(this.convDoc.get(i)) && x == false) {
									x = true;
								}
								if (x == true && tempSnip.contains(terms) == false) {
									tempSnip.add(this.convDoc.get(i));
									if (tempSnip.size() == 1) {
										this.snipFront = i;
									}
									if (tempSnip.size() == this.minSnipSize && tempSnip.containsAll(terms) == true) {
										this.snipEnd = i;
									}
									if (tempSnip.containsAll(terms) == true) {
										x = false;
									}

									break;
								}
							}
						}
					}
					if (this.minSnipSize == tempSnip.size() && tempSnip.containsAll(terms) == true) {
						this.minSnip = tempSnip;

					} else {
						tempSnip = new ArrayList<String>(0);
						currentPos += 1;
					}
					if (currentPos == this.convDoc.size()) {
						currentPos = 0;
						this.minSnipSize += 1;
					}
				}
			}
		}

	}
	//converts iterable document to array for checking whether elements are in
	//the array
	private void createArr(Iterable<String> doc) {
		ArrayList<String> conv = new ArrayList<String>();
		for (String s : doc) {
			conv.add(s);
		}
		this.convDoc = conv;
	}
	//returns whether all terms are in document
	public boolean foundAllTerms() {
		if (this.convDoc.containsAll(this.tempTerms) == true) {
			return true;
		} else {
			return false;
		}
	}
	//returns where snippet starts in original document
	public int getStartingPos() {
		return this.snipFront;
	}
	//returns where snippet ends in original document
	public int getEndingPos() {
		return this.snipEnd;
	}
	//returns length of snippet
	public int getLength() {
		return this.minSnip.size();
	}
	//returns position in snippet of index in original document 
	public int getPos(int posIndex) {
		int currPos = 0;
		for (int i = this.snipFront; i <= this.snipEnd; i++) {
			if (this.tempTerms.get(posIndex).equals(this.convDoc.get(i))) {
				currPos = i;
			}
		}
		return currPos;
	}

}
