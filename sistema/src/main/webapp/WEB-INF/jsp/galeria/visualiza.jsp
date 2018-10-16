
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- para utilizar o foreach e o if e etc.. -->
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
        <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lightbox.js"></script>

        <link href="${pageContext.request.contextPath}/css/lightbox.css" rel="stylesheet" />

        <script type="text/javascript">
            $(function () {
                $('#gallery a').lightBox();
            });
        </script>

        <!-- jQuery lightBox plugin - Gallery style */ -->
        <style type="text/css">
            #gallery {
                background-color: #fff;
                padding: 10px;
                width: auto;
            }
            #gallery ul { list-style: none; }
            #gallery ul li { display: inline; }
            #gallery ul img {
                border: 5px solid #444444;
                border-width: 5px 5px 20px;
            }
            #gallery ul a:hover img {
                border: 5px solid #fff;
                border-width: 5px 5px 20px;
                color: #fff;
            }
            #gallery ul a:hover { color: #fff; }
        </style>

    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top navbar" role="navigation">

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">                    
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/imagem/tela_inserir/${galeria.id}"><b><i>Adicionar Imagem</b></i></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/galeria/compactado/${galeria.id}" download="allImages.zip"><b><i>Baixar Galeria</b></i></a>
                    </li>   
                    <li>
                        <a href="${pageContext.request.contextPath}/galeria/listar">Retornar           </a>
                    </li>   
                </ul>                                  
            </div>                
        </nav>

        <div class="jumbotron">
            <h2 align="center">Galeria ${galeria.titulo}</h2>
            <p align="center">Descrição: ${galeria.descricao}</p>
        </div>
        <table  style="width:25%;"class="table table-hover" align="center">
            <c:forEach items="${vetImagem}" var="imagem">
                <tr>
                    <td align="center"><div class="single">
                            <a href="${pageContext.request.contextPath}/imagem/baixar/${imagem.arquivo.name}" rel="lightbox[plants]" title="${imagem.descricao}"><img src="${pageContext.request.contextPath}/imagem/baixar/${imagem.arquivo.name}" width="160px" height="165px"/></a>
                        </div>
                    </td>
                    <td align="right">  
                        <a href="${pageContext.request.contextPath}/imagem/deletar/${imagem.id}" class="btn btn-default"> Deletar  </a>          
                        <a href="${pageContext.request.contextPath}/imagem/baixar/${imagem.arquivo.name}" download="${imagem.arquivo.name}" class="btn btn-default"> Baixar  </a> 
                    </td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
