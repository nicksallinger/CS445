import java.util.Iterator;
import java.util.NoSuchElementException;


import StackAndQueuePackage.LinkedQueue;
import StackAndQueuePackage.LinkedStack;
import StackAndQueuePackage.QueueInterface;
import StackAndQueuePackage.StackInterface;

public class TernaryTree<T> implements TernaryTreeInterface<T>{
	private TernaryNode<T> root;



	
	
	public TernaryTree(){
		root = null;
	}
	public TernaryTree(T rootData){
		 root = new TernaryNode<>(rootData);
	}
	public TernaryTree(T rootData, TernaryTree<T> leftTree, TernaryTree<T> middleTree, TernaryTree<T> rightTree){
		 privateSetTree(rootData, leftTree, rightTree, middleTree);
	}
	
	
	public void setTree(T rootData) {
        root = new TernaryNode<>(rootData);
    }

	public void setTree(T rootData, TernaryTreeInterface<T> leftTree, TernaryTreeInterface<T> middleTree,
			TernaryTreeInterface<T> rightTree) {
		
        privateSetTree(rootData, (TernaryTree<T>)leftTree, (TernaryTree<T>)middleTree,
                       (TernaryTree<T>) rightTree);
    }
	

    private void privateSetTree(T rootData, TernaryTree<T> leftTree, TernaryTree<T> middleTree,
                                TernaryTree<T> rightTree) {
        root = new TernaryNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftChild(leftTree.root);
        }

        if ((rightTree != null) && !rightTree.isEmpty()) {
            if (rightTree != leftTree) {
                root.setRightChild(rightTree.root);
            } else {
                root.setRightChild(rightTree.root.copy());
            }
        }
        
        if ((middleTree != null) && !middleTree.isEmpty()) {
            if (middleTree != leftTree && middleTree != rightTree) {
                root.setMiddleChild(middleTree.root);
            } else {
                root.setMiddleChild(middleTree.root.copy());
            }
        }

        if ((leftTree != null) && (leftTree != this)) {
            leftTree.clear();
        }

