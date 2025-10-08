package com.leoc.springboot.bttar.services;

import com.leoc.springboot.bttar.model.Tar;
import com.leoc.springboot.bttar.payload.response.MessageResponse;
import com.leoc.springboot.bttar.repository.TarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarService {

    @Autowired
    private TarRepo tarRepo;

    public List<Tar> getAllTar(){
        return tarRepo.findAll();
    }

    public void addTar(Tar tar){
        tarRepo.save(tar);
    }

    public void updateTar(Tar tar){
        tarRepo.save(tar);
    }

    public void deleteTar(String id){
        tarRepo.deleteById(id);
    }

    public List<Tar> getByCompanyId(String id){
        return tarRepo.findByCompanyId(id);
    }

    public Optional<Tar> getTarByid(String id){
        return tarRepo.getTarById(id);


    }
}
