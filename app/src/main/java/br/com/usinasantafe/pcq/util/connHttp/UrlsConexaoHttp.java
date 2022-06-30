package br.com.usinasantafe.pcq.util.connHttp;

import br.com.usinasantafe.pcq.PCQContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PCQContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/pcqdev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcqqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcqprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pcq.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp";

    public static String BrigadistaBean = url + "brigadista.php";
    public static String ColabBean = url + "colab.php";
    public static String EquipBean = url + "equip.php";
    public static String OrigemFogoBean = url + "origemfogo.php";
    public static String QuestaoBean = url + "questao.php";
    public static String RespBean = url + "resp.php";
    public static String SecaoBean = url + "secao.php";
    public static String TalhaoBean = url + "talhao.php";
    public static String TercCombBean = url + "terccomb.php";
    public static String TipoApontBean = url + "tipoapont.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirFormCompleto() {
        return url + "inserirformcompleto.php";
    }

    public String getsInserirFormComplementar() {
        return url + "inserirformcomplementar.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = url + "atualaplic.php";
        } else if (classe.equals("Cabec")) {
            retorno = url + "formreaj.php";
        }
        return retorno;
    }

}
