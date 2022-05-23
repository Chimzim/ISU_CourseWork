module lab06decoder(C, S3, S2, S1, S0, N23, N22, N21, N20, N13, N12, N11, N10);
input C, S3, S2, S1, S0;
output N23, N22, N21, N20, N13, N12, N11, N10;


assign N23 = 0;
assign N22 = 0;
assign N21 = ( (C&S1&S0)|S2|S3 );
assign N20 = ( (~C&S3&S2)|(~C&S3&S1)|(C&~S3&~S2)|(C&S3&S2) );
assign N13 = ( (~C&S3&~S2&~S1)|(C&~S3&~S2&S1)|(C&S3&S2&~S1) );
assign N12 = ( (~C&~S3&S2)|(~C&S1&S2)|(C&~S3&~S2&~S1)|(C&S3&~S2) );
assign N11 = ( (~C&S3&S1)|(~C&S3&S2&~S1)|(C&~S3&~S2&~S1)|(C&~S3&S2&S1)|(C&S3&~S2&S1) );
assign N10 = ( (~C&S0)|(C&S0) );

endmodule
