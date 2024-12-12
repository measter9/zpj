package pl.pollub.zpj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.pollub.zpj.models.Kamper;
import pl.pollub.zpj.repository.KamperRepository;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KamperServiceTest{
    private KamperRepository kamperRepository;
    private KamperService kamperService;

    @BeforeEach
    void setUp(){
        kamperRepository = Mockito.mock(KamperRepository.class);
        kamperService = new KamperService(kamperRepository);
    }

    @Test
    @DisplayName("Save Kamper successfully")
    void testSaveKamper(){
        Kamper kamper = new Kamper();
        when(kamperRepository.save(kamper)).thenReturn(kamper);
        Kamper savedKamper = kamperService.saveKamper(kamper);
        assertNotNull(savedKamper);
        verify(kamperRepository, times(1)).save(kamper);
    }

    @Test
    @DisplayName("Throw exception when trying to fetch Kamper by non-existent ID")
    void testGetKamperByIdNotFound(){
        int nonExistentId = 999;
        when(kamperRepository.findById(nonExistentId)).thenReturn(null);
        Kamper result = kamperService.getKamperById(nonExistentId);
        assertNull(result);
        verify(kamperRepository, times(1)).findById(nonExistentId);
    }

    @Test
    @DisplayName("Return empty list when no Kampers are found")
    void testGetAllKampersEmpty(){
        when(kamperRepository.findAll()).thenReturn(Collections.emptyList());
        List<Kamper> kampers = kamperService.getAllKampers();
        assertTrue(kampers.isEmpty());
        verify(kamperRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Delete Kamper successfully")
    void testDeleteKamper(){
        int validId = 1;
        doNothing().when(kamperRepository).deleteById(validId);
        kamperService.deleteKamperById(validId);
        verify(kamperRepository, times(1)).deleteById(validId);
    }

    @Test
    @DisplayName("Handle update with invalid ID")
    void testUpdateKamperInvalidId(){
        int invalidId = 999;
        Kamper kamper = new Kamper();
        when(kamperRepository.update(invalidId, kamper)).thenReturn(0);
        int result = kamperService.updateKamper(invalidId, kamper);
        assertEquals(0, result);
        verify(kamperRepository, times(1)).update(invalidId, kamper);
    }

    @Test
    @DisplayName("Return Kamper when valid ID is provided")
    void testGetKamperByIdSuccess(){
        Kamper expectedKamper = new Kamper();
        when(kamperRepository.findById(1)).thenReturn(expectedKamper);
        Kamper result = kamperService.getKamperById(1);

        assertNotNull(result);
        assertEquals(expectedKamper, result);
        verify(kamperRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Update Kamper successfully")
    void testUpdateKamperSuccess(){
        int validId = 1;
        Kamper kamper = new Kamper();
        when(kamperRepository.update(validId, kamper)).thenReturn(1);
        int result = kamperService.updateKamper(validId, kamper);

        assertEquals(1, result);
        verify(kamperRepository, times(1)).update(validId, kamper);
    }

    @Test
    @DisplayName("Throw exception when deleting Kamper with invalid ID")
    void testDeleteKamperInvalidId(){
        int invalidId = -1;
        doThrow(new IllegalArgumentException("Invalid ID")).when(kamperRepository).deleteById(invalidId);

        assertThrows(IllegalArgumentException.class, () -> kamperService.deleteKamperById(invalidId));
        verify(kamperRepository, times(1)).deleteById(invalidId);
    }

    @Test
    @DisplayName("Return list of Kampers when multiple Kampers exist")
    void testGetAllKampers(){
        List<Kamper> kampers = Arrays.asList(new Kamper(), new Kamper());
        when(kamperRepository.findAll()).thenReturn(kampers);
        List<Kamper> result = kamperService.getAllKampers();

        assertEquals(2, result.size());
        verify(kamperRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Fetch Kamper by ID when multiple Kampers exist")
    void testGetKamperByIdWhenMultipleExist(){
        Kamper firstKamper = new Kamper();
        Kamper secondKamper = new Kamper();
        when(kamperRepository.findById(1)).thenReturn(firstKamper);
        when(kamperRepository.findById(2)).thenReturn(secondKamper);

        Kamper result = kamperService.getKamperById(1);
        assertNotNull(result);
        assertEquals(firstKamper, result);
        verify(kamperRepository, times(1)).findById(1);

        result = kamperService.getKamperById(2);
        assertNotNull(result);
        assertEquals(secondKamper, result);
        verify(kamperRepository, times(1)).findById(2);
    }
    @Test
    @DisplayName("Throw exception when saving Kamper with invalid data")
    void testSaveKamperWithInvalidData(){
        Kamper invalidKamper = new Kamper();
        when(kamperRepository.save(invalidKamper)).thenThrow(new IllegalArgumentException("Invalid Kamper data"));

        assertThrows(IllegalArgumentException.class, () -> kamperService.saveKamper(invalidKamper));
        verify(kamperRepository, times(1)).save(invalidKamper);
    }

}