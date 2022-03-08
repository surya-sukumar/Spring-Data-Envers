package com.auditing.spike.fleetadmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>, RevisionRepository<User, Long, Integer> {

    @Query(value = "SELECT id, tech, lag(tech) over (order by rev) as previous_tech, name, lag(name) over (order by rev) as previous_name, rev, revtype FROM user_aud where id=:id", nativeQuery = true)
    List<AuditEntity> findRevisionsCustom(@Param("id") Long id);

    @Query(name = "user_history_query", nativeQuery = true)
    List<AuditEntity> findUserHistory(@Param("id") Long id);
}
