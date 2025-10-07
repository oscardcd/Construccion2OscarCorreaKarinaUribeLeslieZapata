
package com.mycompany.clinicmanagement.adapter.out.persistence;

import com.mycompany.clinicmanagement.adapter.out.persistence.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, String> {
    List<AppointmentEntity> findByPatientId(String patientId);
    
    @Query("SELECT COUNT(a) > 0 FROM AppointmentEntity a WHERE a.doctorId = :doctorId " +
           "AND a.status <> 'CANCELLED' " +
           "AND ((a.startTime < :endTime AND a.endTime > :startTime))")
    boolean existsByDoctorIdAndTimeRange(@Param("doctorId") String doctorId, 
                                         @Param("startTime") LocalDateTime startTime, 
                                         @Param("endTime") LocalDateTime endTime);
   
    Optional<AppointmentEntity> findByDoctorIdAndStartTime(String doctorId, LocalDateTime startTime);
}
