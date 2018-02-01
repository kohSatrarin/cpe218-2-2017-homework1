
public class Homework1 {

	public static void main(String[] args) {
		// Begin of arguments input sample
		//String input = "";
		//String input = "124++";
		String input = "251-*32*+";
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

			thisNode.input = String.valueOf(input.charAt(i));		//add input from args
			thisNode.right = new Node();
			thisNode = thisNode.right;								//add more data in Node

		}


		rootNode = infix(rootNode);

		//String ordered = inorder(rootNode);
		System.out.println(rootNode.reorder + " = " + rootNode.input);




	}
	/*
	 * `infix(Node n)`
	 * `inorder(Node n)`
	 * `calculate(Node n)`
	 */

	public static Node infix(Node rootNodeAtInfix){


		//System.out.println( "do rootNodeAtInfix");						//test function

		Node tempInfixNode = rootNodeAtInfix;
		//Node rootInfixNode = rootNodeAtInfix;
		//Node checkLeft = null;

		while (rootNodeAtInfix.right != null){
			if(rootNodeAtInfix.right.input.matches("[0-9]")) {		//check is it number?
				if(rootNodeAtInfix.left==null) {									//if left no problem then move
					rootNodeAtInfix.left = rootNodeAtInfix.right;                //move right to left
					rootNodeAtInfix.right = rootNodeAtInfix.right.right;                //move right.right to right
				}else {														//if left already have number then move to the last left
					tempInfixNode = rootNodeAtInfix;
					while (tempInfixNode.left!=null){						//move to the last left
						tempInfixNode = tempInfixNode.left;
					}
					tempInfixNode.left = rootNodeAtInfix.right;
					rootNodeAtInfix.right = rootNodeAtInfix.right.right;				//move right.right to right
				}
			}else {
//				System.out.println("     " + rootNodeAtInfix.input +"\n = "+rootNodeAtInfix.left.input + " :: " + rootNodeAtInfix.right.input);
				Node temRoot = rootNodeAtInfix;
				while (temRoot!=null){
					//System.out.println("test added "+temRoot.input);
					temRoot = temRoot.left;

				}
				if (rootNodeAtInfix.left.left==null) {                            //if it last symbol can cal root and root.left
					calculate(rootNodeAtInfix, rootNodeAtInfix);
					//inorder(rootNodeAtInfix,rootNodeAtInfix);
					//System.out.println("error1");

				}else {														//if not the last symbol have to move before cal
					tempInfixNode = rootNodeAtInfix;
					while (tempInfixNode.left.left != null){
						tempInfixNode = tempInfixNode.left;
					}
					calculate(tempInfixNode,rootNodeAtInfix);
					//inorder(tempInfixNode,rootNodeAtInfix);
					//System.out.println( "error2");
				}


			}
		}


		return rootNodeAtInfix;
	}

	public static String inorder(Node inoderNode,Node rootNodeAtInorder){
		//System.out.println( "do nodeINorder");						//test function
		rootNodeAtInorder.reorder = inoderNode.input + rootNodeAtInorder.right.input + inoderNode.left.input;

		if (inoderNode.left.left != null){								//incase it not the last node on the left
			inoderNode.left = inoderNode.left.left;					//we have to move the node left left up to left
		}







		return inoderNode.reorder;			//return final text display
	}



	public static Node calculate(Node nodeCalculate,Node rootNodeAtCalculate){
		//System.out.println( "do nodeCal");						//test function
		int result = Integer.valueOf(nodeCalculate.input);					//make it can calculate
		int left = Integer.valueOf(nodeCalculate.left.input);				//by make string to int

		System.out.println( " = " + result + rootNodeAtCalculate.right.input + left );
		switch (rootNodeAtCalculate.right.input){
			case "+": result = result + left;break;
			case "-": result = result - left;break;
			case "*": result = result * left;break;
			case "/": result = result / left;break;
		}
		System.out.println( " = " + result + "\n");



		nodeCalculate.input=String.valueOf(result);							//make int back to string

		if (nodeCalculate == rootNodeAtCalculate){
				//fix bug pointernull error
			nodeCalculate.left = null;
		}else {
			nodeCalculate.left = null;                                            //deleted node that calculated
		}


		if (rootNodeAtCalculate.right.right!=null) {
			//System.out.println("make last node null1");
			rootNodeAtCalculate.right = rootNodeAtCalculate.right.right;        //delete Symbol + move number/Symbol up
		}else {
			//System.out.println("make last node null2");
			rootNodeAtCalculate.right = null;
		}




		return nodeCalculate;
	}
}
