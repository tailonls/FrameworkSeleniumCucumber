package br.com.unicred.caixa.core.report;

public class TagsReporteHTML {

    //Primeira TAG a ser usada quando criar Report
    public static String ABRE_TAG_REPORTE() {
        return TAG_ABRE_HTML() + TAG_ADD_LINKS_EXTERNOS() + TAG_ADD_SCRIPT_JS() + TAG_ADD_STYLE_CSS() + TAG_ABRE_BODY() + TAG_MODAL_IMAGEM();
    }

    // Inicia o reporte  - apenas um deste no reporte
    private static String TAG_ABRE_HTML() {
        String titulo = "Automacao - Caixa ATM";

        return "<!DOCTYPE html>\n" +
                "   <html lang='pt'>\n" +
                "       <head>\n" +
                "           <meta charset='UTF-8'>\n" +
                "           <meta name='generator' content='HTML Tidy for HTML5 for Linux version 5.2.0'>\n" +
                "           <title>" + titulo + "</title>\n";

    }

    // Links  - apenas um deste no reporte
    private static String TAG_ADD_LINKS_EXTERNOS() {
        return "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css' integrity='sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB' crossorigin='anonymous' />\n" +
                "<script src='https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js'></script>\n" +
                "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'></script>\n";
    }

    // JavaScript  - apenas um deste no reporte
    private static String TAG_ADD_SCRIPT_JS() {
        return "<script>\n" +
                "   $(document).ready(function() {\n" +
                "       var data = new Date(); // Obtem a data/hora atual\n" +
                "       // Guarda cada pedaco em uma variavel:  \n" +
                "       var dia     = data.getDate();               // 1-31\n" +
                "       var dia_sem = data.getDay();                // 0-6 (zero=domingo)\n" +
                "       var mes     = data.getMonth();              // 0-11 (zero=janeiro)\n" +
                "       var ano2    = data.getYear();               // 2 digitos\n" +
                "       var ano4    = data.getFullYear();           // 4 digitos\n" +
                "       var hora    = data.getHours();              // 0-23\n" +
                "       var min     = data.getMinutes();            // 0-59\n" +
                "       var seg     = data.getSeconds();            // 0-59\n" +
                "       var mseg    = data.getMilliseconds();       // 0-999\n" +
                "       var tz      = data.getTimezoneOffset();     // em minutos\n" +
                "       var str_data = dia + '/' + (mes+1) + '/' + ano4;    // Formata a data e a hora (note o mes + 1\n" +
                "       var str_hora = hora + 'h' + min + 'm';\n" +
                "       $('#dataHora').html(str_data + '  ' + str_hora);\n" +
                "       var modal = document.getElementById('myModal');     // Get the modal\n" +
                "       //var img = document.getElementsByClassName('image');   // Get the image and insert it inside the modal - use its 'alt' text as a caption\n" +
                "       var modalImg = document.getElementById('img01');\n" +
                "       var captionText = document.getElementById('caption');\n" +
                "       $('.image').click(function() {\n" +
                "           console.log('Clicou na imagem ' + this.alt);\n" +
                "           modal.style.display = 'block';\n" +
                "           modalImg.src = this.src;\n" +
                "           captionText.innerHTML = this.alt;\n" +
                "});\n" +
                "var span = document.getElementsByClassName('close')[0];    // Get the <span> element that closes the modal \n" +
                "span.onclick = function() {    // When the user clicks on <span> (x), close the modal \n" +
                "   modal.style.display = 'none';\n" +
                "}});</script>\n";
    }

