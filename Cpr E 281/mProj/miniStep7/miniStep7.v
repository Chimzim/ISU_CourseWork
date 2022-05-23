module miniStep7 (W, X, Y, Z, p);
input W, X, Y, Z;
output p;


assign p = ( (~W&~X&Y)|(~X&Y&Z)|(~W&Y&Z)|(X&~Y&Z) );

endmodule 