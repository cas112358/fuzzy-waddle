import java.util.ArrayList;

class BinaryTree {
    private BinaryTree left;
    private BinaryTree right;
    private ArrayList<String> value = new ArrayList<String>();
    private Boolean used;
    
    BinaryTree(BinaryTree leftNode, BinaryTree rightNode, ArrayList<String> listValues){
    	setLeft(leftNode);
    	setRight(rightNode);
    	value.addAll(listValues);
    	used = false;
    }
    
    public BinaryTree getLeftChild(){
    	return getLeft();
    }
    
    public BinaryTree getRightChild(){
    	return getRight();
    }
    
    public ArrayList<String> getValue(){
    	return value;
    }
    
    public void setUsedTrue(){
    	used = true;
    }
    
    public Boolean getUsed(){
    	return used;
    }

	/**
	 * @return the left
	 */
	public BinaryTree getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(BinaryTree left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public BinaryTree getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(BinaryTree right) {
		this.right = right;
	}
}