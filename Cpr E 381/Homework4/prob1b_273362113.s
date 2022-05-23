.data
str1: .asciiz "Please enter a number for the height:\n"
.text

.data
str2: .asciiz "*"
.text

.data
str3: .asciiz "\n"
.text

.globl main
main:
# Start program
addi $s1, $zero, 0 # height
addi $t0, $zero, 0 # corresponds to i
addi $s0, $zero, 1 # will keep track of layers
inputs:
# Request some user input:
li $v0, 4
la $a0, str1
syscall
# Read some user input:
li $v0, 5
syscall
# Do some arithmetic:
add $s1, $s1, $v0
#checks to see if the height has been reached or not
Loop:
beq $t0, $s1, Exit
#checks to see if another character should be placed or a newLine should be added
NewLine:
blt $t0, $s0, characters, inputs 
#adds a new line
ori $a1, str3
syscall
j Loop
# adds a single character to be added to the tree
characters:
ori $a1, str2
addi $t0, $t0, 1
j NewLine
# Exit program
Exit:
li $v0, 10
syscall
