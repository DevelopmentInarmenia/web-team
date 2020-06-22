var add_comment = document.querySelector(".add_comment");
var show_form = document.querySelector("#show_form");
var comment_section_ = document.querySelector(".comment_section");

class Add_comment_form {
  constructor() {
    var form = create("form");
    form.id = "submitForm";
    var name_input = create("input");
    name_input.className = "name";
    name_input.name = "name";
    name_input.placeholder = "name...";
    name_input.required = true;

    var title_input = create("input");
    title_input.className = "title";
    title_input.name = "title";
    title_input.placeholder = "title...";
    title_input.required = true;

    var text_input = create("textarea");
    text_input.className = "text";
    text_input.name = "text";
    text_input.placeholder = "comment...";
    text_input.required = true;

    var button = create("button");
    button.id = "submit";

    button.innerText = "send comment";

    form.innerHTML +=
      name_input.outerHTML +
      title_input.outerHTML +
      text_input.outerHTML +
      button.outerHTML;
    return form;
  }
}
class comment_table {
  constructor() {
    var div = create("div");
    div.className = "comment_area";
    for (var i = 0; i < arr.length; i++) {
      var text = create("h5");
      text.innerText = arr[i];
      div.appendChild(text);
    }
    return div;
  }
}
var arr = [];

add_comment.onclick = function () {
  var add_comment_form = new Add_comment_form();
  if (show_form.children.length > 0) {
    show_form.innerHTML = "";
    arr = [];
  } else {
    show_form.appendChild(add_comment_form);
    var form = document.querySelector("form");
    var btn = document.querySelector("form button");
    btn.onclick = function () {
      for (let i = 0; i < form.length - 1; i++) {
        if (form[i].value == "") {
          alert("please fill all areas");
          return false;
        }
        arr.push(form[i].value);
      }
      var comment = new comment_table();
      comment_section_.appendChild(comment);
      show_form.innerHTML = "";
      arr = [];
      form.reset;
      return false;
    };
  }
};

function create(elem) {
  return document.createElement(elem);
}
