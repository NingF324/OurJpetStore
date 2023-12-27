$(function (){
    $('#keyword').on('keyup', function(){
        var keyword=$(this).val();
        if(keyword !==''&&keyword !==null&&keyword.length !==0){
            $.ajax({
                type    :"get",
                url     :'http://localhost:8080/OurJpetStore/productAuto?keyword='+keyword,
                success :function (data){
                    console.log(data);
                    var productListHTML='';
                    for(var i=0;i<data.length;i++){
                        productListHTML+='<li class=\"productAutoItem\" data-productId="';
                        productListHTML+=data[i].productId;
                        productListHTML+='">';
                        productListHTML+=data[i].categoryId;
                        productListHTML+=': ';
                        productListHTML+=data[i].name;
                        productListHTML+='</li>';
                    }
                    $('#productAutoList').html(productListHTML);
                    $('#productAutoList').show();
                    $('#productAutoComplete').show();
                },
                error   :function (errorMsg){
                    console.log(errorMsg);
                }
            });
        }else{
            $('#productAutoComplete').hide();
        }

        $(document).on('click','.productAutoItem',function(){
            var productId=$(this).data('productid');
            $('#productAutoComplete').hide();
            $('#keyword').val('');
            window.location.href='http://localhost:8080/OurJpetStore/productForm?productId='+productId;
        });         //此处鼠标指针样式需要修改

        // 鼠标划入事件监听函数
        $(document).on('mouseenter', '.productAutoItem', function () {
            $(this).addClass('highlighted'); // 添加一个类来改变文字样式
            $(this).css('color','blue');
        });

        // 鼠标移出事件监听函数
        $(document).on('mouseleave', '.productAutoItem', function () {
            $(this).removeClass('highlighted'); // 移除添加的类，恢复默认状态
            $(this).css('color','');
        });

        $('#productAutoComplete').on('mouseleave' ,function (){
           $(this).hide();
           $('#keyword').val('');
        });
    })
})