    // CSS  - apenas um deste no reporte
    private static String TAG_ADD_STYLE_CSS() {
        return "<style>\n" +
                "   .OK { color: green; font-weight: bold }\n" +
                "   .FAIL { color: red; font-weight: bold }\n" +
                "   .INFO { color: blue; font-weight: bold }\n" +
                "   .WARNING { color: yellow; font-weight: bold }\n" +
                "   .footer_passed { font-weight: bold; background-color: green; text-align:center; color: white; cursor: pointer; }\n" +
                "   .footer_failed { font-weight: bold; background-color: #dc3545; text-align:center; color: white; cursor: pointer; }\n" +
                "   /* Chart */\n" +
                "   #principal{\n" +
                "       width:500px;\n" +
                "       height:60px;\n" +
                "       margin-left:10px;\n" +
                "       font-family:Verdana, Helvetica, sans-serif;\n" +
                "       font-size:14px;\n" +
                "       position: absolute;\n" +
                "       top: 80px;\n" +
                "   }\n" +
                "   #principal p {\n" +
                "       position: absolute;\n" +
                "       top: 85px;\n" +
                "   }\n" +
                "   #barras{\n" +
                "       width:428px;\n" +
                "       height:30px;\n" +
                "       float:left;\n" +
                "       margin: 2px 0;\n" +
                "   }\n" +
                "   .barra1, .barra2 {\n" +
                "       color:#FFF;\n" +
                "       padding-left:10px;\n" +
                "       height:30px;\n" +
                "       line-height:30px;\n" +
                "   }\n" +
                "   .barraVermelha{ background-color: #CC0000; }\n" +
                "   .barraVerde{ background-color: #00CC00; }\n" +
                "   /* Style the Image Used to Trigger the Modal */\n" +
                "   .image {\n" +
                "       border-radius: 5px;\n" +
                "       cursor: pointer;\n" +
                "       transition: 0.3s;\n" +
                "       margin-right: 30%;\n" +
                "   }\n" +
                "   .image:hover {opacity: 0.7;}\n" +
                "   /* The Modal (background) */\n" +
                "   .modal {\n" +
                "       display: none; /* Hidden by default */\n" +
                "       position: fixed; /* Stay in place */\n" +
                "       z-index: 1; /* Sit on top */\n" +
                "       padding-top: 100px; /* Location of the box */\n" +
                "       left: 0;\n" +
                "       top: 0;\n" +
                "       width: 100%; /* Full width */\n" +
                "       height: 100%; /* Full height */\n" +
                "       overflow: auto; /* Enable scroll if needed */\n" +
                "       background-color: rgb(0,0,0); /* Fallback color */\n" +
                "       background-color: rgba(0,0,0,0.9); /* Black w/ opacity */\n" +
                "   }\n" +
                "   /* Modal Content (Image) */\n" +
                "   .modal-content {\n" +
                "       margin: auto;\n" +
                "       display: block;\n" +
                "       width: 80%;\n" +
                "       max-width: 1000px;\n" +
                "   }\n" +
                "   /* Caption of Modal Image (Image Text) - Same Width as the Image */\n" +
                "   #caption {\n" +
                "       margin: auto;\n" +
                "       display: block;\n" +
                "       width: 80%;\n" +
                "       max-width: 700px;\n" +
                "       text-align: center;\n" +
                "       color: #ccc;\n" +
                "       padding: 10px 0;\n" +
                "       height: 150px;\n" +
                "   }\n" +
                "   /* Add Animation - Zoom in the Modal */\n" +
                "   .modal-content, #caption {\n" +
                "       animation-name: zoom;\n" +
                "       animation-duration: 0.6s;\n" +
                "   }\n" +
                "   @keyframes zoom {\n" +
                "       from {transform:scale(0)}\n" +
                "       to {transform:scale(1)}\n" +
                "   }\n" +
                "   #info-cabecalho {\n" +
                "       background-color: #bbbaba;\n" +
                "       color:#FFFFFF;\n" +
                "   }\n" +
                "   #info-cabecalho-mensagem {font-size: 20px;}\n" +
                "   /* The Close Button */\n" +
                "   .close {\n" +
                "       position: absolute;\n" +
                "       top: 15px;\n" +
                "       right: 35px;\n" +
                "       color: #f1f1f1;\n" +
                "       font-size: 40px;\n" +
                "       font-weight: bold;\n" +
                "       transition: 0.3s;\n" +
                "   }\n" +
                "   .close:hover, .close:focus {\n" +
                "       color: #bbb;\n" +
                "       text-decoration: none;\n" +
                "       cursor: pointer;\n" +
                "   }\n" +
                "   /* 100% Image Width on Smaller Screens */\n" +
                "   @media only screen and (max-width: 700px){\n" +
                "   .modal-content {\n" +
                "       width: 100%;\n" +
                "   }}\n" +
                "</style>\n";
    }

    // Usar apos TAG_ABRE_HTML()  - apenas um deste no reporte
    private static String TAG_ABRE_BODY() {
        String titulo = "Report - Automação de testes Caixa ATM";
        String urlImageCabecalho = "https://novounicredinternet.e-unicred.com.br/unicred-internetbanking/resources/images/logo.png";

        return "</head>\n" +
                "<body>\n" +
                "   <nav class='navbar navbar-expand-lg navbar-dark fixed' style='margin-bottom: 140px; background-color: #066A4A !important;'>\n" +
                "       <div style='margin-right:20px'>\n" +
                "           <img src='" + urlImageCabecalho + "'>\n" +
                "       </div>\n" +
                "       <a class='navbar-brand' href='#'>" + titulo + "</a>\n" +
                "       <div class='collapse navbar-collapse' id='navbarColor02'>\n" +
                "           <ul class='navbar-nav mr-auto'><li class='nav-item active'><a class='nav-link' href='#'><span class='sr-only'>(current)</span></a></li></ul>\n" +
                "           <div class='form-inline'><span id='dataHora' class='nav-link' style='color:#FFFFFF'></span></div>\n" +
                "       </div>\n" +
                "   </nav>\n";
    }

