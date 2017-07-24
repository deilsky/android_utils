# android_utils
# ToolBarUtils 使用方法：
    ToolBarUtils.getInstance(this, R.id.toolbar).setTitleRes(R.id.toolbar_title, R.string.title_home)//中部文字，可根据自己的layout文件 自行定义
                .setIcon(R.id.left_icon, R.drawable.ic_search)//根据id 设置指定图片
                .setIcon(R.id.right_icon, R.drawable.ic_sms)//根据id 设置指定图片
                .show(R.id.left_icon)//还有相应的hidden与gone方法 显示隐藏指定控件
                .show(R.id.right_icon)// 同上
                .setOnClick(new int[]{R.id.left_icon, R.id.right_icon}, this)//点击事件，以数组形式添加，还有添加单个的点击事件
                .build();
# 更多内容 可根据源码自行定义和使用，共勉
