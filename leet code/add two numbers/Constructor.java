public class Constructor{
	public static ListNode construct(int num){
		ListNode root;
		if(num > 0){
			int tmp 		= num / 10 * 10;
			int remain 		= num - tmp;
			root 			= new ListNode(remain);
			root.next 		= construct(num / 10);
		}else{
			return null;
		}
		return root;
	}

	public static void main(String[] args){
		ListNode head 	= Constructor.construct(84510923);
		Printer.printList(head);
	}
}