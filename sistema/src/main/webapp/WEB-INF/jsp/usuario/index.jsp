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
        <script src="${pageContext.request.contextPath}/js/scripts.js"></script>   
        <script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>          
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <style>
            .container { background-image: url(${pageContext.request.contextPath}/img/backgroundtexture.jpg);background-repeat: no-repeat;background-position: center; background-size: cover;width: 100%;height: 100%; }
            .jumbotron {padding-top: 15%; background-color: transparent;}

        </style>
        <script>
            $('.carousel').carousel({
                interval: 2000

            })
        </script>

    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top navbar" role="navigation">
            <div class="navbar-header">                     
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </button> <a class="navbar-brand" href="#top" id="home">Home</a>
            </div>                
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#cadastro">Cadastrar                             </a>
                    </li>                        
                </ul>
                <form class="navbar-form navbar-right" role="form" action="${pageContext.request.contextPath}/usuario/login" method="post">
                    <div class="form-group">
                        <font color="gray">Login     </font><input class="form-control" name="usuario.login" type="text" />
                    </div> 
                    <div class="form-group">
                        <font color="gray"> Senha     </font><input class="form-control" name="usuario.senha" type="password" /><label>   </label>
                    </div> 
                    <button type="submit" class="btn btn-default">Entrar</button>

                </form>                    
            </div>                
        </nav>

        <div class="container" id="top">
            <div class="jumbotron">
                <h1 align="center">Jesus's Galleries</h1>
                <p align="center">Este é um website projetado para a criação e edição de galerias de imagens para qualquer usuário.<br />Basta cadastrar-se e aproveitar esta maravilhosa obra da programação!</p>
            </div>
        </div>
    </div>
    <header id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${pageContext.request.contextPath}/img/foto1.jpg');"></div>
                <div class="carousel-caption">
                    <h4>Para você, fotografo, que necessita armazenar suas fotografias em algum lugar!</h4>
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${pageContext.request.contextPath}/img/foto2.jpg');"></div>
                <div class="carousel-caption">
                    <h4>Deixe guardadas aqui as suas paisagens mais memoráveis e mais bonitas!</h4>
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${pageContext.request.contextPath}/img/foto3.jpg');"></div>
                <div class="carousel-caption">
                    <h4>Aquela lembrança que marcou a sua vida, ela necessita ser salva em algum lugar!</h4>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>

    </header>
    <div style="width:30%; height:100%; margin: 0 auto; padding-top: 15%;" id="cadastro">
        <div class="container">
            <h3 align="center">                           Cadastre-se abaixo </h3><br>
            <form role="form" action="${pageContext.request.contextPath}/usuario/inserir" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Login </label> 
                    <div class="col-sm-10">
                        <input type="text" name="usuario.login" class="form-control" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Senha </label>
                    <div class="col-sm-10">
                        <input type="password" name="usuario.senha" class="form-control" placeholder="Senha">
                    </div>
                </div>
                <div class="form-group" style="padding-left:30%">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" align="center"> Concluir </button>  
                    </div>     
                </div>     
            </form>
        </div>
    </div>
</body>
</html>
