package org.fireplume.transaction.threads;

import org.fireplume.transaction.dao.Record;
import org.fireplume.transaction.repositories.RecordsRepository;
import org.fireplume.transaction.utils.ThreadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ReaderForUpdate {

    @Autowired
    private RecordsRepository recordsRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = NestedRuntimeException.class)
    public void readForUpdate(int id) {
        try {
            Record record = recordsRepository.readForUpdate(id);
            if (record.getRead() <= 0) {
                ThreadHelper.print(record.getId());
                ThreadHelper.print(record.getRead());
                record.setRead(record.getRead() + 1);
                recordsRepository.save(record);
                ThreadHelper.sleep(3000L);
            }
        } catch (PessimisticLockingFailureException exception) {
            ThreadHelper.print("Skipping " + id);
        }
    }
}
