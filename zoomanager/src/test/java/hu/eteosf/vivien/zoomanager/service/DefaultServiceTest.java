package hu.eteosf.vivien.zoomanager.service;

import hu.eteosf.vivien.zoomanager.model.ZooDTO;
import hu.eteosf.vivien.zoomanager.repository.AnimalRepository;
import hu.eteosf.vivien.zoomanager.repository.ZooEntity;
import hu.eteosf.vivien.zoomanager.repository.ZooRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(JUnit4.class)
public class DefaultServiceTest {

    private final ZooEntity zooEntity = Mockito.mock(ZooEntity.class);
    private final ZooRepository zooRepo = Mockito.mock(ZooRepository.class);
    private final AnimalRepository animalRepo = Mockito.mock(AnimalRepository.class);
    private final DefaultService service = new DefaultService(zooRepo, animalRepo);

    @Test
    public void createNewZooTest() {

        //given
        final Long ownId = 1L;
        final String name = "ZooName";
        final String address = "ZooAddress";
        final ZooDTO zoo = new ZooDTO(ownId, name, address);

        //when
        service.createNewZoo(zoo);

        //then
        final ArgumentCaptor<ZooEntity> captor = ArgumentCaptor.forClass(ZooEntity.class);
        verify(zooRepo, only()).save(captor.capture());

        final ZooEntity expectedValues = captor.getValue();
        assertThat("OwnId is wrong", expectedValues.getOwnid(), is(ownId));
        assertThat("name is wrong", expectedValues.getName(), is(name));
        assertThat("address is wrong", expectedValues.getAddress(), is(address));

    }

    @Test
    public void deleteOneZooTest() {
        Optional<ZooEntity> zooEntityOpt = Optional.of(zooEntity);
        when(zooRepo.findByOwnid(1L)).thenReturn(zooEntityOpt);

        //when
        service.deleteOneZoo(1L);

        //then
        verify(zooRepo, times(1)).deleteByOwnid(1L);
    }

    @Test
    public void getZoosTest(){
        //given
        when(zooRepo.count()).thenReturn(2L);

        //when
        service.getZoos();

        //then
        verify(zooRepo, times(1)).count();
    }


}
