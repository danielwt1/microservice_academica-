package com.microservice.academia.infrastructure.drivenadapters.jparepository.service;

import com.microservice.academia.domain.model.model.ProgramaAcademico;
import com.microservice.academia.domain.model.ports.api.ProgramaAcademicoRepository;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.entity.ProgramaAcademicoEntity;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.mapper.ProgramaAcademicoMapper;
import com.microservice.academia.infrastructure.drivenadapters.jparepository.repository.ProgramaAcademicoJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ProgramaAcademicoRepositoryImpl implements ProgramaAcademicoRepository {
    private final ProgramaAcademicoJpaRepository programaAcademicoJpaRepository;
    private final ProgramaAcademicoMapper programaAcademicoMapper;

    @Override
    public ProgramaAcademico saveProgramaAcademico(ProgramaAcademico programaAcademico) {
        if (programaAcademico == null) {
            throw new IllegalArgumentException("El objeto stock no puede ser nulo");
        }
        ProgramaAcademicoEntity programaAcademicoEntity = programaAcademicoMapper.programaToProgramaEntity(programaAcademico);
        return programaAcademicoMapper.programaEntityToPrograma(programaAcademicoJpaRepository.save(programaAcademicoEntity));
    }

    @Override
    public ProgramaAcademico getProgramaById(Long id) {
        return null;
    }
}
