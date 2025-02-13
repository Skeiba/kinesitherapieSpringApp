package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.entities.Machine;

import java.util.List;

public interface MachineService {

    Machine createMachine(Machine machine);
    List<Machine> getAllMachines();
    Machine getMachineById(Long id);
    Machine updateMachine(Long id, Machine machineDetails);
    void deleteMachine(Long id);
    List<Machine> getAllMachinesBySalle(Long salle_id);
    void deleteMachineBySalle(Long salle_id);
    Machine assignMachineToSalle(Long machineId, Long salleId);

}
