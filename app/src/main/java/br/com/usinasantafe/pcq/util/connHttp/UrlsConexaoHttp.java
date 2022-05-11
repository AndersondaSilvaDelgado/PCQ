package br.com.usinasantafe.pcq.util.connHttp;

import br.com.usinasantafe.pcq.PCQContext;

public class UrlsConexaoHttp {

    public static String versao = "?versao=" + PCQContext.versaoWS.replace(".", "_");

    public static String urlPrincipal = "https://www.usinasantafe.com.br/pcqdev/view/";
    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pcqdev/view/";

//    public static String urlPrincipal = "https://www.usinasantafe.com.br/pcqqa/view/";
//    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pcqqa/view/";

//    public static String urlPrincipal = "https://www.usinasantafe.com.br/pcqprod/" + versao + "/view/";
//    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pcqprod/" + versao + "/view/";


    public static String localPSTEstatica = "br.com.usinasantafe.pcq.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp";

    public static String BrigadistaBean = urlPrincipal + "brigadista.php";
    public static String ColabBean = urlPrincipal + "colab.php";
    public static String EquipBean = urlPrincipal + "equip.php";
    public static String OrigemFogoBean = urlPrincipal + "origemfogo.php";
    public static String QuestaoBean = urlPrincipal + "questao.php";
    public static String RespBean = urlPrincipal + "resp.php";
    public static String SecaoBean = urlPrincipal + "secao.php";
    public static String TalhaoBean = urlPrincipal + "talhao.php";
    public static String TercCombBean = urlPrincipal + "terccomb.php";
    public static String TipoApontBean = urlPrincipal + "tipoapont.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirFormCompleto() {
        return urlPrincEnvio + "inserirformcompleto.php";
    }

    public String getsInserirFormComplementar() {
        return urlPrincEnvio + "inserirformcomplementar.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php";
        } else if (classe.equals("Cabec")) {
            retorno = urlPrincipal + "formreaj.php";
        }
        return retorno;
    }

}
