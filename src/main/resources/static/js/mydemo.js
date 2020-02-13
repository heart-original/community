function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: 'POST',
        url: "/comment",
        contentType:'application/json',
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (responese) {
            if(responese.code == 200){
                $("#comment_section").hide();
            }else {
                if(responese.code == 2003){
                    var isAccepted = confirm(responese.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=abfb42958ef9245c18ff&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }
                alert(responese.message);
            }
            console.log(responese);
        },
        dataType: "json"
    });
}