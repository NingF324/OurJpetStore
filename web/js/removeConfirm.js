$(document).ready(function() {
    // 当点击 Remove 链接时
    $('#removeBtn').on('click', function(event) {
        event.preventDefault(); // 阻止默认链接行为

        // 获取确认删除的信息
        const confirmDelete = confirm('Are you sure you want to remove this item?');

        // 如果用户确认删除
        if (confirmDelete) {
            const url = $(this).attr('href'); // 获取链接的目标 URL

            window.location.href = url;
            console.log('Item will be removed:', url);
        } else {
            // 如果用户取消删除，则阻止进一步的操作
            console.log('Deletion canceled');
        }
    });
});
