package eu.itcrafters.myproject.controller.Workstation;

import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationCreateRequest;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationDto;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationUpdateRequest;
import eu.itcrafters.myproject.service.Workstation.WorkstationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workstations")
public class WorkstationController {

    private final WorkstationService workstationService;


    @GetMapping("/all")
    public List<WorkstationDto> getAllWorkstations() {
        return workstationService.getAllWorkstations();
    }

    @GetMapping("/{id}")
    public WorkstationDto getWorkstation(@PathVariable Long id) {
        return workstationService.getWorkstationById(id);
    }

    @PostMapping("/create")
    public WorkstationDto createWorkstation(@Valid @RequestBody WorkstationCreateRequest request) {
        return workstationService.createWorkstation(request);
    }

    @PatchMapping("/update/{id}")
    public WorkstationDto updateWorkstation(@PathVariable Long id, @RequestBody WorkstationUpdateRequest request) {
        return workstationService.updateWorkstation(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWorkstation(@PathVariable Long id) {
        workstationService.deleteWorkstation(id);
    }
}
