package com.example.loborems.models.services;

import java.util.List;

import com.example.loborems.models.Offer;

public interface OfferDAO {
    void save(Offer offer);
    Offer findById(int id);
    List<Offer> findAll();
    void update(Offer offer);
    void delete(int id);
}