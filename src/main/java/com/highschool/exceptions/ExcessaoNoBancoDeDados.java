package com.highschool.exceptions;




public class ExcessaoNoBancoDeDados extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExcessaoNoBancoDeDados(String msg) {
		super(msg);
	}
}