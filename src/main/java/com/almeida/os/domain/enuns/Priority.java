package com.almeida.os.domain.enuns;

public enum Priority {

	
	BAIXA(0, "BAIXA"),
	MEDIA(1,"MEDIA"),
	ALTA(2,"ALTA");
	
	private Integer cod;
	private String description;
	
	private Priority(Integer cod, String description) {
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
	
	public static Priority toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(Priority x : Priority.values()) {
			
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status com codigo invalido " + cod);
		
	}
	
}
