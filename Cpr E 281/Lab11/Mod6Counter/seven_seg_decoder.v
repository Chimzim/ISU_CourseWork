module seven_seg_decoder(A, B, C, D, E, F, G, W, X, Y, Z);
input W, X, Y, Z;
output A, B, C, D, E, F, G;

assign A = ((~W&~X&~Y&Z)|(~W&X&~Y&~Z)|(W&~X&Y&Z)|(W&X&~Y&Z));
assign B = ((X&Y&~Z)|(W&Y&Z)|(W&X&~Z)|(~W&X&~Y&Z));
assign C = ((W&X&~Z)|(W&X&Y)|(~W&~X&Y&~Z));
assign D = ((X&Y&Z)|(~W&~X&~Y&Z)|(~W&X&~Y&~Z)|(W&~X&Y&~Z));
assign E = ((~W&Z)|(~X&~Y&Z)|(~W&X&~Y));
assign F = ((~W&~X&Z)|(~W&~X&Y)|(~W&Y&Z)|(W&X&~Y&Z));
assign G = ((~W&~X&~Y)|(~W&X&Y&Z)|(W&X&~Y&~Z));

endmodule 