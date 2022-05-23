f: #function f
addi $sp, $sp, -12
sw $r,8($sp)
sw $t1,4($sp)
sw $t0, 0($sp)
move $t1, $a2
move $t0, $a3
jal func
move $a0, $v0
add $a1, $t0, $v0
jal func
lw $ra, 8($sp)
lw $1, 4($sp)
lw $t0, 0($sp)
addi $sp, $sp, 12
jr $ra
