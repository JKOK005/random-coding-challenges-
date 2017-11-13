public class NodeWrapper<K, V>{
	private K key;
	private V value;
	private NodeWrapper<K, V> next;
	private	NodeWrapper<K, V> prev;

	public NodeWrapper(K key, V val){
		this.key 	= key;
		this.value 	= val;
		this.next 	= null;
		this.prev 	= null;
	}

	public void setVal(V val){
		this.value 	= val;
	}

	public V getVal(){
		return this.value;
	}

	public void setKey(K k){
		this.key 	= k;
	}

	public K getKey(){
		return this.key;
	}

	public void setNext(NodeWrapper<K, V> nxt){
		this.next 	= nxt;
	}

	public NodeWrapper<K, V> getNext(){
		return this.next;
	}

	public void setPrev(NodeWrapper<K, V> prv){
		this.prev 	= prv;
	}

	public NodeWrapper<K, V> getPrev(){
		return this.prev;
	}
}