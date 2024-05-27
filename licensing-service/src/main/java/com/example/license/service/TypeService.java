package com.example.license.service;

import com.example.license.model.Type;
import com.example.license.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public Type getType(int typeId){
        return typeRepository.findById(typeId).orElse(null);
    }

    public Iterable<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    public void createType(Type type){
        if (type != null){
            typeRepository.save(type);
        }
    }

    public void updateType(int typeId, Type type){
        Type existingType = typeRepository.findById(typeId).orElse(null);
        if (existingType != null) {
            type.setId(typeId);
            typeRepository.save(type);
        }
    }

    public void deleteType(int typeId){
        typeRepository.deleteById(typeId);
    }
}
