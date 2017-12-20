

// function countAveregeInArr(arrToCountAverage) {
// 	var arraySum = 0;
// 	for (var i=0; arrToCountAverage.length>i; i++) {
// 		arraySum+=arrToCountAverage[i];
// 	};
// 	return arraySum/arrToCountAverage.length
// };

// function countAveregeInArr(arrToCountAverage) {
// 	return arrToCountAverage.reduce(function(a, b) { return a + b; }, 0)/arrToCountAverage.length
// };
var arrayForTest = [2,42,3,6,-1,12,1313,11,425, 0, 1,-123, 21];

function LowestAndHighest(arrForWork) {
	var lowest = arrForWork[0];
	var highest = arrForWork[0];
	for (var i=0; arrForWork.length>i; i++) {
		if (lowest>arrForWork[i]) {
			lowest = arrForWork[i];
		} else if (highest<arrForWork[i]) {
			highest=arrForWork[i];
		}
	};
	return [lowest, highest]
};