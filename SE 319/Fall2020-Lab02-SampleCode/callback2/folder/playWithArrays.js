var sum = 0;
// var array1 = [10, 20, 30,35];
var array1 = []
for (i = 2; i < process.argv.length; i++) {
  array1.push(process.argv[i]);
}

array1.forEach(function(element) {
  sum=sum+Number(element);
});

function isEven(currentValue) {
  return currentValue%2==0;
}

const reducer = (accumulator, currentValue) => accumulator + currentValue;

console.log("ForEach block output is "+sum);

const map1 = array1.map(x => x * x);

console.log("Map block output is "+map1);

const result = array1.filter(array1 => array1%2==0);

console.log("Filter block output is "+result);

console.log("Every block output is "+ array1.every(isEven));

console.log("Some block output is "+array1.some(isEven));

console.log("Reducer block output is "+array1.reduce(reducer));
