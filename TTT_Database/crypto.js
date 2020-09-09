const crypto =require('crypto');

function randomValue(length){
    return crypto.randomBytes(Math.ceil(length/2)).toString('hex').slice(0,length);
}

var value = randomValue(4);
console.log(value);