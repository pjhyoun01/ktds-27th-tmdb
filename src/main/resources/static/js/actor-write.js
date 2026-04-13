$().ready(function () {
  $("#actorWriteVO").on("submit", function (event) {
    event.preventDefault();
    $(this).find(".validation-error").remove();

    var name = $("#actorName").val();
    if (!name) {
      var nameErrorMessage = $("<div>");
      nameErrorMessage.addClass("validation-error");
      nameErrorMessage.text("이름의 형태가 아닙니다.");

      $("#actorName").after(nameErrorMessage);
    }
    var profileUrl = $("#actorProfileUrl").val();
    if (!profileUrl) {
      var profileUrlErrorMessage = $("<div>");
      profileUrlErrorMessage.addClass("validation-error");
     profileUrlErrorMessage.text("프로필사진 형태가 아닙니다.");

      $("#actorProfileUrl").after(profileUrlErrorMessage);
    }

    if ($(".validation-error").length === 0) {

      this.submit();
    }
  });
});