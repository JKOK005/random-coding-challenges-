public class Test{
	public void printByLink(Node root){
		Node tmp 	= root;
		do{
			System.out.print(String.format("%d -> ", tmp.val));
			tmp 	= tmp.Right;
		}while(tmp != null);

		System.out.println(" ");

		if(root.Children != null){
			printByLink(root.Children[0]);
		}
		return;	
	}

	private Node[] construct(int size, int base){
		Node[] tmp 		= new Node[size];
		for(int i =0; i < tmp.length; i++){
			tmp[i] 			= new Node(base + i);
			tmp[i].Right 	= null;
		}
		return tmp;
	}

	public static void main(String[] args){
		Test tester 	= new Test();
		Solution sol 	= new Solution();

		Node root 		= new Node(0);
		root.Right 		= null;
		root.Children 	= tester.construct(8, 15);

		for(int i =0; i < root.Children.length; i++){
			if(i % 2 == 0){
				root.Children[i].Children 	= tester.construct(3, i*100);
			}
 		}
		sol.linkAll(root);
		tester.printByLink(root);
	}
}