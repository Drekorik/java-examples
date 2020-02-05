package org.fireplume.transaction.repositories;

import org.fireplume.transaction.dao.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsRepository extends CrudRepository<Record, Integer> {

    @Query(value = "select id from public.records", nativeQuery = true)
    List<Integer> findAllRecordsId();

    @Query(value = "SELECT * FROM public.records WHERE id = ?1 FOR UPDATE NOWAIT", nativeQuery = true)
//    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Record readForUpdate(int id);

}
