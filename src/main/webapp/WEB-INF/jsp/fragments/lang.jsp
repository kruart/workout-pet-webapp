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
        window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;
    }
</script>