    // Usar apos TAG_ABRE_BODY()  - apenas um deste no reporte
    private static String TAG_MODAL_IMAGEM() {
        return "<!-- The Modal -->\n" +
                "<div id='myModal' class='modal'><!-- The Close Button -->\n" +
                "   <span class='close'> x </span> <!-- Modal Content (The Image) -->\n" +
                "   <img class='modal-content' id='img01'><!-- Modal Caption (Image Text) -->\n" +
                "   <div id='caption'>teste imagem</div>\n" +
                "</div>";
    }

    // =================== =================== =================== ===================

    // Adiciona dados ao final do teste - apenas um no reporte
    public static String TAG_DADOS_TESTES(String ctsPassados, String ctsFalhados, String qntPass, String qntFail, String contadorCenarios) {

        return "<div id='principal'>" +
                "   <div id='barras'>" +
                "       <div class='barraVerde' style='width:" + ctsPassados + "%'>" + ctsPassados + "%</div>" +
                "   </div>" +
                "   <div id='barras'>" +
                "       <div class='barraVermelha' style='width:" + ctsFalhados + "%'>" + ctsFalhados + "%</div>" +
                "   </div>" +
                "   <br />" +
                "   <p><b> Total:</b> " + contadorCenarios + " <b> Passed: </b>" + qntPass + "<b> Failed:</b> " + qntFail + "</p>" +
                "</div>";
    }

    // TAG para adiconar um novo cenario no inicio de um teste
    public static String TAG_ABRIR_CENARIO(String cenario, String colapsar, String hora, String contadorCenarios) {

        return "<button style='border-color: #fff0; cursor: pointer;' data-toggle='collapse' id='btn_" + contadorCenarios + "' data-target='#div_" + contadorCenarios + "'>\n" +
                "   <h2 style='font-size: 25px'>" + cenario + " <span style='font-size: 14px'> # " + hora + "</span></h2>\n" +
                "</button>\n" +
                "<div id='div_" + contadorCenarios + "' " + colapsar + ">\n" +
                "   <table class='table table-sm' style='color: #080808; background-color: #dcd9d9b8;'>\n" +
                "   <thead>\n" +
                "       <tr>\n" +
                "           <th style='width:25%'>HORA</th>\n" +
                "           <th style='width:70%'>ATIVIDADE</th>\n" +
                "           <th style='width:5%'>STATUS</th>\n" +
                "       </tr>\n" +
                "   </thead>\n" +
                "<tbody>\n";
    }

    // TAG para fechar o cenario apos o fim do teste
    public static String TAG_FECHAR_CENARIO(String cor, String mensagem, String classe, String contadorCenarios) {

        return "                <tr data-toggle='collapse' data-target='#div_" + contadorCenarios + "' class='" + classe + "'>\n" +
                "           <td colspan='3'>" + mensagem + "</td></tr>\n" +
                "       </tbody>" +
                "   </table>" +
                "</div><br />\n" +
                "<style>#btn_" + contadorCenarios + "{ color: #fff; background-color: #" + cor + "; border-color: #fff0; width: 100%; text-align: left; }</style>\n";

    }

    // Log de secao - tipo unico
    public static String TAG_SECAO(String secao) {

        return "<tr id='info-cabecalho'>\n" +
                "   <td></td>\n" +
                "   <td id='info-cabecalho-mensagem'><b>" + secao + "</b></td>\n" +
                "   <td></td>\n" +
                "   <td></td>\n" +
                "</tr>\n";
    }

    // Log generico com imagem
    public static String TAG_LOG_IMAGEM(String log, String hora, String pathImagem, String tipo) {
        tipo = tipo.toUpperCase();

        return "<tr>\n" +
                "   <td>" + hora + "</td>\n" +
                "   <td style='line-height: 50px;' class='" + tipo + "'>" + log +
                "       <img class='image' id='myImg' src='" + pathImagem + "' alt='' style='width:100%; max-width:100px; float: right'></td>\n" +
                "   <td class='" + tipo + "'>" + tipo + "</td>\n" +
                "</tr>\n";
    }

    // Log generico
    public static String TAG_LOG(String log, String hora, String tipo) {
        tipo = tipo.toUpperCase();

        return "<tr>\n" +
                "   <td>" + hora + "</td>\n" +
                "   <td style='line-height: 50px;' class='" + tipo + "'>" + log + "</td>\n" +
                "   <td class='" + tipo + "'>" + tipo + "</td>\n" +
                "</tr>\n";
    }

    // Ultima a ser adicionada
    public static String TAG_FECHA_REPORTE() {
        return "    </body>\n" +
                "</html>";
    }
}