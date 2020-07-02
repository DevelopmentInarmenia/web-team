// Logo color function

var logoVar = setInterval(logoColor,700);

function logoColor(){
    var x= document.getElementById("logo-span");
    x.style.color=x.style.color == "black" ? "#1CBBB4" : "black"
    
}



// Stock function

var myVar = setInterval(setColor, 500);
 
function setColor() {
  var z = document.getElementById('stock');
  z.style.color=z.style.color == "red" ? "orange" : "red"

}