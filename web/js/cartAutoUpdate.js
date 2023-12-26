$(document).ready(function() {
    $('.quantityText').on('blur', function() {
        let $row = $(this).closest('tr');
        let itemId = $row.find('.itemId').text();
        let quantity = $(this).val();

        axios.get('http://localhost:8080/OurJpetStore/cartAutoUpdate?itemId=' + itemId + '&quantity=' + quantity)
            .then(result => {
                let price = result.data; // 假设返回的是价格数字
                let formattedPrice = formatPrice(price); // 使用函数进行货币格式化
                if (price === 0) {
                    formattedPrice = '0.00';
                }
                $row.find('.unitPrice').text(formattedPrice);

                let totalUnitPrice = 0; // 初始化总的 unitPrice 之和为 0

                // 遍历每一行，并累加 unitPrice
                $('.unitPrice').each(function() {
                    let unitPriceText = $(this).text().replace(/[^\d.-]/g, ''); // 获取单行的 unitPrice，去除非数字字符
                    let unitPriceValue = parseFloat(unitPriceText); // 将字符串转换为数值

                    if (!isNaN(unitPriceValue)) { // 确保是有效的数值
                        totalUnitPrice += unitPriceValue; // 累加单行的 unitPrice
                    }
                });

                console.log(totalUnitPrice);

                // 更新 Sub Total 的显示值为 totalUnitPrice
                $('#sumPriceNum').text(formatPrice(totalUnitPrice));


            })
            .catch(error => {
                console.error("请求出错:", error);
            });
    });

    // 自定义函数来格式化价格为货币格式
    function formatPrice(price) {
        // 这里可以使用 Intl.NumberFormat 或其他库进行格式化
        // 例如，使用 Intl.NumberFormat
        var formatter = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
            minimumFractionDigits: 2,
        });
        return formatter.format(price);
    }
});
