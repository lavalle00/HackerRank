function processData(input) {
    //Enter your code here
    const lines = input.split('\n');
    const n = Number.parseInt(lines[0].split(' ')[0]);
    const d = Number.parseInt(lines[0].split(' ')[1]);
    const spending = lines[1].split(' ').map(Number);
    console.log(spending);
    var notifCount = 0;
    for(var i = d; i <= spending.length; i++) {
        var runningMedian = computeMedian(spending, i-d, i);
        // console.log(runningMedian);
        if(i < d) {
            continue;
        }
        else {
            //determine if spending[i] >= 2*d
            if(spending[i] >= 2*runningMedian){
                //notify
                notifCount++;
            }
        }
    }
    console.log(notifCount);
} 
function computeMedian(spending, startIndex, endIndex) {
    //print out indices
    console.log("Start Index:" + startIndex + " endIndex: " + endIndex);
    var tempArray = spending.slice(startIndex, endIndex);
    var median = -1;
    tempArray.sort( function(a,b) {return a - b;} );
    console.log(tempArray);
    var half = Math.floor(tempArray.length/2);

    if(tempArray.length % 2)
        return tempArray[half];
    else
        return (tempArray[half-1] + tempArray[half]) / 2.0;
}
var fakeInput = "9 5\n2 3 4 2 3 6 8 4 5";
processData(fakeInput);
