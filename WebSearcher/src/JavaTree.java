
public class JavaTree {
	
	FileHandler files = new FileHandler();
	private BinaryTree root;
	private BinaryTree currentNode;
	
	JavaTree(){
		
		root = new BinaryTree(null, null, files.readAllLines("src/Known_Code/Main_Method.txt"));
		currentNode = root;
		root.setLeft(new BinaryTree(null, null, files.readAllLines("src/Known_Code/Method_Init.txt")));
	}
	
	public BinaryTree GetRootNode(){
		return root;
	}
	
	public BinaryTree GetCurrentNode(){
		return currentNode;
	}
	
	public void moveLeft(){
		currentNode = currentNode.getLeft();
	}
	
	public void moveRight(){
		currentNode = currentNode.getRight();
	}
}
