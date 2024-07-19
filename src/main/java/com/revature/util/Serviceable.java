package com.revature.util;

import java.util.List;
import java.util.Optional;

public interface Serviceable<T> {


    Optional<T> findById(int id);


}
