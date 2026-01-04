package eu.itcrafters.myproject.controller.Workstation;

import eu.itcrafters.myproject.service.Workstation.WorkstationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workstations")
public class WorkstationController {

    private final WorkstationService workstationService;


    @GetMapping
    public void  getAll() {
        workstationService.getAllWorkstations();
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable Long id) {
        workstationService.getWorkstationById(id);
    }
}
