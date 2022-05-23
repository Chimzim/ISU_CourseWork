li $v0, 0
li $a0, 5
li $v1, 0
li $t1, 100

 
add $v0, $zero, $zero
add $t0, $zero, $zero
Loop:
be $t0, $a2, Exit
add $t1, $a0, $zero
lw $t1, $t0($t1)
add $t2, $a1, $zero
lw $t2, $t0($t2)
sub $t3, $t1, $t2
ori $t4, $zero, $t3      
slt $t5, $t3, $zero      
beq $t5, $zero, Pos  
sub $t4, $zero, $t3 
Pos:
add $v0, $v0, $t3
addi $t0, $t0, 1
j Loop
Exit:
jr $ra