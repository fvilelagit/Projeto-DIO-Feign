package com.highschool.exceptions;



public class ExcessaoAlunoNaoEncontrado extends RuntimeException{

		private static final long serialVersionUID = 1L;
		
		public ExcessaoAlunoNaoEncontrado(Object id) {
			super("Resource not found" + id);
		}
}
	
