module lab4step1(Z,F,C,G,W);
	input F, C, G, W;
	output Z;
	reg Z;
	
	always @(F or C or G or W)
	begin 
		case ({F,C,G,W})
		
		
				4'b0000: Z='b0;
				4'b0001: Z='b0;
				4'b0010: Z='b0;
				4'b0011: Z='b1;
				4'b0100: Z='b0;
				4'b0101: Z='b0;
				4'b0110: Z='b1;
				4'b0111: Z='b1;
				4'b1000: Z='b1;
				4'b1001: Z='b1;
				4'b1010: Z='b0;
				4'b1011: Z='b0;
				4'b1100: Z='b1;
				4'b1101: Z='b0;
				4'b1110: Z='b0;
				4'b1111: Z='b0;
		endcase
	end
	
endmodule
		