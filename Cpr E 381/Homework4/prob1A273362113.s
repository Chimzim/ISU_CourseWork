li $v0, 0
li $a0, 5
li $v1, 0
li $t1, 100

Loop:  beq $v0, $t1, Exit
div $v0, $a0
mfhi $t2
beq $t2, $zero, sum
addi $v0, $v0, 1
j Loop
sum: 
addi $v0, $v0, 1
add $v1, $v1, $t2
j Loop
Exit:
addi $a0,$v1, 0
syscall