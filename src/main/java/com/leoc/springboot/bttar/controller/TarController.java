package com.leoc.springboot.bttar.controller;

import com.leoc.springboot.bttar.model.Companies;
import com.leoc.springboot.bttar.model.Tar;
import com.leoc.springboot.bttar.payload.response.MessageResponse;
import com.leoc.springboot.bttar.services.TarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://bttar-frotend.netlify.app/"
})
@RestController
@RequestMapping("/bttar/v1.0")
public class TarController {

    @Autowired
    private TarService tarService;

    @PostMapping("/tar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addTar(@RequestBody Tar tar){

        tarService.addTar(tar);

        return ResponseEntity.ok(new MessageResponse("Tar agregado extiosamente"));

    }

    @GetMapping("/tars")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTars(){

        List<Tar> getTars = tarService.getAllTar();


        return ResponseEntity.ok(getTars);
    }

    @GetMapping("/tar/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTarById(@PathVariable String id){

        Optional<Tar> tar = tarService.getTarByid(id);

        if(tar.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: tar con id '"+id+"' no existe"));
        }
        return ResponseEntity.ok(tar);


    }

    @PutMapping("/tar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTar(@Valid @RequestBody Tar tar, @PathVariable String id){

        Optional<Tar> tarById = tarService.getTarByid(id);

        if(tarById.isPresent()){
            Tar tarUpdate = tarById.get();
            tarUpdate.setYear(tar.getYear());
            tarUpdate.setMonth(tar.getMonth());
            tarUpdate.setAreas(tar.getAreas());
            tarUpdate.setSede(tar.getSede());
            tarUpdate.setHallazgo(tar.getHallazgo());
            tarUpdate.setTypeHallazgo(tar.getTypeHallazgo());
            tarUpdate.setTar(tar.getTar());
            tarUpdate.setDetalle1(tar.getDetalle1());
            tarUpdate.setDetalle2(tar.getDetalle2());
            tarUpdate.setDetalle3(tar.getDetalle3());
            tarUpdate.setPlanAccion(tar.getPlanAccion());
            tarUpdate.setDatePlan(tar.getDatePlan());
            tarUpdate.setState(tar.getState());
            tarUpdate.setCompanyId(tar.getCompanyId());
            tarService.updateTar(tarUpdate);
            return ResponseEntity.ok(new MessageResponse("Tar actualizada con exito!!"));
        }

        else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Tar con id '"+id+"' no existe"));
        }




    }


    @GetMapping("/tar/company/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTarByCompanyId(@PathVariable String id){

        List<Tar> tars = tarService.getByCompanyId(id);

        return ResponseEntity.ok(tars);


    }

    @DeleteMapping("/tar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTarById(@PathVariable String id){


        tarService.deleteTar(id);
        return ResponseEntity.ok(new MessageResponse("Tar eliminada exitosamente"));
    }

}
