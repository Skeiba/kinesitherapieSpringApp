package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findMachinesBySalleId(Long salleId);

}

