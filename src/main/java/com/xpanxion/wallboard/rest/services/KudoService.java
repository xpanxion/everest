package com.xpanxion.wallboard.rest.services;

import java.util.List;

import com.xpanxion.wallboard.rest.dto.kudos.Kudo;

public interface KudoService {

	/**
	 * 
	 * @param kudo
	 * @return
	 */
	Kudo createNewKudo(Kudo kudo);

	/**
	 * 
	 * @param kudoId
	 * @return
	 */
	Kudo getKudoById(Long kudoId);

	/**
	 * 
	 * @return
	 */
	List<Kudo> getAllKudos();

	/**
	 * 
	 * @param kudoId
	 * @param kudo
	 * @return
	 */
	Kudo updateKudo(Long kudoId, Kudo kudo);

	/**
	 * 
	 * @param kudoId
	 */
	void deleteKudoById(Long kudoId);

}
