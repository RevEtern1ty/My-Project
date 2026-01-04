package eu.itcrafters.myproject.service.Workstation;

import eu.itcrafters.myproject.persistence.Workstation.Workstation;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationMapper;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WorkstationService {

    private final WorkstationRepository workstationRepository;
    private final WorkstationMapper workstationMapper;

    public void getAllWorkstations(){
        workstationRepository.findAll();
    }

    public Workstation getWorkstationById (Long id){
        return workstationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workstation not found: " + id));

    }


}
