package com.leoc.springboot.bttar.services;

import com.leoc.springboot.bttar.model.Companies;
import com.leoc.springboot.bttar.repository.CompaniesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesService {

    @Autowired
    private CompaniesRepo companiesRepo;

    public void addCompany(Companies company){

        companiesRepo.save(company);

    }

    public List<Companies> getAllCompanies(){
        return companiesRepo.findAll();
    }

    public Optional<Companies> getCompanyById(String id) {
        return companiesRepo.findById(id);

    }

    public void updateCompany(Companies company) {
        companiesRepo.save(company);

    }

    public void delteCompany(String id){
        companiesRepo.deleteById(id);
    }

    public boolean existByNit(String nit){
        return companiesRepo.existsByNit(nit);

    }
}
