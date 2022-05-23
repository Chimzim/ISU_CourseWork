module figure1(Cin, X, Y, S, Cout);
input Cin, X, Y;
output Cout, S;
assign S = (X^Y^Cin);
assign Cout = ((Cin&Y)|(X&Y)|(Cin&X));
endmodule
