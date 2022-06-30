package br.com.usinasantafe.pcq.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcq.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pcq.model.dao.BrigadistaDAO;
import br.com.usinasantafe.pcq.model.dao.CabecDAO;
import br.com.usinasantafe.pcq.model.dao.ConfigDAO;
import br.com.usinasantafe.pcq.model.dao.EquipDAO;
import br.com.usinasantafe.pcq.model.dao.FotoDAO;
import br.com.usinasantafe.pcq.model.dao.LogErroDAO;
import br.com.usinasantafe.pcq.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcq.model.dao.RespostaDAO;
import br.com.usinasantafe.pcq.model.dao.TalhaoDAO;
import br.com.usinasantafe.pcq.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean getConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig(senha);
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    public void salvarConfig(Long nroAparelho){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho);
    }

    /////////////////////////////////////////// LOG ///////////////////////////////////////////////

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        return logProcessoDAO.logProcessoList();
    }

    public ArrayList<String> logBaseDadoList(){
        ArrayList<String> dadosArrayList = new ArrayList<>();
        CabecDAO cabecDAO = new CabecDAO();
        RespostaDAO respostaDAO = new RespostaDAO();
        BrigadistaDAO brigadistaDAO = new BrigadistaDAO();
        EquipDAO equipDAO = new EquipDAO();
        FotoDAO fotoDAO = new FotoDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        dadosArrayList = cabecDAO.cabecAllArrayList(dadosArrayList);
        dadosArrayList = respostaDAO.respostaAllArrayList(dadosArrayList);
        dadosArrayList = brigadistaDAO.brigadistaItemAllArrayList(dadosArrayList);
        dadosArrayList = equipDAO.equipItemAllArrayList(dadosArrayList);
        dadosArrayList = fotoDAO.fotoItemAllArrayList(dadosArrayList);
        dadosArrayList = talhaoDAO.talhaoItemAllArrayList(dadosArrayList);
        return dadosArrayList;
    }

    public List<LogErroBean> logErroList(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.logErroBeanList();
    }

    public void deleteLogs(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        LogErroDAO logErroDAO = new LogErroDAO();
        logProcessoDAO.deleteLogProcesso();
        logErroDAO.deleteLogErro();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


}
