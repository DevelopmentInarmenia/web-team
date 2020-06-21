

function add(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = x + y;
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}

function sub(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = x - y;
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}

function multi(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = x * y;
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}

function divis(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = x / y;
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}
function sqr(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = Math.sqrt(x);
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}
function exp(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = Math.pow(x,2);
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}

function mod(){
    let x = Number(document.getElementById('num_one').value);
    let y =Number(document.getElementById('num_two').value);
    let res = (x * y)/100;
     let r = "Արդյունք" + " " + " = " + " " + res;
    
    result.innerHTML=r;
}