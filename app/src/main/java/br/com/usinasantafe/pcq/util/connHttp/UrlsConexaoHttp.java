package br.com.usinasantafe.pcq.util.connHttp;

import br.com.usinasantafe.pcq.PCQContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pcqdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pcqdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pcq.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcq.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PCQContext.versaoAplic.replace(".", "_");

    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String EquipBean = urlPrincipal + "equip.php" + put;
    public static String QuestaoBean = urlPrincipal + "questao.php" + put;
    public static String RespBean = urlPrincipal + "resp.php" + put;
    public static String SecaoBean = urlPrincipal + "secao.php" + put;
    public static String TalhaoBean = urlPrincipal + "talhao.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirDados() {
        return urlPrincEnvio + "formulario.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        }
        return retorno;
    }

}
