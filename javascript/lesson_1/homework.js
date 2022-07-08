/* game dam bua bao */
function game(value) {
    let arr = ["Bua", "Keo", "Bao"];
    let pc = Math.floor(Math.random() * 11 % 3);
    if (value == pc) {
        return "Draw";
    }
    if (value != 2) {
        if (value - pc == -1) {
            return "Win";
        }
    } else {
        if (value - pc == 2) {
            return "Win";
        }
    }
    return "Lose";
}

console.log(game(1));

/* BT1 */
function calculateFactorial(number) {
    if (number == 1) {
        return 1;
    }
    return number * calculateFactorial(number - 1);
}

console.log(calculateFactorial(6));

/* BT2 */
function reverseString(str) {
    let sub = "";
    for (let i = str.length - 1; i >= 0; i--) {
        sub += str.charAt(i);
    }
    return sub;
}

console.log(reverseString("hello"));

/* BT3 */
function translate(code) {
    switch (code) {
        case "VN":
            return "Xin chao";
        case "EN":
            return "Hello"
        case "DE":
            return "Hi";
        case "IT":
            return "Ciao";
        case "KR":
            return "안녕";
        default:
            return "No data";
    }
}

console.log(translate("KR"));

/* BT4 */
function subString(str){
    let a = 0;
    let string = "";
    while(a < 10){
        string += str.charAt(a);
        a++;
    }
    string += "...";
    return string;
}

console.log(subString("xinchaocacbandenvoiTechmaster"));