package service;

import com.domain.Extrawork;
import com.persistence.ExtraworkRepository;

import java.util.ArrayList;

public class ExtraworkService {
    private final ExtraworkRepository extraworkRepository = ExtraworkRepository.getInstance();

    public ExtraworkService() {
    }

    public boolean createExtrawork(Extrawork extrawork){
        boolean result = false;
        if(checkDuplicate(extrawork)) {
            result = extraworkRepository.insertExtrawork(extrawork);
        }
        return result;
    }

    public ArrayList<Extrawork> readAllExtraworks() {
        return extraworkRepository.selectAllExtraworks();
    }

    public Extrawork readExtrawork(int id) {
        return extraworkRepository.selectExtrawork(id);
    }

    public boolean deleteExtrawork(int id) {
        boolean result = extraworkRepository.deleteExtrawork(id);
        return  result;
    }

    public boolean updateExtrawork(Extrawork extrawork){
        boolean result = false;
        if(checkDuplicate(extrawork)) {
            result = extraworkRepository.updateExtrawork(extrawork);
        }
        return result;
    }

    private boolean checkDuplicate(Extrawork extrawork) {
        boolean result = true;
        if (!extraworkRepository.checkDuplicate(extrawork)) {
            result = false;
        }
        return result;
    }
}
