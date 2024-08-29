package com.example.DTOvsDAO.service.interfaces;

import com.example.DTOvsDAO.presentation.dto.EmpleadoDTO;

import java.util.List;

public interface IEmpleadoService {

    List<EmpleadoDTO> findAll();

    EmpleadoDTO findById(Long id);

    EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDTO);

    EmpleadoDTO updateEmpleado(EmpleadoDTO empleadoDTO,Long id);

    String deleteEmpleado(Long id);


}
