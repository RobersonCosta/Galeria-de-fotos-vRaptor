
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


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
            <h2 align="center">Inserindo uma imagem</h2>
        </div>
        <div style="width:40%; height:100%; margin: 0 auto; " id="cadastro">
            <form role="form" action="${pageContext.request.contextPath}/imagem/inserir" method="post" enctype = "multipart/form-data" class="form-horizontal">                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Descrição </label>
                    <div class="col-sm-10">
                        <input type="text" name="imagem.descricao" class="form-control" placeholder="Descrição">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Arquivo </label>
                    <div class="col-sm-10">
                        <input type="file" name="file" accept="image/*">
                    </div>
                </div>                
                <input type="hidden" value="${galeria.id}" name="imagem.galeria.id">
                <div class="form-group" style="padding-left:35%">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" align="center"> Concluir </button>  
                    </div>     
                </div>     
            </form>
        </div>
    </body>
</html>
