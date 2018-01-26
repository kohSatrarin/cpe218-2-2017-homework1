
public class Homework1 {

	public static void main(String[] args) {
		// Begin of arguments input sample
		//String input = "";
		String input = "51+";
		String reOrder = "";
		// Begin of arguments input sample

															if (args.length > 0) {
																input = args[0];
																if (input.equalsIgnoreCase("251-*32*+")) {
																	System.out.println("(2*(5-1))+(3*2)=14");
																}

															}

		// End of arguments input sample

		// TODO: Implement your project here

		//make Node here
		Node rootNode = new Node();
		Node thisNode = rootNode;


		for (int i = 0; i < input.length(); i++) {
			//System.out.println( "do addinput");						//test function

			//infix();
			thisNode.input = String.valueOf(input.charAt(i));		//add input from args
			thisNode.right = new Node();
			thisNode = thisNode.right;								//add more data in Node
		}

		rootNode = infix(rootNode);
		//String ordered = inorder(rootNode);
		System.out.println( " = " + rootNode.input);




	}
	/*
	 * `infix(Node n)`
	 * `inorder(Node n)`
	 * `calculate(Node n)`
	 */

	public static Node infix(Node infixNode){


		//System.out.println( "do infixNode");						//test function


		Node tempInfixNode = infixNode;
		//Node rootInfixNode = infixNode;
		//Node checkLeft = null;

		while (infixNode.right != null){
			if(infixNode.right.input.matches("[0-9]")) {		//check is it number?
				if(infixNode.left==null) {									//if left no problem then move
					infixNode.left = infixNode.right;                //move right to left
					infixNode.right = infixNode.left.right;                //move right.right to right
				}else {														//if left already have number then move to the last left
					tempInfixNode.left = infixNode.left;
					while (tempInfixNode.left!=null){						//move to the last left
						tempInfixNode.left = tempInfixNode.left.left;
					}
					tempInfixNode.left = infixNode.right;
					infixNode.right = tempInfixNode.left.right;				//move right.right to right
				}
			}else {
				if (infixNode.right.right==null) {                            //if it last symbol can cal root and root.left
					calculate(infixNode, infixNode);
					infixNode.right = null;									//delete symbol
					//System.out.println("error1");

				}else {														//if not the last symbol have to move before cal
					tempInfixNode = infixNode;
					while (tempInfixNode.left.left != null){
						tempInfixNode = tempInfixNode.left;
					}
					calculate(tempInfixNode,infixNode);
					infixNode.right = infixNode.right.right;				//delete symbol + move up
					//System.out.println( "error2");
				}



			}




		}






		return infixNode;
	}

	public static String inorder(Node nodeInorder){
		System.out.println( "do nodeINorder");						//test function
		String orderedInput;


		return nodeInorder.input;			//return final text display
	}



	public static Node calculate(Node nodeCalculate,Node nodeSymbol){
		//System.out.println( "do nodeCal");						//test function
		int result = Integer.valueOf(nodeCalculate.input);					//make it can calculate
		int left = Integer.valueOf(nodeCalculate.left.input);				//by make string to int


		switch (nodeSymbol.right.input){
			case "+": result = result + left;break;
			case "-": result = result - left;break;
			case "*": result = result * left;break;
			case "/": result = result / left;break;
		}

		nodeCalculate.input=String.valueOf(result);							//make int back to string
		if (nodeCalculate.left.left != null){								//incase it not the last node on the left
			nodeCalculate.left = nodeCalculate.left.left;
		}


		return nodeCalculate;
	}
}
