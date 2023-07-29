package ar.com.prueba.controllers;

import ar.com.deserialize.Partido;
import ar.com.deserialize.Pronostico;

public class UpdatePronosticoController {
	
	public static Integer calculatePoints(Pronostico pronostico, Partido partido) {
		Integer resultScoreHome = partido.getScoreHome();
		Integer resultScoreAway = partido.getScoreAway();
		Integer scoreHome = pronostico.getScoreHome();
		Integer scoreAway = pronostico.getScoreAway();
		Integer puntos;
		
		if (resultScoreHome == null || resultScoreAway == null) {
			puntos = -1;
		} else if ( scoreHome == resultScoreHome && scoreAway == resultScoreAway ) {
			puntos = 3;
		} else if((scoreHome - scoreAway) * (resultScoreHome - resultScoreAway) > 0) {
			puntos = 1;
		} else {
			puntos = 0;
		}

		return puntos;
	}
}
