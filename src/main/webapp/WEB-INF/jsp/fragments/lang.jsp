<%@page contentType="text/html" pageEncoding="UTF-8" %>

<li class="nav-item dropdown">
    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">${pageContext.response.locale}<b class="caret"></b></a>
    <ul class="dropdown-menu">
        <li><a class="dropdown-item" onclick="show('en')">English</a></li>
        <li><a class="dropdown-item" onclick="show('uk')">Українська</a></li>
        <li><a class="dropdown-item" onclick="show('ru')">Русский</a></li>
    </ul>
</li>
<script type="text/javascript">
    function show(lang) {
        if (window.location.href.indexOf("?") > -1) {
            window.location.href = window.location.href + '&lang=' + lang; //if params exists
        } else {
            window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;    //if params no exists
        }
    }
</script>