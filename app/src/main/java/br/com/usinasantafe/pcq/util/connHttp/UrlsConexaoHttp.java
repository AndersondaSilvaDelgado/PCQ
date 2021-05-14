package br.com.usinasantafe.pcq.util.connHttp;

import br.com.usinasantafe.pcq.PCQContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pcqdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pcqdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pcq.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PCQContext.versaoAplic.replace(".", "_");

    public static String BrigadistaBean = urlPrincipal + "brigadista.php" + put;
    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String EquipBean = urlPrincipal + "equip.php" + put;
    public static String OrigemFogoBean = urlPrincipal + "origemfogo.php" + put;
    public static String QuestaoBean = urlPrincipal + "questao.php" + put;
    public static String RespBean = urlPrincipal + "resp.php" + put;
    public static String SecaoBean = urlPrincipal + "secao.php" + put;
    public static String TalhaoBean = urlPrincipal + "talhao.php" + put;
    public static String TercCombBean = urlPrincipal + "terccomb.php" + put;
    public static String TipoApontBean = urlPrincipal + "tipoapont.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirFormCompleto() {
        return urlPrincEnvio + "inserirformcompleto.php" + put;
    }

    public String getsInserirFormComplementar() {
        return urlPrincEnvio + "inserirformcomplementar.php" + put;
    }

    public String getsInsertLogErro() {
        return urlPrincEnvio + "inserirlogerro.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        } else if (classe.equals("Form")) {
            retorno = urlPrincipal + "formreaj.php" + put;
        }
        return retorno;
    }

}
