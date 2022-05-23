module miniStep5 (W, X, Y, Z, Bob);
	input W, X, Y, Z;
	output Bob;
	
	
	assign Bob = ( (X&~Y&Z)|(W&Y&Z)|(~W&~X&Y&~Z) );
	
	endmodule 