package com.valles.bodytrain.db;

import android.provider.BaseColumns;

	public class BDTContract {
	
		public static final String DB_NAME = "BDBodytrain.db";
		public static final int DB_VERSION = 19;
	
	private BDTContract() {
		
	}
	
	public static class EntrenamientosTable implements BaseColumns{
		private EntrenamientosTable() {}
		
		public static final String TABLE_NAME = "Entrenamientos";
		public static final String ID_ENTRE = "entreid";
		public static final String TIT_ENTRE = "entretit";
		public static final String IMG_ENTRE = "entreimg";
		public static final String INT_ENTRE = "entreint";
		public static final String TIME_ENTRE = "entretime";
		public static final String NUM_ENTRE = "entrenum";
		public static final String TIPO_ENTRE = "entretipo";
	}
	
	public static class EjercicioTable implements BaseColumns{
		private EjercicioTable() {}
		
		public static final String TABLE_NAME = "Ejercicios";
		public static final String ID_EJER = "ejeid";
		public static final String TIT_EJER = "ejetit";
		public static final String IMG_EJER = "ejeimg";
		public static final String SERIES_EJER = "ejeseries";
		public static final String REP_EJER = "ejerep";
		public static final String PESO_EJER = "ejepeso";
		public static final String TIME_EJER = "ejetime";
		public static final String GRUPO_EJER = "ejegrupo";	
	}
	
	public static class RelacionTable implements BaseColumns{
		private RelacionTable() {}
		
		public static final String TABLE_NAME = "Relacion";
		public static final String ID_RUTINA = "id_rutina";
		public static final String ID_EJERCICIO = "id_ejercicio";		
	}
	
	public static class HistorialTable implements BaseColumns{
		private HistorialTable() {}
		
		public static final String TABLE_NAME = "Historial";
		public static final String ID_HIST = "hist_id";
		public static final String DATE_HIST = "hist_date";
		public static final String TIT_HIST = "hist_tit";
		public static final String IMG_HIST = "hist_img";
		public static final String SERIES_HIST = "hist_series";
		public static final String ACTUAL_SERIES_HIST = "hist_series_catual";
		public static final String SERIE1_PESO_HIST = "hist_peso_serie_1";
		public static final String SERIE1_REP_HIST = "hist_rep_serie_1";
		public static final String SERIE2_PESO_HIST = "hist_peso_serie_2";
		public static final String SERIE2_REP_HIST = "hist_rep_serie_2";
		public static final String SERIE3_PESO_HIST = "hist_peso_serie_3";
		public static final String SERIE3_REP_HIST = "hist_rep_serie_3";
		public static final String REPET_TIME_HIST = "hist_time_repet";
		public static final String GRUPO_EJE_HIST = "hist_eje_grupo";
		public static final String TIT_ENTRE_HIST = "hist_entre_tit";
	}
}
