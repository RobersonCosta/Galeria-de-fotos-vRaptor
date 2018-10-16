

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- para utilizar o foreach e o if e etc.. -->
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

    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top navbar" role="navigation">
            <div class="navbar-header">                     
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </button> <p class="navbar-brand" id="home">${user} está acessando sua(s) galeria(s)</p>
            </div>                            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>

                    </li>
                </ul>  
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/galeria/tela_inserir"><b><i>Adicionar Galeria</b></i></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/usuario/logout">Deslogar                             </a>
                    </li>   

                </ul>                                  
            </div>                
        </nav>
        <div class="jumbotron">
            <h2 align="center">Visualize, edite ou remova as suas galerias abaixo</h2>
        </div>
        <div class="row" >
            <table  style="width:60%;"class="table table-hover" align="center">
                <c:forEach items="${vetGaleria}" var="galeria">
                    <tr>                              
                        <td> ${galeria.titulo} </td> 
                        <td> ${galeria.descricao} </td> 
                        <td align="right"> 
                            <a href="${pageContext.request.contextPath}/galeria/visualiza/${galeria.id}" class="btn btn-default"> Visualizar  </a>  
                            <a href="${pageContext.request.contextPath}/galeria/deletar/${galeria.id}" class="btn btn-default"> Deletar  </a> 
                            <a href="${pageContext.request.contextPath}/galeria/tela_editar/${galeria.id}" class="btn btn-default"> Editar  </a>
                        </td> 

                    </tr>                       
                </c:forEach>
            </table>
        </div>
    </body>
</html>
