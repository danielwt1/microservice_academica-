package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.academy.AcademicProgram;
import com.microservice.academia.domain.model.model.academy.Pensum;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.PensumEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.PensumEntityMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.AssignmentJpaRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.PensumJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PensumImplTest {
    @Mock
    private PensumJpaRepository pensumJpaRepository;
    @Mock
    private AssignmentJpaRepository assignmentJpaRepository;
    @Mock
    private PensumEntityMapper pensumEntityMapper;

    @InjectMocks
    private PensumImpl pensumImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled
    void testCreatePensum() {
        // Arrange
        Pensum.PensumBuilder pensumBuilder = Pensum.builder()
                .year(2023)
                .programId(AcademicProgram.builder().id(1L).build());

        Pensum pensum = pensumBuilder.build();
        PensumEntity.PensumEntityBuilder pensumEntityBuilder = PensumEntity.builder();
        PensumEntity pensumEntity = pensumEntityBuilder.build();

        when(pensumEntityMapper.pensumToPensumEntity(pensum)).thenReturn(pensumEntity);
        when(pensumJpaRepository.save(pensumEntity)).thenReturn(pensumEntity);
        when(pensumEntityMapper.pensumEntityToPensum(pensumEntity)).thenReturn(pensum);

        // Act
        Pensum createdPensum = pensumImpl.createPensum(pensum);

        // Assert
        assertNotNull(createdPensum);
        assertEquals(pensum, createdPensum);
        verify(pensumEntityMapper).pensumToPensumEntity(pensum);
        verify(pensumJpaRepository).save(pensumEntity);
        verify(pensumEntityMapper).pensumEntityToPensum(pensumEntity);
    }
}