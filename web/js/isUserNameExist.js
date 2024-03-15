
function isUserNameExist(username) {
    console.log("传递的用户名参数为：" + username); // 检查传递给函数的用户名参数
    axios.get('http://localhost:8080/OurJpetStore/isUserNameExist?username='+username)
        .then(result => {
            if (result.data) {
                console.log("用户名存在");
                $('#isUserNameExistLabel').html('用户名已存在');
            } else {
                console.log("用户名不存在");
                $('#isUserNameExistLabel').html('用户名可用');
            }
        })
        .catch(error => {
            console.error("请求出错:", error);
        });
};

$(document).ready(function() {
    $('#usernameInput').on('blur', function() {
        if($(this).val()!==null&&$(this).val()!==''&&$(this).val().length!==0){
            isUserNameExist($(this).val());
        }else {
            $('#isUserNameExistLabel').html('');
        }
    });
});