        if ((rightTree != null) && (rightTree != this)) {
            rightTree.clear();
        }
        if ((middleTree != null) && (middleTree != this)) {
        	middleTree.clear();
        }
    }
			
	public T getRootData() {
		 if (isEmpty()) {
	            throw new EmptyTreeException();
	        } else {
	            return root.getData();
	        }
	}

	
	public int getHeight() {
		 return root.getHeight();
	}

	
	public int getNumberOfNodes() {
		 return root.getNumberOfNodes();
    
	}

	
	public boolean isEmpty() {
		 return root == null;
	}

	
	public void clear() {
		  root = null;
		
	}

	
	public Iterator<T> getPreorderIterator() {
		return new PreorderIterator();
	}

	
	public Iterator<T> getPostorderIterator() {
		 return new PostorderIterator();
	}

	
	public Iterator<T> getInorderIterator() {
		//the inorder iterator is not supported in ternary trees. 
		//The inorder traversal of a binary tree will return the objects
		//in order, but using this in a ternary tree with more than 2 children would 
		//return the root of the subtree multiple times as the iterator recurses through
		//the tree.
		
		throw new java.lang.UnsupportedOperationException();
	}
		
	
	public Iterator<T> getLevelOrderIterator() {
		  return new LevelOrderIterator();
	}
	
	private class PreorderIterator implements Iterator<T> {

        private StackInterface<TernaryNode<T>> nodeStack;

        public PreorderIterator() {
            nodeStack = new LinkedStack<>();
            if (root != null) {
                nodeStack.push(root);
            }
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        public T next() {
            TernaryNode<T> nextNode;

            if (hasNext()) {
                nextNode = nodeStack.pop();
                TernaryNode<T> leftChild = nextNode.getLeftChild();
                TernaryNode<T> rightChild = nextNode.getRightChild();
                TernaryNode<T> middleChild = nextNode.getMiddleChild();

                // Push into stack in reverse order of recursive calls
                if (rightChild != null) {
                    nodeStack.push(rightChild);
                }

                if (leftChild != null) {
                    nodeStack.push(leftChild);
                }
                if (middleChild != null) {
                    nodeStack.push(middleChild);
                }
            } else {
                throw new NoSuchElementException();
            }

            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	 public void iterativePreorderTraverse() {
	        StackInterface<TernaryNode<T>> nodeStack = new LinkedStack<>();
	        if (root != null) {
	            nodeStack.push(root);
	        }
	        TernaryNode<T> nextNode;
	        while (!nodeStack.isEmpty()) {
	            nextNode = nodeStack.pop();
	            TernaryNode<T> leftChild = nextNode.getLeftChild();
	            TernaryNode<T> rightChild = nextNode.getRightChild();
	            TernaryNode<T> middleChild = nextNode.getMiddleChild();

	            // Push into stack in reverse order of recursive calls
	            if (rightChild != null) {
	                nodeStack.push(rightChild);
	            }

	            if (leftChild != null) {
	                nodeStack.push(leftChild);
	            }
	            if (middleChild != null) {
	                nodeStack.push(middleChild);
	            }

	            System.out.print(nextNode.getData() + " ");
	        }
	    }
	 private class InorderIterator implements Iterator<T> { 
	        private StackInterface<TernaryNode<T>> nodeStack;
	        private TernaryNode<T> currentNode;

	        public InorderIterator() {
	            nodeStack = new LinkedStack<>();
	            currentNode = root;
	        }

	        public boolean hasNext() {
	            return !nodeStack.isEmpty() || (currentNode != null);
	        }

	        public T next() {
	            TernaryNode<T> nextNode = null;

	            // Find leftmost node with no left child
	            while (currentNode != null) {
	                nodeStack.push(currentNode);
	                currentNode = currentNode.getLeftChild();
	            }

	            // Get leftmost node, then move to its right subtree
	            if (!nodeStack.isEmpty()) {
	                nextNode = nodeStack.pop();
	                assert nextNode != null; // Since nodeStack was not empty
	                // before the pop
	                currentNode = nextNode.getRightChild();
	            } else {
	                throw new NoSuchElementException();
	            }

	            return nextNode.getData();
	        }

	        public void remove() {
	            throw new UnsupportedOperationException();
	        }
	    }
	 public void iterativeInorderTraverse() { 
	        StackInterface<TernaryNode<T>> nodeStack = new LinkedStack<>();
	        TernaryNode<T> currentNode = root;

	        while (!nodeStack.isEmpty() || (currentNode != null)) {
	            // Find leftmost node with no left child
	            while (currentNode != null) {
	                nodeStack.push(currentNode);
	                currentNode = currentNode.getLeftChild();
	            }

	            // leftmost node, then traverse its right subtree
	            if (!nodeStack.isEmpty()) {
	                TernaryNode<T> nextNode = nodeStack.pop();
	                assert nextNode != null; 
	                // before the pop
	                System.out.print(nextNode.getData() + " ");
	                currentNode = nextNode.getRightChild();
	            }
	        }
	    }
	 
	 private class PostorderIterator implements Iterator<T> { 
	        private StackInterface<TernaryNode<T>> nodeStack;
	        private TernaryNode<T> currentNode;

	        public PostorderIterator() {
	            nodeStack = new LinkedStack<>();
	            currentNode = root;
	        }

	        public boolean hasNext() {
	            return !nodeStack.isEmpty() || (currentNode != null);
	        }
	        public T next() {
	            boolean foundNext = false;
	            TernaryNode<T> leftChild, rightChild, middleChild, nextNode = null;

	            // Find leftmost leaf
	            while (currentNode != null) {
	                nodeStack.push(currentNode);
	                leftChild = currentNode.getLeftChild();
	                middleChild = currentNode.getMiddleChild();
	                if (leftChild == null) {
	                    currentNode = currentNode.getMiddleChild();
	                } 
	                if (middleChild == null){
	                	currentNode = currentNode.getRightChild();
	                }
	                
	                else {
	                    currentNode = leftChild;
	                }
	            }

	           

	            if (!nodeStack.isEmpty()) {
	                nextNode = nodeStack.pop();
	               

	                TernaryNode<T> parent = null;
	                if (!nodeStack.isEmpty()) {
	                    parent = nodeStack.peek();
	                    if (nextNode == parent.getLeftChild()) {
	                        currentNode = parent.getMiddleChild();
	                    } 
	                    else if(nextNode == parent.getMiddleChild()){
	                    	currentNode = parent.getRightChild();
	                    }
	                    else {
	                        currentNode = null;
	                    }
	                } else {
	                    currentNode = null;
	                }
	            } else {
	                throw new NoSuchElementException();
	            }

	            return nextNode.getData();
	        }


	        public void remove() {
	            throw new UnsupportedOperationException();
	        }
	    }
	 private class LevelOrderIterator implements Iterator<T> { 
	        private QueueInterface<TernaryNode<T>> nodeQueue;

	        public LevelOrderIterator() {
	            nodeQueue = new LinkedQueue<>();
	            if (root != null) {
	                nodeQueue.enqueue(root);
	            }
	        }

	        public boolean hasNext() {
	            return !nodeQueue.isEmpty();
	        }

	        public T next() {
	            TernaryNode<T> nextNode;

	            if (hasNext()) {
	                nextNode = nodeQueue.dequeue();
	                TernaryNode<T> leftChild = nextNode.getLeftChild();
	                TernaryNode<T> rightChild = nextNode.getRightChild();
	                TernaryNode<T> middleChild = nextNode.getMiddleChild();

	               
	                if (leftChild != null) {
	                    nodeQueue.enqueue(leftChild);
	                }

	                if (rightChild != null) {
	                    nodeQueue.enqueue(rightChild);
	                }
	                if (middleChild != null) {
	                    nodeQueue.enqueue(middleChild);
	                }
	            } else {
	                throw new NoSuchElementException();
	            }

	            return nextNode.getData();
	        }

	        public void remove() {
	            throw new UnsupportedOperationException();
	        }
	    }
	
	
}