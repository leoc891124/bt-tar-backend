package com.leoc.springboot.bttar.controller;

import com.leoc.springboot.bttar.model.Companies;
import com.leoc.springboot.bttar.payload.response.MessageResponse;
import com.leoc.springboot.bttar.services.CompaniesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:4200",
})
@RestController
@RequestMapping("/bttar/v1.0")
public class CompaniesController {

    @Autowired
    private CompaniesService companiesService;

    @PostMapping("/company")
    public ResponseEntity<?> addCompany(@RequestBody Companies company){

        if (companiesService.existByNit(company.getNit())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: NIT ya registrado!"));
        }

        companiesService.addCompany(company);

        return ResponseEntity.ok(new MessageResponse("Company successfully added"));

    }

    @GetMapping("/companies")
    public List<Companies> getCompanies(){
        return companiesService.getAllCompanies();
    }


    @GetMapping("/company/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCompanyById(@PathVariable String id){
        Optional<Companies> company = companiesService.getCompanyById(id);

        if(company.isEmpty()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: empresa con id '"+id+"' no existe"));
        }

        return ResponseEntity.ok(company);
    }

    @PutMapping("/company/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCompany(@Valid @RequestBody Companies company, @PathVariable String id){

        Optional<Companies> companyUpdate = companiesService.getCompanyById(id);



        if(companyUpdate.isPresent()){

            Companies compani = companyUpdate.get();

            if (companiesService.existByNit(company.getNit()) && !compani.getNit().equals(company.getNit())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: NIT ya registrado!"));
            }


            compani.setNit(company.getNit());
            compani.setName(company.getName());
            compani.setAddress(company.getAddress());
            companiesService.updateCompany(compani);
            return ResponseEntity.ok(new MessageResponse("Empresa actualizada con exito!!"));
        }

        else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: empresa con id '"+id+"' no existe"));
        }




    }

    @DeleteMapping("/company/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCompanyById(@PathVariable String id){




        companiesService.delteCompany(id);
        return ResponseEntity.ok(new MessageResponse("Empresa eliminada exitosamente"));
    }

}
