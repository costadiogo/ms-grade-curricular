package com.cliente.escola.gradecurricular.repository;

import com.cliente.escola.gradecurricular.entity.MateriaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {

    @Query("select m from MateriaEntity m where m.hour >= :hourMin")
    public List<MateriaEntity> findByHourMinimun(@Param("hour")int hourMin);

    @Query("select f from MateriaEntity f where f.frequency =:frequency")
    public List<MateriaEntity> findByFrequency(@Param("freq") int frequency);
}
