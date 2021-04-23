package br.com.usinasantafe.pcq.model.dao;

import java.util.List;

import br.com.usinasantafe.pcq.model.bean.estaticas.QuestaoBean;

public class QuestaoDAO {

    public QuestaoDAO() {
    }

    public List<QuestaoBean> questaoList(){
        QuestaoBean questaoBean = new QuestaoBean();
        return questaoBean.orderBy("seqQuestao",true);
    }

}
