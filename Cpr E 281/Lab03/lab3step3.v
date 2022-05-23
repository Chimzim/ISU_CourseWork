module lab3step3 (F, C, G, W, D);
		input C, G, W, F;
		output D;
		
		assign D=((~F&G&W)|(~F&C&G)|(F&~G&~W)|(F&~C&~G));
		
endmodule