.data
str1: .asciiz "Please enter a number:\n"
.text
.globl main
main:
# Start program
addi $s1, $zero, 0 # s1 is number 1
addi $s2, $zero, 0 # s2 is number 2
addi $s0, $zero, 4 # s0 is loop counter
inputs:
# Request some user input:
li $v0, 4
la $a0, str1
la $a1, str1
syscall
# Read some user input:
li $v0, 5
li $v1, 4
syscall
# Do some arithmetic:
blt $v1, $v0, ceiling
addi $a0, $s1, 0
syscall
# Handle loop control flow:
ceiling:
addi $a0, $s2, 0
syscall

