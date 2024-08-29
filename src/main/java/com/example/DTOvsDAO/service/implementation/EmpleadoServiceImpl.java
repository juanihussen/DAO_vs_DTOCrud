package com.example.DTOvsDAO.service.implementation;

import com.example.DTOvsDAO.persistence.dao.interfaces.IEmpleadoDAO;
import com.example.DTOvsDAO.persistence.entity.EmpleadoEntity;
import com.example.DTOvsDAO.presentation.dto.EmpleadoDTO;
import com.example.DTOvsDAO.service.interfaces.IEmpleadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDAO empleadoDAO;

    @Override
    public List<EmpleadoDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        return this.empleadoDAO.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, EmpleadoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO findById(Long id) {

        Optional<EmpleadoEntity> empleadoEntity = this.empleadoDAO.findById(id);

        if(empleadoEntity.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            EmpleadoEntity currentEmpleado = empleadoEntity.get();
            return modelMapper.map(currentEmpleado, EmpleadoDTO.class);
        } else{
          return new EmpleadoDTO();
        }
    }

    @Override
    public EmpleadoDTO createEmpleado(EmpleadoDTO empleadoDTO) {

        try{
            ModelMapper modelMapper = new ModelMapper();
            EmpleadoEntity empleadoEntity = modelMapper.map(empleadoDTO, EmpleadoEntity.class);
            this.empleadoDAO.saveEmpleado(empleadoEntity);
            return empleadoDTO;
        } catch (Exception e){
            throw new UnsupportedOperationException("Error al guardar el empleado.");
        }
    }

    @Override
    public EmpleadoDTO updateEmpleado(EmpleadoDTO empleadoDTO, Long id) {

        Optional<EmpleadoEntity> empleadoEntity = this.empleadoDAO.findById(id);

        if(empleadoEntity.isPresent()){
            EmpleadoEntity currentEmpleadoEntity = empleadoEntity.get();
            currentEmpleadoEntity.setName(empleadoDTO.getName());
            currentEmpleadoEntity.setLastName(empleadoDTO.getLastName());
            currentEmpleadoEntity.setMail(empleadoDTO.getMail());
            currentEmpleadoEntity.setAge(empleadoDTO.getAge());

            this.empleadoDAO.updateEmpleado(currentEmpleadoEntity);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentEmpleadoEntity, EmpleadoDTO.class);
        } else{
            throw new IllegalArgumentException("El empleado no existe.");
        }
    }

    @Override
    public String deleteEmpleado(Long id) {

        Optional<EmpleadoEntity> empleadoEntity = this.empleadoDAO.findById(id);

        if(empleadoEntity.isPresent()){
            EmpleadoEntity currentEmpleadoEntity = empleadoEntity.get();
            this.empleadoDAO.deleteEmpleado(currentEmpleadoEntity);
            return "Empleado con ID :  " + id + "ha sido eliminado";

        } else{
            return "El empleado con ID :  " + id + "no existe";
        }
    }
}
