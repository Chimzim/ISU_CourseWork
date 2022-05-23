var sum = 0;
var array1 = []
for (i = 2; i < process.argv.length; i++) {
  array1.push(parseInt(process.argv[i]));
}

array1.forEach(function(element) {
  sum=sum+Number(element);
});

function isEven(currentValue) {
  return currentValue%2==0;
}
