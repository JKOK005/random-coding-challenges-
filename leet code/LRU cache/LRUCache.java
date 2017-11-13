import java.io.*;
import java.lang.*;
import java.util.*;

public class LRUCache<K, V>{
	private HashMap<K, NodeWrapper<K, V>> keyValueMap;
	private int capacity;
	private NodeWrapper<K, V> head;
	private NodeWrapper<K, V> tail;

	private void bridgePrevWithNext(NodeWrapper<K, V> node){
		if(node.getPrev() != null){
			node.getPrev().setNext(node.getNext());
		}

		if(node.getNext() != null){
			node.getNext().setPrev(node.getPrev());
		}
	}

	private void setHeadAndTail(NodeWrapper<K, V> node){
		if(this.head != null){
			node.setNext(this.head);
			this.head.setPrev(node);
		}
		this.head = node;

		if(this.tail == null){
			this.tail = node;
		}
	}

	private void updateToLatest(NodeWrapper<K, V> node){
		this.bridgePrevWithNext(node);
		this.setHeadAndTail(node);
	}

	public LRUCache(int cap){
		this.keyValueMap 	= new HashMap<K, NodeWrapper<K, V>>();
		this.head 			= null;
		this.tail 			= null;
		this.capacity 		= cap;
	}

	public boolean isFull(){
		if(this.keyValueMap.size() > this.capacity) return true;
		return false;
	}

	public V get(K key){
		if(!this.keyValueMap.containsKey(key)) return null;
		NodeWrapper<K, V> node = this.keyValueMap.get(key);
		this.updateToLatest(node);
		return node.getVal();
	}

	public void put(K key, V val){
		NodeWrapper<K, V> newNode = new NodeWrapper<K, V>(key, val);
		if(this.isFull()){
			NodeWrapper<K, V> oldestNode = this.tail;
			this.tail = oldestNode.getPrev();
			if(this.tail != null) this.tail.setNext(null);

			K oldestNodeKey = oldestNode.getKey();
			this.keyValueMap.remove(oldestNodeKey);
			oldestNode 		= null;
		}
		this.keyValueMap.put(key, newNode);
		this.updateToLatest(newNode);
	}
}