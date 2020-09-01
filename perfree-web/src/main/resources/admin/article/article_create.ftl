<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>面板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no"/>
    <meta name="renderer" content="webkit"/>
    <meta name="force-rendering" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="/public/libs/editormd/css/editormd.min.css" rel="stylesheet"/>
    <link href="/public/libs/mdui/css/mdui.min.css" rel="stylesheet"/>
    <link href="/admin/static/css/article_create.css" rel="stylesheet"/>
</head>
<body>
    <div class="p-container">
        <div class="p-container-left">
            <div class="mdui-textfield mdui-textfield-floating-label">
                <label class="mdui-textfield-label">文章标题</label>
                <input class="mdui-textfield-input" type="text"/>
            </div>
            <div class="editor-switch-box">
                当前正在使用<span id="currEditor">MarkDown</span>编辑器,点击 <a class="mdui-text-color-indigo editor-switch-btn">切换富文本编辑器</a>
            </div>
           <div id="editorBox">
               <div id="editor"></div>
           </div>
        </div>
        <div class="p-container-right">

        </div>
    </div>
    <script src="/public/libs/jquery/jquery-3.5.1.min.js"></script>
    <script src="/public/libs/editormd/editormd.min.js"></script>
    <script src="/public/libs/wangeditor/wangEditor.js"></script>
    <script src="/public/libs/mdui/js/mdui.min.js"></script>
    <script src="/admin/static/js/article_create.js"></script>
</body>
</html>