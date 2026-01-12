package eu.itcrafters.myproject;

import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationCreateRequest;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationDto;
import eu.itcrafters.myproject.controller.Workstation.dto.WorkstationUpdateRequest;
import eu.itcrafters.myproject.infrastructure.rest.exception.BadRequestException;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.Workstation.Workstation;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationMapper;
import eu.itcrafters.myproject.persistence.Workstation.WorkstationRepository;
import eu.itcrafters.myproject.service.Workstation.WorkstationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkstationServiceTest {

    @Mock private WorkstationRepository workstationRepository;
    @Mock private WorkstationMapper workstationMapper;

    @InjectMocks
    private WorkstationService workstationService;

    @Test
    void getWorkstationByIdShouldThrowWhenNotFound() {
        long id = 404L;
        when(workstationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> workstationService.getWorkstationById(id));

        verify(workstationRepository).findById(id);
        verifyNoMoreInteractions(workstationRepository, workstationMapper);
    }

    @Test
    void getAllWorkstationsShouldReturnDtos() {
        Workstation ws1 = new Workstation();
        Workstation ws2 = new Workstation();
        when(workstationRepository.findAll()).thenReturn(List.of(ws1, ws2));

        WorkstationDto dto1 = new WorkstationDto();
        WorkstationDto dto2 = new WorkstationDto();
        when(workstationMapper.toDto(ws1)).thenReturn(dto1);
        when(workstationMapper.toDto(ws2)).thenReturn(dto2);

        List<WorkstationDto> result = workstationService.getAllWorkstations();

        assertEquals(2, result.size());
        assertSame(dto1, result.get(0));
        assertSame(dto2, result.get(1));

        verify(workstationRepository).findAll();
        verify(workstationMapper).toDto(ws1);
        verify(workstationMapper).toDto(ws2);
        verifyNoMoreInteractions(workstationRepository, workstationMapper);
    }

    @Test
    void createWorkstationShouldThrowWhenCodeExists() {
        WorkstationCreateRequest request = new WorkstationCreateRequest();
        request.setCode("WS1");

        when(workstationRepository.existsByCode("WS1")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> workstationService.createWorkstation(request));

        verify(workstationRepository).existsByCode("WS1");
        verifyNoMoreInteractions(workstationRepository);
        verifyNoInteractions(workstationMapper);
    }

    @Test
    void createWorkstationShouldSaveWhenValid() {
        WorkstationCreateRequest request = new WorkstationCreateRequest();
        request.setCode("WS9");

        when(workstationRepository.existsByCode("WS9")).thenReturn(false);

        Workstation entity = new Workstation();
        when(workstationMapper.toEntity(request)).thenReturn(entity);

        Workstation saved = new Workstation();
        when(workstationRepository.save(entity)).thenReturn(saved);

        WorkstationDto dto = new WorkstationDto();
        when(workstationMapper.toDto(saved)).thenReturn(dto);

        WorkstationDto result = workstationService.createWorkstation(request);

        assertSame(dto, result);
        assertNotNull(entity.getCreatedAt());

        verify(workstationRepository).existsByCode("WS9");
        verify(workstationMapper).toEntity(request);
        verify(workstationRepository).save(entity);
        verify(workstationMapper).toDto(saved);
        verifyNoMoreInteractions(workstationRepository, workstationMapper);
    }

    @Test
    void updateWorkstationShouldThrowWhenNotFound() {
        long id = 404L;
        WorkstationUpdateRequest request = new WorkstationUpdateRequest();

        when(workstationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> workstationService.updateWorkstation(id, request));

        verify(workstationRepository).findById(id);
        verifyNoMoreInteractions(workstationRepository);
        verifyNoInteractions(workstationMapper);
    }

    @Test
    void updateWorkstationShouldPatchAndSaveWhenExists() {
        long id = 1L;
        WorkstationUpdateRequest request = new WorkstationUpdateRequest();

        Workstation ws = new Workstation();
        when(workstationRepository.findById(id)).thenReturn(Optional.of(ws));

        doNothing().when(workstationMapper).updateEntity(request, ws);

        Workstation saved = new Workstation();
        when(workstationRepository.save(ws)).thenReturn(saved);

        WorkstationDto dto = new WorkstationDto();
        when(workstationMapper.toDto(saved)).thenReturn(dto);

        WorkstationDto result = workstationService.updateWorkstation(id, request);

        assertSame(dto, result);

        verify(workstationRepository).findById(id);
        verify(workstationMapper).updateEntity(request, ws);
        verify(workstationRepository).save(ws);
        verify(workstationMapper).toDto(saved);
        verifyNoMoreInteractions(workstationRepository, workstationMapper);
    }

    @Test
    void deleteWorkstationShouldThrowWhenNotFound() {
        long id = 404L;
        when(workstationRepository.existsById(id)).thenReturn(false);

        assertThrows(DataNotFoundException.class, () -> workstationService.deleteWorkstation(id));

        verify(workstationRepository).existsById(id);
        verifyNoMoreInteractions(workstationRepository);
        verifyNoInteractions(workstationMapper);
    }

    @Test
    void deleteWorkstationShouldDeleteWhenExists() {
        long id = 1L;
        when(workstationRepository.existsById(id)).thenReturn(true);

        workstationService.deleteWorkstation(id);

        verify(workstationRepository).existsById(id);
        verify(workstationRepository).deleteById(id);
        verifyNoMoreInteractions(workstationRepository);
        verifyNoInteractions(workstationMapper);
    }
}
