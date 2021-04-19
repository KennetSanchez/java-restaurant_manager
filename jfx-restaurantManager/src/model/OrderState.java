package model;

import java.io.Serializable;

public enum OrderState implements Serializable{
	SOLICITADO, PREPARÁNDOSE, ENVIADO, ENTREGADO, CANCELADO; 
	private static final long serialVersionUID = 1L;	
}
