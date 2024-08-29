package com.example.DTOvsDAO.persistence.dao.implementation;

import com.example.DTOvsDAO.persistence.dao.interfaces.IEmpleadoDAO;
import com.example.DTOvsDAO.persistence.entity.EmpleadoEntity;
import com.example.DTOvsDAO.presentation.dto.EmpleadoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class EmpleadoDaoImpl implements IEmpleadoDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoEntity> findAll() {
        return this.em.createQuery("SELECT e FROM EmpleadoEntity e").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(EmpleadoEntity.class, id));
    }

    @Override
    @Transactional
    public void saveEmpleado(EmpleadoEntity empleadoEntity) {
        this.em.persist(empleadoEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateEmpleado(EmpleadoEntity empleadoEntity) {
        this.em.merge(empleadoEntity);
    }

    @Override
    @Transactional
    public void deleteEmpleado(EmpleadoEntity empleadoEntity) {
        this.em.remove(empleadoEntity);
    }
}
