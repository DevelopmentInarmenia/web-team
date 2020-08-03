var mobile_menu = document.querySelector('.mobile-menu');
var ul = document.querySelector('.menu ul');

mobile_menu.onclick = function(){
    if(ul.style.display == "block"){
        ul.style.display = "none";
    }
    else{
        ul.style.display = "block";
    }
}

var header = document.querySelector('.header');
var logo = document.querySelector('.logo');
console.log(mobile_menu, ul, header);

window.onscroll = function(){
    scroll_function();
};

function scroll_function(){
    if( document.body.scrollTop > 75 || document.documentElement.scrollTop > 75){
        header.style.height = "50px";
        logo.style.padding = "5px 0";
        ul.style.padding = "5px 0";
        mobile_menu.style.marginTop = "5px";
    }else{
        header.style.height = "";
        logo.style.padding = "";
        ul.style.padding = "";
        mobile_menu.style.marginTop = "";

    }
}