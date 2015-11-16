package com.xpanxion.wallboard.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpanxion.wallboard.rest.dao.hibernate.HibernateKudoDao;
import com.xpanxion.wallboard.rest.dto.kudos.Kudo;

@Service
public class KudoServiceImpl implements KudoService {

	@Autowired
	private HibernateKudoDao kudoDao;
	
	@Override
	public Kudo createNewKudo(Kudo kudo) {
		kudo.setCreateTime(new Date().getTime());
		return this.kudoDao.save(kudo);
	}

	@Override
	public Kudo getKudoById(Long kudoId) {
		return this.kudoDao.findOne(kudoId);
	}

	@Override
	public List<Kudo> getAllKudos() {
		return (List<Kudo>) this.kudoDao.findAll();
	}

	@Override
	public Kudo updateKudo(Long kudoId, Kudo kudo) {
		final Kudo existingKudo = this.getKudoById(kudoId);
		BeanUtils.copyProperties(kudo, existingKudo, "id");
		return this.kudoDao.save(existingKudo);
	}

	@Override
	public void deleteKudoById(Long kudoId) {
		final Kudo existingKudo = this.getKudoById(kudoId);
		this.kudoDao.delete(existingKudo);
	}

}
