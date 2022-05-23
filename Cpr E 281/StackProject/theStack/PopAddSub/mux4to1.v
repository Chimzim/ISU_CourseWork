module mux4to1(W3, W2, W1, W0, S0, S1, F);
input W3, W2, W1, W0, S0, S1;
output F;

assign F = S1 ? (S0 ? W3: W2) : (S0 ? W1: W0);
endmodule 