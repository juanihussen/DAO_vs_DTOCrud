package com.example.DTOvsDAO.presentation.controllers;

import com.example.DTOvsDAO.presentation.dto.EmpleadoDTO;
import com.example.DTOvsDAO.service.interfaces.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")


public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;


    //FindAll empleados
    @GetMapping("/find")
    public ResponseEntity<List<EmpleadoDTO>> findAll() {
        return new ResponseEntity<>(this.empleadoService.findAll(), HttpStatus.OK);
    }

    //FindById empleado
    @GetMapping("/find/{id}")
    public ResponseEntity<EmpleadoDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.empleadoService.findById(id), HttpStatus.OK);
    }

    //Create empleado
    @PostMapping("/create")
    public ResponseEntity<EmpleadoDTO> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO ) {
        return new ResponseEntity<>(this.empleadoService.createEmpleado(empleadoDTO),HttpStatus.CREATED);
    }

    //Update empleado
    @PutMapping("/update/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@RequestBody EmpleadoDTO empleadoDTO,@PathVariable Long id){
        return new ResponseEntity<>(this.empleadoService.updateEmpleado(empleadoDTO,id),HttpStatus.OK);
    }

    //Delete empleado
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable Long id){
        return new ResponseEntity<>(this.empleadoService.deleteEmpleado(id),HttpStatus.NO_CONTENT);
    }



}
