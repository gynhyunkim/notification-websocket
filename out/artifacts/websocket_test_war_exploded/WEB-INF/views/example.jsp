<%--
  Created by IntelliJ IDEA.
  User: gyeonghyun
  Date: 2023/05/15
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        var ws = new WebSocket("ws://localhost:8080/echo");


        ws.onopen = function () {
            console.log('Info: connection opened.');
            setTimeout( function(){ connect(); }, 1000); // retry connection!!
        };


        ws.onmessage = function (event) {
            console.log(event.data+'\n');
        };


        ws.onclose = function (event) { console.log('Info: connection closed.'); };
        ws.onerror = function (event) { console.log('Info: connection closed.'); };

        // $('#btnSend').on('click', function(evt) {
        //     evt.preventDefault();
        //     if (socket.readyState !== 1) return;
        //     let msg = $('input#msg').val();
        //     ws.send(msg);
        // });

    </script>
    <title>Title</title>
</head>
<body>
    <H1>Hello, World!</H1>
</body>
</html>
