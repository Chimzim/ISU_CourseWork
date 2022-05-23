var sum = 0;
var i;
// console.log(process.argv.length+"");
if(process.argv.length<4){
  console.log("Let's input at least 2 integers");
} else{
  for (i = 2; i < process.argv.length; i++) {
    //sum = sum + process.argv[i]; // this will concatenate strings
    sum = sum + Number(process.argv[i]);
  }
  console.log("The sum of command-line numbers is : ", sum)
}
