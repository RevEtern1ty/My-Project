package eu.itcrafters.myproject.service.Workstation;

import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationCreateRequest;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationDto;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationUpdateRequest;
import eu.itcrafters.myproject.persistence.Workstation.Workstation;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationMapper;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WorkstationService {

    private final WorkstationRepository workstationRepository;
    private final WorkstationMapper workstationMapper;

  @Transactional
    public List<WorkstationDto> getAllWorkstations(){
        List<Workstation> entities = workstationRepository.findAll();
        List<WorkstationDto> result = new ArrayList<>();

        for (Workstation ws : entities){
            result.add(workstationMapper.toDto(ws));
        }
        return result;
    }

    @Transactional
    public WorkstationDto getWorkstationById (Long id){
        Workstation ws = workstationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workstation not found: " + id));

        return workstationMapper.toDto(ws);
    }

    @Transactional
    public WorkstationDto createWorkstation (WorkstationCreateRequest request){
        //Validate if code is unique

        if (workstationRepository.existsByCode(request.getCode())){
            throw new IllegalArgumentException("WS code already exists "+request.getCode());
        }

        Workstation entity = workstationMapper.toEntity(request);

        entity.setCreatedAt(Instant.now());

        Workstation saved = workstationRepository.save(entity);
        return workstationMapper.toDto(saved);
    }

    @Transactional
    public WorkstationDto updateWorkstation(Long id, WorkstationUpdateRequest request) {
        Workstation ws = workstationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workstation not found " + id));

        // Patch non-null fields
        workstationMapper.updateEntity(request, ws);

        Workstation saved = workstationRepository.save(ws);
        return workstationMapper.toDto(saved);
    }

    @Transactional
    public void deleteWorkstation(Long id) {
        if (!workstationRepository.existsById(id)) {
            throw new IllegalArgumentException("Workstation not found " + id);
        }
        workstationRepository.deleteById(id);
    }




}
