package org.fireplume.transaction.threads;

import org.fireplume.transaction.repositories.RecordsRepository;
import org.fireplume.transaction.utils.ThreadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Reader implements Runnable {

    @Autowired
    private RecordsRepository recordsRepository;

    @Autowired
    private ReaderForUpdate readerForUpdate;

    @Override
//    @Transactional
    public void run() {
        List<Integer> allRecordsId = recordsRepository.findAllRecordsId();
        ThreadHelper.print(allRecordsId);
        for (Integer aInt : allRecordsId) {
            readerForUpdate.readForUpdate(aInt);
        }
    }
}
