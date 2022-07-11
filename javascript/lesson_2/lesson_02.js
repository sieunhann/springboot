const grades = [
    { name: 'John', grade: 8, sex: 'M' },
    { name: 'Sarah', grade: 12, sex: 'F' },
    { name: 'Bob', grade: 16, sex: 'M' },
    { name: 'Johnny', grade: 2, sex: 'M' },
    { name: 'Ethan', grade: 4, sex: 'M' },
    { name: 'Paula', grade: 18, sex: 'F' },
    { name: 'Donald', grade: 5, sex: 'M' },
    { name: 'Jennifer', grade: 13, sex: 'F' },
    { name: 'Courtney', grade: 15, sex: 'F' },
    { name: 'Jane', grade: 9, sex: 'F' }
]

/* BT1 */
function averagePoint(grades) {
    let sum = 0;
    for (let i = 0; i < grades.length; i++) {
        sum += grades[i].grade;
    }

    return sum / (grades.length);
}

console.log(averagePoint(grades));

/* BT2 */
function averageMalePoint(grades) {
    let sum = 0, count = 0;
    for (let i = 0; i < grades.length; i++) {
        if (grades[i].sex == 'M') {
            sum += grades[i].grade;
            count++;
        }
    }

    return sum / count;
}

console.log(averageMalePoint(grades));

/* BT3 */
function averageFemalePoint(grades) {
    let sum = 0, count = 0;
    for (let i = 0; i < grades.length; i++) {
        if (grades[i].sex == 'F') {
            sum += grades[i].grade;
            count++;
        }
    }

    return sum / count;
}

console.log(averageFemalePoint(grades));

/* BT4 */
function findMaxMale(grades) {
    let max = Number.MAX_VALUE;
    let student;
    for (let i = 0; i < grades.length; i++) {
        let value = Object.values(grades[i]);
        if (value[2] == 'M' && value[1] < max) {
            max = value[1];
            student = grades[i];
        }
    }

    return student;
}

console.log(findMaxMale(grades));

/* BT5 */
function findMaxFemale(grades) {
    let max = Number.MAX_VALUE;
    let student;
    for (let i = 0; i < grades.length; i++) {
        let value = Object.values(grades[i]);
        if (value[2] == 'F' && value[1] < max) {
            max = value[1];
            student = grades[i];
        }
    }

    return student;
}

console.log(findMaxFemale(grades));

/* BT6 */
function findMinMale(grades) {
    let min = 0;
    let student;
    for (let i = 0; i < grades.length; i++) {
        let value = Object.values(grades[i]);
        if (value[2] == 'M' && value[1] > min) {
            min = value[1];
            student = grades[i];
        }
    }

    return student;
}

console.log(findMinMale(grades));

/* BT7 */
function findMinFemale(grades) {
    let min = 0;
    let student;
    for (let i = 0; i < grades.length; i++) {
        let value = Object.values(grades[i]);
        if (value[2] == 'F' && value[1] > min) {
            min = value[1];
            student = grades[i];
        }
    }

    return student;
}

console.log(findMinFemale(grades));

/* BT8 */
function findMax(grades){
    let max = Number.MAX_VALUE;
    let student;
    for(let i = 0; i < grades.length; i++){
        let value = Object.values(grades[i]);
        if(value[1] < max){
            max = value[1];
            student = grades[i];
        }
    }
    return student;
}

console.log(findMax(grades));

/* BT9 */
function findMin(grades){
    let min = Number.MIN_VALUE;
    let student;
    for(let i = 0; i < grades.length; i++){
        let value = Object.values(grades[i]);
        if(value[1] > min){
            min = value[1];
            student = grades[i];
        }
    }
    return student;
}

console.log(findMin(grades));

/* BT10 */
function getListFemale(grades){
    let arr = [];
    for(let i = 0; i < grades.length; i++){
        let value = Object.values(grades[i]);
        if(value[2] === "F"){
            arr.push(grades[i]);
        }
    }

    return arr;
}

console.log(getListFemale(grades));

/* BT11 */
function sortByName(grades){
    grades.sort(function(o1,o2){
        if(o1.name < o2.name) return -1;
        if(o1.name > o2.name) return 1;
        return 0;
    })
    return grades;
}

console.log(sortByName(grades));

/* BT12 */
function sortByGrade(grades){
    grades.sort(function (o1, o2){
        return o2.grade - o1.grade;
    })

    return grades;
}

console.log(sortByGrade(grades));

/* BT13 */
function getListFemaleJ(grades){
    let arr = [];
    for(let i = 0; i < grades.length; i++){
        if(grades[i].sex == 'F' && grades[i].name.charAt(0) == 'J'){
            arr.push(grades[i]);
        }
    }
    return arr;
}

console.log(getListFemaleJ(grades));

/* BT14 */
function getListTop5(grades){
    grades.sort(function(o1,o2){
        return o2.grade - o1.grade;
    })
    let arr = [];
    let i = 0;
    while(i < 5){
        arr.push(grades[i]);
        i++;
    }

    return arr;
}

console.log(getListTop5(grades));