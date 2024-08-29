package com.example.DTOvsDAO.persistence.dao.interfaces;

import com.example.DTOvsDAO.persistence.entity.EmpleadoEntity;
import com.example.DTOvsDAO.presentation.dto.EmpleadoDTO;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoDAO {

    List<EmpleadoEntity> findAll();

    Optional<EmpleadoEntity> findById(Long id);

    void saveEmpleado(EmpleadoEntity empleadoEntity);

    void updateEmpleado(EmpleadoEntity empleadoEntity);

    void deleteEmpleado(EmpleadoEntity empleadoEntity);


}
