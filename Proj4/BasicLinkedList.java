package listClass;

import java.util.Iterator;
import java.util.*;
public class BasicLinkedList<T> implements Iterable<T> {

	private static class Node<T>{ //Private Static Node Class
		private T data;
		private Node<T> next;
		private Node(T element) { // Node Constructor
			next = null;
			this.data = element;
		}
	}
	private Node<T> head; //Head element
	private Node<T> tail; //Tail element
	private int listSize; //List Size
	public BasicLinkedList() { //Default list constructor
		head = null;
		tail = null;
		listSize = 0;
	}
	
	public int getSize() {	// returns size of list
		return listSize;
	}
	public BasicLinkedList<T> addToEnd(T data){ // Adds element to end of list
		Node<T> n = new Node<T>(data);
		if(listSize == 0) {
			head = n;
			tail = n;
		}else if(listSize ==1) {
			head.next = n;
			tail = n;
		}
		else {
			tail.next = n;
			tail = n;
		}
		listSize++; //Increments list size
		return this;
		
	}
	public BasicLinkedList<T> addToFront(T data){//Adds element to front of list
		Node<T> n = new Node<T>(data);
		    if(listSize == 0){
		        head = n;
		    	tail = n;
		    }else {
		    	n.next = head;
		    	head = n;
		    }
		    listSize++; //Increments list size
		    return this;
		}
	public T getFirst() { //Returns first element in list
		if(listSize == 0) {
			return null;
		}else {
			return head.data;
		}
	}
	public T getLast() {//Returns last element in list
		if(listSize == 0) {
			return null;
		}else {
			return tail.data;
		}
	}
	public T retrieveFirstElement() {
		//Returns first element and removes from list
		Node<T> node  = head;
		if(listSize == 0) {
			return null;
		}else {
			head = head.next;
			listSize--;
			return node.data;
		}
		
	}
	public T retrieveLastElement() {
		//Returns last element and removes it from list
		
		//Initialize nodes to iterate through list
		Node<T> currNode = head;
		Node<T> prev = null;
		if(listSize == 0) {
			//If list is empty, return null
			return null;
		}else if(listSize == 1) {
		//if list is of size 1, set currNode to head and remove head from list
			if(head.next == null) {
				currNode = head;
				head = null;
			}
		}else {
		//Else, iterate through list until tail and remove tail
			while(currNode.next != null) {
				prev = currNode;
				currNode = currNode.next;
			}
			tail = prev;
			tail.next = null;
		}
		//Decrement size and return data of last element 
		listSize--;
		return currNode.data;
	}
	public BasicLinkedList<T> removeAllInstances(T targetData){
		//Removes all instances of targetData from list
		Node<T> temp = head;
		while(temp.next != null) {
			if(temp.next.data == targetData) {
				temp.next = temp.next.next;
				listSize--;
			}else {
				temp = temp.next;
			}
		}
		if(head.data == targetData) {
			head = head.next;
			listSize--;
		}
		return this;
		
	}
	@Override
	public Iterator<T> iterator() {
		//Iterator to loop through the linked list
		return new Iterator<T>() {
            Node<T> tempNode = head;
            @Override
            public boolean hasNext() {
                return tempNode != null;
            }

            @Override
            public T next() {
                if(hasNext() == true){
                    T data = tempNode.data;
                    tempNode = tempNode.next;
                    return data;
                }else {
                	throw new NoSuchElementException();
                }
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException("Method Not Implemented.");
            }

        };
	}
}
