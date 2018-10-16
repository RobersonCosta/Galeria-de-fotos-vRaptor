
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">


        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/full-slider.css" rel="stylesheet">    
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <style>
            .container { background-image: url(${pageContext.request.contextPath}/img/backgroundtexture.jpg);background-repeat: no-repeat;background-position: center; background-size: cover;width: 100%;height: 100%; }

            
        </style>

    </head>
    <body>

        <div class="jumbotron">
            <h2 align="center">Editando a galeria</h2>
        </div>
        <div style="width:40%; height:100%; margin: 0 auto; " id="cadastro">
            <form role="form" action="${pageContext.request.contextPath}/galeria/editar" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Título </label> 
                    <div class="col-sm-10">
                        <input type="text" name="galeria.titulo" value="${galeria.titulo}" class="form-control" placeholder="Título">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Descrição </label>
                    <div class="col-sm-10">
                        <input type="text" name="galeria.descricao" value="${galeria.descricao}" class="form-control" placeholder="Descrição">
                    </div>
                </div>
                <input type="hidden" value="${galeria.id}" name="galeria.id">
                <input type="hidden" value="${galeria.usuario.id}" name="galeria.usuario.id">
                <div class="form-group" style="padding-left:35%">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" align="center"> Concluir </button>  
                    </div>     
                </div>     
            </form>
        </div>
    </body>
</html>