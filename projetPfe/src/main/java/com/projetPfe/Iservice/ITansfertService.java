package com.projetPfe.Iservice;

import java.util.List;

import com.projetPfe.entities.Transfert;

public interface ITansfertService {
	public Transfert findTransfert(String id) ;
	public List<Transfert> getAllTransferts() ;
	

}
