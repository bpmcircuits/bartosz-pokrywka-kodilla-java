package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Item;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ItemDao extends CrudRepository<Item, Integer> {
}
