$().ready(function () {
    
    var pathname = location.pathname;
       if(pathname !== "/login"){
           pathname = "?go=" + pathname;
       }
       else {
           pathname ="";
       }
       
       $("#loginVO").attr({action: "/login" + pathname});


  $("#email").on("blur", function () {
    setTimeout(function () {
      $("#email").trigger("keyup");
    }, 150);
  });

  $("#email").on("keyup", function () {

    var emailPattern =
      /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

    // 이메일을 입력했을 때
    if (emailPattern.test(emailValue)) {
      fetch("/regist/check/duplicate/" + emailValue)
        .then(function (fetchResult) {
          return fetchResult.json();
        })
        .then(function (json) {

          var duplicateResult = $("#email")
            .closest(".input-div")
            .children(".validation-error");

          if (duplicateResult.length === 0) {
            duplicateResult = $("#email")
              .closest(".input-div")
              .children(".validation-okay");
          }

          if (duplicateResult.length === 0) {
            duplicateResult = $("<div>");
            $("#email").after(duplicateResult);
          }


          if (!json.duplicate) {
            duplicateResult.removeClass("validation-error");
            duplicateResult.addClass("validation-okay");
            duplicateResult.text(json.email + "은 사용 가능");
          } else {
            duplicateResult.removeClass("validation-okay");
            duplicateResult.addClass("validation-error");
            duplicateResult.text(json.email + "은 이미 사용 중 입니다");
          }
        });
    } else {
      $(this)
        .closest.closest(".input-div")
        .children(".validation-okay, .validation-error")
        .remove();
    }
  });

  $("#confirm-password, #password").on("keyup", function () {
    var confirmPasswordValue = $("#confirm-password").val();
    var passwordValue = $("#password").val();

    $("#password,#confirm-password")
      .closest(".input-div")
      .children(".validation-error, .validation-okay")
      .remove();

    if (confirmPasswordValue != passwordValue) {
      var passwordErrorMessage = $("<div>");
      passwordErrorMessage.addClass("validation-error");
      passwordErrorMessage.text("비밀번호 일치 오류");

      var confirmpasswordErrorMessage = $("<div>");
      confirmpasswordErrorMessage.addClass("validation-error");
      confirmpasswordErrorMessage.text("비밀번호 일치 오류");

      $("#password").after(passwordErrorMessage);
      $("#confirm-password").after(passwordErrorMessage);
    }
    else if ((confirmPasswordValue === passwordValue) && (confirmPasswordValue && passwordValue)) {
        var success = $("<div>");
        success.addClass("validation-okay");
        success.text("비밀번호 일치");
        
        $("#confirm-password").after(success);
    }
  });

  $("#show-password").on("change", function () {
    var checked = $(this).prop("checked");
    if (checked) {
      $("#password").attr("type", "text");
      $("#confirm-password").attr("type", "text");
    } else {
      $("#password").attr("type", "password");
      $("#confirm-password").attr("type", "password");
    }
  });

  $("#registVO").on("submit", function (event) {
    // 이미 브라우저에 할당된 submit callback 이벤트를 제거한다
    event.preventDefault();

    $(this).find(".validation-error").remove();

    $("#password").trigger("keyup");

    var email = $("#email").val();
    if (!email) {
      var emailErrorMessage = $("<div>");
      emailErrorMessage.addClass("validation-error");
      emailErrorMessage.text("이메일 형태가 아닙니다");

      $("#email").after(emailErrorMessage);
    }

    var name = $("#name").val();
    if (!name) {
      var nameErrorMessage = $("<div>");
      nameErrorMessage.addClass("validation-error");
      nameErrorMessage.text("이름을 입력하세요");

      $("#name").after(nameErrorMessage);
    }


    if ($(".validation-error").length == 0) {
      this.submit();
    }
  });
});
