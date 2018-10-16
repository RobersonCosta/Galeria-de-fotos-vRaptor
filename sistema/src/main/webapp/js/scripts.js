 $(function(){
            //chama a função de scroll
            navegation($('nav a'),0);
        });
     
        //função para o scroll do menu
        function navegation(elemento,desconto){
            elemento.bind('click',function(event){
                var $anchor = $(this);
                var calculo = $($anchor.attr('href')).offset().top;
                if(desconto){
                    var goto = calculo-desconto;
                }else{
                    var goto = calculo;
                }
     
                $('html, body').stop().animate({
                    scrollTop: goto
                }, 900,'easeInOutExpo');
     
                event.preventDefault();
        });             
}
