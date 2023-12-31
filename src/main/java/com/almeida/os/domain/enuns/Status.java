package com.almeida.os.domain.enuns;

public enum Status {

	
	ABERTO(0, "ABERTO"),
	EMANDAMENTO(1,"EMANDAMENTO"),
	ENCERRADO(2,"ENCERRADO");
	
	private Integer cod;
	private String description;
	
	private Status(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Status toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Prioridade com codigo invalido");
		
	}
	
}
