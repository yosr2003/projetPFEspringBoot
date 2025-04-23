package com.projetPfe.dto;


public class ResponseBodyDTO <T> {

	private T data;

    // Constructeur
    public ResponseBodyDTO(T data) {
        this.data = data;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
