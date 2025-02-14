package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.entities.Machine;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.repositories.MachineRepository;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import com.miniProjet.kinesitherapie.services.interfaces.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final SalleRepository salleRepository;

    @Override
    public Machine createMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    @Override
    public Machine getMachineById(Long id) {
        Optional<Machine> machineOptional = machineRepository.findById(id);
        if (machineOptional.isPresent()) {
            return machineOptional.get();
        } else {
            throw new RuntimeException("Machine not found with id: " + id);
        }
    }

    @Override
    public Machine updateMachine(Long id, Machine machineDetails) {
        Machine existingMachine = getMachineById(id);
        existingMachine.setModel(machineDetails.getModel());
        existingMachine.setSalle(machineDetails.getSalle());
        return machineRepository.save(existingMachine);
    }

    @Override
    public void deleteMachine(Long id) {
        Machine machine = getMachineById(id);
        machineRepository.delete(machine);
    }

    @Override
    public List<Machine> getAllMachinesBySalle(Long salleId) {
        return machineRepository.findMachinesBySalleId(salleId);
    }

    @Override
    public void deleteMachineBySalle(Long salle_id) {

    }

    @Override
    public Machine assignMachineToSalle(Long machineId, Long salleId) {
        // Trouver La Machine ----
        Optional<Machine> machineOptional = machineRepository.findById(machineId);
        if (!machineOptional.isPresent()) {
            throw new RuntimeException("Machine not found with id: " + machineId);
        }

        // Trouver La Salle ----
        Optional<Salle> salleOptional = salleRepository.findById(salleId);
        if (!salleOptional.isPresent()) {
            throw new RuntimeException("Salle not found with id: " + salleId);
        }

        // Effectuer la machine a une salle ----
        Machine machine = machineOptional.get();
        Salle salle = salleOptional.get();
        machine.setSalle(salle);

        // Save
        return machineRepository.save(machine);
    }

}