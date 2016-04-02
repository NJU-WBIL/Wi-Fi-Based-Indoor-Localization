package com.example.experiment_1;

public class Node {
	int X,Y,Z;
	//double SigStrenSum;//Count;
	String SigStrenSum,APName;
	
	public Node(int x, int y, int z, String aPName, String sigStrenSum) {
		super();
		X = x;
		Y = y;
		Z = z;
		APName = aPName;
		SigStrenSum = sigStrenSum;
		//Count = count;
	}

	public String toString() {
		return "X=" + X + ", Y=" + Y + ", Z=" + Z + ","  + APName + ", "+ SigStrenSum;
				//+ ", Count=" + Count ;
	}
}
