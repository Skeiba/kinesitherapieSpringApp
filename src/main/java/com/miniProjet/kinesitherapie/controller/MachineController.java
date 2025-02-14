package com.miniProjet.kinesitherapie.controller;


import com.miniProjet.kinesitherapie.model.entities.Machine;
import com.miniProjet.kinesitherapie.services.interfaces.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class MachineController {

    private final MachineService machineService;

    @GetMapping
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }

    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineService.getMachineById(id);
    }

    @PostMapping
    public Machine createMachine(@RequestBody Machine machine) {
        return machineService.createMachine(machine);
    }

    @PutMapping("/{id}")
    public Machine updateMachine(@PathVariable Long id, @RequestBody Machine machineDetails) {
        return machineService.updateMachine(id, machineDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
        return ResponseEntity.ok().build();
    }
}
