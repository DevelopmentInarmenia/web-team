


var item=document.getElementById('item');
console.log('item');

var j=2;
var k=3;
document.write (j+k/2*10);

function print (j,k){
    document.write(j,k);
}
print (" <br /> ");

item.onclick =function(){
    item.style.color="red";
    item.style.fontSize='200px';

}

var block= document.getElementsByClassName('block');
for (let i =0; i<block.length; i++){
    block[i].onmouseover = function () {
         this.style.color = "blue"
         this.style.fontSize='200px';
     }
 }


for (var j=2; j<=50;j++){
    document.write(j);
    console.log(j);
}



var new_div=document.getElementById ("new_div");

console.log(new_div);
new_div.onclick =function(){
    new_div.style.color ="red";

}

var block =document.getElementsByClassName("block");

console.log(block);
block.onclick =function(){
    block.style.color="red";
}

function changeFunction() {
    var i = document.getElementById("Select").value;
    document.getElementById("result").innerHTML = "Result: " + i;
  }

  

