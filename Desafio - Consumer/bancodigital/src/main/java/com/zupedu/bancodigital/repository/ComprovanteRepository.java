package com.zupedu.bancodigital.repository;

import com.zupedu.bancodigital.model.Comprovante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprovanteRepository extends CrudRepository<Comprovante, Long> {

}
