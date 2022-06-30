package br.com.usinasantafe.pcq.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pcq.model.bean.estaticas.BrigadistaBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.OrigemFogoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.RespBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.SecaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TercCombBean;
import br.com.usinasantafe.pcq.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.BrigadistaItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.CabecBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.EquipItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.FotoItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.RespItemBean;
import br.com.usinasantafe.pcq.model.bean.variaveis.TalhaoItemBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pcq_db";
	public static final int FORCA_BD_VERSION = 2;

	private static DatabaseHelper instance;

	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);
		instance = this;
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			TableUtils.createTable(cs, BrigadistaBean.class);
			TableUtils.createTable(cs, ColabBean.class);
			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, OrigemFogoBean.class);
			TableUtils.createTable(cs, QuestaoBean.class);
			TableUtils.createTable(cs, RespBean.class);
			TableUtils.createTable(cs, SecaoBean.class);
			TableUtils.createTable(cs, TalhaoBean.class);
			TableUtils.createTable(cs, TercCombBean.class);
			TableUtils.createTable(cs, TipoApontBean.class);

			TableUtils.createTable(cs, BrigadistaItemBean.class);
			TableUtils.createTable(cs, CabecBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, EquipItemBean.class);
			TableUtils.createTable(cs, FotoItemBean.class);
			TableUtils.createTable(cs, LogErroBean.class);
			TableUtils.createTable(cs, LogProcessoBean.class);
			TableUtils.createTable(cs, RespItemBean.class);
			TableUtils.createTable(cs, TalhaoItemBean.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){

				TableUtils.dropTable(cs, BrigadistaBean.class, true);
				TableUtils.dropTable(cs, ColabBean.class, true);
				TableUtils.dropTable(cs, EquipBean.class, true);
				TableUtils.dropTable(cs, OrigemFogoBean.class, true);
				TableUtils.dropTable(cs, QuestaoBean.class, true);
				TableUtils.dropTable(cs, RespBean.class, true);
				TableUtils.dropTable(cs, SecaoBean.class, true);
				TableUtils.dropTable(cs, TalhaoBean.class, true);
				TableUtils.dropTable(cs, TercCombBean.class, true);
				TableUtils.dropTable(cs, TipoApontBean.class, true);

				TableUtils.dropTable(cs, BrigadistaItemBean.class, true);
				TableUtils.dropTable(cs, CabecBean.class, true);
				TableUtils.dropTable(cs, ConfigBean.class, true);
				TableUtils.dropTable(cs, EquipItemBean.class, true);
				TableUtils.dropTable(cs, FotoItemBean.class, true);
				TableUtils.dropTable(cs, LogErroBean.class, true);
				TableUtils.dropTable(cs, LogProcessoBean.class, true);
				TableUtils.dropTable(cs, RespItemBean.class, true);
				TableUtils.dropTable(cs, TalhaoItemBean.class, true);

				TableUtils.createTable(cs, BrigadistaBean.class);
				TableUtils.createTable(cs, ColabBean.class);
				TableUtils.createTable(cs, EquipBean.class);
				TableUtils.createTable(cs, OrigemFogoBean.class);
				TableUtils.createTable(cs, QuestaoBean.class);
				TableUtils.createTable(cs, RespBean.class);
				TableUtils.createTable(cs, SecaoBean.class);
				TableUtils.createTable(cs, TalhaoBean.class);
				TableUtils.createTable(cs, TercCombBean.class);
				TableUtils.createTable(cs, TipoApontBean.class);

				TableUtils.createTable(cs, BrigadistaItemBean.class);
				TableUtils.createTable(cs, CabecBean.class);
				TableUtils.createTable(cs, ConfigBean.class);
				TableUtils.createTable(cs, EquipItemBean.class);
				TableUtils.createTable(cs, FotoItemBean.class);
				TableUtils.createTable(cs, LogErroBean.class);
				TableUtils.createTable(cs, LogProcessoBean.class);
				TableUtils.createTable(cs, RespItemBean.class);
				TableUtils.createTable(cs, TalhaoItemBean.class);

			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
