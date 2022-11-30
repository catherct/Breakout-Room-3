package com.company.gamestore.repository;
import com.company.gamestore.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConsoleControllerRepository extends JpaRepository <Console, Integer>{
    //    List<Console> findConsoleById(int id);
    List<Console> findConsoleByManufacturer (String manufacturer);
}
