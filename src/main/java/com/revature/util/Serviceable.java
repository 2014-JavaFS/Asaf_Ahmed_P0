package com.revature.util;

import java.util.List;
import java.util.Optional;

public interface Serviceable<T> {

    List<T> findAll();
    Optional<T> findById(int id);
    void delete(int id);

}
